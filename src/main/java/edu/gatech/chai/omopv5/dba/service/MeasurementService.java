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
import java.util.Date;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
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
		if (measurement == null)
			measurement = new Measurement();

		if (alias == null || alias.isEmpty())
			alias = Measurement._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_measurement_id")) {
					measurement.setId(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					measurement.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("measurementConcept_concept_id")) {
					Concept measurementConcept = ConceptService._construct(rs, null, "measurementConcept");
					measurement.setMeasurementConcept(measurementConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_measurement_date")) {
					measurement.setMeasurementDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_measurement_datetime")) {
					measurement.setMeasurementDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("measurementTypeConcept_concept_id")) {
					Concept measurementTypeConcept = ConceptService._construct(rs, null, "measurementTypeConcept");
					measurement.setMeasurementTypeConcept(measurementTypeConcept);
				} else if (columnInfo.equalsIgnoreCase("operatorConcept_concept_id")) {
					Concept operatorConcept = ConceptService._construct(rs, null, "operatorConcept");
					measurement.setOperationConcept(operatorConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
					double valueAsNumber = rs.getDouble(columnInfo);
					if (!rs.wasNull()) {
						measurement.setValueAsNumber(valueAsNumber);
					}
				} else if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
					Concept valueAsConcept = ConceptService._construct(rs, null, "valueAsConcept");
					measurement.setValueAsConcept(valueAsConcept);
				} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					measurement.setUnitConcept(unitConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_range_low")) {
					measurement.setRangeLow(rs.getDouble(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_range_high")) {
					measurement.setRangeHigh(rs.getDouble(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					measurement.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					measurement.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_measurement_source_value")) {
					measurement.setMeasurementSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("measurementSourceConcept_concept_id")) {
					Concept measurementSourceConcept = ConceptService._construct(rs, null, "measurementSourceConcept");
					measurement.setMeasurementSourceConcept(measurementSourceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					measurement.setUnitSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_source_value")) {
					measurement.setValueSourceValue(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return measurement;
	}

	public static Measurement _construct(FieldValueList rowResult, Measurement measurement, String alias,
			List<String> columns) {
		if (measurement == null)
			measurement = new Measurement();

		if (alias == null || alias.isEmpty())
			alias = Measurement._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_measurement_id")) {
				measurement.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				measurement.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("measurementConcept_concept_id")) {
				Concept measurementConcept = ConceptService._construct(rowResult, null, "measurementConcept", columns);
				measurement.setMeasurementConcept(measurementConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_measurement_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					measurement.setMeasurementDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_measurement_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					measurement.setMeasurementDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("measurementTypeConcept_concept_id")) {
				Concept measurementTypeConcept = ConceptService._construct(rowResult, null, "measurementTypeConcept", columns);
				measurement.setMeasurementTypeConcept(measurementTypeConcept);
			} else if (columnInfo.equalsIgnoreCase("operatorConcept_concept_id")) {
				Concept operatorConcept = ConceptService._construct(rowResult, null, "operatorConcept", columns);
				measurement.setOperationConcept(operatorConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
				measurement.setValueAsNumber(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
				Concept valueAsConcept = ConceptService._construct(rowResult, null, "valueAsConcept", columns);
				measurement.setValueAsConcept(valueAsConcept);
			} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
				Concept unitConcept = ConceptService._construct(rowResult, null, "unitConcept", columns);
				measurement.setUnitConcept(unitConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_range_low")) {
				measurement.setRangeLow(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_range_high")) {
				measurement.setRangeHigh(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				measurement.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				measurement.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_measurement_source_value")) {
				measurement.setMeasurementSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("measurementSourceConcept_concept_id")) {
				Concept measurementSourceConcept = ConceptService._construct(rowResult, null, "measurementSourceConcept", columns);
				measurement.setMeasurementSourceConcept(measurementSourceConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
				measurement.setUnitSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_source_value")) {
				measurement.setValueSourceValue(rowResult.get(columnInfo).getStringValue());
			}

		}

		return measurement;
	}
}
