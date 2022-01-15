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
import java.util.ArrayList;
import java.util.List;

import org.ohdsi.sql.SqlRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Location;
import edu.gatech.chai.omopv5.model.entity.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class FPersonServiceImp.
 */
@Service
public class FPersonServiceImp extends BaseEntityServiceImp<FPerson> implements FPersonService {
	private static final Logger logger = LoggerFactory.getLogger(FPersonServiceImp.class);
	private boolean fTableExists;

	/**
	 * Instantiates a new f person service imp.
	 */
	public FPersonServiceImp() {
		super(FPerson.class);

		if ("False".equalsIgnoreCase(System.getenv("F_TABLE"))) {
			fTableExists = false;
		} else {
			fTableExists = true;
		}
	}

	public FPerson searchByNameAndLocation(String familyName, String given1Name, String given2Name, Location location) {
		FPerson entity = null;

		// String queryString = "select f_person.person_id as f_person_person_id, person.person_id as person_person_id, f_person.family_name as f_person_family_name, f_person.given1_name as f_person_given1_name, f_person.given2_name as f_person_given2_name, f_person.prefix_name as f_person_prefix_name, f_person.suffix_name as f_person_suffix_name, f_person.preferred_language as f_person_preferred_language, f_person.ssn as f_person_ssn, f_person.maritalstatus as f_person_maritalstatus, f_person.active as f_person_active, f_person.contact_point1 as f_person_contact_point1, f_person.contact_point2 as f_person_contact_point2, f_person.contact_point3 as f_person_contact_point3, genderConcept.concept_id as genderConcept_concept_id, genderConcept.concept_name as genderConcept_concept_name, genderConcept.domain_id as genderConcept_domain_id, genderConcept.vocabulary_id as genderConcept_vocabulary_id, genderConcept.concept_class_id as genderConcept_concept_class_id, genderConcept.standard_concept as genderConcept_standard_concept, genderConcept.concept_code as genderConcept_concept_code, genderConcept.valid_start_date as genderConcept_valid_start_date, genderConcept.valid_end_date as genderConcept_valid_end_date, genderConcept.invalid_reason as genderConcept_invalid_reason, person.year_of_birth as person_year_of_birth, person.month_of_birth as person_month_of_birth, person.day_of_birth as person_day_of_birth, person.birth_datetime as person_birth_datetime, raceConcept.concept_id as raceConcept_concept_id, raceConcept.concept_name as raceConcept_concept_name, raceConcept.domain_id as raceConcept_domain_id, raceConcept.vocabulary_id as raceConcept_vocabulary_id, raceConcept.concept_class_id as raceConcept_concept_class_id, raceConcept.standard_concept as raceConcept_standard_concept, raceConcept.concept_code as raceConcept_concept_code, raceConcept.valid_start_date as raceConcept_valid_start_date, raceConcept.valid_end_date as raceConcept_valid_end_date, raceConcept.invalid_reason as raceConcept_invalid_reason, ethnicityConcept.concept_id as ethnicityConcept_concept_id, ethnicityConcept.concept_name as ethnicityConcept_concept_name, ethnicityConcept.domain_id as ethnicityConcept_domain_id, ethnicityConcept.vocabulary_id as ethnicityConcept_vocabulary_id, ethnicityConcept.concept_class_id as ethnicityConcept_concept_class_id, ethnicityConcept.standard_concept as ethnicityConcept_standard_concept, ethnicityConcept.concept_code as ethnicityConcept_concept_code, ethnicityConcept.valid_start_date as ethnicityConcept_valid_start_date, ethnicityConcept.valid_end_date as ethnicityConcept_valid_end_date, ethnicityConcept.invalid_reason as ethnicityConcept_invalid_reason, location.location_id as location_location_id, location.address_1 as location_address_1, location.address_2 as location_address_2, location.city as location_city, location.state as location_state, location.zip as location_zip, location.county as location_county, location.location_source_value as location_location_source_value, provider.provider_id as provider_provider_id, provider.provider_name as provider_provider_name, provider.npi as provider_npi, provider.dea as provider_dea, provider.specialty_concept_id as provider_specialty_concept_id, provider.care_site_id as provider_care_site_id, provider.year_of_birth as provider_year_of_birth, provider.gender_concept_id as provider_gender_concept_id, provider.provider_source_value as provider_provider_source_value, provider.specialty_source_value as provider_specialty_source_value, provider.specialty_source_concept_id as provider_specialty_source_concept_id, provider.gender_source_value as provider_gender_source_value, provider.gender_source_concept_id as provider_gender_source_concept_id, careSite.care_site_id as careSite_care_site_id, careSite.care_site_name as careSite_care_site_name, careSite.place_of_service_concept_id as careSite_place_of_service_concept_id, careSite.location_id as careSite_location_id, careSite.care_site_source_value as careSite_care_site_source_value, careSite.place_of_service_source_value as careSite_place_of_service_source_value, person.person_source_value as person_person_source_value, person.gender_source_value as person_gender_source_value, genderSourceConcept.concept_id as genderSourceConcept_concept_id, genderSourceConcept.concept_name as genderSourceConcept_concept_name, genderSourceConcept.domain_id as genderSourceConcept_domain_id, genderSourceConcept.vocabulary_id as genderSourceConcept_vocabulary_id, genderSourceConcept.concept_class_id as genderSourceConcept_concept_class_id, genderSourceConcept.standard_concept as genderSourceConcept_standard_concept, genderSourceConcept.concept_code as genderSourceConcept_concept_code, genderSourceConcept.valid_start_date as genderSourceConcept_valid_start_date, genderSourceConcept.valid_end_date as genderSourceConcept_valid_end_date, genderSourceConcept.invalid_reason as genderSourceConcept_invalid_reason, person.race_source_value as person_race_source_value, raceSourceConcept.concept_id as raceSourceConcept_concept_id, raceSourceConcept.concept_name as raceSourceConcept_concept_name, raceSourceConcept.domain_id as raceSourceConcept_domain_id, raceSourceConcept.vocabulary_id as raceSourceConcept_vocabulary_id, raceSourceConcept.concept_class_id as raceSourceConcept_concept_class_id, raceSourceConcept.standard_concept as raceSourceConcept_standard_concept, raceSourceConcept.concept_code as raceSourceConcept_concept_code, raceSourceConcept.valid_start_date as raceSourceConcept_valid_start_date, raceSourceConcept.valid_end_date as raceSourceConcept_valid_end_date, raceSourceConcept.invalid_reason as raceSourceConcept_invalid_reason, person.ethnicity_source_value as person_ethnicity_source_value, ethnicitySourceConcept.concept_id as ethnicitySourceConcept_concept_id, ethnicitySourceConcept.concept_name as ethnicitySourceConcept_concept_name, ethnicitySourceConcept.domain_id as ethnicitySourceConcept_domain_id, ethnicitySourceConcept.vocabulary_id as ethnicitySourceConcept_vocabulary_id, ethnicitySourceConcept.concept_class_id as ethnicitySourceConcept_concept_class_id, ethnicitySourceConcept.standard_concept as ethnicitySourceConcept_standard_concept, ethnicitySourceConcept.concept_code as ethnicitySourceConcept_concept_code, ethnicitySourceConcept.valid_start_date as ethnicitySourceConcept_valid_start_date, ethnicitySourceConcept.valid_end_date as ethnicitySourceConcept_valid_end_date, ethnicitySourceConcept.invalid_reason as ethnicitySourceConcept_invalid_reason from f_person f_person left join person person on f_person.person_id=person.person_id left join concept genderConcept on person.gender_concept_id=genderConcept.concept_id left join concept raceConcept on person.race_concept_id=raceConcept.concept_id left join concept ethnicityConcept on person.ethnicity_concept_id=ethnicityConcept.concept_id left join location location on person.location_id=location.location_id left join provider provider on person.provider_id=provider.provider_id left join care_site careSite on person.care_site_id=careSite.care_site_id left join concept genderSourceConcept on person.gender_source_concept_id=genderSourceConcept.concept_id left join concept raceSourceConcept on person.race_source_concept_id=raceSourceConcept.concept_id left join concept ethnicitySourceConcept on person.ethnicity_source_concept_id=ethnicitySourceConcept.concept_id where ";
		String queryString = constructSqlSelectWithoutWhere() + " where ";
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		// Construct where clause here.
		String where_clause = "";
		if (familyName != null) {
			where_clause = FPerson._getColumnName("familyName") + " like @fname";
			parameterList.add("fname");
			valueList.add(familyName);
		}

		if (given1Name != null) {
			if (where_clause == "")
				where_clause = FPerson._getColumnName("givenName1") + " like @gname1";
			else
				where_clause += " AND " + FPerson._getColumnName("givenName1") + " like @gname1";
			parameterList.add("gname1");
			valueList.add(given1Name);
		}
		
		if (given2Name != null) {
			if (where_clause == "")
				where_clause = FPerson._getColumnName("givenName2") + " like @gname2";
			else
				where_clause += " AND " + FPerson._getColumnName("givenName2") + " like @gname2";

			parameterList.add("gname2");
			valueList.add(given2Name);
		}

		if (location != null) {
			if (where_clause == "")
				where_clause = FPerson._getColumnName("location") + " = @location";
			else
				where_clause += " AND " + FPerson._getColumnName("location") + " = @location";

			parameterList.add("location");
			valueList.add(location.getId().toString());
		}

		queryString += " " + where_clause;
		
		queryString = renderedSql(queryString, parameterList, valueList);
		
		logger.debug("Query for FPerson: " + queryString);
		logger.debug(
				FPerson._getColumnName("familyName") + ":" + familyName + " " + FPerson._getColumnName("givenName1")
						+ ":" + given1Name + " " + FPerson._getColumnName("givenName2") + ":" + given2Name);

		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(queryString);
				List<String> columns = listOfColumns(queryString);
				for (FieldValueList row : result.iterateAll()) {
					entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						break;
					}
				}
			} else {
				// ResultSet rs = getQueryEntityDao().runQuery(queryString);
				List<FPerson> myEntities = runQuery(queryString, null, "t");
				if (!myEntities.isEmpty()) {
					entity = myEntities.get(0);
				}
				// while (rs.next()) {
				// 	entity = construct(rs, null, "t");
				// 	if (entity != null)
				// 		break;
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

	@Override
	public Long removeById(Long id) {
		Long retVal = 0L;

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = "delete from @table where @parameter=@value;";
		parameterList.add("table");
		parameterList.add("parameter");
		parameterList.add("value");

		valueList.add(getEntity().getTableName());
		String idColumn = getEntity().getColumnName("id");
		// idColumn has tablealias.column. But, when we delete, we need delete one at a time.
		// so, we must not use alias here. 
		idColumn = idColumn.substring(idColumn.indexOf(".")+1);
		valueList.add(idColumn);
		valueList.add(id.toString());

		String[] parameters = new String[parameterList.size()];
		parameters = parameterList.toArray(parameters);

		String[] values = new String[valueList.size()];
		values = valueList.toArray(values);

		sql = SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");
		try {
			if (isBigQuery()) {
				runBigQuery(sql);
				retVal = 1L;
			} else {
				retVal = updateQuery(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fTableExists) {
			// We just deleted f_person. Now, delete person table.
			sql = "delete from @table where @parameter=@value;";

			valueList.set(0, Person._getTableName());
			values = new String[valueList.size()];
			values = valueList.toArray(values);
			sql = SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");

			try {
				if (isBigQuery()) {
					runBigQuery(sql);
					retVal = 1L;
				} else {
					retVal = updateQuery(sql);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return retVal;
	}

	@Override
	public FPerson construct(ResultSet rs, FPerson entity, String alias) {
		return FPersonService._construct(rs, entity, alias);
	}

	@Override
	public FPerson construct(FieldValueList rowResult, FPerson entity, String alias, List<String> columns) {
		return FPersonService._construct(rowResult, entity, alias, columns);
	}

}
