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
import edu.gatech.chai.omopv5.model.entity.Provider;

/**
 * The Interface ProviderService.
 */
public interface ProviderService extends IService<Provider> {
	
	public static Provider _construct(ResultSet rs, Provider provider, String alias) {
		if (provider == null) provider = new Provider();
		
		if (alias == null || alias.isEmpty())
			alias = Provider._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_provider_id")) {
					provider.setId(rs.getLong(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_provider_name")) {
					provider.setProviderName(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_npi")) {
					provider.setNpi(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_dea")) {
					provider.setDea(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("specialtyConcept_concept_id")) {
					Concept specialtyConcept = ConceptService._construct(rs, null, "specialtyConcept");
					provider.setSpecialtyConcept(specialtyConcept);
				}
				
				if (columnInfo.equalsIgnoreCase("careSite_care_site_id")) {
					CareSite careSite = CareSiteService._construct(rs, null, "careSite");
					provider.setCareSite(careSite);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_year_of_birth")) {
					provider.setYearOfBirth(rs.getInt(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("genderConcept_concept_id")) {
					Concept genderConcept = ConceptService._construct(rs, null, "genderConcept");
					provider.setGenderConcept(genderConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_provider_source_value")) {
					provider.setProviderSourceValue(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_specialty_source_value")) {
					provider.setSpecialtySourceValue(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("specialtySourceConcept_concept_id")) {
					Concept specialtySourceConcept = ConceptService._construct(rs, null, "specialtySourceConcept");
					provider.setSpecialtySourceConcept(specialtySourceConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_gender_source_value")) {
					provider.setGenderSourceValue(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("genderSourceConcept_concept_id")) {
					Concept genderSourceConcept = ConceptService._construct(rs, null, "genderSourceConcept");
					provider.setGenderSourceConcept(genderSourceConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return provider;
	}

}
