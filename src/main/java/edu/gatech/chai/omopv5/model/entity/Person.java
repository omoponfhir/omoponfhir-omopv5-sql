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
import java.util.Date;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "person")
public class Person extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_id_seq")
	@JoinColumn(name = "person_id", table="f_person:f_person", nullable = false)
	private Long id;

	@JoinColumn(name = "gender_concept_id", referencedColumnName="concept_id", nullable = false)
	private Concept genderConcept;

	@Column(name = "year_of_birth", nullable = false)
	private Integer yearOfBirth;

	@Column(name = "month_of_birth")
	private Integer monthOfBirth;

	@Column(name = "day_of_birth")
	private Integer dayOfBirth;

	@Column(name = "birth_datetime")
	private Date birthDateTime;

	@JoinColumn(name = "race_concept_id", referencedColumnName="concept_id", nullable = false)
	private Concept raceConcept;

	@JoinColumn(name = "ethnicity_concept_id", referencedColumnName="concept_id", nullable = false)
	private Concept ethnicityConcept;

	@JoinColumn(name = "location_id")
	private Location location;

	@JoinColumn(name = "provider_id")
	private Provider provider;

	@JoinColumn(name = "care_site_id")
	private CareSite careSite;

	@Column(name = "person_source_value")
	private String personSourceValue;

	@Column(name = "gender_source_value")
	private String genderSourceValue;

	@JoinColumn(name = "gender_source_concept_id", referencedColumnName="concept_id")
	private Concept genderSourceConcept;

	@Column(name = "race_source_value")
	private String raceSourceValue;

	@JoinColumn(name = "race_source_concept_id", referencedColumnName="concept_id")
	private Concept raceSourceConcept;

	@Column(name = "ethnicity_source_value")
	private String ethnicitySourceValue;

	@JoinColumn(name = "ethnicity_source_concept_id", referencedColumnName="concept_id")
	private Concept ethnicitySourceConcept;

	public Person() {
		super();
		this.genderConcept = new Concept();
		this.genderConcept.setId(0L);
		this.raceConcept = new Concept();
		this.raceConcept.setId(0L);
		this.setYearOfBirth(0);
		this.ethnicityConcept = new Concept();
		this.ethnicityConcept.setId(0L);
	}

	public Person(Long id, Concept genderConcept, Integer yearOfBirth, Integer monthOfBirth, Integer dayOfBirth,
			Date birthDateTime, Concept raceConcept, Concept ethnicityConcept, Location location, Provider provider,
			CareSite careSite, String personSourceValue, String genderSourceValue, Concept genderSourceConcept,
			String raceSourceValue, Concept raceSourceConcept, String ethnicitySourceValue,
			Concept ethnicitySourceConcept) {
		super();
		this.id = id;
		this.genderConcept = genderConcept;
		this.yearOfBirth = yearOfBirth;
		this.monthOfBirth = monthOfBirth;
		this.dayOfBirth = dayOfBirth;
		this.birthDateTime = birthDateTime;
		this.raceConcept = raceConcept;
		this.ethnicityConcept = ethnicityConcept;
		this.location = location;
		this.provider = provider;
		this.careSite = careSite;
		this.personSourceValue = personSourceValue;
		this.genderSourceValue = genderSourceValue;
		this.genderSourceConcept = genderSourceConcept;
		this.raceSourceValue = raceSourceValue;
		this.raceSourceConcept = raceSourceConcept;
		this.ethnicitySourceValue = ethnicitySourceValue;
		this.ethnicitySourceConcept = ethnicitySourceConcept;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Concept getGenderConcept() {
		return genderConcept;
	}

	public void setGenderConcept(Concept genderConcept) {
		this.genderConcept = genderConcept;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public Integer getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(Integer monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public Integer getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Integer dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Date getBirthDateTime() {
		return birthDateTime;
	}

	public void setBirthDateTime(Date birthDateTime) {
		this.birthDateTime = birthDateTime;
	}

	public Concept getRaceConcept() {
		return raceConcept;
	}

	public void setRaceConcept(Concept raceConcept) {
		this.raceConcept = raceConcept;
	}

	public Concept getEthnicityConcept() {
		return ethnicityConcept;
	}

	public void setEthnicityConcept(Concept ethnicityConcept) {
		this.ethnicityConcept = ethnicityConcept;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public CareSite getCareSite() {
		return careSite;
	}

	public void setCareSite(CareSite careSite) {
		this.careSite = careSite;
	}

	public String getPersonSourceValue() {
		return personSourceValue;
	}

	public void setPersonSourceValue(String personSourceValue) {
		this.personSourceValue = personSourceValue;
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

	public String getRaceSourceValue() {
		return raceSourceValue;
	}

	public void setRaceSourceValue(String raceSourceValue) {
		this.raceSourceValue = raceSourceValue;
	}

	public Concept getRaceSourceConcept() {
		return raceSourceConcept;
	}

	public void setRaceSourceConcept(Concept raceSourceConcept) {
		this.raceSourceConcept = raceSourceConcept;
	}

	public String getEthnicitySourceValue() {
		return ethnicitySourceValue;
	}

	public void setEthnicitySourceValue(String ethnicitySourceValue) {
		this.ethnicitySourceValue = ethnicitySourceValue;
	}

	public Concept getEthnicitySourceConcept() {
		return ethnicitySourceConcept;
	}

	public void setEthnicitySourceConcept(Concept ethnicitySourceConcept) {
		this.ethnicitySourceConcept = ethnicitySourceConcept;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Person._getColumnName(columnVariable);
	}

	@Override
  	public String getFirstColumnName() {
  		return "person_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Person.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Person._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Person._getTableName() + "." + joinAnnotation.name();
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
	}

	public static String _getAllfieldsName() {
		String allFieldsName = new String();
		Field[] Ffields = FPerson.class.getDeclaredFields();
		for (Field field : Ffields) {
			String fieldName = field.getName();
			if (allFieldsName.isEmpty() == false)
				allFieldsName = allFieldsName.concat(", ");
			allFieldsName = allFieldsName.concat(FPerson._getColumnName(fieldName));
		}

		return allFieldsName;
	}

	@Override
	public String getTableName() {
		return Person._getTableName();
	}

	public static String _getTableName() {
		Table annotation = Person.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "person";
	}

	@Override
	public String toString() {
		String out = "" + "person_id: " + this.getId() + "\n" + "gender_concept_id: "
				+ (this.getGenderConcept() == null ? "\n" : this.getGenderConcept().getConceptName() + "\n");

		return out;
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		if ("genderConcept".equals(foreignVariable) 
				|| "raceConcept".equals(foreignVariable)
				|| "ethnicityConcept".equals(foreignVariable) 
				|| "genderSourceConcept".equals(foreignVariable)
				|| "raceSourceConcept".equals(foreignVariable) 
				|| "ethnicitySourceConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("location".equals(foreignVariable))
			return Location._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("careSite".equals(foreignVariable))
			return CareSite._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Person._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from person ";
	}

}
