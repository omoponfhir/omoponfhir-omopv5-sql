/*******************************************************************************
 * Copyright (c) 2019 Georgia Tech Research Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *******************************************************************************/
package edu.gatech.chai.omopv5.dba.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ohdsi.sql.SqlRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.util.DateTime;
import com.google.cloud.Date;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.jpa.dao.QueryEntityDao;
import edu.gatech.chai.omopv5.model.entity.BaseEntity;
import edu.gatech.chai.omopv5.model.entity.Vocabulary;
import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;
import jdk.internal.jline.internal.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseEntityServiceImp.
 *
 * @param <T> the generic BaseEntity type
 * @param <V> the generic BaseEntityDao type
 */
public abstract class BaseEntityServiceImp<T extends BaseEntity> implements IService<T> {
	private static final Logger logger = LoggerFactory.getLogger(BaseEntityServiceImp.class);

	@Autowired
	private QueryEntityDao queryEntityDao;

	/** The entity class. */
	private Class<T> entityClass;

	/** The entity instance */
	private T entity;

	/**
	 * Instantiates a new base entity service imp.
	 *
	 * @param entityClass the entity class
	 */
	public BaseEntityServiceImp(Class<T> entityClass) {
		this.entityClass = entityClass;
		try {
			this.entity = entityClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the entity dao.
	 *
	 * @return the entity dao
	 */
	public QueryEntityDao getQueryEntityDao() {
		return queryEntityDao;
	}

	/**
	 * Gets the entity class.
	 *
	 * @return the entity class
	 */
	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	public T getEntity() {
		return this.entity;
	}

	protected String renderedSql(String sql, List<String> parameterList, List<String> valueList) {
		String[] parameters = new String[parameterList.size()];
		parameters = parameterList.toArray(parameters);

		String[] values = new String[valueList.size()];
		values = valueList.toArray(values);

		return SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");
	}

	protected String getSqlTableName() {
		return getSqlTableName(getEntityClass());
	}

	protected String getSqlTableName(Class<T> clazz) {
		Table annotation = clazz.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		} else {
			return null;
		}
	}

//	private String getAliasedSqlColumnName(String tableInfo, String columnName, String sqlColumnName) {
//		String alias = columnName;
//		String[] tables = tableInfo.split(",");
//		for (String table : tables) {
//			String[] tableNameAlias = table.split(":");
//			if (tableNameAlias.length == 1) {
//				alias = columnName;
//			} else {
//				alias = tableNameAlias[1];
//			}
//		}
//
//		return alias + "." + sqlColumnName;
//	}

	public String getSqlTableColumnName(Field field) {
		if (field != null) {
			Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
			if (columnAnnotation != null) {
				return columnAnnotation.name();
			} else {
				JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
				if (joinColumnAnnotation != null) {
					return joinColumnAnnotation.name();
				}
				System.out.println("ERROR: annotation is null for field=" + field.toString());
				return null;
			}
		} else {
			return null;
		}
	}

	public String getSqlTableColumnName(String columnName) {
		Class<T> clazz = getEntityClass();

		return getSqlTableColumnName(clazz, columnName);
	}

	public String getSqlTableColumnName(Class<T> clazz, String columnName) {
		Class<? super T> parentClazz = clazz.getSuperclass();
		String sqlColumnName = null;
		Field field;
		try {
			field = clazz.getDeclaredField(columnName);
			sqlColumnName = getSqlTableColumnName(field);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();

			if (parentClazz != null) {
				try {
					field = parentClazz.getDeclaredField(columnName);
					sqlColumnName = getSqlTableColumnName(field);
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return sqlColumnName;
	}

	public String constructSqlSelectWithoutWhere() {
		String sqlResultList = "";

		Class<T> clazz = getEntityClass();
		Class<T> parentClazz = (Class<T>) clazz.getSuperclass();

//		Table tableAnnotation = clazz.getDeclaredAnnotation(Table.class);
//		if (tableAnnotation == null) {
//			clazz = (Class<T>) clazz.getSuperclass();
//			if (clazz == null) {
//				logger.error("Annontation for Table class is null, and there is no parent class either");
//				return null;
//			}
//			tableAnnotation = clazz.getDeclaredAnnotation(Table.class);
//			if (tableAnnotation == null) {
//				logger.error("Annotation for Table class: " + clazz.getCanonicalName() + " is null");
//			}
//		}

		String rootTableName = SqlUtil.getTableName(clazz);
		if (rootTableName == null) {
			logger.error("Failed to get SQL tablename");
			return null;
		}

		// main table that will be joined to
		String sqlFromTableList = rootTableName + " " + rootTableName;

		Field[] fields = null;
		Field[] fields_ = clazz.getDeclaredFields();
		if (parentClazz != null) {
			Field[] parentFields = parentClazz.getDeclaredFields();
			// combine with fields.
			int pFSize = parentFields.length;
			if (pFSize > 0) {
				int fSize = fields_.length;
				fields = new Field[pFSize + fSize];
				System.arraycopy(fields_, 0, fields, 0, fSize);
				System.arraycopy(parentFields, 0, fields, fSize, pFSize);
			}

		}

		if (fields == null) {
			fields = fields_;
		}

		for (Field field : fields) {
			String tableName = SqlUtil.getTableName(field.getDeclaringClass());
			String variableName = field.getName();
			Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
			JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);

			if (columnAnnotation != null) {
				if (sqlResultList != "") {
					sqlResultList = sqlResultList.concat(", ");
				}
				sqlResultList = sqlResultList.concat(
						tableName + "." + columnAnnotation.name() + " as " + tableName + "_" + columnAnnotation.name());
			}

			if (joinColumnAnnotation != null) {
				String[] tables = joinColumnAnnotation.table().split(",");
				for (String table : tables) {
					// We will need to add columns of this foreign tables to result list.
					Class<?> foreignTableClazz = field.getType();
					Class<?> foreignTableParentClazz = foreignTableClazz.getSuperclass();

					String referenceTableName;
					String referenceTableAlias;
					String[] tableInfo = table.split(":");
					if (tableInfo.length == 1) {
						if (table.equalsIgnoreCase("")) {
							// JoinColumn does not have table specified. In this case,
							// we need to get the table from field class.
							referenceTableName = SqlUtil.getTableName(foreignTableClazz);
							if (referenceTableName == null) {
								continue;
							}
						} else {
							referenceTableName = table;
						}
						referenceTableAlias = variableName;
					} else {
						referenceTableName = tableInfo[0];
						referenceTableAlias = tableInfo[1];
					}

					if (referenceTableName.equalsIgnoreCase(rootTableName)) {
						continue;
					}

					String keyTableName = SqlUtil.getTableName(field.getDeclaringClass());
					if (keyTableName == null)
						keyTableName = tableName;

					String joinColumnName;
					if (joinColumnAnnotation.referencedColumnName().equalsIgnoreCase("")) {
						joinColumnName = joinColumnAnnotation.name();
					} else {
						joinColumnName = joinColumnAnnotation.referencedColumnName();
					}

					String inner_statement = " left join " + referenceTableName + " " + referenceTableAlias + " on "
							+ keyTableName + "." + joinColumnAnnotation.name() + "=" + referenceTableAlias + "."
							+ joinColumnName;

					if (sqlFromTableList.contains(inner_statement) == false) {
						sqlFromTableList = sqlFromTableList.concat(inner_statement);
					} else {
						continue;
					}

					Table fTableAnnotation = foreignTableClazz.getDeclaredAnnotation(Table.class);
					if (fTableAnnotation == null && foreignTableParentClazz != null)
						fTableAnnotation = foreignTableParentClazz.getDeclaredAnnotation(Table.class);

					if (fTableAnnotation != null && referenceTableName.equalsIgnoreCase(fTableAnnotation.name())) {
						Field[] foreignFields = foreignTableClazz.getDeclaredFields();
						for (Field foreignField : foreignFields) {
							Column foreignFieldColumnAnnotation = foreignField.getDeclaredAnnotation(Column.class);
							if (foreignFieldColumnAnnotation != null) {
								String add2List = referenceTableAlias + "." + foreignFieldColumnAnnotation.name()
										+ " as " + referenceTableAlias + "_" + foreignFieldColumnAnnotation.name();
								if (sqlResultList.contains(add2List) == false) {
									sqlResultList = sqlResultList.concat(", " + add2List);
								}
							}

							JoinColumn foreignFieldJoinColumnAnnotation = foreignField
									.getDeclaredAnnotation(JoinColumn.class);
							if (foreignFieldJoinColumnAnnotation != null) {
								String foreignFieldJoinColumnName = foreignFieldJoinColumnAnnotation.name();
								String add2List = referenceTableAlias + "." + foreignFieldJoinColumnName + " as "
										+ referenceTableAlias + "_" + foreignFieldJoinColumnName;
								if (sqlResultList.contains(add2List) == false) {
									sqlResultList = sqlResultList.concat(", " + add2List);
								}
							}
						}
					} else {
						String add2List = tableName + "." + joinColumnName + " as " + tableName + "_" + joinColumnName
								+ ", " + referenceTableAlias + "." + joinColumnName + " as " + referenceTableAlias + "_"
								+ joinColumnName;
						if (sqlResultList.contains(add2List) == false) {
							if (sqlResultList != null && !sqlResultList.isEmpty()) {
								sqlResultList = sqlResultList.concat(", ");
							}
							sqlResultList = sqlResultList.concat(add2List);
						}
					}
				}
			}
		}

		return "select " + sqlResultList + " from " + sqlFromTableList;
	}

	public String getSqlResultTableStatement(List<String> parameterList, List<String> valueList) {
		// Get all columns from this table.
		String ret = "";
		Field[] fields = getEntityClass().getDeclaredFields();
		for (Field field : fields) {
			ret += ret != null && !ret.isEmpty() ? ", " : "";
			ret += "myTable." + getSqlTableColumnName(field);
		}

		return ret;
	}

	public Long getSize() {
		String queryString = "select count(*) as count from " + getSqlTableName() + ";";
		try {
			ResultSet rs = getQueryEntityDao().runQuery(queryString);
			if (rs.next()) {
				return (long) rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getSize(List<ParameterWrapper> paramList) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = constructSqlSelectWithoutWhere();
		try {
			String joinTablesWhere = ParameterWrapper.constructClause(getEntityClass(), paramList, parameterList,
					valueList);
			if (joinTablesWhere != null && !joinTablesWhere.isEmpty()) {
				sql = sql + joinTablesWhere;
			}

			ResultSet rs = getQueryEntityDao().runQuery(sql);
			if (rs.next()) {
				return (long) rs.getInt("count");
			}

		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | SQLException e) {
			e.printStackTrace();

			return 0L;
		}

		return 0L;
	}

//	private String getNextId(Class<T> clazz, List<String> parameterList, List<String> valueList) {
//		String primaryId = getSqlTableColumnName("id");
//
//		String sql = "coalesce(max("+primaryId+"), 0)+1";
//
//		return sql;
//	}

	private Long insertEntity(Class<T> clazz, T entity) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = "insert into @table ";
		parameterList.add("table");
		valueList.add(getSqlTableName(clazz));

		String columns = "";
		String values = "";
		Field[] fields = clazz.getDeclaredFields();
		int i = 1;
		for (Field field : fields) {
			String nextIdString = null;

			// All the fieldw are private. Set accessible true.
			field.setAccessible(true);

			// See if this field is primary key. If so, then we need to put id.
			Id idAnnotation = field.getDeclaredAnnotation(Id.class);
			if (idAnnotation != null) {
				try {
					if (field.getType() == Long.class) {
						// Vocabulary table has Id. But, it's string. So, we only care Long ID
						Long nextId = (Long) field.get(entity);
						if (nextId == null || nextId == 0L) {
							String primaryId = getSqlTableColumnName("id");
							nextIdString = "coalesce(max(" + primaryId + "), 0)+1";
//
//							nextIdString = getNextId(clazz, parameterList, valueList);
//								if (nextId != null) {
//									field.setLong(entity, getNextId());
//								} else {
//									logger.error(
//											"Error: Failed to get next Id for table = " + getEntity().getTableName());
//									return null;
//								}
							GeneratedValue sequenceGenertorAnnotation = field
									.getDeclaredAnnotation(GeneratedValue.class);
							if (sequenceGenertorAnnotation != null
									&& sequenceGenertorAnnotation.strategy() == GenerationType.SEQUENCE) {
								String sequenceTable = sequenceGenertorAnnotation.generator();
//								String sqlUpdateSequenceTable = "";
//								nextIdString = sequenceTable + ".nextval";
							}
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException | IllegalArgumentException e) {
					// If setId failed, then we have a problem.
					e.printStackTrace();
					return null;
				}
			}

			String fieldValue = null;
			Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
			JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);

			// Column name
			String columnVar = field.getName();
			String columnName = getSqlTableColumnName(clazz, columnVar);

			try {
				Object fieldObject = field.get(entity);
				if (fieldObject != null) {
					if (field.getType() == String.class) {
						fieldValue = "'" + (String) fieldObject + "'";
					} else if (field.getType() == Double.class || field.getType() == Integer.class
							|| field.getType() == Date.class || field.getType() == Short.class
							|| field.getType() == DateTime.class || field.getType() == Long.class) {
						fieldValue = fieldObject.toString();
					} else {
						Method vocabularyGetIdMethod = fieldObject.getClass().getMethod("getId");
						Object idObject = vocabularyGetIdMethod.invoke(fieldObject);
						if (idObject instanceof String) {
							fieldValue = "'" + (String) idObject + "'";
						} else {
							fieldValue = idObject.toString();
						}
					}
				} else if (nextIdString == null) {
					// if value is null and not required, we skip this.
					if (columnAnnotation != null) {
						if (columnAnnotation.nullable() == true) {
							continue;
						}
					}

					if (joinColumnAnnotation != null) {
						if (joinColumnAnnotation.nullable() == true) {
							continue;
						}
					}

					logger.error(columnName + " cannot be null");
					return null;
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e) {
				if (columnAnnotation != null) {
					if (columnAnnotation.nullable() == false) {
						e.printStackTrace();
						return null;
					}
				}

				if (joinColumnAnnotation != null) {
					if (joinColumnAnnotation.nullable() == false) {
						e.printStackTrace();
						return null;
					}
				}
				continue;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				return null;
			} catch (SecurityException e) {
				e.printStackTrace();
				return null;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}

			if (i > 1) {
				columns += ", ";
				values += ", ";
			}

			columns = columns.concat("@col" + i);
			parameterList.add("col" + i);

			valueList.add(columnName);

			// Value
			values = values.concat("@val" + i);
			parameterList.add("val" + i);

			if (nextIdString != null) {
				valueList.add(nextIdString);
			} else {
				valueList.add(fieldValue);
			}

			i++;

		}

		if (!columns.isEmpty() && !values.isEmpty() && !"".equals(columns) && !"".equals(values)) {
			sql += "(" + columns + ") select " + values + " from @table";
			sql = renderedSql(sql, parameterList, valueList);

			System.out.println(sql);
			logger.debug("SqlRender:" + sql);

			try {
				Long id = getQueryEntityDao().updateQuery(sql);
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public T create(T entity) {
		Class<T> clazz = (Class<T>) entity.getClass();
		Class<T> parentClazz = (Class<T>) clazz.getSuperclass();

		Long newId = null;
		if (parentClazz != null) {
			newId = insertEntity(parentClazz, entity);
			if (newId == null) {
				logger.error("Failed to create parent table: " + getSqlTableName(parentClazz));
				return null;
			}
			Method setMethod;
			try {
				setMethod = clazz.getMethod("setId", Long.class);
				setMethod.invoke(entity, newId);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		Long id = insertEntity(clazz, entity);
		if (newId == null) {
			Method setMethod;
			try {
				setMethod = clazz.getMethod("setId", Long.class);
				setMethod.invoke(entity, id);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return entity;
	}

	private Long idEqualTo(Class<T> clazz, T entity) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object idObject = null;
		Method setMethod;
		setMethod = clazz.getMethod("getId");
		idObject = setMethod.invoke(entity);

		if (idObject == null || ((Long) idObject) == 0L) {
			return null;
		} else {
			return (Long) idObject;
		}
	}

	private T updateEntity(Long id, Class<T> clazz, T entity) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = "update @table set ";
		String where = "@whereId = @whereIdVal";

		parameterList.add("table");
		valueList.add(getSqlTableName(clazz));

		parameterList.add("whereIdVal");
		valueList.add(Long.toString(id));

		String assignments = "";
		Field[] fields = clazz.getDeclaredFields();
		int i = 1;
		for (Field field : fields) {
			// All the fieldw are private. Set accessible true.
			field.setAccessible(true);

			// Column name
			String columnVar = field.getName();
			String columnName = getSqlTableColumnName(clazz, columnVar);

			// See if this field is primary key. If so, then we need to put id.
			Id idAnnotation = field.getDeclaredAnnotation(Id.class);
			if (idAnnotation != null) {
				parameterList.add("whereId");
				valueList.add(columnName);

				// This is ID field. We do not update ID. so skip.
				continue;
			}

			String fieldValue = null;
			Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
			JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);

			try {
				Object fieldObject = field.get(entity);
				if (fieldObject != null) {
					if (field.getType() == String.class) {
						fieldValue = "'" + (String) fieldObject + "'";
					} else if (field.getType() == Double.class || field.getType() == Integer.class
							|| field.getType() == Date.class || field.getType() == Short.class
							|| field.getType() == DateTime.class || field.getType() == Long.class) {
						fieldValue = fieldObject.toString();
					} else {
						Method vocabularyGetIdMethod = fieldObject.getClass().getMethod("getId");
						Object idObject = vocabularyGetIdMethod.invoke(fieldObject);
						if (idObject instanceof String) {
							fieldValue = "'" + (String) idObject + "'";
						} else {
							fieldValue = idObject.toString();
						}
					}
				} else {
					continue; // value is null. So we skip this.
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e) {
				if (columnAnnotation != null) {
					if (columnAnnotation.nullable() == false) {
						e.printStackTrace();
						return null;
					}
				}

				if (joinColumnAnnotation != null) {
					if (joinColumnAnnotation.nullable() == false) {
						e.printStackTrace();
						return null;
					}
				}
				continue;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				return null;
			} catch (SecurityException e) {
				e.printStackTrace();
				return null;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}

			if (i > 1) {
				assignments += ", ";
			}

			assignments = assignments.concat("@assignment" + i + "=@assignmentValue" + i);
			parameterList.add("assignment" + i);

			valueList.add(columnName);

			parameterList.add("assignmentValue" + i);
			valueList.add(fieldValue);
			
			i++;

		}

		if (!assignments.isEmpty() && !"".equals(assignments)) {
			sql += assignments + " where " + where;
			sql = renderedSql(sql, parameterList, valueList);

			System.out.println(sql);
			logger.debug("SqlRender:" + sql);

			try {
				getQueryEntityDao().updateQuery(sql);
				return entity;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public T update(T entity) {
		Class<T> clazz = (Class<T>) entity.getClass();
		Class<T> parentClazz = (Class<T>) clazz.getSuperclass();

		Long id = null;
		try {
			id = idEqualTo(clazz, entity);
			if (id == null || id == 0L) {
				logger.error("Update needs id != null for table: " + getSqlTableName(clazz));
				return null;
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		if (parentClazz != null) {
			if (updateEntity(id, parentClazz, entity) == null) {
				logger.error("Failed to update parent table: " + getSqlTableName(parentClazz));
				return null;
			}
		}

		if (updateEntity(id, clazz, entity) == null) {
			logger.error("Failed to update table: " + getSqlTableName(parentClazz));
			return null;
		}

		return entity;
	}

	public T findById(Long id) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = constructSqlSelectWithoutWhere();
		sql = sql + " where @cname=@value";
		parameterList.add("cname");
		parameterList.add("value");
		valueList.add(getSqlTableColumnName("id"));
		valueList.add(id.toString());

		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);
		logger.debug("SqlRender:" + sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			if (rs.next()) {
				T entity = construct(rs, null, getSqlTableName());
				if (entity != null) {
					return entity;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Long removeById(Long id) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = "delete from @table where @parameter=@value;";
		parameterList.add("table");
		parameterList.add("parameter");
		parameterList.add("value");

		valueList.add(getSqlTableName());
		valueList.add(getSqlTableColumnName("id"));
		valueList.add(id.toString());

		sql = renderedSql(sql, parameterList, valueList);
		try {
			Long ret = getQueryEntityDao().updateQuery(sql);
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0L;
	}

	@Override
	public List<T> searchByColumnString(String column, String value) {
		List<T> entities = new ArrayList<T>();

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = constructSqlSelectWithoutWhere();
		sql = " where @column='@value'";

		parameterList.add("column");
		parameterList.add("value");

		valueList.add(getSqlTableColumnName(column));
		valueList.add(value);

		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);
		logger.debug("SqlRender:" + sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			while (rs.next()) {
				T entity = construct(rs, null, getSqlTableName());
				if (entity != null) {
					entities.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entities;
	}

	@Override
	public List<T> searchByColumnString(String column, Long value) {
		List<T> entities = new ArrayList<T>();

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = constructSqlSelectWithoutWhere();
		sql = sql + " where @column=@value";

		parameterList.add("column");
		parameterList.add("value");

		valueList.add(getEntity().getColumnName(column));
		valueList.add(value.toString());

		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);
		logger.debug("SqlRender:" + sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			while (rs.next()) {
				T entity = construct(rs, null, getSqlTableName());
				if (entity != null) {
					entities.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entities;
	}

	public List<T> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		List<T> entities = new ArrayList<T>();
		int length = toIndex - fromIndex;

		// sort is comma separated sort parameter. ex. id asc,yearOfBirth asc
		String sortClause = getEntity().getSortClause(sort);

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		String sql = constructSqlSelectWithoutWhere() + " @sort @limit";
		parameterList.add("sort");
		parameterList.add("limit");

		if (sortClause != null && !sortClause.isEmpty()) {
			valueList.add(sortClause);
		} else {
			valueList.add("");
		}

		if (length > 0) {
			valueList.add("limit " + length + " offset " + fromIndex);
		} else {
			valueList.add("");
		}

		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);
		logger.debug("SqlRender:" + sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			while (rs.next()) {
				T entity = construct(rs, null, getSqlTableName());
				if (entity != null) {
					entities.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entities;
	}

	public List<T> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList, String sort) {
		List<T> entities = new ArrayList<T>();
		int length = toIndex - fromIndex;

		String sortClause = getEntity().getSortClause(sort);
		String sql = null;

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		try {
			String joinTablesWhere = ParameterWrapper.constructClause(getEntityClass(), paramList, parameterList,
					valueList);
			if (joinTablesWhere != null && !joinTablesWhere.isEmpty()) {
				sql = constructSqlSelectWithoutWhere() + joinTablesWhere;
			}
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();

			return null;
		}

		if (sql != null && !sql.isEmpty()) {
			sql = sql + " @sort @limit";
			parameterList.add("sort");
			parameterList.add("limit");

			if (sortClause != null && !sortClause.isEmpty()) {
				valueList.add(sortClause);
			} else {
				valueList.add("");
			}

			if (length > 0) {
				valueList.add("limit " + length + " offset " + fromIndex);
			} else {
				valueList.add("");
			}

//			String[] parameters = new String[parameterList.size()];
//			parameters = parameterList.toArray(parameters);
//
//			String[] values = new String[valueList.size()];
//			values = valueList.toArray(values);
//
//			sql = SqlRender.renderSql(sql.trim(), parameters, values).replaceAll("\\s+", " ");
			sql = renderedSql(sql, parameterList, valueList);

			System.out.println(sql);
			logger.debug("SqlRender:" + sql);

			try {
				ResultSet rs = getQueryEntityDao().runQuery(sql);

				while (rs.next()) {
					T entity = construct(rs, null, getSqlTableName());
					if (entity != null) {
						entities.add(entity);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return entities;
	}
}
