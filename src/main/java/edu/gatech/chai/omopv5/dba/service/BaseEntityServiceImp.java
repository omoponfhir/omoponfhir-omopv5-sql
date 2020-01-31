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

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ohdsi.sql.SqlRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.gatech.chai.omopv5.jpa.dao.QueryEntityDao;
import edu.gatech.chai.omopv5.model.entity.BaseEntity;

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

	private String renderedSql(String sql, List<String> parameterList, List<String> valueList) {
		String[] parameters = new String[parameterList.size()];
		parameters = parameterList.toArray(parameters);

		String[] values = new String[valueList.size()];
		values = valueList.toArray(values);

		return SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");
	}

	public Long getSize() {
		String queryString = "select count(*) as count from " + getEntity().getTableName() + ";";
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

		String queryString = getEntity().getSqlTableStatement(parameterList, valueList);
		try {
			String joinTablesWhere = ParameterWrapper.constructClause(getEntityClass(), paramList, parameterList,
					valueList);
			if (joinTablesWhere != null && !joinTablesWhere.isEmpty()) {
				queryString = queryString + joinTablesWhere;
			}

			ResultSet rs = getQueryEntityDao().runQuery(queryString);
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

	public T findById(Long id) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = getEntity().getSqlTableStatement(parameterList, valueList);
		sql = sql + " where @cname=@value";
		parameterList.add("cname");
		parameterList.add("value");
		valueList.add(getEntity().getColumnName("id"));
		valueList.add(id.toString());

		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);
		logger.debug("SqlRender:" + sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			if (rs.next()) {
				T entity = construct(rs);
				if (entity != null) {
					return entity;
				}
			}
		} catch (SQLException e) {
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

		valueList.add(getEntity().getTableName());
		valueList.add(getEntity().getColumnName("id"));
		valueList.add(id.toString());

		sql = renderedSql(sql, parameterList, valueList);
		try {
			int ret = getQueryEntityDao().updateQuery(sql);
			return Long.valueOf(ret);
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

		String queryString = getEntity().getSqlTableStatement(parameterList, valueList);
		queryString = queryString + " where @column='@value'";

		parameterList.add("column");
		parameterList.add("value");

		valueList.add(getEntity().getColumnName(column));
		valueList.add(value);

		queryString = renderedSql(queryString, parameterList, valueList);

		System.out.println(queryString);
		logger.debug("SqlRender:" + queryString);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(queryString);

			while (rs.next()) {
				T entity = construct(rs);
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

		String queryString = getEntity().getSqlTableStatement(parameterList, valueList);
		queryString = queryString + " where @column=@value";

		parameterList.add("column");
		parameterList.add("value");

		valueList.add(getEntity().getColumnName(column));
		valueList.add(value.toString());

		queryString = renderedSql(queryString, parameterList, valueList);

		System.out.println(queryString);
		logger.debug("SqlRender:" + queryString);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(queryString);

			while (rs.next()) {
				T entity = construct(rs);
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
		String sql = getEntity().getSqlTableStatement(parameterList, valueList) + " @sort @limit";
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

//		String[] parameters = new String[parameterList.size()];
//		parameters = parameterList.toArray(parameters);
//
//		String[] values = new String[valueList.size()];
//		values = valueList.toArray(values);
//
//		sql = SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");
//		
		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);
		logger.debug("SqlRender:" + sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			while (rs.next()) {
				T entity = construct(rs);
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
				sql = getEntity().getSqlTableStatement(parameterList, valueList);
				sql = sql + joinTablesWhere;
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
					T entity = construct(rs);
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
