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

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ohdsi.sql.SqlRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.model.entity.CareSite;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Location;
import edu.gatech.chai.omopv5.model.entity.Provider;

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
		FPerson fPerson = new FPerson();
		
		String queryString = "SELECT t FROM " + fPerson.getTableName() + " t WHERE";
		Map<String, String> columnList = new HashMap<String, String> ();
		
		// Construct where clause here.
		String where_clause = "";
		if (familyName != null) {
			where_clause = fPerson.getColumnName("familyName") + " like :fname";
			columnList.put(":fname", familyName);
		}
		if (given1Name != null) {
			if (where_clause == "")
				where_clause = fPerson.getColumnName("givenName1") + " like :gname1";
			else
				where_clause += " AND " + fPerson.getColumnName("givenName1") + " like :gname1";
			columnList.put(":gname1", given1Name);
		}
		if (given2Name != null) {
			if (where_clause == "")
				where_clause = fPerson.getColumnName("givenName2") + " like :gname2";
			else
				where_clause += " AND " + fPerson.getColumnName("givenName2") + " like :gname2";
			columnList.put(":gname2", given1Name);
		}

		if (location != null) {
			if (where_clause == "")
				where_clause = fPerson.getColumnName("location") + " = :location";
			else
				where_clause += " AND " + fPerson.getColumnName("location") + " = :location";
			
			columnList.put(":location", location.getId().toString());
		}

		queryString += " " + where_clause;
		logger.debug("Query for FPerson" + queryString);
		logger.debug(fPerson.getColumnName("familyName") + ":" + familyName + " " + fPerson.getColumnName("givenName1")
				+ ":" + given1Name + " " + fPerson.getColumnName("givenName2") + ":" + given2Name);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(queryString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public FPerson findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FPerson> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FPerson> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FPerson> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList, String sort) {
		List<FPerson> fPersons = new ArrayList<FPerson>();
		int length = toIndex - fromIndex;

		String sortClause = getEntity().getSortClause(sort);
		String sql=null;

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		try {
			String joinTablesWhere = ParameterWrapper.constructClause(getEntityClass(), paramList, parameterList, valueList);
			if (joinTablesWhere != null && !joinTablesWhere.isEmpty()) {
				sql = FPerson._getSqlTableStatement(parameterList, valueList);
				sql = sql+joinTablesWhere;
			}
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			
			return null;
		}
		
		if (sql != null && !sql.isEmpty()) {
			sql = sql + " @sort @limit";
			parameterList.add("sort");
			parameterList.add("limit");
			
			if (sortClause != null && !sortClause.isEmpty()) {
				valueList.add(sortClause);
			} else {
				valueList.add("");
			}
			
			if (length > 0) {
				valueList.add("limit " + length + " offset " + fromIndex);
			} else {
				valueList.add("");
			}
			
			String[] parameters = new String[parameterList.size()];
			parameters = parameterList.toArray(parameters);
			
			String[] values = new String[valueList.size()];
			values = valueList.toArray(values);

			sql = SqlRender.renderSql(sql.trim(), parameters, values).replaceAll("\\s+"," ");
			System.out.println(sql);
			logger.debug("SqlRender:"+sql);

			try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

				while (rs.next()) {
					FPerson fp = constructFPerson(rs);
					if (fp != null)
						fPersons.add(fp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return fPersons;
	}

	@Override
	public List<FPerson> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		List<FPerson> fPersons = new ArrayList<FPerson>();
		int length = toIndex - fromIndex;

		// sort is comma separated sort parameter. ex. id asc,yearOfBirth asc
		String sortClause = getEntity().getSortClause(sort);
		
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		String sql = FPerson._getSqlTableStatement(parameterList, valueList)+" @sort @limit";
		parameterList.add("sort");
		parameterList.add("limit");

		if (sortClause != null && !sortClause.isEmpty()) {
			valueList.add(sortClause);
		} else {
			valueList.add("");
		}

		if (length > 0) {
			valueList.add("limit " + length + " offset " + fromIndex);
		} else {
			valueList.add("");
		}

		String[] parameters = new String[parameterList.size()];
		parameters = parameterList.toArray(parameters);
		
		String[] values = new String[valueList.size()];
		values = valueList.toArray(values);

		sql = SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+"," ");
		System.out.println(sql);
		logger.debug("SqlRender:"+sql);

		try {
		ResultSet rs = getQueryEntityDao().runQuery(sql);

			while (rs.next()) {
				FPerson fp = constructFPerson(rs);
				if (fp != null)
					fPersons.add(fp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fPersons;
	}

	@Override
	public FPerson create(FPerson entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FPerson update(FPerson entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSize(List<ParameterWrapper> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

	FPerson constructFPerson(ResultSet rs) {
		FPerson fPerson = new FPerson();
		try {
			// Required Person table entries
			fPerson.setId(rs.getLong("person_id"));
			Concept genderConcept = new Concept(rs.getLong("gender_concept_id"));
			fPerson.setGenderConcept(genderConcept);
			fPerson.setYearOfBirth(rs.getInt("year_of_birth"));
			fPerson.setMonthOfBirth(rs.getInt("month_of_birth"));
			fPerson.setDayOfBirth(rs.getInt("day_of_birth"));
			fPerson.setTimeOfBirth(rs.getString("time_of_birth"));
			Concept raceConcept = new Concept(rs.getLong("race_concept_id"));
			fPerson.setRaceConcept(raceConcept);
			Concept ethnicityConcept = new Concept(rs.getLong("ethnicity_concept_id"));
			fPerson.setEthnicityConcept(ethnicityConcept);
			Location location = new Location(rs.getLong("location_id"));
			fPerson.setLocation(location);
			Provider provider = new Provider(rs.getLong("provider_id"));
			fPerson.setProvider(provider);
			CareSite careSite = new CareSite(rs.getLong("care_site_id"));
			fPerson.setCareSite(careSite);
			fPerson.setPersonSourceValue(rs.getString("person_source_value"));
			fPerson.setGenderSourceValue(rs.getString("gender_source_value"));
			Concept genderSourceConcept = new Concept(rs.getLong("gender_source_concept_id"));
			fPerson.setGenderSourceConcept(genderSourceConcept);
			fPerson.setRaceSourceValue(rs.getString("race_source_value"));
			Concept raceSourceConcept = new Concept(rs.getLong("race_source_concept_id"));
			fPerson.setRaceSourceConcept(raceSourceConcept);
			fPerson.setEthnicitySourceValue(rs.getString("ethnicity_source_value"));
			Concept ethnicitySourceConcept = new Concept(rs.getLong("ethnicity_source_concept_id"));
			fPerson.setEthnicitySourceConcept(ethnicitySourceConcept);

			// f_person table if f_table exits.
			if (fTableExists) {
				fPerson.setFamilyName(rs.getString("family_name"));
				fPerson.setGivenName1(rs.getString("given1_name"));
				fPerson.setGivenName2(rs.getString("given2_name"));
				fPerson.setPrefixName(rs.getString("prefix_name"));
				fPerson.setSuffixName(rs.getString("suffix_name"));
				fPerson.setPreferredLanguage(rs.getString("preferred_language"));
				fPerson.setSsn(rs.getString("ssn"));
				fPerson.setMaritalStatus(rs.getString("maritalstatus"));
				fPerson.setActive(rs.getShort("active"));
				fPerson.setContactPoint1(rs.getString("contact_point1"));
				fPerson.setContactPoint2(rs.getString("contact_point2"));
				fPerson.setContactPoint3(rs.getString("contact_point3"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fPerson;
	}
}
