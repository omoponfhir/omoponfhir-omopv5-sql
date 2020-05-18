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

@Table(name = "visit_detail")
public class VisitDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_detail_id_seq")
	@Column(name = "visit_detail_id", nullable = false)
	private Long id;

	@JoinColumn(name = "person_id", table = "f_person:fPerson,person:person", nullable = false)
	private FPerson fPerson;

	@JoinColumn(name = "visit_detail_concept_id", referencedColumnName = "concept_id", nullable = false)
	private Concept visitDetailConcept;

	@Column(name = "visit_detail_start_date", nullable = false)
	private Date visitDetailStartDate;

	@Column(name = "visit_detail_start_datetime")
	private String visitDetailStartDateTime;

	@Column(name = "visit_detail_end_date", nullable = false)
	private Date visitDetailEndDate;

	@Column(name = "visit_detail_end_datetime")
	private String visitDetailEndDateTime;

	@JoinColumn(name = "visit_detail_type_concept_id", referencedColumnName = "concept_id", nullable = false)
	private Concept visitDetailTypeConcept;

	@JoinColumn(name = "provider_id")
	private Provider provider;

	@JoinColumn(name = "care_site_id")
	private CareSite careSite; // FIXME field names should reflect fhir names, for validation purposes.

	@JoinColumn(name = "admitting_source_concept_id", referencedColumnName = "concept_id")
	private Concept admittingSourceConcept;

	@JoinColumn(name = "discharge_to_concept_id", referencedColumnName = "concept_id")
	private Concept dischargeToConcept;

	@JoinColumn(name = "preceding_visit_detail_id")
	private VisitDetail precedingVisitDetail;

	@Column(name = "visit_detail_source_value")
	private String visitDetailSourceValue;

	@JoinColumn(name = "visit_detail_source_concept_id", referencedColumnName = "concept_id")
	private Concept visitDetailSourceConcept;

	@Column(name = "admitting_source_value")
	private String admittingSourceValue;

	@Column(name = "discharge_to_source_value")
	private String dischargeToSourceValue;

	@JoinColumn(name = "visit_detail_parent_id")
	private VisitDetail visitDetailParent;

	@JoinColumn(name = "visit_occurrence_id", nullable = false)
	private VisitOccurrence visitOccurrence;

	public VisitDetail() {
	}

	public VisitDetail(Long id) {
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

	public Concept getVisitDetailConcept() {
		return visitDetailConcept;
	}

	public void setVisitDetailConcept(Concept visitDetailConcept) {
		this.visitDetailConcept = visitDetailConcept;
	}

	public Date getVisitDetailStartDate() {
		return visitDetailStartDate;
	}

	public void setVisitDetailStartDate(Date visitDetailStartDate) {
		this.visitDetailStartDate = visitDetailStartDate;
	}

	public String getVisitDetailStartDateTime() {
		return visitDetailStartDateTime;
	}

	public void setVisitDetailStartDateTime(String visitDetailStartDateTime) {
		this.visitDetailStartDateTime = visitDetailStartDateTime;
	}

	public Date getVisitDetailEndDate() {
		return visitDetailEndDate;
	}

	public void setVisitDetailEndDate(Date visitDetailEndDate) {
		this.visitDetailEndDate = visitDetailEndDate;
	}

	public String getVisitDetailEndDateTime() {
		return visitDetailEndDateTime;
	}

	public void setVisitDetailEndDateTime(String visitDetailEndDateTime) {
		this.visitDetailEndDateTime = visitDetailEndDateTime;
	}

	public Concept getVisitDetailTypeConcept() {
		return visitDetailTypeConcept;
	}

	public void setVisitDetailTypeConcept(Concept visitDetailTypeConcept) {
		this.visitDetailTypeConcept = visitDetailTypeConcept;
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

	public Concept getAdmittingSourceConcept() {
		return admittingSourceConcept;
	}

	public void setAdmittingSourceConcept(Concept admittingSourceConcept) {
		this.admittingSourceConcept = admittingSourceConcept;
	}

	public Concept getDischargeToConcept() {
		return dischargeToConcept;
	}

	public void setDischargeToConcept(Concept dischargeToConcept) {
		this.dischargeToConcept = dischargeToConcept;
	}

	public VisitDetail getPrecedingVisitDetail() {
		return precedingVisitDetail;
	}

	public void setPrecedingVisitDetail(VisitDetail precedingVisitDetail) {
		this.precedingVisitDetail = precedingVisitDetail;
	}

	public String getVisitDetailSourceValue() {
		return visitDetailSourceValue;
	}

	public void setVisitDetailSourceValue(String visitDetailSourceValue) {
		this.visitDetailSourceValue = visitDetailSourceValue;
	}

	public Concept getVisitDetailSourceConcept() {
		return visitDetailSourceConcept;
	}

	public void setVisitDetailSourceConcept(Concept visitDetailSourceConcept) {
		this.visitDetailSourceConcept = visitDetailSourceConcept;
	}

	public String getAdmittingSourceValue() {
		return admittingSourceValue;
	}

	public void setAdmittingSourceValue(String admittingSourceValue) {
		this.admittingSourceValue = admittingSourceValue;
	}

	public String getDischargeToSourceValue() {
		return dischargeToSourceValue;
	}

	public void setDischargeToSourceValue(String dischargeToSourceValue) {
		this.dischargeToSourceValue = dischargeToSourceValue;
	}

	public VisitDetail getVisitDetailParent() {
		return visitDetailParent;
	}

	public void setVisitDetailParent(VisitDetail visitDetailParent) {
		this.visitDetailParent = visitDetailParent;
	}

	public VisitOccurrence getVisitOccurrence() {
		return visitOccurrence;
	}

	public void setVisitOccurrence(VisitOccurrence visitOccurrence) {
		this.visitOccurrence = visitOccurrence;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return VisitDetail._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		try {
			Field field = VisitDetail.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return VisitDetail._getTableName() + "." + annotation.name();
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
	}

	@Override
	public String getTableName() {
		return VisitDetail._getTableName();
	}

	public static String _getTableName() {
		Table annotation = VisitDetail.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "visit_detail";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		if ("visitDetailConcept".equals(foreignVariable) 
				|| "visitDetailTypeConcept".equals(foreignVariable)
				|| "admittingSourceConcept".equals(foreignVariable)
				|| "dischargeToConcept".equals(foreignVariable)
				|| "visitDetailSourceConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("careSite".equals(foreignVariable))
			return CareSite._getTableName();
		
		if ("precedingVisitDetail".equals(foreignVariable)
				|| "visitDetailParent".equals(foreignVariable))
			return VisitDetail._getTableName();

		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return VisitDetail._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from visit_detail ";
	}

}
