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

public class Measurement extends BaseEntity {
	private Long id;
	private FPerson fPerson;
	private String sourceValue; 
	private Concept sourceValueConcept; 
	private Concept measurementConcept;
	private Double valueAsNumber;
	private Concept valueAsConcept;
	private String valueSourceValue;
	private Concept operatorConcept;
	private Concept unitConcept;
	private String unitSourceValue; 
	private Double rangeLow;
	private Double rangeHigh;
	private Provider provider;
	private Date date;
	private String time;
	private VisitOccurrence visitOccurrence;
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
		if ("id".equals(columnVariable)) 
			return "measurement.measurement_id";

		if ("fPerson".equals(columnVariable)) 
			return "measurement.person_id";

		if ("sourceValue".equals(columnVariable)) 
			return "measurement.measurement_source_value";

		if ("sourceValueConcept".equals(columnVariable)) 
			return "measurement.measurement_source_concept_id";

		if ("measurementConcept".equals(columnVariable)) 
			return "measurement.measurement_concept_id";

		if ("valueAsNumber".equals(columnVariable)) 
			return "measurement.value_as_number";

		if ("valueAsConcept".equals(columnVariable)) 
			return "measurement.value_as_concept_id";

		if ("valueSourceValue".equals(columnVariable)) 
			return "measurement.value_source_value";

		if ("operatorConcept".equals(columnVariable)) 
			return "measurement.operator_concept_id";

		if ("unitConcept".equals(columnVariable)) 
			return "measurement.unit_concept_id";

		if ("unitSourceValue".equals(columnVariable)) 
			return "measurement.unit_source_value";

		if ("rangeLow".equals(columnVariable)) 
			return "measurement.range_low";

		if ("rangeHigh".equals(columnVariable)) 
			return "measurement.range_high";

		if ("provider".equals(columnVariable)) 
			return "measurement.provider_id";

		if ("date".equals(columnVariable)) 
			return "measurement.measurement_date";

		if ("time".equals(columnVariable)) 
			return "measurement.measurement_time";

		if ("visitOccurrence".equals(columnVariable)) 
			return "measurement.visit_occurrence_id";

		if ("type".equals(columnVariable)) 
			return "measurement.measurement_type_concept_id";

		return null;
	}

	@Override
	public String getTableName() {
		return Measurement._getTableName();
	}
	
	public static String _getTableName() {
		return "measurement";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Measurement._getForeignTableName(foreignVariable);
	}
	
	@Override
	public String getSqlTableStatement(List<String> parameterList, List<String> valueList) {
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
