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
import edu.gatech.chai.omopv5.model.entity.FObservationView;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

// TODO: Auto-generated Javadoc
/**
 * The Interface FObservationViewService.
 */
@Transactional
public interface FObservationViewService extends IService<FObservationView> {

	/**
	 * Find diastolic.
	 *
	 * @param conceptId the concept id
	 * @param personId  the person id
	 * @param date      the date
	 * @param time      the time
	 * @return the f observation view
	 */
	public FObservationView findDiastolic(Long conceptId, Long personId, Date date, Date time);

	public static FObservationView _construct(ResultSet rs, FObservationView fObservationView, String alias) {
		if (fObservationView == null)
			fObservationView = new FObservationView();

		if (alias == null || alias.isEmpty())
			alias = FObservationView._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);
				if (columnInfo.equalsIgnoreCase(alias + "_observation_id")) {
					fObservationView.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					fObservationView.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("observationConcept_concept_id")) {
					Concept observationConcept = ConceptService._construct(rs, null, "observationConcept");
					fObservationView.setObservationConcept(observationConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_observation_date")) {
					fObservationView.setObservationDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_observation_datetime")) {
					fObservationView.setObservationDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_string")) {
					String valueAsString = rs.getString(columnInfo);
					if (!rs.wasNull()) {
						fObservationView.setValueAsString(valueAsString);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
					double valueAsNumber = rs.getDouble(columnInfo);
					if (!rs.wasNull()) {
						fObservationView.setValueAsNumber(valueAsNumber);
					}
				} else if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
					Concept valueAsConcept = ConceptService._construct(rs, null, "valueAsConcept");
					fObservationView.setValueAsConcept(valueAsConcept);
				} else if (columnInfo.equalsIgnoreCase("observationTypeConcept_concept_id")) {
					Concept observationTypeConcept = ConceptService._construct(rs, null, "observationTypeConcept");
					fObservationView.setObservationTypeConcept(observationTypeConcept);
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					fObservationView.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					fObservationView.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_observation_source_value")) {
					fObservationView.setObservationSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("observationSourceConcept_concept_id")) {
					Concept observationSourceConcept = ConceptService._construct(rs, null, "observationSourceConcept");
					fObservationView.setObservationSourceConcept(observationSourceConcept);
				} else if (columnInfo.equalsIgnoreCase("qualifierConcept_concept_id")) {
					Concept qualifierConcept = ConceptService._construct(rs, null, "qualifierConcept");
					fObservationView.setQualifierConcept(qualifierConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_qualifier_source_value")) {
					fObservationView.setQualifierSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					fObservationView.setUnitConcept(unitConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					fObservationView.setUnitSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_range_low")) {
					fObservationView.setRangeLow(rs.getBigDecimal(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_range_high")) {
					fObservationView.setRangeHigh(rs.getBigDecimal(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_value_source_value")) {
					fObservationView.setValueSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("observationOperatorConcept_concept_id")) {
					Concept observationOperatorConcept = ConceptService._construct(rs, null,
							"observationOperatorConcept");
					fObservationView.setObservationOperatorConcept(observationOperatorConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return fObservationView;
	}

	public static FObservationView _construct(FieldValueList rowResult, FObservationView fObservationView, String alias,
			List<String> columns) {
		if (fObservationView == null)
			fObservationView = new FObservationView();

		if (alias == null || alias.isEmpty())
			alias = FObservationView._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_observation_id")) {
				fObservationView.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				fObservationView.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("observationConcept_concept_id")) {
				Concept observationConcept = ConceptService._construct(rowResult, null, "observationConcept", columns);
				fObservationView.setObservationConcept(observationConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_observation_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					fObservationView.setObservationDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_observation_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					fObservationView.setObservationDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_string")) {
				fObservationView.setValueAsString(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
				fObservationView.setValueAsNumber(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
				Concept valueAsConcept = ConceptService._construct(rowResult, null, "valueAsConcept", columns);
				fObservationView.setValueAsConcept(valueAsConcept);
			} else if (columnInfo.equalsIgnoreCase("observationTypeConcept_concept_id")) {
				Concept observationTypeConcept = ConceptService._construct(rowResult, null, "observationTypeConcept", columns);
				fObservationView.setObservationTypeConcept(observationTypeConcept);
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				fObservationView.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				fObservationView.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_observation_source_value")) {
				fObservationView.setObservationSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("observationSourceConcept_concept_id")) {
				Concept observationSourceConcept = ConceptService._construct(rowResult, null, "observationSourceConcept", columns);
				fObservationView.setObservationSourceConcept(observationSourceConcept);
			} else if (columnInfo.equalsIgnoreCase("qualifierConcept_concept_id")) {
				Concept qualifierConcept = ConceptService._construct(rowResult, null, "qualifierConcept", columns);
				fObservationView.setQualifierConcept(qualifierConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_qualifier_source_value")) {
				fObservationView.setQualifierSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
				Concept unitConcept = ConceptService._construct(rowResult, null, "unitConcept", columns);
				fObservationView.setUnitConcept(unitConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
				fObservationView.setUnitSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_range_low")) {
				fObservationView.setRangeLow(rowResult.get(columnInfo).getNumericValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_range_high")) {
				fObservationView.setRangeHigh(rowResult.get(columnInfo).getNumericValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_value_source_value")) {
				fObservationView.setValueSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("observationOperatorConcept_concept_id")) {
				Concept observationOperatorConcept = ConceptService._construct(rowResult, null, "observationOperatorConcept", columns);
				fObservationView.setObservationOperatorConcept(observationOperatorConcept);
			}

		}

		return fObservationView;
	}
}
