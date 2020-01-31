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
package edu.gatech.chai.omopv5.model.entity;

import java.util.List;

public class ConceptRelationshipPK extends ConceptRelationship {
	/**
	 * 
	 */

	public ConceptRelationshipPK() {
		super();
	}

	public ConceptRelationshipPK(Long concept1, Long concept2, String relationshipId) {
		super(concept1, concept2, relationshipId);
	}

	@Override
	public String getColumnName(String columnVariable) {
		return ConceptRelationshipPK._getColumnName(columnVariable);
	}

	public static String _getColumnName(String columnVariable) {
		return ConceptRelationship._getColumnName(columnVariable);
	}

	@Override
	public String getTableName() {
		return ConceptRelationshipPK._getTableName();
	}

	public static String _getTableName() {
		return ConceptRelationship._getTableName();
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		// This table has no foreign tables
		return null;
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return ConceptRelationship._getSqlTableStatement(parameterList, valueList);
	}

}
