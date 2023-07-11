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
import edu.gatech.chai.omopv5.model.entity.DrugExposure;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface DrugExposureService.
 */
public interface DrugExposureService extends IService<DrugExposure> {
	public static DrugExposure _construct(ResultSet rs, DrugExposure drugExposure, String alias) {
		if (drugExposure == null) drugExposure = new DrugExposure();
		
		if (alias == null || alias.isEmpty())
			alias = DrugExposure._getTableName();
		
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_id")) {
					drugExposure.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					drugExposure.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("drugConcept_concept_id")) {
					Concept drugConcept = ConceptService._construct(rs, null, "drugConcept");
					drugExposure.setDrugConcept(drugConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_start_date")) {
					drugExposure.setDrugExposureStartDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_start_datetime")) {
					drugExposure.setDrugExposureStartDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_end_date")) {
					drugExposure.setDrugExposureEndDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_end_datetime")) {
					drugExposure.setDrugExposureEndDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_verbatim_end_date")) {
					drugExposure.setVerbatimEndDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("drugTypeConcept_concept_id")) {
					Concept drugTypeConcept = ConceptService._construct(rs, null, "drugTypeConcept");
					drugExposure.setDrugTypeConcept(drugTypeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_stop_reason")) {
					drugExposure.setStopReason(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_refills")) {
					int valueAsInt = rs.getInt(columnInfo);
					if (!rs.wasNull()) {
						drugExposure.setRefills(valueAsInt);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					double valueAsDouble = rs.getDouble(columnInfo);
					if (!rs.wasNull()) {
						drugExposure.setQuantity(valueAsDouble);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_days_supply")) {
					int valueAsInt = rs.getInt(columnInfo);
					if (!rs.wasNull()) {
						drugExposure.setDaysSupply(valueAsInt);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_sig")) {
					drugExposure.setSig(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("routeConcept_concept_id")) {
					Concept routeConcept = ConceptService._construct(rs, null, "routeConcept");
					drugExposure.setRouteConcept(routeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_lot_number")) {
					drugExposure.setLotNumber(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					drugExposure.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					drugExposure.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_source_value")) {
					drugExposure.setDrugSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("drugSourceConcept_concept_id")) {
					Concept drugSourceConcept = ConceptService._construct(rs, null, "drugSourceConcept");
					drugExposure.setDrugSourceConcpet(drugSourceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_route_source_value")) {
					drugExposure.setRouteSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_dose_unit_source_value")) {
					drugExposure.setDoseUnitSourceValue(rs.getString(columnInfo));
				}


			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return drugExposure;
	}

	public static DrugExposure _construct(FieldValueList rowResult, DrugExposure drugExposure, String alias, List<String> columns) {
		if (drugExposure == null) drugExposure = new DrugExposure();
		
		if (alias == null || alias.isEmpty())
			alias = DrugExposure._getTableName();
		
			for (String columnInfo : columns) {
				if (rowResult.get(columnInfo).isNull()) continue;

				if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_id")) {
					drugExposure.setId(rowResult.get(columnInfo).getLongValue());
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
					drugExposure.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("drugConcept_concept_id")) {
					Concept drugConcept = ConceptService._construct(rowResult, null, "drugConcept", columns);
					drugExposure.setDrugConcept(drugConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_start_date")) {
					String dateString = rowResult.get(columnInfo).getStringValue();
					Date date = SqlUtil.string2Date(dateString);
					if (date != null) {
						drugExposure.setDrugExposureStartDate(date);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_start_datetime")) {
					String dateString = rowResult.get(columnInfo).getStringValue();
					Date date = SqlUtil.string2DateTime(dateString);
					if (date != null) {
						drugExposure.setDrugExposureStartDateTime(date);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_end_date")) {
					String dateString = rowResult.get(columnInfo).getStringValue();
					Date date = SqlUtil.string2Date(dateString);
					if (date != null) {
						drugExposure.setDrugExposureEndDate(date);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_end_datetime")) {
					String dateString = rowResult.get(columnInfo).getStringValue();
					Date date = SqlUtil.string2DateTime(dateString);
					if (date != null) {
						drugExposure.setDrugExposureEndDateTime(date);
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_verbatim_end_date")) {
					String dateString = rowResult.get(columnInfo).getStringValue();
					Date date = SqlUtil.string2Date(dateString);
					if (date != null) {
						drugExposure.setVerbatimEndDate(date);
					}
				} else if (columnInfo.equalsIgnoreCase("drugTypeConcept_concept_id")) {
					Concept drugTypeConcept = ConceptService._construct(rowResult, null, "drugTypeConcept", columns);
					drugExposure.setDrugTypeConcept(drugTypeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_stop_reason")) {
					drugExposure.setStopReason(rowResult.get(columnInfo).getStringValue());
				} else if (columnInfo.equalsIgnoreCase(alias + "_refills")) {
					drugExposure.setRefills((int) rowResult.get(columnInfo).getLongValue());
				} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					drugExposure.setQuantity(rowResult.get(columnInfo).getDoubleValue());
				} else if (columnInfo.equalsIgnoreCase(alias + "_days_supply")) {
					drugExposure.setDaysSupply((int) rowResult.get(columnInfo).getLongValue());
				} else if (columnInfo.equalsIgnoreCase(alias + "_sig")) {
					drugExposure.setSig(rowResult.get(columnInfo).getStringValue());
				} else if (columnInfo.equalsIgnoreCase("routeConcept_concept_id")) {
					Concept routeConcept = ConceptService._construct(rowResult, null, "routeConcept", columns);
					drugExposure.setRouteConcept(routeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_lot_number")) {
					drugExposure.setLotNumber(rowResult.get(columnInfo).getStringValue());
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
					drugExposure.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
					drugExposure.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_drug_source_value")) {
					drugExposure.setDrugSourceValue(rowResult.get(columnInfo).getStringValue());
				} else if (columnInfo.equalsIgnoreCase("drugSourceConcept_concept_id")) {
					Concept drugSourceConcept = ConceptService._construct(rowResult, null, "drugSourceConcept", columns);
					drugExposure.setDrugSourceConcpet(drugSourceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_route_source_value")) {
					drugExposure.setRouteSourceValue(rowResult.get(columnInfo).getStringValue());
				} else if (columnInfo.equalsIgnoreCase(alias + "_dose_unit_source_value")) {
					drugExposure.setDoseUnitSourceValue(rowResult.get(columnInfo).getStringValue());
				}


			}
		
		return drugExposure;
	}
}
