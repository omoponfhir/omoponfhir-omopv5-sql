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
import edu.gatech.chai.omopv5.model.entity.ConditionOccurrence;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface ConditionOccurrenceService.
 */
public interface ConditionOccurrenceService extends IService<ConditionOccurrence> {
	public static ConditionOccurrence _construct(ResultSet rs, ConditionOccurrence conditionOccurrence, String alias) {
		if (conditionOccurrence == null)
			conditionOccurrence = new ConditionOccurrence();

		if (alias == null || alias.isEmpty())
			alias = ConditionOccurrence._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_condition_occurrence_id")) {
					conditionOccurrence.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					conditionOccurrence.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("conditionConcept_concept_id")) {
					Concept conditionConcept = ConceptService._construct(rs, null, "conditionConcept");
					conditionOccurrence.setConditionConcept(conditionConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_condition_start_date")) {
					conditionOccurrence.setConditionStartDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_condition_end_date")) {
					conditionOccurrence.setConditionEndDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("conditionTypeConcept_concept_id")) {
					Concept conditionTypeConcept = ConceptService._construct(rs, null, "conditionTypeConcept");
					conditionOccurrence.setConditionTypeConcept(conditionTypeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_stop_reason")) {
					conditionOccurrence.setStopReason(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					conditionOccurrence.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					conditionOccurrence.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_condition_source_value")) {
					conditionOccurrence.setConditionSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("conditionSourceConcept_concept_id")) {
					Concept conditionSourceConcept = ConceptService._construct(rs, null, "conditionSourceConcept");
					conditionOccurrence.setConditionSourceConcept(conditionSourceConcept);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return conditionOccurrence;
	}

	public static ConditionOccurrence _construct(FieldValueList rowResult, ConditionOccurrence conditionOccurrence,
			String alias, List<String> columns) {
		if (conditionOccurrence == null)
			conditionOccurrence = new ConditionOccurrence();

		if (alias == null || alias.isEmpty())
			alias = ConditionOccurrence._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_condition_occurrence_id")) {
				conditionOccurrence.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				conditionOccurrence.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("conditionConcept_concept_id")) {
				Concept conditionConcept = ConceptService._construct(rowResult, null, "conditionConcept", columns);
				conditionOccurrence.setConditionConcept(conditionConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_condition_start_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					conditionOccurrence.setConditionStartDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_condition_end_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					conditionOccurrence.setConditionEndDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase("conditionTypeConcept_concept_id")) {
				Concept conditionTypeConcept = ConceptService._construct(rowResult, null, "conditionTypeConcept", columns);
				conditionOccurrence.setConditionTypeConcept(conditionTypeConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_stop_reason")) {
				conditionOccurrence.setStopReason(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				conditionOccurrence.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				conditionOccurrence.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_condition_source_value")) {
				conditionOccurrence.setConditionSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("conditionSourceConcept_concept_id")) {
				Concept conditionSourceConcept = ConceptService._construct(rowResult, null, "conditionSourceConcept", columns);
				conditionOccurrence.setConditionSourceConcept(conditionSourceConcept);
			}
		}

		return conditionOccurrence;
	}
}
