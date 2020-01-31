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

public class DeviceExposure extends BaseEntity {
	private Long id;
	private FPerson fPerson;
	private Concept deviceConcept;
	private Date deviceExposureStartDate;
	private Date deviceExposureEndDate;
	private String uniqueDeviceId;
	private Concept deviceTypeConcept;
	private Provider provider;
	private VisitOccurrence visitOccurrence;
	private Concept deviceSourceConcept;
	private String deviceSourceValue;
	private Integer quantity;
	
	public Long getId() {
		return id;
	}
	
	public void setId (Long id) {
		this.id = id;
	}

	public FPerson getFPerson () {
		return fPerson;
	}
	
	public void setFPerson (FPerson fPerson) {
		this.fPerson = fPerson;
	}
	
	public Concept getDeviceConcept () {
		return deviceConcept;
	}
	
	public void setDeviceConcept (Concept deviceConcept) {
		this.deviceConcept = deviceConcept;
	}
	
	public Date getDeviceExposureStartDate () {
		return deviceExposureStartDate;
	}
	
	public void setDeviceExposureStartDate (Date deviceExposureStartDate) {
		this.deviceExposureStartDate = deviceExposureStartDate;
	}
	
	public Date getDeviceExposureEndDate () {
		return deviceExposureEndDate;
	}
	
	public void setDeviceExposureEndDate (Date deviceExposureEndDate) {
		this.deviceExposureEndDate = deviceExposureEndDate;
	}
	
	public String getUniqueDeviceId () {
		return uniqueDeviceId;
	}
	
	public void setUniqueDeviceId (String uniqueDeviceId) {
		this.uniqueDeviceId = uniqueDeviceId;
	}
	
	public Concept getDeviceTypeConcept () {
		return deviceTypeConcept;
	}
	
	public void setDeviceTypeConcept (Concept deviceTypeConcept) {
		this.deviceTypeConcept = deviceTypeConcept;
	}
	
	public Provider getProvider () {
		return provider;
	}
	
	public void setProvider (Provider provider) {
		this.provider = provider;
	}
	
	public VisitOccurrence getVisitOccurrence () {
		return visitOccurrence;
	}
	
	public void setVisitOccurrence (VisitOccurrence visitOccurrence) {
		this.visitOccurrence = visitOccurrence;
	}
	
	public Concept getDeviceSourceConcept () {
		return this.deviceSourceConcept;
	}
	
	public void setDeviceSourceConcept (Concept deviceSourceConcept) {
		this.deviceSourceConcept = deviceSourceConcept;
	}
	
	public String getDeviceSourceValue () {
		return this.deviceSourceValue;
	}
	
	public void setDeviceSourceValue (String deviceSourceValue) {
		this.deviceSourceValue = deviceSourceValue;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return DeviceExposure._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		if ("id".equals(columnVariable)) 
			return "device_exposure.device_exposure_id";

		if ("fPerson".equals(columnVariable)) 
			return "device_exposure.person_id";

		if ("deviceConcept".equals(columnVariable)) 
			return "device_exposure.device_concept_id";

		if ("deviceExposureStartDate".equals(columnVariable)) 
			return "device_exposure.device_exposure_start_date";

		if ("deviceExposureEndDate".equals(columnVariable)) 
			return "device_exposure.device_exposure_end_date";

		if ("uniqueDeviceId".equals(columnVariable)) 
			return "device_exposure.unique_device_id";

		if ("deviceTypeConcept".equals(columnVariable)) 
			return "device_exposure.device_type_concept_id";

		if ("provider".equals(columnVariable)) 
			return "device_exposure.provider_id";

		if ("visitOccurrence".equals(columnVariable)) 
			return "device_exposure.visit_occurrence_id";

		if ("deviceSourceConcept".equals(columnVariable)) 
			return "device_exposure.device_source_concept_id";

		if ("deviceSourceValue".equals(columnVariable)) 
			return "device_exposure.device_source_value";

		if ("quantity".equals(columnVariable)) 
			return "device_exposure.quantity";

		return null;
	}

	@Override
	public String getTableName() {
		return DeviceExposure._getTableName();
	}
	
	public static String _getTableName() {
		return "device_exposure";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return DeviceExposure._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("deviceConcept".equals(foreignVariable) || "deviceTypeConcept".equals(foreignVariable)
				|| "deviceSourceConcept".equals(foreignVariable))
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
	public String getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return DeviceExposure._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from device_exposure ";
	}

}
