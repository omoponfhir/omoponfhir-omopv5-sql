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

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "concept_relationship")
public class ConceptRelationship extends BaseEntity {
	/**
	 * 
	 */

	@Column(name="concept_id_1", nullable=false)
	private Long conceptId1;
	
	@Column(name="concept_id_2", nullable=false)
    private Long conceptId2;
	
	@Column(name="relationship_id", nullable=false)
	private String relationshipId;
	
	@Column(name="valid_start_date", nullable=false)
	private Date validStartDate;
	
	@Column(name="valid_end_date", nullable=false)
	private Date validEndDate;
	
	@Column(name="invalid_reason")
	private String invalidReason;

    public ConceptRelationship() {
    }
    
    public ConceptRelationship(Long conceptId1, Long conceptId2, String relationshipId) {
    	this.conceptId1 = conceptId1;
    	this.conceptId2 = conceptId2;
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
	
	public Long getConceptId1() {
		return this.conceptId1;
	}
	
	public void setConceptId1(Long conceptId1) {
		this.conceptId1 = conceptId1;
	}

	public Long getConceptId2() {
		return this.conceptId2;
	}
	
	public void setConceptId2(Long conceptId2) {
		this.conceptId2 = conceptId2;
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
	
	@Override
  	public String getFirstColumnName() {
  		return "concept_id_1";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = ConceptRelationship.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return ConceptRelationship._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return ConceptRelationship._getTableName() + "." + joinAnnotation.name();
					}

					System.out.println("ERROR: annotation is null for field=" + field.toString());
					return null;
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;
		
//		if ("concept1".equals(columnVariable) || "id.concept1".equals(columnVariable)) 
//			return "concept_relationship.concept_id_1";
//
//		if ("concept2".equals(columnVariable) || "id.concept2".equals(columnVariable)) 
//			return "concept_relationship.concept_id_2";
//
//		if ("relationshipId".equals(columnVariable) || "id.relationshipId".equals(columnVariable)) 
//			return "concept_relationship.relationship_id";
//
//		if ("validStartDate".equals(columnVariable)) 
//			return "concept_relationship.valid_start_date";
//
//		if ("validEndDate".equals(columnVariable)) 
//			return "concept_relationship.valid_end_date";
//
//		if ("invalidReason".equals(columnVariable)) 
//			return "concept_relationship.invalid_reason";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return ConceptRelationship._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = ConceptRelationship.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "concept_relationship";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		// This table has no foreign tables
		return null;
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return ConceptRelationship._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from concept_relationship ";
	}

}
