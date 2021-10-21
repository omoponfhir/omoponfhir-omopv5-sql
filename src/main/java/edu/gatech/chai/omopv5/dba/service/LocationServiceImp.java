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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationServiceImp.
 */
@Service
public class LocationServiceImp extends BaseEntityServiceImp<Location> implements LocationService {
	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImp.class);

	/**
	 * Instantiates a new location service imp.
	 */
	public LocationServiceImp() {
		super(Location.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.LocationService#searchByAddress(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Location searchByAddress(String line1, String line2, String city, String state, String zip) {
		Location entity = null;
		
		String queryString = "SELECT location.location_id as location_location_id, location.address_1 as location_address_1, " +
			"location.address_2 as location_address_2, location.city as location_city, location.state as location_state, location.zip as location_zip, " + 
			"location.county as location_county, location.location_source_value as location_location_source_value FROM " + Location._getTableName() + " location WHERE";
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String where_clause = new String();
		if (line1 != null) {
			where_clause = Location._getColumnName("address1") + " like '@line1'";
			parameterList.add("line1");
			valueList.add(line1);
		}

		if (line2 != null) {
			if (where_clause.isEmpty())
				where_clause = Location._getColumnName("address2") + " like '@line2'";
			else
				where_clause += " AND " + Location._getColumnName("address2") + " like '@line2'";

			parameterList.add("line2");
			valueList.add(line2);
		}

		if (city != null) {
			if (where_clause.isEmpty())
				where_clause = Location._getColumnName("city") + " like '@city'";
			else
				where_clause += " AND " + Location._getColumnName("city") + " like '@city'";

			parameterList.add("city");
			valueList.add(city);
		}

		if (state != null) {
			if (where_clause.isEmpty())
				where_clause = Location._getColumnName("state") + " like '@state'";
			else
				where_clause += " AND " + Location._getColumnName("state") + " like '@state'";

			parameterList.add("state");
			valueList.add(state);
		}

		if (zip != null) {
			if (where_clause.isEmpty())
				where_clause = Location._getColumnName("zip") + " like '@zip'";
			else
				where_clause += " AND " + Location._getColumnName("zip") + " like '@zip'";

			parameterList.add("zip");
			valueList.add(zip);
		}


		queryString += " " + where_clause;
		
		queryString = renderedSql(queryString, parameterList, valueList);

		logger.debug("Query for Location: " + queryString);
		logger.debug(
				Location._getColumnName("address1") + ":" + line1 + " " + Location._getColumnName("address2")
						+ ":" + line2 + " " + Location._getColumnName("city") + ":" + city
						+ " " + Location._getColumnName("state") + ":" + state + " " + Location._getColumnName("zip")
						+ ":" + zip);

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
				List<Location> myEntities = runQuery(queryString, null, "location");
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

//		if (line2 != null) {
//			query = "SELECT t FROM " + Location._getTableName() + " t WHERE address1 LIKE :line1 AND address2 LIKE :line2 AND city LIKE :city AND state LIKE :state AND zipCode LIKE :zip";
//		} else { 
//			query = "SELECT t FROM " + Location._getTableName() + " t WHERE address1 LIKE :line1 AND city LIKE :city AND state LIKE :state AND zipCode LIKE :zip";
//		}
		
		

		return entity;

	}

	@Override
	public Location construct(ResultSet rs, Location entity, String alias) {
		return LocationService._construct(rs, entity, alias);
	}

	@Override
	public Location construct(FieldValueList rowResult, Location entity, String alias, List<String> columns) {
		return LocationService._construct(rowResult, entity, alias, columns);
	}

}
