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
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "concept_ancestor")
public class ConceptAncestor extends BaseEntity {
	/**
	 * 
	 */

	@JoinColumn(name="ancestor_concept_id", referencedColumnName="concept_id", table="concept", nullable=false)
	private Concept ancestorConcept;

	@JoinColumn(name="descendant_concept_id", referencedColumnName="concept_id", table="concept", nullable=false)
	private Concept descendantConcept;
	
	@Column(name="min_levels_of_separation", nullable=false)
	private Integer minLevelsOfSeparation;
	
	@Column(name="max_levels_of_separation", nullable=false)
	private Integer maxLevelsOfSeparation;
	
	public Concept getAncestorConcept() {
		return this.ancestorConcept;
	}
	
	public Concept getDescendantConcept() {
		return this.descendantConcept;
	}
	
	public Integer getMinLevelsOfSeparation() {
		return this.minLevelsOfSeparation;
	}
	
	public Integer getMaxLevelsOfSeparation() {
		return this.maxLevelsOfSeparation;
	}
	
	@Override
	public Long getIdAsLong() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return ConceptAncestor._getColumnName(columnVariable);
	}
	
	@Override
  	public String getFirstColumnName() {
  		return "ancestor_concept_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = ConceptAncestor.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return ConceptAncestor._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return ConceptAncestor._getTableName() + "." + joinAnnotation.name();
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

//		if ("ancestorConcept".equals(columnVariable)) 
//			return "concept_ancestor.ancestor_concept_id";
//
//		if ("descendantConcept".equals(columnVariable)) 
//			return "concept_ancestor.descendant_concept_id";
//
//		if ("minLevelsOfSeparation".equals(columnVariable)) 
//			return "concept_ancestor.min_levels_of_separation";
//
//
//		if ("maxLevelsOfSeparation".equals(columnVariable)) 
//			return "concept_ancestor.max_levels_of_separation";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return ConceptAncestor._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = ConceptAncestor.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "concept_ancestor";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return ConceptAncestor._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("ancestorConcept".equals(foreignVariable) || "descendantConcept".equals(foreignVariable)) 
			return Concept._getTableName();
		
		return null;
	}

	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return ConceptAncestor._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from concept_ancestor ";
	}

}
