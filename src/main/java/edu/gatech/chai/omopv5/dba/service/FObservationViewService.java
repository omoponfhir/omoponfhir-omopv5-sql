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

import org.springframework.transaction.annotation.Transactional;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FObservationView;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitDetail;
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
	 * @param personId the person id
	 * @param date the date
	 * @param time the time
	 * @return the f observation view
	 */
	public FObservationView findDiastolic (Long conceptId, Long personId, Date date, String time);

	public static FObservationView _construct(ResultSet rs, FObservationView fObservationView, String alias) {
		if (fObservationView == null) fObservationView = new FObservationView();
		
		if (alias == null || alias.isEmpty())
			alias = FObservationView._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_observation_id")) {
					fObservationView.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					fObservationView.setFPerson(fPerson);
				}
				
				if (columnInfo.equalsIgnoreCase("observationConcept_concept_id")) {
					Concept observationConcept = ConceptService._construct(rs, null, "observationConcept");
					fObservationView.setObservationConcept(observationConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_observation_date")) {
					fObservationView.setObservationDate(rs.getDate(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_observation_datetime")) {
					fObservationView.setObservationDateTime(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_observation_time")) {
					fObservationView.setObservationTime(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_value_as_string")) {
					fObservationView.setValueAsString(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_value_as_number")) {
					fObservationView.setValueAsNumber(rs.getDouble(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("valueAsConcept_concept_id")) {
					Concept valueAsConcept = ConceptService._construct(rs, null, "valueAsConcept");
					fObservationView.setValueAsConcept(valueAsConcept);
				}

				if (columnInfo.equalsIgnoreCase("observationTypeConcept_concept_id")) {
					Concept observationTypeConcept = ConceptService._construct(rs, null, "observationTypeConcept");
					fObservationView.setObservationTypeConcept(observationTypeConcept);
				}

				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					fObservationView.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					fObservationView.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase("visitDetail_visit_detail_id")) {
					VisitDetail visitDetail = VisitDetailService._construct(rs, null, "visitDetail");
					fObservationView.setVisitDetail(visitDetail);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_observation_source_value")) {
					fObservationView.setObservationSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("observationSourceConcept_concept_id")) {
					Concept observationSourceConcept = ConceptService._construct(rs, null, "observationSourceConcept");
					fObservationView.setObservationSourceConcept(observationSourceConcept);
				}

				if (columnInfo.equalsIgnoreCase("qualifierConcept_concept_id")) {
					Concept qualifierConcept = ConceptService._construct(rs, null, "qualifierConcept");
					fObservationView.setQualifierConcept(qualifierConcept);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_qualifier_source_value")) {
					fObservationView.setQualifierSourceValue(rs.getString(columnInfo));
				}				

				if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					fObservationView.setUnitConcept(unitConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					fObservationView.setUnitSourceValue(rs.getString(columnInfo));
				}				

				if (columnInfo.equalsIgnoreCase(alias + "_range_low")) {
					fObservationView.setRangeLow(rs.getBigDecimal(columnInfo));
				}				

				if (columnInfo.equalsIgnoreCase(alias + "_range_high")) {
					fObservationView.setRangeHigh(rs.getBigDecimal(columnInfo));
				}				

				if (columnInfo.equalsIgnoreCase(alias + "_value_source_value")) {
					fObservationView.setValueSourceValue(rs.getString(columnInfo));
				}				

				if (columnInfo.equalsIgnoreCase("observationOperatorConcept_concept_id")) {
					Concept observationOperatorConcept = ConceptService._construct(rs, null, "observationOperatorConcept");
					fObservationView.setObservationOperatorConcept(observationOperatorConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return fObservationView;
	}

}
