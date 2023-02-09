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

@Table(name = "device_exposure")
public class DeviceExposure extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="device_exposure_id_seq")
	@Column(name="device_exposure_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="device_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept deviceConcept;
	
	@Column(name="device_exposure_start_date", nullable=false)
	private Date deviceExposureStartDate;
	
	@Column(name="device_exposure_start_datetime")
	private Date deviceExposureStartDateTime;

	@Column(name="device_exposure_end_date")
	private Date deviceExposureEndDate;
	
	@Column(name="device_exposure_end_datetime")
	private Date deviceExposureEndDateTime;
	
	@JoinColumn(name="device_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept deviceTypeConcept;
	
	@Column(name="unique_device_id")
	private String uniqueDeviceId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@JoinColumn(name="visit_occurrence_id")
	private VisitOccurrence visitOccurrence;
	
	@Column(name="device_source_value")
	private String deviceSourceValue;
	
	@JoinColumn(name="device_source_concept_id", referencedColumnName="concept_id")
	private Concept deviceSourceConcept;
	
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
	
	public Date getDeviceExposureStartDateTime () {
		return deviceExposureStartDateTime;
	}
	
	public void setDeviceExposureStartDateTime (Date deviceExposureStartDateTime) {
		this.deviceExposureStartDateTime = deviceExposureStartDateTime;
	}
	
	public Date getDeviceExposureEndDate () {
		return deviceExposureEndDate;
	}
	
	public void setDeviceExposureEndDate (Date deviceExposureEndDate) {
		this.deviceExposureEndDate = deviceExposureEndDate;
	}
	
	public Date getDeviceExposureEndDateTime () {
		return deviceExposureEndDateTime;
	}
	
	public void setDeviceExposureEndDateTime (Date deviceExposureEndDateTime) {
		this.deviceExposureEndDateTime = deviceExposureEndDateTime;
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
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	
	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return DeviceExposure._getColumnName(columnVariable);
	}
	
	@Override
  	public String getFirstColumnName() {
  		return "device_exposure_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = DeviceExposure.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return DeviceExposure._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return DeviceExposure._getTableName() + "." + joinAnnotation.name();
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
//			return "device_exposure.device_exposure_id";
//
//		if ("fPerson".equals(columnVariable)) 
//			return "device_exposure.person_id";
//
//		if ("deviceConcept".equals(columnVariable)) 
//			return "device_exposure.device_concept_id";
//
//		if ("deviceExposureStartDate".equals(columnVariable)) 
//			return "device_exposure.device_exposure_start_date";
//
//		if ("deviceExposureEndDate".equals(columnVariable)) 
//			return "device_exposure.device_exposure_end_date";
//
//		if ("uniqueDeviceId".equals(columnVariable)) 
//			return "device_exposure.unique_device_id";
//
//		if ("deviceTypeConcept".equals(columnVariable)) 
//			return "device_exposure.device_type_concept_id";
//
//		if ("provider".equals(columnVariable)) 
//			return "device_exposure.provider_id";
//
//		if ("visitOccurrence".equals(columnVariable)) 
//			return "device_exposure.visit_occurrence_id";
//
//		if ("deviceSourceConcept".equals(columnVariable)) 
//			return "device_exposure.device_source_concept_id";
//
//		if ("deviceSourceValue".equals(columnVariable)) 
//			return "device_exposure.device_source_value";
//
//		if ("quantity".equals(columnVariable)) 
//			return "device_exposure.quantity";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return DeviceExposure._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = DeviceExposure.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "device_exposure";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return DeviceExposure._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("deviceConcept".equals(foreignVariable) 
				|| "deviceTypeConcept".equals(foreignVariable)
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
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return DeviceExposure._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from device_exposure ";
	}

}
