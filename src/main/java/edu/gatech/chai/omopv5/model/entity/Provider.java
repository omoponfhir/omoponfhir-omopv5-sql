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

@Table(name = "provider")
public class Provider extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="provider_id_seq")
	@Column(name="provider_id", nullable=false)
	private Long id;
	
	@Column(name="provider_name")
	private String providerName;
	
	@Column(name="npi")
	private String npi;
	
	@Column(name="dea")
	private String dea;
	
	@JoinColumn(name="specialty_concept_id", referencedColumnName="concept_id")
	private Concept specialtyConcept;
	
	@JoinColumn(name="care_site_id")
	private CareSite careSite;
	
	@Column(name="year_of_birth")
	private Integer yearOfBirth;
	
	@JoinColumn(name="gender_concept_id", referencedColumnName="concept_id")
	private Concept genderConcept;
	
	@Column(name="provider_source_value")
	private String providerSourceValue;

	@Column(name="specialty_source_value")
	private String specialtySourceValue;
	
	@JoinColumn(name="specialty_source_concept_id", referencedColumnName="concept_id")
	private Concept specialtySourceConcept;
	
	@Column(name="gender_source_value")
	private String genderSourceValue;
	
	@JoinColumn(name="gender_source_concept_id", referencedColumnName="concept_id")
	private Concept genderSourceConcept;	

	public Provider() {
		super();
	}
	
	public Provider(Long id) {
		this.id = id;
	}
	
	public Provider(Long id, String providerName, String npi, String dea, Concept specialtyConcept, 
			CareSite careSite, Integer yearOfBirth, Concept genderConcept, String providerSourceValue, 
			String specialtySourceValue, Concept specialtySourceConcept, String genderSourceValue,
			Concept genderSourceConcept) {
		this.id = id;
		this.providerName = providerName;
		this.npi = npi;
		this.dea = dea;
		this.specialtyConcept = specialtyConcept;
		this.careSite = careSite;
		this.yearOfBirth = yearOfBirth;
		this.genderConcept = genderConcept;
		this.providerSourceValue = providerSourceValue;
		this.specialtySourceValue = specialtySourceValue;
		this.specialtySourceConcept = specialtySourceConcept;
		this.genderSourceValue = genderSourceValue;
		this.genderSourceConcept = genderSourceConcept;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}
	
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public String getNpi() {
		return npi;
	}
	
	public void setNpi(String npi) {
		this.npi = npi;
	}
	
	public String getDea() {
		return dea;
	}
	
	public void setDea(String dea) {
		this.dea = dea;
	}
	
	public Concept getSpecialtyConcept() {
		return specialtyConcept;
	}
	
	public void setSpecialtyConcept(Concept specialtyConcept) {
		this.specialtyConcept = specialtyConcept;
	}
	
	public CareSite getCareSite() {
		return careSite;
	}
	
	public void setCareSite(CareSite careSite) {
		this.careSite = careSite;
	}
	
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public Concept getGenderConcept() {
		return genderConcept;
	}

	public void setGenderConcept(Concept genderConcept) {
		this.genderConcept = genderConcept;
	}
	
	public String getProviderSourceValue() {
		return providerSourceValue;
	}
	
	public void setProviderSourceValue(String providerSourceValue) {
		this.providerSourceValue = providerSourceValue;
	}

	public Concept getSpecialtySourceConcept() {
		return specialtySourceConcept;
	}

	public void setSpecialtySourceConcept(Concept specialtySourceConcept) {
		this.specialtySourceConcept = specialtySourceConcept;
	}

	public String getSpecialtySourceValue() {
		return specialtySourceValue;
	}
	
	public void setSpecialtySourceValue(String specialtySourceValue) {
		this.specialtySourceValue = specialtySourceValue;
	}
	
	public String getGenderSourceValue() {
		return genderSourceValue;
	}

	public void setGenderSourceValue(String genderSourceValue) {
		this.genderSourceValue = genderSourceValue;
	}

	public Concept getGenderSourceConcept() {
		return genderSourceConcept;
	}

	public void setGenderSourceConcept(Concept genderSourceConcept) {
		this.genderSourceConcept = genderSourceConcept;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Provider._getColumnName(columnVariable);
	}

	@Override
  	public String getFirstColumnName() {
  		return "provider_id";
  	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Provider.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Provider._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Provider._getTableName() + "." + joinAnnotation.name();
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
//			return "provider.provider_id";
//
//		if ("providerName".equals(columnVariable)) 
//			return "provider.provider_name";
//
//		if ("npi".equals(columnVariable)) 
//			return "provider.npi";
//
//		if ("dea".equals(columnVariable)) 
//			return "provider.dea";
//
//		if ("specialtyConcept".equals(columnVariable)) 
//			return "provider.specialty_concept_id";
//
//		if ("careSite".equals(columnVariable)) 
//			return "provider.care_site_id";
//
//		if ("yearOfBirth".equals(columnVariable)) 
//			return "provider.year_of_birth";
//
//		if ("genderConcept".equals(columnVariable)) 
//			return "provider.gender_concept_id";
//
//		if ("providerSourceValue".equals(columnVariable)) 
//			return "provider.provider_source_value";
//
//		if ("specialtySourceValue".equals(columnVariable)) 
//			return "provider.specialty_source_value";
//
//		if ("specialtySourceConcept".equals(columnVariable)) 
//			return "provider.specialty_source_concept_id";
//
//		if ("genderSourceValue".equals(columnVariable)) 
//			return "provider.gender_source_value";
//
//		if ("genderSourceConcept".equals(columnVariable)) 
//			return "provider.gender_source_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Provider._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Provider.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "provider";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Provider._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("specialtyConcept".equals(foreignVariable) 
				|| "genderConcept".equals(foreignVariable)
				|| "specialtySourceConcept".equals(foreignVariable) 
				|| "genderSourceConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("careSite".equals(foreignVariable))
			return CareSite._getTableName();

		return null;
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Provider._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from provider ";
	}

}
