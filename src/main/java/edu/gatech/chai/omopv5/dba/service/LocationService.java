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

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.Location;

// TODO: Auto-generated Javadoc
/**
 * The Interface LocationService.
 */
public interface LocationService extends IService<Location> {

	/**
	 * Search by address.
	 *
	 * @param line1   the line 1
	 * @param line2   the line 2
	 * @param city    the city
	 * @param state   the state
	 * @param zipCode the zip code
	 * @return the location
	 */
	public Location searchByAddress(String line1, String line2, String city, String state, String zipCode);

	public static Location _construct(ResultSet rs, Location location, String alias) {
		if (location == null)
			location = new Location();

		if (alias == null || alias.isEmpty())
			alias = Location._getTableName();

		ResultSetMetaData metaData;
		try {
			metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_location_id")) {
					location.setId(rs.getLong(columnInfo));
					if (rs.wasNull()) return null;
				} else if (columnInfo.equalsIgnoreCase(alias + "_address_1")) {
					location.setAddress1(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_address_2")) {
					location.setAddress2(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_city")) {
					location.setCity(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_state")) {
					location.setState(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_zip")) {
					location.setZip(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_location_source_value")) {
					location.setLocationSourceValue(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return location;
	}

	public static Location _construct(FieldValueList rowResult, Location location, String alias, List<String> columns) {
		if (location == null)
			location = new Location();

		if (alias == null || alias.isEmpty())
			alias = Location._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_location_id")) {
				location.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_address_1")) {
				location.setAddress1(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_address_2")) {
				location.setAddress2(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_city")) {
				location.setCity(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_state")) {
				location.setState(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_zip")) {
				location.setZip(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_location_source_value")) {
				location.setLocationSourceValue(rowResult.get(columnInfo).getStringValue());
			}

		}
		return location;
	}

}
