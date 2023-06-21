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
		if (visitOccurrence == null)
			visitOccurrence = new VisitOccurrence();

		if (alias == null || alias.isEmpty())
			alias = VisitOccurrence._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_visit_occurrence_id")) {
					visitOccurrence.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					visitOccurrence.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("visitConcept_concept_id")) {
					Concept visitConcept = ConceptService._construct(rs, null, "visitConcept");
					visitOccurrence.setVisitConcept(visitConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_visit_start_date")) {
					visitOccurrence.setVisitStartDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_visit_start_datetime")) {
					visitOccurrence.setVisitStartDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_visit_end_date")) {
					visitOccurrence.setVisitEndDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_visit_end_datetime")) {
					visitOccurrence.setVisitEndDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("visitTypeConcept_concept_id")) {
					Concept visitTypeConcept = ConceptService._construct(rs, null, "visitTypeConcept");
					visitOccurrence.setVisitTypeConcept(visitTypeConcept);
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					visitOccurrence.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("careSite_provider_id")) {
					CareSite careSite = CareSiteService._construct(rs, null, "careSite");
					visitOccurrence.setCareSite(careSite);
				} else if (columnInfo.equalsIgnoreCase(alias + "_visit_source_value")) {
					visitOccurrence.setVisitSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("visitSourceConcept_concept_id")) {
					Concept visitSourceConcept = ConceptService._construct(rs, null, "visitSourceConcept");
					visitOccurrence.setVisitSourceConcept(visitSourceConcept);
				} else if (columnInfo.equalsIgnoreCase("admittingSourceConcept_concept_id")) {
					Concept admittingSourceConcept = ConceptService._construct(rs, null, "admittingSourceConcept");
					visitOccurrence.setAdmittingSourceConcept(admittingSourceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_admitting_source_value")) {
					visitOccurrence.setAdmittingSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("dischargeToConcept_concept_id")) {
					Concept dischargeToConcept = ConceptService._construct(rs, null, "dischargeToConcept");
					visitOccurrence.setDischargeToConcept(dischargeToConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_discharge_to_source_value")) {
					visitOccurrence.setDischargeToSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("precedingVisitOccurrence_visit_occurrence_id")) {
					VisitOccurrence precedingVisitOccurrence = VisitOccurrenceService._construct(rs, null,
							"precedingVisitOccurrence");
					visitOccurrence.setPrecedingVisitOccurrence(precedingVisitOccurrence);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return visitOccurrence;
	}

	public static VisitOccurrence _construct(FieldValueList rowResult, VisitOccurrence visitOccurrence, String alias,
			List<String> columns) {
		if (visitOccurrence == null)
			visitOccurrence = new VisitOccurrence();

		if (alias == null || alias.isEmpty())
			alias = VisitOccurrence._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_visit_occurrence_id")) {
				visitOccurrence.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				visitOccurrence.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("visitConcept_concept_id")) {
				Concept visitConcept = ConceptService._construct(rowResult, null, "visitConcept", columns);
				visitOccurrence.setVisitConcept(visitConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_visit_start_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					visitOccurrence.setVisitStartDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_visit_start_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					visitOccurrence.setVisitStartDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_visit_end_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					visitOccurrence.setVisitEndDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_visit_end_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					visitOccurrence.setVisitEndDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("visitTypeConcept_concept_id")) {
				Concept visitTypeConcept = ConceptService._construct(rowResult, null, "visitTypeConcept", columns);
				visitOccurrence.setVisitTypeConcept(visitTypeConcept);
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				visitOccurrence.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("careSite_provider_id")) {
				CareSite careSite = CareSiteService._construct(rowResult, null, "careSite", columns);
				visitOccurrence.setCareSite(careSite);
			} else if (columnInfo.equalsIgnoreCase(alias + "_visit_source_value")) {
				visitOccurrence.setVisitSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("visitSourceConcept_concept_id")) {
				Concept visitSourceConcept = ConceptService._construct(rowResult, null, "visitSourceConcept", columns);
				visitOccurrence.setVisitSourceConcept(visitSourceConcept);
			} else if (columnInfo.equalsIgnoreCase("admittingSourceConcept_concept_id")) {
				Concept admittingSourceConcept = ConceptService._construct(rowResult, null, "admittingSourceConcept", columns);
				visitOccurrence.setAdmittingSourceConcept(admittingSourceConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_admitting_source_value")) {
				visitOccurrence.setAdmittingSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("dischargeToConcept_concept_id")) {
				Concept dischargeToConcept = ConceptService._construct(rowResult, null, "dischargeToConcept", columns);
				visitOccurrence.setDischargeToConcept(dischargeToConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_discharge_to_source_value")) {
				visitOccurrence.setDischargeToSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("precedingVisitOccurrence_visit_occurrence_id")) {
				VisitOccurrence precedingVisitOccurrence = VisitOccurrenceService._construct(rowResult, null, "precedingVisitOccurrence", columns);
				visitOccurrence.setPrecedingVisitOccurrence(precedingVisitOccurrence);
			}

		}

		return visitOccurrence;
	}

}
