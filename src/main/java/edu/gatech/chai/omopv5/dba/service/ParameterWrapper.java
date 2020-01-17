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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.model.entity.BaseEntity;

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
		String joinTables = "";

		// Get my tablename.
//		Method method = entityClass.getDeclaredMethod("_getTableName");
//		String myTableName = (String) method.invoke(null);
//
		Method method = entityClass.getDeclaredMethod("_getColumnName", String.class);
		String myTablePrimaryId = (String) method.invoke(null, "id");

//		joinTables = "@table0" + " " + "@tableAlias0";
//		parameterList.add("table0");
//		valueList.add(myTableName);
//
//		parameterList.add("tableAlias0");
//		valueList.add("t0");
//		
		parameterList.add("tableId0");
		valueList.add(myTablePrimaryId);

		// paramList has FHIR parameters mapped Omop parameters (or columns).
		int i = 0;
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
					.iterator(), valueIter = param.getValues()
							.iterator(); (attributeIter.hasNext() || valueIter.hasNext()) && operIter.hasNext();) {

				if (attributeIter.hasNext())
					attributeName = attributeIter.next();
				if (valueIter.hasNext())
					_valueName = valueIter.next();

				if ("String".equals(param.getParameterType())) {
					valueName = "'"+_valueName+"'";
				} else if ("Code:In".equals(param.getParameterType())) {
					// change oper to in and put valueName in parenthesis
					valueName = "(" + _valueName + ")";
				} else if ("Date".equals(param.getParameterType())) {
					Long dateInMili = Long.valueOf(_valueName);
					Date value = new Date(dateInMili);
					DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
					valueName = "'" + dateFormat.format(value) + "'";
				}

				String oper = operIter.next();
				logger.debug("--- Attribute name:" + attributeName);
				logger.debug("--- value:" + valueName);
				logger.debug("--- operator:" + oper);
				String[] columnPath = attributeName.split("\\.");
				if (columnPath.length == 1) {
					Method getColumnName = entityClass.getDeclaredMethod("_getColumnName", String.class);
					String columnName = (String) getColumnName.invoke(null, attributeName);
					String subWhereToAdd = "@column" + i + " " + oper + " @value" + i;
					parameterList.add("column" + i);
					valueList.add(columnName);

					parameterList.add("value" + i);
					valueList.add(valueName);

					if (subWhere.isEmpty() || "".equals(subWhere)) {
						subWhere = subWhereToAdd;
					} else {
						subWhere = subWhere + " " + subWhereRelation + " " + subWhereToAdd;
					}
				} else if (columnPath.length == 2) {
					Method getForeignTable = entityClass.getDeclaredMethod("_getForeignTableName", String.class);
					String foreignTableName = (String) getForeignTable.invoke(null, columnPath[0]);

					Field field = entityClass.getDeclaredField(columnPath[0]);
					Class<?> foreignClass = field.getType();
					Method getForeignTableColumnName = foreignClass.getDeclaredMethod("_getColumnName", String.class);
					String foreignTablePrimaryId = (String) getForeignTableColumnName.invoke(null, "id");
					String foreignTableColumnName = foreignTablePrimaryId;

					// Add this foreignTableName into joinTables with its alias(columnPath[0])
					if (joinTables.contains(foreignTableName) == false) {
						joinTables = joinTables + " inner join @table" + i + " on " + "@tableId0" + "=@tableId" + i;

						parameterList.add("@table" + i);
						valueList.add(foreignTableName);

						parameterList.add("@tableId" + i);
						valueList.add(foreignTablePrimaryId);
					}

					if ("id".equals(columnPath[1]) == false) {
						// Add this to where.
						foreignTableColumnName = (String) getForeignTableColumnName.invoke(null, columnPath[1]);
						String subWhereToAdd = "@column" + i + " " + oper + " @value" + i;
						parameterList.add("column" + i);
						valueList.add(foreignTableColumnName);

						parameterList.add("value" + i);
						valueList.add(valueName);

						if (subWhere.isEmpty() || "".equals(subWhere)) {
							subWhere = subWhereToAdd;
						} else {
							subWhere = subWhere + " " + subWhereRelation + " " + subWhereToAdd;
						}
					}
				} else if (columnPath.length == 3) {
					Method getForeignTable = entityClass.getDeclaredMethod("_getForeignTableName", String.class);
					String foreignTableName = (String) getForeignTable.invoke(null, columnPath[0]);

					Field field = entityClass.getDeclaredField(columnPath[0]);
					Class<?> foreignClass = field.getType();
					Method getForeignTableColumnName = foreignClass.getDeclaredMethod("_getColumnName", String.class);
					String foreignTable1PrimaryId = (String) getForeignTableColumnName.invoke(null, "id");

					// Add this foreignTableName into joinTables with its alias(columnPath[0])
					if (joinTables.contains(foreignTableName) == false) {
						joinTables = joinTables + " inner join @table" + i + " on " + "@tableId0" + "=@tableId" + i;

						parameterList.add("@table" + i);
						valueList.add(foreignTableName);

						parameterList.add("@tableId" + i);
						valueList.add(foreignTable1PrimaryId);
					}

					i++;

					getForeignTable = foreignClass.getDeclaredMethod("_getForeignTableName", String.class);
					String foreignTable2Name = (String) getForeignTable.invoke(null, columnPath[1]);

					field = foreignClass.getDeclaredField(columnPath[1]);
					foreignClass = field.getType();
					getForeignTableColumnName = foreignClass.getDeclaredMethod("_getColumnName", String.class);
					String foreignTable2PrimaryId = (String) getForeignTableColumnName.invoke(null, "id");

					// Add this foreignTableName into joinTables with its alias(columnPath[1])
					if (joinTables.contains(foreignTable2Name) == false) {
						joinTables = joinTables + " inner join @table" + i + " @tableAlias" + i + " on " + "@tableId"
								+ (i - 1) + "=@tableId" + i;

						parameterList.add("@table" + i);
						valueList.add(foreignTable2Name);

						parameterList.add("@tableId" + i);
						valueList.add(foreignTable2PrimaryId);
					}

					if ("id".equals(columnPath[2]) == false) {
						// Add this to where.
						String foreignTableColumnName = (String) getForeignTableColumnName.invoke(null, columnPath[2]);
						String subWhereToAdd = "@column" + i + " " + oper + " @value" + i;
						parameterList.add("column" + i);
						valueList.add(foreignTableColumnName);

						parameterList.add("value" + i);
						valueList.add(valueName);

						if (subWhere.isEmpty() || "".equals(subWhere)) {
							subWhere = subWhereToAdd;
						} else {
							subWhere = subWhere + " " + subWhereRelation + " " + subWhereToAdd;
						}
					}

				} else {
					// We don't support more than 3 level.
					logger.warn("ParameterWrapper attribute has more then 3 level foreign keys");
				}
				
				i++;
			}
			if (!subWhere.isEmpty() && !"".equals(subWhere))
				subWhere = "(" + subWhere + ")";

			if (where != null && !where.isEmpty()) {
				if (param.getUpperRelationship() != null && param.getUpperRelationship().equalsIgnoreCase("or")) {
					where = where + " or " + subWhere;
				} else {
					where = where + " and " + subWhere;
				}
			} else {
				if (param.getUpperRelationship() != null && param.getUpperRelationship().equalsIgnoreCase("or")) {
					where = subWhere;
				} else {
					where = subWhere;
				}				
			}
		}

		if (where == null || where.isEmpty())
			return null;

		return joinTables + " where " + where;
	}

}
