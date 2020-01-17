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

public class Provider extends BaseEntity {

	private Long id;
	private String providerName;
	private String npi;
	private String dea;
	private Concept specialtyConcept;
	private CareSite careSite;
	private Integer yearOfBirth;
	private Concept genderConcept;
	private String providerSourceValue;
	private String specialtySourceValue;
	private Concept specialtySourceConcept;
	private String genderSourceValue;
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
	
	public static String _getColumnName(String columnVariable) {
		if ("id".equals(columnVariable)) 
			return "provider.provider_id";

		if ("providerName".equals(columnVariable)) 
			return "provider.provider_name";

		if ("npi".equals(columnVariable)) 
			return "provider.npi";

		if ("dea".equals(columnVariable)) 
			return "provider.dea";

		if ("specialtyConcept".equals(columnVariable)) 
			return "provider.specialty_concept_id";

		if ("careSite".equals(columnVariable)) 
			return "provider.care_site_id";

		if ("yearOfBirth".equals(columnVariable)) 
			return "provider.year_of_birth";

		if ("genderConcept".equals(columnVariable)) 
			return "provider.gender_concept_id";

		if ("providerSourceValue".equals(columnVariable)) 
			return "provider.provider_source_value";

		if ("specialtySourceValue".equals(columnVariable)) 
			return "provider.specialty_source_value";

		if ("specialtySourceConcept".equals(columnVariable)) 
			return "provider.specialty_source_concept_id";

		if ("genderSourceValue".equals(columnVariable)) 
			return "provider.gender_source_value";

		if ("genderSourceConcept".equals(columnVariable)) 
			return "provider.gender_source_concept_id";

		return null;
	}

	@Override
	public String getTableName() {
		return Provider._getTableName();
	}
	
	public static String _getTableName() {
		return "provider";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Provider._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("specialtyConcept".equals(foreignVariable) || "genderConcept".equals(foreignVariable)
				|| "specialtySourceConcept".equals(foreignVariable) || "genderSourceConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("careSite".equals(foreignVariable))
			return CareSite._getTableName();

		return null;
	}
	
	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from provider ";
	}

}
