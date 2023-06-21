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

@Table(name = "specimen")
public class Specimen extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="specimen_id_seq")
	@Column(name="specimen_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="specimen_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept specimenConcept;
	
	@JoinColumn(name="specimen_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept specimenTypeConcept;
	
	@Column(name="specimen_date", nullable=false)
	private Date specimenDate;
	
	@Column(name="specimen_datetime")
	private Date specimenDateTime;
	
	@Column(name="quantity")
	private Double quantity;
	
	@JoinColumn(name="unit_concept_id", referencedColumnName="concept_id")
	private Concept unitConcept;
	
	@JoinColumn(name="anatomic_site_concept_id", referencedColumnName="concept_id")
	private Concept anatomicSiteConcept;
	
	@JoinColumn(name="disease_status_concept_id", referencedColumnName="concept_id")
	private Concept diseaseStatusConcept;
	
	@Column(name="specimen_source_id")
	private String specimenSourceId;
	
	@Column(name="specimen_source_value")
	private String specimenSourceValue;
	
	@Column(name="unit_source_value")
	private String unitSourceValue;

	@Column(name="anatomic_site_source_value")
	private String anatomicSiteSourceValue;

	@Column(name="disease_status_source_value")
	private String diseaseStatusSourceValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FPerson getFPerson() {
		return fPerson;
	}

	public void setFPerson(FPerson fPerson) {
		this.fPerson = fPerson;
	}

	public Concept getSpecimenConcept() {
		return specimenConcept;
	}

	public void setSpecimenConcept(Concept specimenConcept) {
		this.specimenConcept = specimenConcept;
	}

	public Concept getSpecimenTypeConcept() {
		return specimenTypeConcept;
	}

	public void setSpecimenTypeConcept(Concept specimenTypeConcept) {
		this.specimenTypeConcept = specimenTypeConcept;
	}

	public Date getSpecimenDate() {
		return specimenDate;
	}

	public void setSpecimenDate(Date specimenDate) {
		this.specimenDate = specimenDate;
	}

	public Date getSpecimenDateTime() {
		return specimenDateTime;
	}

	public void setSpecimenDateTime(Date specimenDateTime) {
		this.specimenDateTime = specimenDateTime;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Concept getUnitConcept() {
		return unitConcept;
	}

	public void setUnitConcept(Concept unitConcept) {
		this.unitConcept = unitConcept;
	}

	public Concept getAnatomicSiteConcept() {
		return anatomicSiteConcept;
	}

	public void setAnatomicSiteConcept(Concept anatomicSiteConcept) {
		this.anatomicSiteConcept = anatomicSiteConcept;
	}

	public Concept getDiseaseStatusConcept () {
		return diseaseStatusConcept;
	}
	
	public void setDiseaseStatusConcept (Concept diseaseStatusConcept) {
		this.diseaseStatusConcept = diseaseStatusConcept;
	}
	
	public String getSpecimenSourceId() {
		return specimenSourceId;
	}

	public void setSpecimenSourceId(String specimenSourceId) {
		this.specimenSourceId = specimenSourceId;
	}

	public String getSpecimenSourceValue() {
		return this.specimenSourceValue;
	}

	public void setSpecimenSourceValue(String specimenSourceValue) {
		this.specimenSourceValue = specimenSourceValue;
	}

	public String getUnitSourceValue() {
		return unitSourceValue;
	}

	public void setUnitSourceValue(String unitSourceValue) {
		this.unitSourceValue = unitSourceValue;
	}

	public String getAnatomicSiteSourceValue() {
		return anatomicSiteSourceValue;
	}

	public void setAnatomicSiteSourceValue(String anatomicSiteSourceValue) {
		this.anatomicSiteSourceValue = anatomicSiteSourceValue;
	}

	public String getDiseaseStatusSourceValue() {
		return diseaseStatusSourceValue;
	}

	public void setDiseaseStatusSourceValue(String diseaseStatusSourceValue) {
		this.diseaseStatusSourceValue = diseaseStatusSourceValue;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Specimen._getColumnName(columnVariable);
	}
	
	@Override
 	public String getFirstColumnName() {
 		return "specimen_id";
 	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Specimen.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Specimen._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Specimen._getTableName() + "." + joinAnnotation.name();
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

	@Override
	public String getTableName() {
		return Specimen._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Specimen.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "specimen";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Specimen._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("specimenConcept".equals(foreignVariable) 
				|| "specimenTypeConcept".equals(foreignVariable) 
				|| "unitConcept".equals(foreignVariable)
				|| "anatomicSiteConcept".equals(foreignVariable)
				|| "diseaseStatusConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Specimen._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from specimen ";
	}

}