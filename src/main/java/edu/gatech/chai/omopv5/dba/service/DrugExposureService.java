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
import edu.gatech.chai.omopv5.model.entity.DrugExposure;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitDetail;
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
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					drugExposure.setFPerson(fPerson);
				}

				if (columnInfo.equalsIgnoreCase("drugConcept_concept_id")) {
					Concept drugConcept = ConceptService._construct(rs, null, "drugConcept");
					drugExposure.setDrugConcept(drugConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_start_date")) {
					drugExposure.setDrugExposureStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_start_datetime")) {
					drugExposure.setDrugExposureStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_end_date")) {
					drugExposure.setDrugExposureEndDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_drug_exposure_end_datetime")) {
					drugExposure.setDrugExposureEndDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_verbatim_end_date")) {
					drugExposure.setVerbatimEndDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("drugTypeConcept_concept_id")) {
					Concept drugTypeConcept = ConceptService._construct(rs, null, "drugTypeConcept");
					drugExposure.setDrugTypeConcept(drugTypeConcept);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_stop_reason")) {
					drugExposure.setStopReason(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_refills")) {
					drugExposure.setRefills(rs.getInt(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					drugExposure.setQuantity(rs.getDouble(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_days_supply")) {
					drugExposure.setDaysSupply(rs.getInt(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_sig")) {
					drugExposure.setSig(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("routeConcept_concept_id")) {
					Concept routeConcept = ConceptService._construct(rs, null, "routeConcept");
					drugExposure.setRouteConcept(routeConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_lot_number")) {
					drugExposure.setLotNumber(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					drugExposure.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					drugExposure.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase("visitDetail_visit_detail_id")) {
					VisitDetail visitDetail = VisitDetailService._construct(rs, null, "visitDetail");
					drugExposure.setVisitDetail(visitDetail);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_drug_source_value")) {
					drugExposure.setDrugSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("drugSourceConcept_concept_id")) {
					Concept drugSourceConcept = ConceptService._construct(rs, null, "drugSourceConcept");
					drugExposure.setDrugSourceConcpet(drugSourceConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_route_source_value")) {
					drugExposure.setRouteSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_dose_unit_source_value")) {
					drugExposure.setDoseUnitSourceValue(rs.getString(columnInfo));
				}


			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return drugExposure;
	}

}
