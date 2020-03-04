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
package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.DeviceExposure;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface DeviceExposureService.
 */
public interface DeviceExposureService extends IService<DeviceExposure> {
	public static DeviceExposure _construct(ResultSet rs, DeviceExposure deviceExposure, String alias) {
		if (deviceExposure == null) deviceExposure = new DeviceExposure();
		
		if (alias == null || alias.isEmpty())
			alias = DeviceExposure._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_device_exposure_id")) {
					deviceExposure.setId(rs.getLong(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					deviceExposure.setFPerson(fPerson);
				}
				
				if (columnInfo.equalsIgnoreCase("deviceConcept_concept_id")) {
					Concept deviceConcept = ConceptService._construct(rs, null, "deviceConcept");
					deviceExposure.setDeviceConcept(deviceConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_device_exposure_start_date")) {
					deviceExposure.setDeviceExposureStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_device_exposure_end_date")) {
					deviceExposure.setDeviceExposureEndDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_unique_device_id")) {
					deviceExposure.setUniqueDeviceId(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("deviceTypeConcept_concept_id")) {
					Concept deviceTypeConcept = ConceptService._construct(rs, null, "deviceTypeConcept");
					deviceExposure.setDeviceTypeConcept(deviceTypeConcept);
				}
				
				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					deviceExposure.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					deviceExposure.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase("deviceSourceConcept_concept_id")) {
					Concept deviceSourceConcept = ConceptService._construct(rs, null, "deviceSourceConcept");
					deviceExposure.setDeviceSourceConcept(deviceSourceConcept);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_device_source_value")) {
					deviceExposure.setDeviceSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					deviceExposure.setQuantity(rs.getInt(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return deviceExposure;
	}

}
