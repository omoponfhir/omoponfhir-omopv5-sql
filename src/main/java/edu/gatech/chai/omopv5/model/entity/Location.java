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
package edu.gatech.chai.omopv5.model.entity;

import java.util.List;

public class Location extends BaseEntity { 
	
	private Long id;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String locationSourceValue;

	public Location() {
		super();
	}

	public Location(Long id) {
		this.id = id;
	}
	
	public Location(String address1, String address2, String city,
			String state, String zipCode) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;		
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocationSourceValue() {
		return locationSourceValue;
	}

	public void setLocationSourceValue(String locationSourceValue) {
		this.locationSourceValue = locationSourceValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Location._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		if ("id".equals(columnVariable)) 
			return "location.location_id";

		if ("address1".equals(columnVariable)) 
			return "location.address_1";

		if ("address2".equals(columnVariable)) 
			return "location.address_2";

		if ("city".equals(columnVariable)) 
			return "location.city";

		if ("state".equals(columnVariable)) 
			return "location.state";

		if ("zipCode".equals(columnVariable)) 
			return "location.zip";

		if ("locationSourceValue".equals(columnVariable)) 
			return "location.location_source_value";

		return null;
	}

	@Override
	public String getTableName() {
		return Location._getTableName();
	}

	public static String _getTableName() {
		return "location";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Location._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		// no foreign table
		return null;
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from location ";
	}

}
