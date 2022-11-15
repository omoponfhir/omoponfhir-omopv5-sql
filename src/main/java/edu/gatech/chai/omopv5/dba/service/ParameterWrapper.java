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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.model.entity.BaseEntity;
import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

// TODO: Auto-generated Javadoc
/**
 * ParameterWrapper for database operations.
 * 
 * paramterType stores variable type such as String, Short, etc.
 * constructPredicate() method should convert this to appropriate type for the
 * database.
 * 
 * parameters store column name(s).
 * 
 * operators store SQL comparisons
 * 
 * values store value(s) to be compared.
 * 
 * relationship store either "or" or "and" - relationship between parameters and
 * values default is "or"
 * 
 * Parameters and values both cannot be multiple at the same time. Either one
 * should be single.
 * 
 * eg) parameters: "givenName1", "givenName2" values: "TOM" operator: "like"
 * relationship: "or"
 * 
 * This should be read as, Column_givenName1 like "TOM" or Column_givenName2
 * like "TOM"
 * 
 * The operators should match with largest number of parameters or values. It
 * means, if there are 3 parameters (there should be only one value), then 3
 * operators are needed for each parameter. If there are 3 values (there should
 * be only one parameter), then 3 operators are needed for each value.
 * 
 * The order should be parameter(left)-operator-value(right). So, put the
 * operator in this order.
 * 
 * @author mc142
 *
 */
public class ParameterWrapper {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ParameterWrapper.class);

	/** The parameter type. */
	private String parameterType;

	/** The parameters. */
	private List<String> parameters;

	/** The operators. */
	private List<String> operators;

	/** The values. */
	private List<String> values;

	/** The relationship. */
	private String relationship;

	/** The upper relationship. */
	private String upperRelationship;

	/**
	 * Instantiates a new parameter wrapper.
	 */
	public ParameterWrapper() {
	}

	/**
	 * Instantiates a new parameter wrapper.
	 *
	 * @param parameterType the parameter type
	 * @param parameters    the parameters
	 * @param operators     the operators
	 * @param values        the values
	 * @param relationship  the relationship
	 */
	public ParameterWrapper(String parameterType, List<String> parameters, List<String> operators, List<String> values,
			String relationship) {
		this.parameterType = parameterType;
		this.parameters = parameters;
		this.operators = operators;
		this.values = values;
		this.relationship = relationship;
		this.upperRelationship = null; // this is used only at the special case.
	}

	/**
	 * Gets the parameter type.
	 *
	 * @return the parameter type
	 */
	public String getParameterType() {
		return parameterType;
	}

	/**
	 * Sets the parameter type.
	 *
	 * @param parameterType the new parameter type
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<String> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the new parameters
	 */
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Gets the operators.
	 *
	 * @return the operators
	 */
	public List<String> getOperators() {
		return operators;
	}

	/**
	 * Sets the operators.
	 *
	 * @param operators the new operators
	 */
	public void setOperators(List<String> operators) {
		this.operators = operators;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values the new values
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	/**
	 * Gets the relationship.
	 *
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * Sets the relationship.
	 *
	 * @param relationship the new relationship
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/**
	 * Gets the upper relationship.
	 *
	 * @return the upper relationship
	 */
	public String getUpperRelationship() {
		return upperRelationship;
	}

	/**
	 * Sets the upper relationship.
	 *
	 * @param upperRelationship the new upper relationship
	 */
	public void setUpperRelationship(String upperRelationship) {
		this.upperRelationship = upperRelationship;
	}

	public void addParameter(String parameter) {
		this.parameters.add(parameter);
	}

	public void addOperator(String operator) {
		this.operators.add(operator);
	}

	public void addValue(String value) {
		this.values.add(value);
	}

	/**
	 * Construct predicate.
	 *
	 * @param builder   the builder
	 * @param paramList the param list
	 * @param rootUser  the root user
	 * @return the list
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String constructClause(Class<? extends BaseEntity> entityClass, List<ParameterWrapper> paramList,
			List<String> parameterList, List<String> valueList) throws NoSuchFieldException, SecurityException,
			NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String subWhereRelation = "";
		String where = "";
		Table tableAnnotation = entityClass.getDeclaredAnnotation(Table.class);
		if (tableAnnotation == null) {
			Class<?> parentEntityClass = entityClass.getSuperclass();
			if (parentEntityClass != null) {
				tableAnnotation = parentEntityClass.getDeclaredAnnotation(Table.class);
				if (tableAnnotation == null) {
					logger.error("Table annotation is not available for " + entityClass.getCanonicalName());
					return null;
				}
			}
		}

		// String tableName = tableAnnotation.name();

//		Method method = entityClass.getDeclaredMethod("_getColumnName", String.class);
//		String myTablePrimaryId = (String) method.invoke(null, "id");

//		parameterList.add("tableId0");
//		valueList.add(myTablePrimaryId);
//
		// paramList has FHIR parameters mapped Omop parameters (or columns).
		int i = 0;
		String upperRelationshipOrClauses = "";
		for (ParameterWrapper param : paramList) {
			String subWhere = "";
			i++;

			if (param.getRelationship() == null || param.getRelationship().equalsIgnoreCase("or")) {
				subWhereRelation = "or";
			} else {
				subWhereRelation = "and";
			}

			String attributeName = null;
			String valueName = null;
			String _valueName = null;
			for (Iterator<String> attributeIter = param.getParameters().iterator(), operIter = param.getOperators()
					.iterator(), valueIter = param.getValues().iterator(); (attributeIter.hasNext() 
					|| valueIter.hasNext()) && operIter.hasNext();) {
						
				String tableName = tableAnnotation.name();

				if (attributeIter.hasNext())
					attributeName = attributeIter.next();
				if (valueIter.hasNext())
					_valueName = valueIter.next();

				if ("String".equals(param.getParameterType())) {
					String valueEscaped = StringEscapeUtils.escapeSql(_valueName);
					valueName = "'" + valueEscaped + "'";
				} else if ("Code:In".equals(param.getParameterType())) {
					// change oper to in and put valueName in parenthesis
					valueName = "(" + _valueName + ")";
				} else if ("Date".equals(param.getParameterType())) {
					Long dateInMili = Long.valueOf(_valueName);
					Date value = new Date(dateInMili);
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					valueName = "cast ('" + dateFormat.format(value) + "' as date)";
				} else {
					valueName = _valueName;
				}

				String oper = operIter.next();
				logger.debug("--- Attribute name:" + attributeName);
				logger.debug("--- value:" + valueName);
				logger.debug("--- operator:" + oper);

				Field field; 

				// Column path can be set with "." for foreign tables.
				String[] columnPath = attributeName.split("\\.");
				if (columnPath.length == 1) {
					// If columnPath is not set, then this is local table column.
					try {
						field = entityClass.getDeclaredField(attributeName);
					} catch (NoSuchFieldException e) {
						Class<?> parentClass = entityClass.getSuperclass();
						if (parentClass != null) {
							// this is local column but this must be 1-to-1 mapped class
							// set the parent table name.
							Table newTableAnnotation = parentClass.getDeclaredAnnotation(Table.class);
							if (newTableAnnotation == null) {
								logger.error("Table annotation is not available for " + parentClass.getCanonicalName());
								return null;
							}
							tableName = newTableAnnotation.name();
							field = parentClass.getDeclaredField(attributeName);
						} else {
							throw new NoSuchFieldException(e.getMessage());
						}
					}
					
					// the attribute name needs to be changed to alias.sqlColumnName format.
					Column columnAnnotation = field.getDeclaredAnnotation(Column.class);

					String sqlColumnName;
					if (columnAnnotation != null) {
						sqlColumnName = tableName + "." + columnAnnotation.name();
					} else {
						JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
						if (joinColumnAnnotation != null) {
							sqlColumnName = tableName + "." + joinColumnAnnotation.name();
						} else {
							logger.error("Column annotation is mission for this attribute, " + attributeName);
							return null;
						}
					}

//					Method getColumnName = entityClass.getDeclaredMethod("_getColumnName", String.class);
//					String columnName = (String) getColumnName.invoke(null, attributeName);

					String subWhereToAdd = "@column" + i + " " + oper + " @value" + i;
					parameterList.add("column" + i);
					valueList.add(sqlColumnName);
					parameterList.add("value" + i);
					valueList.add(valueName);

					if (subWhere.isEmpty() || "".equals(subWhere)) {
						subWhere = subWhereToAdd;
					} else {
						subWhere = subWhere + " " + subWhereRelation + " " + subWhereToAdd;
					}
				} else if (columnPath.length == 2) {
					// columnPath exists. This means that the column is in a foreign table.
					// We need to find a proper alias.
					//
					// columnPath[0]: variableName in this table for the foreign table.
					// columnPath[1]: column variable in the foreign table class column variable.
					//
					// The annotation for this field must be JoinColumn.

					field = entityClass.getDeclaredField(columnPath[0]);

					JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinColumnAnnotation == null) {
						logger.error("JoinColumn is missing for this attribute, " + attributeName);

						return null;
					}

					// Now, check if the foreign table column exists and get real sql column name.
//					Class<?> fTableClazz = field.getDeclaringClass();
					Class<?> fTableClazz = field.getType();
					String sqlColumnName = SqlUtil.getSqlColumnName(fTableClazz, columnPath[1]);
					if (sqlColumnName == null) {
						logger.error("Failed to get sql column name for table=" + fTableClazz.getCanonicalName()
								+ " with attributeName=" + attributeName);

						return null;
					}

					// Now, we have SQL Column name. We need to use alias so we use right joined
					// table.
					// The default alias for JoinColumn is its variable name. But, we could have
					// one-to-one mapping (f_person). In this case, we may have different alias,
					// which is
					// specified in JoinColumn annotation at table().
					//
					// fTableClazz should have a right class (updated with a class used in
					// SqlUtil.getSqlColumnName.
					String alias = columnPath[0];
					String aliasInfo = joinColumnAnnotation.table();
					if (aliasInfo != null && !aliasInfo.equals("")) {
						Table referredTableAnnotation = fTableClazz.getDeclaredAnnotation(Table.class);
						if (referredTableAnnotation != null) {
							String referredTableName = referredTableAnnotation.name();
							String[] aliasTables = aliasInfo.split(",");
							for (String aliasTable : aliasTables) {
								String[] tables = aliasTable.split(":");
								if (tables[0].equals(referredTableName) && tables.length == 2) {
									alias = tables[1];
									break;
								}
							}
						}
					}

					sqlColumnName = alias + "." + sqlColumnName;

					String subWhereToAdd = "@column" + i + " " + oper + " @value" + i;
					parameterList.add("column" + i);
					valueList.add(sqlColumnName);
					parameterList.add("value" + i);
					valueList.add(valueName);

					if (subWhere.isEmpty() || "".equals(subWhere)) {
						subWhere = subWhereToAdd;
					} else {
						subWhere = subWhere + " " + subWhereRelation + " " + subWhereToAdd;
					}
				} else {
					// We don't support more than 2 level.
					logger.warn("ParameterWrapper attribute has more then 2 level foreign keys");
				}

				i++;
			}
			if (!subWhere.isEmpty() && !"".equals(subWhere))
				subWhere = "(" + subWhere + ")";

			if (where != null && !where.isEmpty()) {
				if (param.getUpperRelationship() != null && param.getUpperRelationship().equalsIgnoreCase("or")) {
					if (upperRelationshipOrClauses.isEmpty()) {
						upperRelationshipOrClauses = "(" + subWhere;
					} else {
						upperRelationshipOrClauses += " or " + subWhere;
					}
				} else {
					if (!upperRelationshipOrClauses.isEmpty()) {
						where = where + " and " + upperRelationshipOrClauses + ")";
						upperRelationshipOrClauses = "";
					}
					where = where + " and " + subWhere;
				}
			} else {
				if (param.getUpperRelationship() != null && param.getUpperRelationship().equalsIgnoreCase("or")) {
					if (upperRelationshipOrClauses.isEmpty()) {
						upperRelationshipOrClauses = "(" + subWhere;
					} else {
						upperRelationshipOrClauses += " or " + subWhere;
					}
				} else {
					where = subWhere;
				}
			}
		}

		if (!upperRelationshipOrClauses.isEmpty()) {
			if (where.isEmpty()) {
				where = upperRelationshipOrClauses + ")";
			} else {
				where = where + " and " + upperRelationshipOrClauses + ")";
			}
		}

		if (where == null || where.isEmpty()) {
			return "";
		} else {
			return " where " + where;
		}
	}

}
