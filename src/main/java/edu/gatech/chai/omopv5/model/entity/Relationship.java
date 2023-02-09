package edu.gatech.chai.omopv5.model.entity;

import java.lang.reflect.Field;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "relationship")
public class Relationship extends BaseEntity {
	@Id
	@Column(name="relationship_id", nullable=false)
	private String id;
	
	@Column(name="relationship_name", nullable=false)
	private String relationshipName;
	
	@Column(name="is_hierarchical", nullable=false)
	private Character isHierarchical;
	
	@Column(name="defines_ancestry", nullable=false)
	private Character definesAncestry;
	
	@Column(name="reverse_relationship_id", nullable=false)
	private String reverseRelationshipId;
	
	@JoinColumn(name="relationship_concept_id", referencedColumnName="concept_id", nullable=false)
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

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
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
	
	@Override
  	public String getFirstColumnName() {
  		return "relationship_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Relationship.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Relationship._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Relationship._getTableName() + "." + joinAnnotation.name();
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

//		if ("id".equals(columnVariable)) 
//			return "relationship.relationship_id";
//
//		if ("name".equals(columnVariable)) 
//			return "relationship.relationship_name";
//
//		if ("isHierarchical".equals(columnVariable)) 
//			return "relationship.is_hierarchical";
//
//		if ("definesAncestry".equals(columnVariable)) 
//			return "relationship.defines_ancestry";
//
//		if ("reverseRelationshipId".equals(columnVariable)) 
//			return "relationship.reverse_relationship_id";
//
//		if ("relationshipConcept".equals(columnVariable)) 
//			return "relationship.relationship_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Relationship._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Relationship.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
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

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Relationship._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from relationship ";
	}

}
