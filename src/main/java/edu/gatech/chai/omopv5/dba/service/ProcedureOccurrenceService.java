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
import edu.gatech.chai.omopv5.model.entity.ProcedureOccurrence;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface ProcedureOccurrenceService.
 */
public interface ProcedureOccurrenceService extends IService<ProcedureOccurrence> {
	public static ProcedureOccurrence _construct(ResultSet rs, ProcedureOccurrence procedureOccurrence, String alias) {
		if (procedureOccurrence == null)
			procedureOccurrence = new ProcedureOccurrence();

		if (alias == null || alias.isEmpty())
			alias = ProcedureOccurrence._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_procedure_occurrence_id")) {
					procedureOccurrence.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					procedureOccurrence.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("procedureConcept_concept_id")) {
					Concept procedureConcept = ConceptService._construct(rs, null, "procedureConcept");
					procedureOccurrence.setProcedureConcept(procedureConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_procedure_date")) {
					procedureOccurrence.setProcedureDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_procedure_datetime")) {
					procedureOccurrence.setProcedureDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("procedureTypeConcept_concept_id")) {
					Concept procedureTypeConcept = ConceptService._construct(rs, null, "procedureTypeConcept");
					procedureOccurrence.setProcedureTypeConcept(procedureTypeConcept);
				} else if (columnInfo.equalsIgnoreCase("modifierConcept_concept_id")) {
					Concept modifierConcept = ConceptService._construct(rs, null, "modifierConcept");
					procedureOccurrence.setModifierConcept(modifierConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					procedureOccurrence.setQuantity(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					procedureOccurrence.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					procedureOccurrence.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_procedure_source_value")) {
					procedureOccurrence.setProcedureSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("procedureSourceConcept_concept_id")) {
					Concept procedureSourceConcept = ConceptService._construct(rs, null, "procedureSourceConcept");
					procedureOccurrence.setProcedureSourceConcept(procedureSourceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_modifier_source_value")) {
					procedureOccurrence.setModifierSourceValue(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return procedureOccurrence;
	}

	public static ProcedureOccurrence _construct(FieldValueList rowResult, ProcedureOccurrence procedureOccurrence,
			String alias, List<String> columns) {
		if (procedureOccurrence == null)
			procedureOccurrence = new ProcedureOccurrence();

		if (alias == null || alias.isEmpty())
			alias = ProcedureOccurrence._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_procedure_occurrence_id")) {
				procedureOccurrence.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				procedureOccurrence.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("procedureConcept_concept_id")) {
				Concept procedureConcept = ConceptService._construct(rowResult, null, "procedureConcept", columns);
				procedureOccurrence.setProcedureConcept(procedureConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_procedure_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					procedureOccurrence.setProcedureDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_procedure_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					procedureOccurrence.setProcedureDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("procedureTypeConcept_concept_id")) {
				Concept procedureTypeConcept = ConceptService._construct(rowResult, null, "procedureTypeConcept", columns);
				procedureOccurrence.setProcedureTypeConcept(procedureTypeConcept);
			} else if (columnInfo.equalsIgnoreCase("modifierConcept_concept_id")) {
				Concept modifierConcept = ConceptService._construct(rowResult, null, "modifierConcept", columns);
				procedureOccurrence.setModifierConcept(modifierConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
				procedureOccurrence.setQuantity(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				procedureOccurrence.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				procedureOccurrence.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_procedure_source_value")) {
				procedureOccurrence.setProcedureSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("procedureSourceConcept_concept_id")) {
				Concept procedureSourceConcept = ConceptService._construct(rowResult, null, "procedureSourceConcept", columns);
				procedureOccurrence.setProcedureSourceConcept(procedureSourceConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_modifier_source_value")) {
				procedureOccurrence.setModifierSourceValue(rowResult.get(columnInfo).getStringValue());
			}

		}

		return procedureOccurrence;
	}
}
