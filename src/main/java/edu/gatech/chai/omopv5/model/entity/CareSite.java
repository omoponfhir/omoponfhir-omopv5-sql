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

@Table(name = "care_site")
public class CareSite extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="caresite_seq_gen")
	@Column(name="care_site_id", nullable=false)
	private Long id;
	
	@Column(name="care_site_name")
	private String careSiteName;
	
	@JoinColumn(name="place_of_service_concept_id", referencedColumnName="concept_id", table="concept")
	private Concept placeOfServiceConcept;
	
	@JoinColumn(name="location_id", table="location")
	private Location location;

	@Column(name="care_site_source_value")
	private String careSiteSourceValue;
	
	@Column(name="place_of_service_source_value")
	private String placeOfServiceSourceValue;
	
	public CareSite() {
		super();
	}
	
	public CareSite(Long id) {
		this.id = id;
	}
	
	public CareSite(Long id, Location location, Concept placeOfServiceConcept, String careSiteName, String careSiteSourceValue, String placeOfServiceSourceValue) {
		super();
		
		this.id = id;
		this.location = location;
		this.placeOfServiceConcept = placeOfServiceConcept;
		this.careSiteName = careSiteName;
		this.careSiteSourceValue = careSiteSourceValue;
		this.placeOfServiceSourceValue = placeOfServiceSourceValue;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCareSiteName() {
		return careSiteName;
	}
	
	public void setCareSiteName(String careSiteName) {
		this.careSiteName = careSiteName;
	}
	
	public Concept getPlaceOfServiceConcept() {
		return placeOfServiceConcept;
	}
	
	public void setPlaceOfServiceConcept(Concept placeOfServiceConcept) {
		this.placeOfServiceConcept = placeOfServiceConcept;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getCareSiteSourceValue() {
		return careSiteSourceValue;
	}
	
	public void setCareSiteSourceValue(String careSiteSourceValue) {
		this.careSiteSourceValue = careSiteSourceValue;
	}
	
	public String getPlaceOfServiceSourceValue() {
		return placeOfServiceSourceValue;
	}
	
	public void setPlaceOfServiceSourceValue(String placeOfServiceSourceValue) {
		this.placeOfServiceSourceValue = placeOfServiceSourceValue;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}
	
	@Override
	public String getColumnName(String columnVariable) {
		return CareSite._getColumnName(columnVariable);
	}

	@Override
  	public String getFirstColumnName() {
  		return "care_site_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = CareSite.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return CareSite._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return CareSite._getTableName() + "." + joinAnnotation.name();
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
//			return "care_site.care_site_id";
//		
//		if ("location".equals(columnVariable))
//			return "care_site.location_id";
//		
//		if ("placeOfServiceConcept".equals(columnVariable))
//			return "care_site.place_of_service_concept_id";
//		
//		if ("careSiteName".equals(columnVariable))
//			return "care_site.care_site_name";
//		
//		if ("careSiteSourceValue".equals(columnVariable))
//			return "care_site.care_site_source_value";
//		
//		if ("placeOfServiceSourceValue".equals(columnVariable))
//			return "care_site.place_of_service_source_value";
//		
//		return null;
	}
	
	@Override
	public String getTableName() {
		return CareSite._getTableName();		
	}

	public static String _getTableName() {
		Table annotation = CareSite.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "care_site";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return CareSite._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("placeOfServiceConcept".equals(foreignVariable))
			return Concept._getTableName();
			
		if ("location".equals(foreignVariable))
			return Location._getTableName();		
		
		return null;
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return CareSite._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from care_site ";
	}
	

}
