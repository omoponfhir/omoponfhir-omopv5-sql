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

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.CareSite;
import edu.gatech.chai.omopv5.model.entity.Location;

@Service
public class CareSiteServiceImp extends BaseEntityServiceImp<CareSite> implements CareSiteService {
	
	/**
	 * Instantiates a new care site service imp.
	 */
	public CareSiteServiceImp() {
		super(CareSite.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.CareSiteService#searchByLocation(edu.gatech.chai.omopv5.model.entity.Location)
	 */
	public CareSite searchByLocation(Location location) {
		List<CareSite> careSites = new ArrayList<CareSite>();

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sqlWithoutWhere = constructSqlSelectWithoutWhere();
		String sql = sqlWithoutWhere + " where care_site.location_id = @loc_id";

		parameterList.add("loc_id");
		valueList.add(location.getId().toString());

		sql = renderedSql(sql, parameterList, valueList);

		CareSite entity;
		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(sql);
				List<String> columns = listOfColumns(sql);
				for (FieldValueList row : result.iterateAll()) {
					entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						break;
					}
				}
			} else {
				List<CareSite> myEntities = runQuery(sql, null, "care_site");
				if (!myEntities.isEmpty()) {
					careSites.addAll(myEntities);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (careSites.isEmpty()) {
			return null;
		} else {
			return careSites.get(0);
		}
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.CareSiteService#searchByNameAndLocation(java.lang.String, edu.gatech.chai.omopv5.model.entity.Location)
	 */
	public CareSite searchByNameAndLocation(String careSiteName, Location location) {
		List<CareSite> careSites = new ArrayList<CareSite>();

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sqlWithoutWhere = constructSqlSelectWithoutWhere();
		String sql = sqlWithoutWhere + " where care_site.care_site_name like @caresite_name and location_id = @loc_id";

		parameterList.add("caresite_name");
		valueList.add("'%" + careSiteName + "%'");

		parameterList.add("loc_id");
		valueList.add(location.getId().toString());

		sql = renderedSql(sql, parameterList, valueList);

		CareSite entity;
		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(sql);
				List<String> columns = listOfColumns(sql);
				for (FieldValueList row : result.iterateAll()) {
					entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						break;
					}
				}
			} else {
				List<CareSite> myEntities = runQuery(sql, null, "care_site");
				if (!myEntities.isEmpty()) {
					careSites.addAll(myEntities);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (careSites.isEmpty()) {
			return null;
		} else {
			return careSites.get(0);
		}	
	}

	@Override
	public CareSite construct(ResultSet rs, CareSite entity, String alias) {
		return CareSiteService._construct(rs, entity, alias);
	}

	@Override
	public CareSite construct(FieldValueList rowResult, CareSite entity, String alias, List<String> columns) {
		return CareSiteService._construct(rowResult, entity, alias, columns);
	}

}
