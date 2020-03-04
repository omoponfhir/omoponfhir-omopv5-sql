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
import edu.gatech.chai.omopv5.model.entity.Observation;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface ObservationService.
 */
public interface ObservationService extends IService<Observation> {
	public static Observation _construct(ResultSet rs, Observation observation, String alias) {
		if (observation == null) observation = new Observation();
		
		if (alias == null || alias.isEmpty())
			alias = Observation._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_observation_id")) {
					observation.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					observation.setFPerson(fPerson);
				}
				
				if (columnInfo.equalsIgnoreCase("observationConcept_concept_id")) {
					Concept observationConcept = ConceptService._construct(rs, null, "observationConcept");
					observation.setObservationConcept(observationConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_observation_date")) {
					observation.setDate(rs.getDate(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_observation_time")) {
					observation.setTime(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_value_as_string")) {
					observation.setValueAsString(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
					observation.setValueAsNumber(rs.getDouble(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
					Concept valueAsConcept = ConceptService._construct(rs, null, "valueAsConcept");
					observation.setValueAsConcept(valueAsConcept);
				}

				if (columnInfo.equalsIgnoreCase("typeConcept_concept_id")) {
					Concept typeConcept = ConceptService._construct(rs, null, "typeConcept");
					observation.setTypeConcept(typeConcept);
				}

				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					observation.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					observation.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_source_value")) {
					observation.setSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("sourceConcept_concept_id")) {
					Concept sourceConcept = ConceptService._construct(rs, null, "sourceConcept");
					observation.setSourceConcept(sourceConcept);
				}

				if (columnInfo.equalsIgnoreCase("qualifierConcept_concept_id")) {
					Concept qualifierConcept = ConceptService._construct(rs, null, "qualifierConcept");
					observation.setQualifierConcept(qualifierConcept);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_qualifier_source_value")) {
					observation.setQualifierSourceValue(rs.getString(columnInfo));
				}				

				if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					observation.setUnitConcept(unitConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					observation.setUnitSourceValue(rs.getString(columnInfo));
				}				

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return observation;
	}
	
}
