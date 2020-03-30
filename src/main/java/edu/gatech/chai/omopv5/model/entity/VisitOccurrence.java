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

@Table(name = "visit_occurrence")
public class VisitOccurrence extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="visit_occurrence_id_seq")
	@Column(name="visit_occurrence_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="visit_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept visitConcept;
	
	@Column(name="visit_start_date", nullable=false)
	private Date startDate;
	
	@Column(name="visit_start_time")
	private String startTime;
	
	@Column(name="visit_end_date", nullable=false)
	private Date endDate;

	@Column(name="visit_end_time")
	private String endTime;
	
	@JoinColumn(name="visit_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept visitTypeConcept;

	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@JoinColumn(name="care_site_id")
	private CareSite careSite; //FIXME field names should reflect fhir names, for validation purposes.
	
	@Column(name="visit_source_value")
	private String visitSourceValue;
	
	@JoinColumn(name="visit_source_concept_id", referencedColumnName="concept_id")
	private Concept visitSourceConcept;
	

	public VisitOccurrence() {
	}
	
	public VisitOccurrence(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FPerson getFPerson() {
		return fPerson;
	}
	
	public void setFPerson(FPerson person) {
		this.fPerson = person;
	}
	
	public Concept getVisitConcept() {
		return visitConcept;
	}
	
	public void setVisitConcept(Concept visitConcept) {
		this.visitConcept = visitConcept;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Concept getVisitTypeConcept() {
		return visitTypeConcept;
	}
	
	public void setVisitTypeConcept(Concept visitTypeConcept) {
		this.visitTypeConcept = visitTypeConcept;
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
	
	public String getVisitSourceValue() {
		return visitSourceValue;
	}
	
	public void setVisitSourceValue(String visitSourceValue) {
		this.visitSourceValue = visitSourceValue;
	}
	
	public Concept getVisitSourceConcept() {
		return visitSourceConcept;
	}
	
	public void setVisitSourceConcept(Concept visitSourceConcept) {
		this.visitSourceConcept = visitSourceConcept;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return VisitOccurrence._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = VisitOccurrence.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return VisitOccurrence._getTableName() + "." + annotation.name();
				} else {
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
//			return "visit_occurrence.visit_occurrence_id";
//
//		if ("fPerson".equals(columnVariable)) 
//			return "visit_occurrence.person_id";
//
//		if ("visitConcept".equals(columnVariable)) 
//			return "visit_occurrence.visit_concept_id";
//
//		if ("startDate".equals(columnVariable)) 
//			return "visit_occurrence.visit_start_date";
//
//		if ("startTime".equals(columnVariable)) 
//			return "visit_occurrence.visit_start_time";
//
//		if ("endDate".equals(columnVariable)) 
//			return "visit_occurrence.visit_end_date";
//
//		if ("endTime".equals(columnVariable)) 
//			return "visit_occurrence.visit_end_time";
//
//		if ("visitTypeConcept".equals(columnVariable)) 
//			return "visit_occurrence.visit_type_concept_id";
//
//		if ("provider".equals(columnVariable)) 
//			return "visit_occurrence.provider_id";
//
//		if ("careSite".equals(columnVariable)) 
//			return "visit_occurrence.care_site_id";
//
//		if ("visitSourceValue".equals(columnVariable)) 
//			return "visit_occurrence.visit_source_value";
//
//		if ("visitSourceConcept".equals(columnVariable)) 
//			return "visit_occurrence.visit_source_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return VisitOccurrence._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = VisitOccurrence.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "visit_occurrence";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return VisitOccurrence._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("visitConcept".equals(foreignVariable) || "visitTypeConcept".equals(foreignVariable)
				|| "visitSourceConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("careSite".equals(foreignVariable))
			return CareSite._getTableName();

		return null;
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return VisitOccurrence._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from visit_occurrence ";
	}

}
