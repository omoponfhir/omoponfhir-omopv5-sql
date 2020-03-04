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
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "measurement")
public class Measurement extends BaseEntity {
	@Id
	@Column(name="measurement_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@Column(name="measurement_source_value")
	private String sourceValue;
	
	@JoinColumn(name="measurement_source_concept_id", referencedColumnName="concept_id")
	private Concept sourceValueConcept;
	
	@JoinColumn(name="measurement_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept measurementConcept;
	
	@Column(name="value_as_number")
	private Double valueAsNumber;
	
	@JoinColumn(name="value_as_concept_id", referencedColumnName="concept_id")
	private Concept valueAsConcept;
	
	@Column(name="value_source_value")
	private String valueSourceValue;
	
	@JoinColumn(name="operator_concept_id", referencedColumnName="concept_id")
	private Concept operatorConcept;
	
	@JoinColumn(name="unit_concept_id", referencedColumnName="concept_id")
	private Concept unitConcept;
	
	@Column(name="unit_source_value")
	private String unitSourceValue;
	
	@Column(name="range_low")
	private Double rangeLow;
	
	@Column(name="range_high")
	private Double rangeHigh;
	
	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@Column(name="measurement_date", nullable=false)
	private Date date;
	
	@Column(name="measurement_time")
	private String time;
	
	@JoinColumn(name="visit_occurrence_id")
	private VisitOccurrence visitOccurrence;
	
	@JoinColumn(name="measurement_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept type;

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
	
	public String getSourceValue() {
		return sourceValue;
	}
	
	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}

	public Concept getSourceValueConcept() {
		return sourceValueConcept;
	}
	
	public void setSourceValueConcept(Concept sourceValueConcept) {
		this.sourceValueConcept = sourceValueConcept;
	}

	public Concept getOperatorConcept() {
		return operatorConcept;
	}
	
	public void setOperationConcept(Concept operatorConcept) {
		this.operatorConcept = operatorConcept;
	}

	public Concept getMeasurementConcept() {
		return measurementConcept;
	}
	
	public void setMeasurementConcept(Concept measurementConcept) {
		this.measurementConcept = measurementConcept;
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
	
	public String getValueSourceValue() {
		return valueSourceValue;
	}
	
	public void setValueSourceValue(String valueSourceValue) {
		this.valueSourceValue = valueSourceValue;
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

	public Double getRangeLow() {
		return rangeLow;
	}
	
	public void setRangeLow(Double rangeLow) {
		this.rangeLow =rangeLow;
	}
	
	public Double getRangeHigh() {
		return rangeHigh;
	}
	
	public void setRangeHigh(Double rangeHigh) {
		this.rangeHigh = rangeHigh;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public VisitOccurrence getVisitOccurrence() {
		return visitOccurrence;
	}
	
	public void setVisitOccurrence (VisitOccurrence visitOccurrence) {
		this.visitOccurrence = visitOccurrence;
	}
	
	public Concept getType() {
		return type;
	}
	
	public void setType(Concept type) {
		this.type = type;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Measurement._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Measurement.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Measurement._getTableName() + "." + annotation.name();
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
//			return "measurement.measurement_id";
//
//		if ("fPerson".equals(columnVariable)) 
//			return "measurement.person_id";
//
//		if ("sourceValue".equals(columnVariable)) 
//			return "measurement.measurement_source_value";
//
//		if ("sourceValueConcept".equals(columnVariable)) 
//			return "measurement.measurement_source_concept_id";
//
//		if ("measurementConcept".equals(columnVariable)) 
//			return "measurement.measurement_concept_id";
//
//		if ("valueAsNumber".equals(columnVariable)) 
//			return "measurement.value_as_number";
//
//		if ("valueAsConcept".equals(columnVariable)) 
//			return "measurement.value_as_concept_id";
//
//		if ("valueSourceValue".equals(columnVariable)) 
//			return "measurement.value_source_value";
//
//		if ("operatorConcept".equals(columnVariable)) 
//			return "measurement.operator_concept_id";
//
//		if ("unitConcept".equals(columnVariable)) 
//			return "measurement.unit_concept_id";
//
//		if ("unitSourceValue".equals(columnVariable)) 
//			return "measurement.unit_source_value";
//
//		if ("rangeLow".equals(columnVariable)) 
//			return "measurement.range_low";
//
//		if ("rangeHigh".equals(columnVariable)) 
//			return "measurement.range_high";
//
//		if ("provider".equals(columnVariable)) 
//			return "measurement.provider_id";
//
//		if ("date".equals(columnVariable)) 
//			return "measurement.measurement_date";
//
//		if ("time".equals(columnVariable)) 
//			return "measurement.measurement_time";
//
//		if ("visitOccurrence".equals(columnVariable)) 
//			return "measurement.visit_occurrence_id";
//
//		if ("type".equals(columnVariable)) 
//			return "measurement.measurement_type_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Measurement._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Measurement.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "measurement";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Measurement._getForeignTableName(foreignVariable);
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Measurement._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getForeignTableName(String foreignVariable) {
		if ("sourceValueConcept".equals(foreignVariable) || "measurementConcept".equals(foreignVariable)
				|| "valueAsConcept".equals(foreignVariable) || "operatorConcept".equals(foreignVariable)
				|| "unitConcept".equals(foreignVariable) || "type".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();

		return null;
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from measurement ";
	}

}
