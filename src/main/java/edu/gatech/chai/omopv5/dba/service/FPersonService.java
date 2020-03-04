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
import edu.gatech.chai.omopv5.model.entity.Location;
import edu.gatech.chai.omopv5.model.entity.Provider;

// TODO: Auto-generated Javadoc
/**
 * The Interface FPersonService.
 */
public interface FPersonService extends IService<FPerson> {

	/**
	 * Search by name and location.
	 *
	 * @param familyName the family name
	 * @param given1Name the given 1 name
	 * @param given2Name the given 2 name
	 * @param location   the location
	 * @return the f person
	 */
	public FPerson searchByNameAndLocation(String familyName, String given1Name, String given2Name, Location location);

	public static FPerson _construct(ResultSet rs, FPerson fPerson, String alias) {
		if (fPerson == null)
			fPerson = new FPerson();

		if (alias == null || alias.isEmpty())
			alias = FPerson._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				// f_table content
				if (columnInfo.equalsIgnoreCase(alias + "_person_id")) {
					fPerson.setId(rs.getLong(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_family_name")) {
					fPerson.setFamilyName(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_given1_name")) {
					fPerson.setGivenName1(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_given2_name")) {
					fPerson.setGivenName2(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_prefix_name")) {
					fPerson.setPrefixName(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_suffix_name")) {
					fPerson.setSuffixName(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_preferred_language")) {
					fPerson.setPreferredLanguage(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_ssn")) {
					fPerson.setSsn(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_maritalstatus")) {
					fPerson.setMaritalStatus(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_active")) {
					fPerson.setActive(rs.getShort(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_contact_point1")) {
					fPerson.setContactPoint1(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_contact_point2")) {
					fPerson.setContactPoint2(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_contact_point3")) {
					fPerson.setContactPoint3(rs.getString(columnInfo));
				}

				// person table content.
				if (columnInfo.equalsIgnoreCase("genderConcept_concept_id")) {
					Concept genderConcept = ConceptService._construct(rs, null, "genderConcept");
					fPerson.setGenderConcept(genderConcept);
				}

				if (columnInfo.equalsIgnoreCase("person_year_of_birth")) {
					fPerson.setYearOfBirth(rs.getInt(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("person_month_of_birth")) {
					fPerson.setMonthOfBirth(rs.getInt(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("person_day_of_birth")) {
					fPerson.setDayOfBirth(rs.getInt(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("person_time_of_birth")) {
					fPerson.setTimeOfBirth(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("raceConcept_concept_id")) {
					Concept raceConcept = ConceptService._construct(rs, null, "genderConcept");
					fPerson.setRaceConcept(raceConcept);
				}

				if (columnInfo.equalsIgnoreCase("ethnicityConcept_concept_id")) {
					Concept ethnicityConcept = ConceptService._construct(rs, null, "ethnicityConcept");
					fPerson.setEthnicityConcept(ethnicityConcept);
				}

				if (columnInfo.equalsIgnoreCase("location_location_id")) {
					Location location = LocationService._construct(rs, null, "location");
					fPerson.setLocation(location);
				}

				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					fPerson.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("care_site_care_site_id")) {
					CareSite careSite = CareSiteService._construct(rs, null, "care_site");
					fPerson.setCareSite(careSite);
				}

				if (columnInfo.equalsIgnoreCase("person_person_source_value")) {
					fPerson.setPersonSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("person_gender_source_value")) {
					fPerson.setGenderSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("genderSourceConcept_concept_id")) {
					Concept genderSourceConcept = ConceptService._construct(rs, null, "genderSourceConcept");
					fPerson.setGenderSourceConcept(genderSourceConcept);
				}

				if (columnInfo.equalsIgnoreCase("person_race_source_value")) {
					fPerson.setRaceSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("raceSourceConcept_concept_id")) {
					Concept raceSourceConcept = ConceptService._construct(rs, null, "raceSourceConcept");
					fPerson.setRaceSourceConcept(raceSourceConcept);
				}

				if (columnInfo.equalsIgnoreCase("person_ethnicity_source_value")) {
					fPerson.setEthnicitySourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("ethnicitySourceConcept_concept_id")) {
					Concept ethnicitySourceConcept = ConceptService._construct(rs, null, "ethnicitySourceConcept");
					fPerson.setEthnicitySourceConcept(ethnicitySourceConcept);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fPerson;
	}
}
