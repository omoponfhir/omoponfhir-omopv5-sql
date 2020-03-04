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
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Measurement;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface MeasurementService.
 */
public interface MeasurementService extends IService<Measurement> {
	public static Measurement _construct(ResultSet rs, Measurement measurement, String alias) {
		if (measurement == null) measurement = new Measurement();
		
		if (alias == null || alias.isEmpty())
			alias = Measurement._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_measurement_id")) {
					measurement.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					measurement.setFPerson(fPerson);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_measurement_source_value")) {
					measurement.setSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("sourceValueConcept_concept_id")) {
					Concept sourceValueConcept = ConceptService._construct(rs, null, "sourceValueConcept");
					measurement.setSourceValueConcept(sourceValueConcept);
				}

				if (columnInfo.equalsIgnoreCase("measurementConcept_concept_id")) {
					Concept measurementConcept = ConceptService._construct(rs, null, "measurementConcept");
					measurement.setMeasurementConcept(measurementConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
					measurement.setValueAsNumber(rs.getDouble(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
					Concept valueAsConcept = ConceptService._construct(rs, null, "valueAsConcept");
					measurement.setValueAsConcept(valueAsConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "value_source_value")) {
					measurement.setValueSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("operatorConcept_concept_id")) {
					Concept operatorConcept = ConceptService._construct(rs, null, "operatorConcept");
					measurement.setOperationConcept(operatorConcept);
				}

				if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					measurement.setUnitConcept(unitConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					measurement.setUnitSourceValue(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_range_low")) {
					measurement.setRangeLow(rs.getDouble(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_range_high")) {
					measurement.setRangeHigh(rs.getDouble(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					measurement.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_measurement_date")) {
					measurement.setDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_measurement_time")) {
					measurement.setTime(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					measurement.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase("type_concept_id")) {
					Concept type = ConceptService._construct(rs, null, "type");
					measurement.setType(type);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return measurement;
	}

}
