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
import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "condition_occurrence")
public class ConditionOccurrence extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="condition_occurrence_id_seq")
	@Column(name="condition_occurrence_id", nullable=false)
	private Long id;

	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="condition_concept_id", referencedColumnName="concept_id", table="concept", nullable=false)
	private Concept conditionConcept;
	
	@Column(name="condition_start_date", nullable=false)
	private Date conditionStartDate;
	
	@Column(name="condition_start_datetime")
	private Date conditionStartDateTime;

	@Column(name="condition_end_date")
	private Date conditionEndDate;
	
	@Column(name="condition_end_datetime")
	private Date conditionEndDateTime;

	@JoinColumn(name="condition_type_concept_id", referencedColumnName="concept_id", table="concept", nullable=false)
	private Concept conditionTypeConcept;
	
	@Column(name="stop_reason")
	private String stopReason;
	
	@JoinColumn(name="provider_id", table="provider")
	private Provider provider;

	@JoinColumn(name="visit_occurrence_id", table="visit_occurrence")
	private VisitOccurrence visitOccurrence;

	@Column(name="condition_source_value")
	private String conditionSourceValue;

	@JoinColumn(name="condition_source_concept_id", referencedColumnName="concept_id", table="concept")
	private Concept conditionSourceConcept;

	@Column(name="condition_status_source_value")
	private String conditionStatusSourceValue;
	
	@JoinColumn(name="condition_status_concept_id", referencedColumnName="concept_id", table="concept")
	private Concept conditionStatusConcept;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FPerson getFPerson() {
		return fPerson;
	}

	public void setFPerson(FPerson fPerson) {
		this.fPerson = fPerson;
	}

	public Concept getConditionConcept() {
		return conditionConcept;
	}

	public void setConditionConcept(Concept conditionConcept) {
		this.conditionConcept = conditionConcept;
	}

	public Date getConditionStartDate() {
		return conditionStartDate;
	}

	public void setConditionStartDate(Date conditionStartDate) {
		this.conditionStartDate = conditionStartDate;
	}

	public Date getConditionStartDateTime() {
		return conditionStartDateTime;
	}
	
	public void setConditionStartDateTime(Date conditionStartDateTime) {
		this.conditionStartDateTime = conditionStartDateTime;
	}

	public Date getConditionEndDate() {
		return conditionEndDate;
	}

	public void setConditionEndDate(Date conditionEndDate) {
		this.conditionEndDate = conditionEndDate;
	}

	public Date getConditionEndDateTime() {
		return conditionEndDateTime;
	}
	
	public void setConditionEndDateTime(Date conditionEndDateTime) {
		this.conditionEndDateTime = conditionEndDateTime;
	}
	
	public Concept getConditionTypeConcept() {
		return conditionTypeConcept;
	}

	public void setConditionTypeConcept(Concept conditionTypeConcept) {
		this.conditionTypeConcept = conditionTypeConcept;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public VisitOccurrence getVisitOccurrence() {
		return visitOccurrence;
	}

	public void setVisitOccurrence(VisitOccurrence visitOccurrence) {
		this.visitOccurrence = visitOccurrence;
	}
	
	public String getConditionSourceValue() {
		return conditionSourceValue;
	}

	public void setConditionSourceValue(String conditionSourceValue) {
		this.conditionSourceValue = conditionSourceValue;
	}

	public Concept getConditionSourceConcept() {
		return conditionSourceConcept;
	}

	public void setConditionSourceConcept(Concept conditionSourceConcept) {
		this.conditionSourceConcept = conditionSourceConcept;
	}

	public String getConditionStatusSourceValue() {
		return conditionStatusSourceValue;
	}
	
	public void setConditionStatusSourceValue(String conditionStatusSourceValue) {
		this.conditionStatusSourceValue = conditionStatusSourceValue;
	}
	
	public Concept getStatusConcept() {
		return conditionStatusConcept;
	}
	
	public void setStatusConcept(Concept conditionStatusConcept) {
		this.conditionStatusConcept = conditionStatusConcept;
	}
	
	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return ConditionOccurrence._getColumnName(columnVariable);
	}

	@Override
  	public String getFirstColumnName() {
  		return "condition_occurrence_id";
  	}

	public static String _getColumnName(String columnVariable) {

		try {
			Field field = ConditionOccurrence.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return ConditionOccurrence._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return ConditionOccurrence._getTableName() + "." + joinAnnotation.name();
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

//		if ("id".equals(columnVariable))
//			return "condition_occurrence.condition_occurrence_id";
//
//		if ("fPerson".equals(columnVariable))
//			return "condition_occurrence.person_id";
//
//		if ("conceptId".equals(columnVariable))
//			return "condition_occurrence.condition_concept_id";
//
//		if ("startDate".equals(columnVariable))
//			return "condition_occurrence.condition_start_date";
//
//		if ("endDate".equals(columnVariable))
//			return "condition_occurrence.condition_end_date";
//
//		if ("typeConceptId".equals(columnVariable))
//			return "condition_occurrence.condition_type_concept_id";
//
//		if ("stopReason".equals(columnVariable))
//			return "condition_occurrence.stop_reason";
//
//		if ("provider".equals(columnVariable))
//			return "condition_occurrence.provider_id";
//
//		if ("visitOccurrence".equals(columnVariable))
//			return "condition_occurrence.visit_occurrence_id";
//
//		if ("conditionSourceValue".equals(columnVariable))
//			return "condition_occurrence.condition_source_value";
//
//		if ("sourceConceptId".equals(columnVariable))
//			return "condition_occurrence.condition_source_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return ConditionOccurrence._getTableName();
	}

	public static String _getTableName() {
		Table annotation = ConditionOccurrence.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "condition_occurrence";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return ConditionOccurrence._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();
		
		if ("provider".equals(foreignVariable))
			return Provider._getTableName();
		
		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();
		
		if ("conditionConcept".equals(foreignVariable) 
				|| "conditionTypeConcept".equals(foreignVariable)
				|| "conditionSourceConcept".equals(foreignVariable)
				|| "conditionStatusConcept".equals(foreignVariable))
			return Concept._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return ConditionOccurrence._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from condition_occurrence ";
	}

}
