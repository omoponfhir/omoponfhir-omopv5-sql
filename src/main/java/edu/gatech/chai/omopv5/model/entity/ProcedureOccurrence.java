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

@Table(name = "procedure_occurrence")
public class ProcedureOccurrence extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="procedure_occurrence_id_seq")
	@Column(name="procedure_occurrence_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@JoinColumn(name="procedure_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept procedureConcept;
	
	@Column(name="procedure_date", nullable=false)
	private Date procedureDate;
	
	@Column(name="procedure_datetime")
	private Date procedureDateTime;
	
	@JoinColumn(name="procedure_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept procedureTypeConcept;
	
	@JoinColumn(name="modifier_concept_id", referencedColumnName="concept_id")
	private Concept modifierConcept;
	
	@Column(name="quantity")
	private Long quantity;
	
	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@JoinColumn(name="visit_occurrence_id")
	private VisitOccurrence visitOccurrence;
	
	@Column(name="procedure_source_value")
	private String procedureSourceValue;
	
	@JoinColumn(name="procedure_source_concept_id", referencedColumnName="concept_id")
	private Concept procedureSourceConcept;
	
	@Column(name="modifier_source_value")
	private String modifierSourceValue;

	public ProcedureOccurrence() {
		super();
	}

	public ProcedureOccurrence(Long id) {
		super();
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

	public void setFPerson(FPerson fPerson) {
		this.fPerson = fPerson;
	}

	public Concept getProcedureConcept() {
		return procedureConcept;
	}

	public void setProcedureConcept(Concept procedureConcept) {
		this.procedureConcept = procedureConcept;
	}

	public Date getProcedureDate() {
		return procedureDate;
	}

	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}

	public Date getProcedureDateTime() {
		return procedureDateTime;
	}

	public void setProcedureDateTime(Date procedureDateTime) {
		this.procedureDateTime = procedureDateTime;
	}

	public Concept getProcedureTypeConcept() {
		return procedureTypeConcept;
	}

	public void setProcedureTypeConcept(Concept procedureTypeConcept) {
		this.procedureTypeConcept = procedureTypeConcept;
	}

	public Concept getModifierConcept() {
		return modifierConcept;
	}
	
	public void setModifierConcept(Concept modifierConcept) {
		this.modifierConcept = modifierConcept;
	}
	
	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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

	public String getProcedureSourceValue() {
		return procedureSourceValue;
	}

	public void setProcedureSourceValue(String procedureSourceValue) {
		this.procedureSourceValue = procedureSourceValue;
	}

	public Concept getProcedureSourceConcept() {
		return procedureSourceConcept;
	}

	public void setProcedureSourceConcept(Concept procedureSourceConcept) {
		this.procedureSourceConcept = procedureSourceConcept;
	}

	public String getModifierSourceValue() {
		return modifierSourceValue;
	}
	
	public void setModifierSourceValue(String modifierSourceValue) {
		this.modifierSourceValue = modifierSourceValue;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return ProcedureOccurrence._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = ProcedureOccurrence.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return ProcedureOccurrence._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return ProcedureOccurrence._getTableName() + "." + joinAnnotation.name();
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
		return ProcedureOccurrence._getTableName();
	}
	
	@Override
  	public String getFirstColumnName() {
  		return "procedure_occurrence_id";
  	}

	public static String _getTableName() {
		Table annotation = ProcedureOccurrence.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "procedure_occurrence";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return ProcedureOccurrence._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("procedureConcept".equals(foreignVariable) 
				|| "procedureTypeConcept".equals(foreignVariable)
				|| "modifierConcept".equals(foreignVariable) 
				|| "procedureSourceConcept".equals(foreignVariable))
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
		return ProcedureOccurrence._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from procedure_occurrence ";
	}

}
