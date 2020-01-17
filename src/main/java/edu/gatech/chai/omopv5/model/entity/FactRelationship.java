package edu.gatech.chai.omopv5.model.entity;

import java.util.List;

public class FactRelationship extends BaseEntity {

	private Long domainConcept1;
	private Long factId1;
	private Long domainConcept2;
	private Long factId2;
	private Concept relationshipConcept;
	
	public Long getDomainConcept1() {
		return this.domainConcept1;
	}
	
	public void setDomainConcept1(Long domainConcept1) {
		this.domainConcept1 = domainConcept1;
	}
	
	public Long getFactId1() {
		return this.factId1;
	}
	
	public void setFactId1(Long factId1) {
		this.factId1 = factId1;
	}
	
	public Long getDomainConcept2() {
		return this.domainConcept2;
	}
	
	public void setDomainConcept2(Long domainConcept2) {
		this.domainConcept2 = domainConcept2;
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
		return this.domainConcept1;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return FactRelationship._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		if ("domainConcept1".equals(columnVariable)) 
			return "fact_relationship.domain_concept_id_1";

		if ("factId1".equals(columnVariable)) 
			return "fact_relationship.fact_id_1";

		if ("domainConcept2".equals(columnVariable)) 
			return "fact_relationship.domain_concept_id_2";

		if ("factId2".equals(columnVariable)) 
			return "fact_relationship.fact_id_2";

		if ("relationshipConcept".equals(columnVariable)) 
			return "fact_relationship.relationship_concept_id";

		return null;
	}

	@Override
	public String getTableName() {
		return FactRelationship._getTableName();
	}
	
	public static String _getTableName() {
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

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from fact_relationship ";
	}

}
