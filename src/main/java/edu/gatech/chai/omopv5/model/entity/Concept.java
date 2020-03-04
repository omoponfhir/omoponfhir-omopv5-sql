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
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "concept")
public class Concept extends BaseEntity {

	@Id
	@Column(name="concept_id", nullable=false)
	private Long id;

	@Column(name="concept_name", nullable=false)
	private String name;
	
	@Column(name="domain_id", nullable=false)
	private String domain;
	
	@Column(name="concept_class_id", nullable=false)
	private String conceptClass;
	
	@Column(name="standard_concept")
	private Character standardConcept;
	
	@Column(name="vocabulary_id", nullable=false)
	private String vocabulary;

	@Column(name="concept_code", nullable=false)
	private String conceptCode;
	
	@Column(name="valid_start_date", nullable=false)
	private Date validStartDate;

	@Column(name="valid_end_date", nullable=false)
	private Date validEndDate;
	
	@Column(name="invalid_reason")
	private String invalidReason;

	public Concept() {
		super();
	}

	public Concept(Long id) {
		super();
		this.id = id;
	}

	public Concept(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Concept(Long id, String name, String domain, String conceptClass, Character standardConcept,
			String vocabulary, String conceptCode, Date validStartDate, Date validEndDate, String invalidReason) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.conceptClass = conceptClass;
		this.standardConcept = standardConcept;
		this.vocabulary = vocabulary;
		this.conceptCode = conceptCode;
		this.validStartDate = validStartDate;
		this.validEndDate = validEndDate;
		this.invalidReason = invalidReason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getConceptClass() {
		return conceptClass;
	}

	public void setConceptClass(String conceptClass) {
		this.conceptClass = conceptClass;
	}

	public Character getStandardConcept() {
		return standardConcept;
	}

	public void setStandardConcept(Character standardConcept) {
		this.standardConcept = standardConcept;
	}

	public String getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(String vocabulary) {
		this.vocabulary = vocabulary;
	}

	public String getConceptCode() {
		return conceptCode;
	}

	public void setConceptCode(String conceptCode) {
		this.conceptCode = conceptCode;
	}

	public Date getValidStartDate() {
		return validStartDate;
	}

	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}

	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	public String getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}

	@Override
	public String toString() {
		// Since this is an omop v.4 based model, all the information below is expected
		// to be not null.
		return this.getId() + ", " + this.getName() + ", " + this.getDomain() + ", " + this.getConceptClass() + ", "
				+ this.getStandardConcept() + ", " + this.getVocabulary() + ", " + this.getConceptCode() + ", "
				+ this.getValidStartDate() + ", " + this.getValidEndDate();
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Concept._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Concept.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Concept._getTableName() + "." + annotation.name();
				} else {
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

//		if ("id".equals(columnVariable)) 
//			return "concept.concept_id";
//		
//		if ("name".equals(columnVariable))
//			return "concept.concept_name";
//		
//		if ("domain".equals(columnVariable))
//			return "concept.domain_id";
//		
//		if ("conceptClass".equals(columnVariable))
//			return "concept.concept_class_id";
//		
//		if ("standard_concept".equals(columnVariable))
//			return "concept.standard_concept";
//		
//		if ("vocabulary".equals(columnVariable))
//			return "concept.vocabulary_id";
//		
//		if ("conceptCode".equals(columnVariable))
//			return "concept.concept_code";
//		
//		if ("validStartDate".equals(columnVariable))
//			return "concept.valid_start_date";
//		
//		if ("validEndDate".equals(columnVariable))
//			return "concept.valid_end_date";
//		
//		if ("invalidReason".equals(columnVariable))
//			return "concept.invalid_reason";
//		
//		return null;
	}

	@Override
	public String getTableName() {
		return Concept._getTableName();
	}

	public static String _getTableName() {
		return "concept";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		// This table has no foreign table.
		
		return null;
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Concept._getSqlTableStatement(parameterList, valueList);
	}
	
	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from concept ";
	}

}
