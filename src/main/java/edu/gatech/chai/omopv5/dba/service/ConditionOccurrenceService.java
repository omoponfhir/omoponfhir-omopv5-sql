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
import edu.gatech.chai.omopv5.model.entity.ConditionOccurrence;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface ConditionOccurrenceService.
 */
public interface ConditionOccurrenceService extends IService<ConditionOccurrence>{
	public static ConditionOccurrence _construct(ResultSet rs, ConditionOccurrence conditionOccurrence, String alias) {
		if (conditionOccurrence == null) conditionOccurrence = new ConditionOccurrence();
		
		if (alias == null || alias.isEmpty())
			alias = ConditionOccurrence._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_condition_occurrence_id")) {
					conditionOccurrence.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					conditionOccurrence.setFPerson(fPerson);
				}

				if (columnInfo.equalsIgnoreCase("conceptId_concept_id")) {
					Concept conceptId = ConceptService._construct(rs, null, "conceptId");
					conditionOccurrence.setConceptId(conceptId);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_condition_start_date")) {
					conditionOccurrence.setStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_condition_end_date")) {
					conditionOccurrence.setEndDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("typeConceptId_concept_id")) {
					Concept typeConceptId = ConceptService._construct(rs, null, "typeConceptId");
					conditionOccurrence.setTypeConceptId(typeConceptId);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_stop_reason")) {
					conditionOccurrence.setStopReason(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					conditionOccurrence.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					conditionOccurrence.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_condition_source_value")) {
					conditionOccurrence.setConditionSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("sourceConceptId_concept_id")) {
					Concept sourceConceptId = ConceptService._construct(rs, null, "sourceConceptId");
					conditionOccurrence.setSourceConceptId(sourceConceptId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return conditionOccurrence;
	}

}
