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

import edu.gatech.chai.omopv5.model.entity.CareSite;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface VisitOccurrenceService.
 */
public interface VisitOccurrenceService extends IService<VisitOccurrence> {
	public static VisitOccurrence _construct(ResultSet rs, VisitOccurrence visitOccurrence, String alias) {
		if (visitOccurrence == null) visitOccurrence = new VisitOccurrence();
		
		if (alias == null || alias.isEmpty())
			alias = VisitOccurrence._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_visit_occurrence_id")) {
					visitOccurrence.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					visitOccurrence.setFPerson(fPerson);
				}

				if (columnInfo.equalsIgnoreCase("visitConcept_concept_id")) {
					Concept visitConcept = ConceptService._construct(rs, null, "visitConcept");
					visitOccurrence.setVisitConcept(visitConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_start_date")) {
					visitOccurrence.setStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_start_time")) {
					visitOccurrence.setStartTime(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_end_date")) {
					visitOccurrence.setEndDate(rs.getDate(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_visit_end_time")) {
					visitOccurrence.setEndTime(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("visitTypeConcept_concept_id")) {
					Concept visitTypeConcept = ConceptService._construct(rs, null, "visitTypeConcept");
					visitOccurrence.setVisitTypeConcept(visitTypeConcept);
				}
				
				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					visitOccurrence.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("careSite_provider_id")) {
					CareSite careSite = CareSiteService._construct(rs, null, "careSite");
					visitOccurrence.setCareSite(careSite);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_source_value")) {
					visitOccurrence.setVisitSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("visitSourceConcept_concept_id")) {
					Concept visitSourceConcept = ConceptService._construct(rs, null, "visitSourceConcept");
					visitOccurrence.setVisitSourceConcept(visitSourceConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return visitOccurrence;
	}

}
