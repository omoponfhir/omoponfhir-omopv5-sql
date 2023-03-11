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
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.CareSite;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.Location;

// TODO: Auto-generated Javadoc
/**
 * The Interface CareSiteService.
 */
public interface CareSiteService extends IService<CareSite> {

	/**
	 * Search by location.
	 *
	 * @param location the location
	 * @return the care site
	 */
	public CareSite searchByLocation(Location location);

	/**
	 * Search by name and location.
	 *
	 * @param careSiteName the care site name
	 * @param location     the location
	 * @return the care site
	 */
	public CareSite searchByNameAndLocation(String careSiteName, Location location);

	/**
	 * For ResultSet based Entity Construction
	 * 
	 * @param rs
	 * @param careSite
	 * @param alias
	 * @return
	 */
	public static CareSite _construct(ResultSet rs, CareSite careSite, String alias) {
		if (careSite == null)
			careSite = new CareSite();

		boolean isReference = false;
		if (alias == null || alias.isEmpty()) {
			alias = CareSite._getTableName();
		} else {
			isReference = true;
		}

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_care_site_id")) {
					careSite.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase(isReference?alias+"_location_id":"location_location_id")) {
 					Location location;
 					if (isReference) {
 						location = LocationService._construct(rs, null, alias);
 					} else {
 						location = LocationService._construct(rs, null, "location");
 					}
					careSite.setLocation(location);
				} else if (columnInfo.equalsIgnoreCase("placeOfServiceConcept_concept_id")) {
					Concept placeOfServiceConcept = ConceptService._construct(rs, null, "placeOfServiceConcept");
					careSite.setPlaceOfServiceConcept(placeOfServiceConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_care_site_name")) {
					careSite.setCareSiteName(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_care_site_source_value")) {
					careSite.setCareSiteSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_place_of_service_source_value")) {
					careSite.setPlaceOfServiceSourceValue(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return careSite;
	}

	public static CareSite _construct(FieldValueList rowResult, CareSite careSite, String alias, List<String> columns) {
		if (careSite == null)
			careSite = new CareSite();

		boolean isReference = false;
		if (alias == null || alias.isEmpty()) {
			alias = CareSite._getTableName();
		} else {
			isReference = true;
		}

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_care_site_id")) {
				careSite.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase(isReference?alias+"_location_id":"location_location_id")) {
				Location location;
				if (isReference) {
					location = LocationService._construct(rowResult, null, alias, columns);
				} else {
					location = LocationService._construct(rowResult, null, "location", columns);
				}
				careSite.setLocation(location);
			} else if (columnInfo.equalsIgnoreCase("placeOfServiceConcept_concept_id")) {
				Concept placeOfServiceConcept = ConceptService._construct(rowResult, null, "placeOfServiceConcept", columns);
				careSite.setPlaceOfServiceConcept(placeOfServiceConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_care_site_name")) {
				careSite.setCareSiteName(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_care_site_source_value")) {
				careSite.setCareSiteSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_place_of_service_source_value")) {
				careSite.setPlaceOfServiceSourceValue(rowResult.get(columnInfo).getStringValue());
			}

		}

		return careSite;
	}

}
