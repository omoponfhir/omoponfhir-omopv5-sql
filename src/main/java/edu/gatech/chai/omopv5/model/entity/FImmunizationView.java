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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name="f_immunization_view")
public class FImmunizationView extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(FImmunizationView.class);

    @Id
	@Column(name="immunization_id")
	private Long id;

	@JoinColumn(name = "person_id", nullable = false)
	private FPerson fPerson;

	@JoinColumn(name = "immunization_concept_id", referencedColumnName="concept_id", nullable = false)
	private Concept immunizationConcept;

	@Column(name = "immunization_date", nullable = false)
	private Date immunizationDate;

	@Column(name = "immunization_datetime")
	private Date immunizationDatetime;

	@JoinColumn(name = "immunization_type_concept_id", referencedColumnName="concept_id", nullable = false)
	private Concept immunizationTypeConcept;

	@Column(name = "immunization_status")
	private String immunizationStatus;
	
	@JoinColumn(name = "provider_id")
	private Provider provider;

	@JoinColumn(name = "visit_occurrence_id")
	private VisitOccurrence visitOccurrence;

	@Column(name = "lot_number")
	private String lotNumber;

	@JoinColumn(name = "route_concept_id", referencedColumnName="concept_id")
	private Concept routeConcept;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "immunization_note")
	private String immunizationNote;

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

	public Concept getImmunizationConcept() {
		return immunizationConcept;
	}

	public void setImmunizationConcept(Concept immunizationConcept) {
		this.immunizationConcept = immunizationConcept;
	}

	public Date getImmunizationDate() {
		return immunizationDate;
	}

	public void setImmunizationDate(Date immunizationDate) {
		this.immunizationDate = immunizationDate;
	}

	public Date getImmunizationDatetime() {
		return immunizationDatetime;
	}

	public void setImmunizationDatetime(Date immunizationDatetime) {
		this.immunizationDatetime = immunizationDatetime;
	}

	public Concept getImmunizationTypeConcept() {
		return immunizationTypeConcept;
	}

	public void setImmunizationTypeConcept(Concept immunizationTypeConcept) {
		this.immunizationTypeConcept = immunizationTypeConcept;
	}

	public String getImmunizationStatus () {
		return immunizationStatus;
	}
	
	public void setImmunizationStatus (String immunizationStatus) {
		this.immunizationStatus = immunizationStatus;
	}
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public VisitOccurrence getVisitOccurrence() {
		return visitOccurrence;
	}

	public void setVisitOccurrence(VisitOccurrence visitOccurrence) {
		this.visitOccurrence = visitOccurrence;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public Concept getRouteConcept () {
		return routeConcept;
	}
	
	public void setRouteConcept (Concept routeConcept) {
		this.routeConcept = routeConcept;
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getImmunizationNote() {
		return immunizationNote;
	}

	public void setImmunizationNote(String immunizationNote) {
		this.immunizationNote = immunizationNote;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

    @Override
    public String getColumnName(String columnVariable) {
		return FImmunizationView._getColumnName(columnVariable);
    }

	@Override
  	public String getFirstColumnName() {
  		return "immunization_id";
  	}

    public static String _getColumnName(String columnVariable) {
		try {
			Field field = FImmunizationView.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return FImmunizationView._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return FImmunizationView._getTableName() + "." + joinAnnotation.name();
					}

					logger.error("ERROR: annotation is null for field=" + field.toString());
					return null;
				}
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getTableName() {
		return FImmunizationView._getTableName();
	}

	public static String _getTableName() {
		Table annotation = FImmunizationView.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "f_observation_view";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return FImmunizationView._getForeignTableName(foreignVariable);
	}

	public static String _getForeignTableName(String foreignVariable) {
		if ("immunizationConcept".equals(foreignVariable) 
				|| "immunizationTypeConcept".equals(foreignVariable) 
				|| "routeConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return FImmunizationView._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from f_immunization_view ";
	}
}