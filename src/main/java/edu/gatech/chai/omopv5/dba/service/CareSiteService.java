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
	 * @param location the location
	 * @return the care site
	 */
	public CareSite searchByNameAndLocation(String careSiteName, Location location);
	
	public static CareSite _construct(ResultSet rs, CareSite careSite, String alias) {
		if (careSite == null) careSite = new CareSite();
		
		if (alias == null || alias.isEmpty())
			alias = CareSite._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_care_site_id")) {
					careSite.setId(rs.getLong(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("location_location_id")) {
					Location location = LocationService._construct(rs, null, "location");
					careSite.setLocation(location);
				}

				if (columnInfo.equalsIgnoreCase("placeOfServiceConcept_concept_id")) {
					Concept placeOfServiceConcept = ConceptService._construct(rs, null, "placeOfServiceConcept");
					careSite.setPlaceOfServiceConcept(placeOfServiceConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_care_site_name")) {
					careSite.setCareSiteName(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_care_site_source_value")) {
					careSite.setCareSiteSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_place_of_service_source_value")) {
					careSite.setPlaceOfServiceSourceValue(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return careSite;
	}

	
}
