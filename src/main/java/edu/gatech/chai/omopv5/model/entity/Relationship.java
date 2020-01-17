package edu.gatech.chai.omopv5.model.entity;

import java.util.List;

public class Relationship extends BaseEntity {
	private String id;
	private String name;
	private Character isHierarchical;
	private Character definesAncestry;
	private String reverseRelationshipId;
	private Concept relationshipConcept;
	
	public Relationship() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getIsHierarchical() {
		return isHierarchical;
	}
	
	public void setIsHierarchical(Character isHierarchical) {
		this.isHierarchical = isHierarchical;
	}

	public Character getDefinesAncestry(){
		return definesAncestry;
	}
	public void setDefinesAncestry(Character definesAncestry) {
		this.definesAncestry = definesAncestry;
	}

	public String getReverseRelationshipId() {
		return reverseRelationshipId;
	}
	
	public void setReverseRelationshipId(String reverseRelationshipId) {
		this.reverseRelationshipId = reverseRelationshipId;
	}
	
	public Concept getRelationshipConcept() {
		return relationshipConcept;
	}
	
	public void setRelationshipConcept(Concept relationshipConcept) {
		this.relationshipConcept = relationshipConcept;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Relationship._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		if ("id".equals(columnVariable)) 
			return "relationship.relationship_id";

		if ("name".equals(columnVariable)) 
			return "relationship.relationship_name";

		if ("isHierarchical".equals(columnVariable)) 
			return "relationship.is_hierarchical";

		if ("definesAncestry".equals(columnVariable)) 
			return "relationship.defines_ancestry";

		if ("reverseRelationshipId".equals(columnVariable)) 
			return "relationship.reverse_relationship_id";

		if ("relationshipConcept".equals(columnVariable)) 
			return "relationship.relationship_concept_id";

		return null;
	}

	@Override
	public String getTableName() {
		return Relationship._getTableName();
	}
	
	public static String _getTableName() {
		return "relationship";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Relationship._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("relationshipConcept".equals(foreignVariable))
			return Concept._getTableName();

		return null;
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from relationship ";
	}

}
