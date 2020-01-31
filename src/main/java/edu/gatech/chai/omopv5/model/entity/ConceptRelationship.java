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

import java.util.Date;
import java.util.List;

public class ConceptRelationship extends BaseEntity {
	/**
	 * 
	 */

    private Long concept1;
    private Long concept2;
	private String relationshipId;
	private Date validStartDate;
	private Date validEndDate;
	private String invalidReason;

    public ConceptRelationship() {
    }
    
    public ConceptRelationship(Long concept1, Long concept2, String relationshipId) {
    	this.concept1 = concept1;
    	this.concept2 = concept2;
    	this.relationshipId = relationshipId;
    }

	@Override
	public Long getIdAsLong() {
		return null;
	}
	
	// To support JPA's non primary key. We need to add this.
	public ConceptRelationship getId() {
		return this;
	}
	
	public void setId(ConceptRelationshipPK conceptRelationshipPK) {
		// this is for JPA implementation where it must have primary key. 
		// We don't. So, just silently ignore...
	}
	
	public Long getConcept1() {
		return this.concept1;
	}
	
	public void setConcept1(Long concept1) {
		this.concept1 = concept1;
	}

	public Long getConcept2() {
		return this.concept2;
	}
	
	public void setConcept2(Long concept2) {
		this.concept2 = concept2;
	}
	
	public String getRelationshipId() {
		return this.relationshipId;
	}
	
	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}
	
	public Date getValidStartDate() {
		return this.validStartDate;
	}
	
	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}
	
	public Date getValidEndDate() {
		return this.validEndDate;
	}
	
	public void setValidEndDate(Date validEndDate) {
		this.validEndDate =validEndDate;
	}
	
	public String getInvalidReason() {
		return this.invalidReason;
	}
	
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return ConceptRelationship._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		if ("concept1".equals(columnVariable) || "id.concept1".equals(columnVariable)) 
			return "concept_relationship.concept_id_1";

		if ("concept2".equals(columnVariable) || "id.concept2".equals(columnVariable)) 
			return "concept_relationship.concept_id_2";

		if ("relationshipId".equals(columnVariable) || "id.relationshipId".equals(columnVariable)) 
			return "concept_relationship.relationship_id";

		if ("validStartDate".equals(columnVariable)) 
			return "concept_relationship.valid_start_date";

		if ("validEndDate".equals(columnVariable)) 
			return "concept_relationship.valid_end_date";

		if ("invalidReason".equals(columnVariable)) 
			return "concept_relationship.invalid_reason";

		return null;
	}

	@Override
	public String getTableName() {
		return ConceptRelationship._getTableName();
	}
	
	public static String _getTableName() {
		return "concept_relationship";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		// This table has no foreign tables
		return null;
	}
	
	@Override
	public String getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return ConceptRelationship._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from concept_relationship ";
	}

}
