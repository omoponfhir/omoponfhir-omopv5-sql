package edu.gatech.chai.omopv5.model.entity;

import java.lang.reflect.Field;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "fact_relationship")
public class FactRelationship extends BaseEntity {
	@Column(name="domain_concept_id_1", nullable=false)
	private Long domainConceptId1;
	
	@Column(name="fact_id_1", nullable=false)
	private Long factId1;
	
	@Column(name="domain_concept_id_2", nullable=false)
	private Long domainConceptId2;
	
	@Column(name="fact_id_2", nullable=false)
	private Long factId2;
	
	@JoinColumn(name="relationship_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept relationshipConcept;
	
	public Long getDomainConceptId1() {
		return this.domainConceptId1;
	}
	
	public void setDomainConceptId1(Long domainConceptId1) {
		this.domainConceptId1 = domainConceptId1;
	}
	
	public Long getFactId1() {
		return this.factId1;
	}
	
	public void setFactId1(Long factId1) {
		this.factId1 = factId1;
	}
	
	public Long getDomainConceptId2() {
		return this.domainConceptId2;
	}
	
	public void setDomainConceptId2(Long domainConceptId2) {
		this.domainConceptId2 = domainConceptId2;
	}
	
	public Long getFactId2() {
		return this.factId2;
	}
	
	public void setFactId2(Long factId2) {
		this.factId2 = factId2;
	}
	
	public Concept getRelationshipConcept() {
		return this.relationshipConcept;
	}
	
	public void setRelationshipConcept(Concept relationshipConcept) {
		this.relationshipConcept = relationshipConcept;
	}
	
	@Override
	public Long getIdAsLong() {
		return this.domainConceptId1;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return FactRelationship._getColumnName(columnVariable);
	}
	
	@Override
  	public String getFirstColumnName() {
  		return "domain_concept_id_1";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = FactRelationship.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return FactRelationship._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return FactRelationship._getTableName() + "." + joinAnnotation.name();
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

//		if ("domainConcept1".equals(columnVariable)) 
//			return "fact_relationship.domain_concept_id_1";
//
//		if ("factId1".equals(columnVariable)) 
//			return "fact_relationship.fact_id_1";
//
//		if ("domainConcept2".equals(columnVariable)) 
//			return "fact_relationship.domain_concept_id_2";
//
//		if ("factId2".equals(columnVariable)) 
//			return "fact_relationship.fact_id_2";
//
//		if ("relationshipConcept".equals(columnVariable)) 
//			return "fact_relationship.relationship_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return FactRelationship._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = FactRelationship.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "fact_relationship";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return FactRelationship._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("relationshipConcept".equals(foreignVariable))
			return Concept._getTableName();

		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return FactRelationship._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from fact_relationship ";
	}

}
