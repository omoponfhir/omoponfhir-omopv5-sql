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

import java.lang.reflect.Field;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "location")
public class Location extends BaseEntity { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="location_id_seq")
	@Column(name="location_id", nullable=false)
	private Long id;
	
	@Column(name="address_1")
	private String address1;
	
	@Column(name="address_2")
	private String address2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="county")
	private String county;
	
	@Column(name="location_source_value")
	private String locationSourceValue;

	public Location() {
		super();
	}

	public Location(Long id) {
		this.id = id;
	}
	
	public Location(String address1, String address2, String city, String state, String zip) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;		
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
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
		try {
			Field field = Location.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Location._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Location._getTableName() + "." + joinAnnotation.name();
					}

					System.out.println("ERROR: annotation is null for field=" + field.toString());
					return null;
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;

//		if ("id".equals(columnVariable)) 
//			return "location.location_id";
//
//		if ("address1".equals(columnVariable)) 
//			return "location.address_1";
//
//		if ("address2".equals(columnVariable)) 
//			return "location.address_2";
//
//		if ("city".equals(columnVariable)) 
//			return "location.city";
//
//		if ("state".equals(columnVariable)) 
//			return "location.state";
//
//		if ("zipCode".equals(columnVariable)) 
//			return "location.zip";
//
//		if ("locationSourceValue".equals(columnVariable)) 
//			return "location.location_source_value";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Location._getTableName();
	}

	@Override
  	public String getFirstColumnName() {
  		return "location_id";
  	}

	public static String _getTableName() {
		Table annotation = Location.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
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

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Location._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from location ";
	}

}
