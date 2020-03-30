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

@Table(name = "observation")
public class Observation extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="observation_id_seq")
	@Column(name="observation_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="observation_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept observationConcept;
	
	@Column(name="observation_date", nullable=false)
	private Date date;
	
	@Column(name="observation_time")
	private String time;
	
	@Column(name="value_as_string")
	private String valueAsString;
	
	@Column(name="value_as_number")
	private Double valueAsNumber;
	
	@JoinColumn(name="value_as_concept_id", referencedColumnName="concept_id")
	private Concept valueAsConcept;
	
	@JoinColumn(name="observation_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept typeConcept;
	
	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@JoinColumn(name="visit_occurrence_id", referencedColumnName="concept_id")
	private VisitOccurrence visitOccurrence;
	
	@Column(name="observation_source_value")
	private String sourceValue;
	
	@JoinColumn(name="observation_source_concept_id", referencedColumnName="concept_id")
	private Concept sourceConcept;
	
	@JoinColumn(name="qualifier_concept_id", referencedColumnName="concept_id")
	private Concept qualifierConcept;
	
	@Column(name="qualifier_source_value")
	private String qualifierSourceValue;

	@JoinColumn(name="unit_concept_id", referencedColumnName="concept_id")
	private Concept unitConcept;
	
	@Column(name="unit_source_value")
	private String unitSourceValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public FPerson getFPerson() {
		return fPerson;
	}

	public void setFPerson(FPerson fPerson) {
		this.fPerson = fPerson;
	}

	public Concept getObservationConcept() {
		return observationConcept;
	}

	public void setObservationConcept(Concept observationConcept) {
		this.observationConcept = observationConcept;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getValueAsString() {
		return valueAsString;
	}

	public void setValueAsString(String valueAsString) {
		this.valueAsString = valueAsString;
	}

	public Double getValueAsNumber() {
		return valueAsNumber;
	}

	public void setValueAsNumber(Double valueAsNumber) {
		this.valueAsNumber = valueAsNumber;
	}

	public Concept getValueAsConcept() {
		return valueAsConcept;
	}

	public void setValueAsConcept(Concept valueAsConcept) {
		this.valueAsConcept = valueAsConcept;
	}

	public Concept getTypeConcept() {
		return typeConcept;
	}

	public void setTypeConcept(Concept typeConcept) {
		this.typeConcept = typeConcept;
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

	public Concept getQualifierConcept () {
		return qualifierConcept;
	}
	
	public void setQualifierConcept (Concept qualifierConcept) {
		this.qualifierConcept = qualifierConcept;
	}
	
	public String getQualifierSourceValue () {
		return qualifierSourceValue;
	}
	
	public void setQualifierSourceValue (String qualifierSourceValue) {
		this.qualifierSourceValue = qualifierSourceValue;
	}
	
	public String getSourceValue() {
		return sourceValue;
	}

	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}

	public Concept getSourceConcept() {
		return sourceConcept;
	}
	
	public void setSourceConcept(Concept sourceConcept) {
		this.sourceConcept = sourceConcept;
	}
	
	public Concept getUnitConcept() {
		return unitConcept;
	}

	public void setUnitConcept(Concept unitConcept) {
		this.unitConcept = unitConcept;
	}

	public String getUnitSourceValue() {
		return unitSourceValue;
	}

	public void setUnitSourceValue(String unitSourceValue) {
		this.unitSourceValue = unitSourceValue;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Observation._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Observation.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Observation._getTableName() + "." + annotation.name();
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
//			return "observation.observation_id";
//
//		if ("fPerson".equals(columnVariable)) 
//			return "observation.person_id";
//
//		if ("observationConcept".equals(columnVariable)) 
//			return "observation.observation_concept_id";
//
//		if ("date".equals(columnVariable)) 
//			return "observation.observation_date";
//
//		if ("time".equals(columnVariable)) 
//			return "observation.observation_time";
//
//		if ("valueAsString".equals(columnVariable)) 
//			return "observation.value_as_string";
//
//		if ("valueAsNumber".equals(columnVariable)) 
//			return "observation.value_as_number";
//
//		if ("valueAsConcept".equals(columnVariable)) 
//			return "observation.value_as_concept_id";
//
//		if ("typeConcept".equals(columnVariable)) 
//			return "observation.observation_type_concept_id";
//
//		if ("provider".equals(columnVariable)) 
//			return "observation.provider_id";
//
//		if ("visitOccurrence".equals(columnVariable)) 
//			return "observation.visit_occurrence_id";
//
//		if ("sourceValue".equals(columnVariable)) 
//			return "observation.observation_source_value";
//
//		if ("sourceConcept".equals(columnVariable)) 
//			return "observation.observation_source_concept_id";
//
//		if ("qualifierConcept".equals(columnVariable)) 
//			return "observation.qualifier_concept_id";
//
//		if ("qualifierSourceValue".equals(columnVariable)) 
//			return "observation.qualifier_source_value";
//
//		if ("unitConcept".equals(columnVariable)) 
//			return "observation.unit_concept_id";
//
//		if ("unitSourceValue".equals(columnVariable)) 
//			return "observation.unit_source_value";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Observation._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Observation.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "observation";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Observation._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("observationConcept".equals(foreignVariable) || "valueAsConcept".equals(foreignVariable)
				|| "typeConcept".equals(foreignVariable) || "sourceConcept".equals(foreignVariable)
				|| "qualifierConcept".equals(foreignVariable) || "unitConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Observation._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from observation ";
	}

}
