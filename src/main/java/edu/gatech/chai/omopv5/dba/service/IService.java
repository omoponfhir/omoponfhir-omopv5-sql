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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface IService.
 *
 * @param <v> the generic type
 */
public interface IService<v extends BaseEntity> {
	
	/** The version. */
	static String version = "5.x";
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the v
	 */
	public v findById (Long id) throws Exception;
	
	/**
	 * Removes the by id.
	 *
	 * @param id the id
	 * @return the long
	 */
	public Long removeById (Long id) throws Exception;
	
	/**
	 * Search by column string.
	 *
	 * @param column the column
	 * @param value the value
	 * @return the list
	 */
	public List<v> searchByColumnString (String column, String value) throws Exception;
	
	/**
	 * Search by column string.
	 *
	 * @param column the column
	 * @param value the value
	 * @return the list
	 */
	public List<v> searchByColumnStringIgnoreCase (String column, String value) throws Exception;

	/**
	 * Search by column string.
	 *
	 * @param column the column
	 * @param value the value
	 * @return the list
	 */
	public List<v> searchByColumnString (String column, Long value) throws Exception;
	
	public List<v> searchBySql (int fromIndex, int toIndex, String sql, List<String> parameterList, List<String> valueList, String sort) throws Exception;

	/**
	 * Search with params.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @param paramList the param list
	 * @param sort the sort
	 * @return the list
	 */
	public List<v> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList, String sort) throws Exception;
	
	/**
	 * Search without params.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @param sort the sort
	 * @return the list
	 */
	public List<v> searchWithoutParams(int fromIndex, int toIndex, String sort) throws Exception;
	
	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 * @return the v
	 */
	v create(v entity) throws Exception;
	
	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return the v
	 */
	v update(v entity) throws Exception;
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	Long getSize() throws Exception;
	// Long getSize(boolean cacheOnly);

	/**
	 * Gets the size.
	 *
	 * @param paramList the param list
	 * @return the size
	 */
	Long getSize(List<ParameterWrapper> paramList) throws Exception;
	// Long getSize(List<ParameterWrapper> paramList, boolean cacheOnly);
	
	Long getSize(String sqlString, List<String> parameterList, List<String> valueList) throws Exception;
	// Long getSize(String sqlString, List<String> parameterList, List<String> valueList, boolean cacheOnly);

	//	Long getNextId();
	
	v construct (ResultSet rs, v entity, String alias) throws SQLException;

	v construct (FieldValueList rowResult, v entity, String alias, List<String> columns);

}
