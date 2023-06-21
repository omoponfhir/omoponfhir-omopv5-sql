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
import edu.gatech.chai.omopv5.model.entity.Observation;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface ObservationService.
 */
public interface ObservationService extends IService<Observation> {
	public static Observation _construct(ResultSet rs, Observation observation, String alias) {
		if (observation == null)
			observation = new Observation();

		if (alias == null || alias.isEmpty())
			alias = Observation._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_observation_id")) {
					observation.setId(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					observation.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("observationConcept_concept_id")) {
					Concept observationConcept = ConceptService._construct(rs, null, "observationConcept");
					observation.setObservationConcept(observationConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_observation_date")) {
					observation.setObservationDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_observation_datetime")) {
					observation.setObservationDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("observationTypeConcept_concept_id")) {
					Concept observationTypeConcept = ConceptService._construct(rs, null, "observationTypeConcept");
					observation.setObservationTypeConcept(observationTypeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
					observation.setValueAsNumber(rs.getDouble(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_string")) {
					observation.setValueAsString(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
					Concept valueAsConcept = ConceptService._construct(rs, null, "valueAsConcept");
					observation.setValueAsConcept(valueAsConcept);
				} else if (columnInfo.equalsIgnoreCase("qualifierConcept_concept_id")) {
					Concept qualifierConcept = ConceptService._construct(rs, null, "qualifierConcept");
					observation.setQualifierConcept(qualifierConcept);
				} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					observation.setUnitConcept(unitConcept);
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					observation.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					observation.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_observation_source_value")) {
					observation.setObservationSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("observationSourceConcept_concept_id")) {
					Concept observationSourceConcept = ConceptService._construct(rs, null, "observationSourceConcept");
					observation.setObservationSourceConcept(observationSourceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					observation.setUnitSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_qualifier_source_value")) {
					observation.setQualifierSourceValue(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return observation;
	}

	public static Observation _construct(FieldValueList rowResult, Observation observation, String alias,
			List<String> columns) {
		if (observation == null)
			observation = new Observation();

		if (alias == null || alias.isEmpty())
			alias = Observation._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_observation_id")) {
				observation.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				observation.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("observationConcept_concept_id")) {
				Concept observationConcept = ConceptService._construct(rowResult, null, "observationConcept", columns);
				observation.setObservationConcept(observationConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_observation_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					observation.setObservationDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_observation_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					observation.setObservationDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("observationTypeConcept_concept_id")) {
				Concept observationTypeConcept = ConceptService._construct(rowResult, null, "observationTypeConcept", columns);
				observation.setObservationTypeConcept(observationTypeConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
				observation.setValueAsNumber(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_string")) {
				observation.setValueAsString(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
				Concept valueAsConcept = ConceptService._construct(rowResult, null, "valueAsConcept", columns);
				observation.setValueAsConcept(valueAsConcept);
			} else if (columnInfo.equalsIgnoreCase("qualifierConcept_concept_id")) {
				Concept qualifierConcept = ConceptService._construct(rowResult, null, "qualifierConcept", columns);
				observation.setQualifierConcept(qualifierConcept);
			} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
				Concept unitConcept = ConceptService._construct(rowResult, null, "unitConcept", columns);
				observation.setUnitConcept(unitConcept);
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				observation.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				observation.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_observation_source_value")) {
				observation.setObservationSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("observationSourceConcept_concept_id")) {
				Concept observationSourceConcept = ConceptService._construct(rowResult, null, "observationSourceConcept", columns);
				observation.setObservationSourceConcept(observationSourceConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
				observation.setUnitSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_qualifier_source_value")) {
				observation.setQualifierSourceValue(rowResult.get(columnInfo).getStringValue());
			}

		}

		return observation;
	}

}
