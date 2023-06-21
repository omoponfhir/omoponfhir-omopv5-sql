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

import org.springframework.transaction.annotation.Transactional;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FImmunizationView;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface FImmunizationViewService.
 */
@Transactional
public interface FImmunizationViewService extends IService<FImmunizationView> {

	public static FImmunizationView _construct(ResultSet rs, FImmunizationView fImmunizationView, String alias) {
		if (fImmunizationView == null)
			fImmunizationView = new FImmunizationView();

		if (alias == null || alias.isEmpty())
			alias = FImmunizationView._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_immunization_id")) {
					fImmunizationView.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					fImmunizationView.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("immunizationConcept_concept_id")) {
					Concept immunizationConcept = ConceptService._construct(rs, null, "immunizationConcept");
					fImmunizationView.setImmunizationConcept(immunizationConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_date")) {
					fImmunizationView.setImmunizationDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_datetime")) {
					fImmunizationView.setImmunizationDatetime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("immunizationTypeConcept_concept_id")) {
					Concept immunizationTypeConcept = ConceptService._construct(rs, null, "immunizationTypeConcept");
					fImmunizationView.setImmunizationTypeConcept(immunizationTypeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_status")) {
					fImmunizationView.setImmunizationStatus(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					fImmunizationView.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					fImmunizationView.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_lot_number")) {
					fImmunizationView.setLotNumber(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("routeConcept_concept_id")) {
					Concept routeConcept = ConceptService._construct(rs, null, "routeConcept");
					fImmunizationView.setRouteConcept(routeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					fImmunizationView.setQuantity(rs.getDouble(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_note")) {
					fImmunizationView.setImmunizationNote(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return fImmunizationView;
	}

	public static FImmunizationView _construct(FieldValueList rowResult, FImmunizationView fImmunizationView, String alias,
			List<String> columns) {
		if (fImmunizationView == null)
			fImmunizationView = new FImmunizationView();

		if (alias == null || alias.isEmpty())
			alias = FImmunizationView._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_immunization_id")) {
				fImmunizationView.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				fImmunizationView.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("immunization_concept_id")) {
				Concept observationConcept = ConceptService._construct(rowResult, null, "observationConcept", columns);
				fImmunizationView.setImmunizationConcept(observationConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					fImmunizationView.setImmunizationDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					fImmunizationView.setImmunizationDatetime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("immunizationTypeConcept_concept_id")) {
				Concept immunizationTypeConcept = ConceptService._construct(rowResult, null, "immunizationTypeConcept", columns);
				fImmunizationView.setImmunizationConcept(immunizationTypeConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_status")) {
				fImmunizationView.setImmunizationStatus(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				fImmunizationView.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				fImmunizationView.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_lot_number")) {
				fImmunizationView.setLotNumber(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("routeConcept_concept_id")) {
				Concept routeConcept = ConceptService._construct(rowResult, null, "observationSourceConcept", columns);
				fImmunizationView.setRouteConcept(routeConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
				fImmunizationView.setQuantity(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_immunization_note")) {
				fImmunizationView.setImmunizationNote(rowResult.get(columnInfo).getStringValue());
			}
		}

		return fImmunizationView;
	}
}