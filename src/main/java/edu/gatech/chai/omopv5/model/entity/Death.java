package edu.gatech.chai.omopv5.model.entity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "death")
public class Death extends BaseEntity {
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;

	@Column(name="death_date", nullable=false)
	private Date deathDate;

	@Column(name="death_datetime")
	private Date deathDateTime;

	@JoinColumn(name="death_type_concept_id", referencedColumnName="concept_id", table="concept", nullable=false)
	private Concept deathTypeConcept;

	@JoinColumn(name="cause_concept_id", referencedColumnName="concept_id", table="concept")
	private Concept causeConcept;

	@Column(name="cause_source_value")
	private String causeSourceValue;

	@JoinColumn(name="cause_source_concept_id", referencedColumnName="concept_id", table="concept")
	private Concept causeSourceConcept;

	public FPerson getFPerson() {
		return fPerson;
	}

	public void setFPerson(FPerson fPerson) {
		this.fPerson = fPerson;
	}
	
	public Date getDeathDate() {
		return deathDate;
	}
	
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public Date getDeathDateTime() {
		return deathDateTime;
	}
	
	public void setDeathDateTime(Date deathDateTime) {
		this.deathDateTime = deathDateTime;
	}
	
	public Concept getDeathTypeConcept() {
		return deathTypeConcept;
	}
	
	public void setDeathTypeConcept(Concept deathTypeConcept) {
		this.deathTypeConcept = deathTypeConcept;
	}
	
	public Concept getCauseConcept() {
		return causeConcept;
	}
	
	public void setCauseConcept(Concept causeConcept) {
		this.causeConcept = causeConcept;
	}
	
	public String getCauseSourceValue() {
		return causeSourceValue;
	}
	
	public void setCauseSourceValue(String causeSourceValue) {
		this.causeSourceValue = causeSourceValue;
	}
	
	public Concept getCauseSourceConcept() {
		return causeSourceConcept;
	}
	
	public void setCauseSourceConcept(Concept causeSourceConcept) {
		this.causeSourceConcept = causeSourceConcept;
	}
	
	@Override
	public String getColumnName(String columnVariable) {
		return Death._getColumnName(columnVariable);
	}

	@Override
  	public String getFirstColumnName() {
  		return "person_id";
  	}

	public static String _getColumnName(String columnVariable) {

		try {
			Field field = Death.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Death._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Death._getTableName() + "." + joinAnnotation.name();
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
		return Death._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Death.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "death";
	}


	@Override
	public String getForeignTableName(String foreignVariable) {
		return Death._getForeignTableName(foreignVariable);
	}

	public static String _getForeignTableName(String foreignVariable) {
		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();
		
		if ("deathTypeConcept".equals(foreignVariable) 
				|| "causeConcept".equals(foreignVariable)
				|| "causeSourceConcept".equals(foreignVariable))
			return Concept._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Death._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from death ";
	}

}
