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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "f_observation_view")
public class FObservationView extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(FObservationView.class);

	@Id
	@Column(name="observation_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="observation_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept observationConcept;
	
	@Column(name="observation_date", nullable=false)
	private Date observationDate;
	
	@Column(name="observation_datetime")
	private Date observationDateTime;
	
	@JoinColumn(name="observation_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept observationTypeConcept;
	
	@JoinColumn(name="observation_operator_concept_id", referencedColumnName="concept_id")
	private Concept observationOperatorConcept;

	@Column(name="value_as_number")
	private Double valueAsNumber;
	
	@Column(name="value_as_string")
	private String valueAsString;
	
	@JoinColumn(name="value_as_concept_id", referencedColumnName="concept_id")
	private Concept valueAsConcept;
	
	@JoinColumn(name="qualifier_concept_id", referencedColumnName="concept_id")
	private Concept qualifierConcept;
	
	@JoinColumn(name="unit_concept_id", referencedColumnName="concept_id")
	private Concept unitConcept;
	
	@Column(name="range_low")
	private BigDecimal rangeLow;
	
	@Column(name="range_high")
	private BigDecimal rangeHigh;
	
	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@JoinColumn(name="visit_occurrence_id")
	private VisitOccurrence visitOccurrence;
	
	@Column(name="observation_source_value")
	private String observationSourceValue;
	
	@JoinColumn(name="observation_source_concept_id", referencedColumnName="concept_id")
	private Concept observationSourceConcept;
	
	@Column(name="unit_source_value")
	private String unitSourceValue;
	
	@Column(name="value_source_value")
	private String valueSourceValue;
	
	@Column(name="qualifier_source_value")
	private String qualifierSourceValue;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FPerson getFPerson() {
		return fPerson;
	}

	public void setFPerson(FPerson person) {
		this.fPerson = person;
	}

	public Concept getObservationConcept() {
		return observationConcept;
	}

	public void setObservationConcept(Concept observationConcept) {
		this.observationConcept = observationConcept;
	}

	public Date getObservationDate() {
		return observationDate;
	}

	public void setObservationDate(Date observationDate) {
		this.observationDate = observationDate;
	}

	public Date getObservationDateTime() {
		return observationDateTime;
	}

	public void setObservationDateTime(Date observationDateTime) {
		this.observationDateTime = observationDateTime;
	}

	public Concept getObservationTypeConcept() {
		return observationTypeConcept;
	}

	public void setObservationTypeConcept(Concept observationTypeConcept) {
		this.observationTypeConcept = observationTypeConcept;
	}

	public Concept getObservationOperatorConcept() {
		return observationOperatorConcept;
	}

	public void setObservationOperatorConcept(Concept observationOperatorConcept) {
		this.observationOperatorConcept = observationOperatorConcept;
	}

	public Double getValueAsNumber() {
		return valueAsNumber;
	}

	public void setValueAsNumber(Double valueAsNumber) {
		this.valueAsNumber = valueAsNumber;
	}

	public String getValueAsString() {
		return valueAsString;
	}

	public void setValueAsString(String valueAsString) {
		this.valueAsString = valueAsString;
	}

	public Concept getValueAsConcept() {
		return valueAsConcept;
	}

	public void setValueAsConcept(Concept valueAsConcept) {
		this.valueAsConcept = valueAsConcept;
	}

	public Concept getQualifierConcept() {
		return qualifierConcept;
	}

	public void setQualifierConcept(Concept qualifierConcept) {
		this.qualifierConcept = qualifierConcept;
	}

	public Concept getUnitConcept() {
		return unitConcept;
	}

	public void setUnitConcept(Concept unitConcept) {
		this.unitConcept = unitConcept;
	}

	public BigDecimal getRangeLow() {
		return rangeLow;
	}

	public void setRangeLow(BigDecimal rangeLow) {
		this.rangeLow = rangeLow;
	}

	public BigDecimal getRangeHigh() {
		return rangeHigh;
	}

	public void setRangeHigh(BigDecimal rangeHigh) {
		this.rangeHigh = rangeHigh;
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

	public String getObservationSourceValue() {
		return observationSourceValue;
	}

	public void setObservationSourceValue(String observationSourceValue) {
		this.observationSourceValue = observationSourceValue;
	}

	public Concept getObservationSourceConcept() {
		return observationSourceConcept;
	}

	public void setObservationSourceConcept(Concept observationSourceConcept) {
		this.observationSourceConcept = observationSourceConcept;
	}

	public String getUnitSourceValue() {
		return unitSourceValue;
	}

	public void setUnitSourceValue(String unitSourceValue) {
		this.unitSourceValue = unitSourceValue;
	}

	public String getValueSourceValue() {
		return valueSourceValue;
	}

	public void setValueSourceValue(String valueSourceValue) {
		this.valueSourceValue = valueSourceValue;
	}

	public String getQualifierSourceValue() {
		return qualifierSourceValue;
	}

	public void setQualifierSourceValue(String qualifierSourceValue) {
		this.qualifierSourceValue = qualifierSourceValue;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return FObservationView._getColumnName(columnVariable);
	}

	@Override
  	public String getFirstColumnName() {
  		return "observation_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = FObservationView.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return FObservationView._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return FObservationView._getTableName() + "." + joinAnnotation.name();
					}

					logger.error("ERROR: annotation is null for field=" + field.toString());
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
//			return "f_observation_view.observation_id";
//
//		if ("fPerson".equals(columnVariable))
//			return "f_observation_view.person_id";
//
//		if ("observationConcept".equals(columnVariable))
//			return "f_observation_view.observation_concept_id";
//
//		if ("date".equals(columnVariable))
//			return "f_observation_view.observation_date";
//
//		if ("time".equals(columnVariable))
//			return "f_observation_view.observation_time";
//
//		if ("valueAsString".equals(columnVariable))
//			return "f_observation_view.value_as_string";
//
//		if ("valueAsNumber".equals(columnVariable))
//			return "f_observation_view.value_as_number";
//
//		if ("valueAsConcept".equals(columnVariable))
//			return "f_observation_view.value_as_concept_id";
//
//		if ("typeConcept".equals(columnVariable))
//			return "f_observation_view.observation_type_concept_id";
//
//		if ("provider".equals(columnVariable))
//			return "f_observation_view.provider_id";
//
//		if ("visitOccurrence".equals(columnVariable))
//			return "f_observation_view.visit_occurrence_id";
//
//		if ("sourceValue".equals(columnVariable))
//			return "f_observation_view.source_value";
//
//		if ("sourceConcept".equals(columnVariable))
//			return "f_observation_view.source_concept_id";
//
//		if ("qualifierConcept".equals(columnVariable))
//			return "f_observation_view.qualifier_concept_id";
//
//		if ("qualifierSourceValue".equals(columnVariable))
//			return "f_observation_view.qualifier_source_value";
//
//		if ("unitConcept".equals(columnVariable))
//			return "f_observation_view.unit_concept_id";
//
//		if ("unitSourceValue".equals(columnVariable))
//			return "f_observation_view.unit_source_value";
//
//		if ("rangeLow".equals(columnVariable))
//			return "f_observation_view.range_low";
//
//		if ("rangeHigh".equals(columnVariable))
//			return "f_observation_view.range_high";
//
//		if ("valueSourceValue".equals(columnVariable))
//			return "f_observation_view.value_source_value";
//
//		if ("operatorConcept".equals(columnVariable))
//			return "f_observation_view.observation_operator_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return FObservationView._getTableName();
	}

	public static String _getTableName() {
		Table annotation = FObservationView.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "f_observation_view";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return FObservationView._getForeignTableName(foreignVariable);
	}

	public static String _getForeignTableName(String foreignVariable) {
		if ("observationConcept".equals(foreignVariable) 
				|| "observationTypeConcept".equals(foreignVariable) 
				|| "observationOperatorConcept".equals(foreignVariable)
				|| "valueAsConcept".equals(foreignVariable)
				|| "qualifierConcept".equals(foreignVariable) 
				|| "unitConcept".equals(foreignVariable)
				|| "observationSourceConcept".equals(foreignVariable))
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
		return FObservationView._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from f_observation_view ";
	}

}
