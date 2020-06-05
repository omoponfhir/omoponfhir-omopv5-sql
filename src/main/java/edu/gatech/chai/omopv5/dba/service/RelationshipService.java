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
package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.Relationship;

// TODO: Auto-generated Javadoc
/**
 * The Interface RelationshipService.
 */
public interface RelationshipService extends IService<Relationship> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the relationship
	 */
	public Relationship findById(String id);

	/**
	 * Removes the by id.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String removeById(String id);

	public static Relationship _construct(ResultSet rs, Relationship relationship, String alias) {
		if (relationship == null)
			relationship = new Relationship();

		if (alias == null || alias.isEmpty())
			alias = Relationship._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_relationship_id")) {
					relationship.setId(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_relationship_name")) {
					relationship.setRelationshipName(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_is_hierarchical")) {
					String value = rs.getString(columnInfo);
					if (value != null) {
						relationship.setIsHierarchical(value.charAt(0));
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_defines_ancestry")) {
					String value = rs.getString(columnInfo);
					if (value != null) {
						relationship.setDefinesAncestry(value.charAt(0));
					}
				} else if (columnInfo.equalsIgnoreCase(alias + "_reverse_relationship_id")) {
					relationship.setReverseRelationshipId(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("relationshipConcept_concept_id")) {
					Concept relationshipConcept = ConceptService._construct(rs, null, "relationshipConcept");
					relationship.setRelationshipConcept(relationshipConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return relationship;
	}

	public static Relationship _construct(FieldValueList rowResult, Relationship relationship, String alias,
			List<String> columns) {
		if (relationship == null)
			relationship = new Relationship();

		if (alias == null || alias.isEmpty())
			alias = Relationship._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_relationship_id")) {
				relationship.setId(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_relationship_name")) {
				relationship.setRelationshipName(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_is_hierarchical")) {
				String value = rowResult.get(columnInfo).getStringValue();
				if (value != null) {
					relationship.setIsHierarchical(value.charAt(0));
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_defines_ancestry")) {
				String value = rowResult.get(columnInfo).getStringValue();
				if (value != null) {
					relationship.setDefinesAncestry(value.charAt(0));
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_reverse_relationship_id")) {
				relationship.setReverseRelationshipId(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("relationshipConcept_concept_id")) {
				Concept relationshipConcept = ConceptService._construct(rowResult, null, "relationshipConcept", columns);
				relationship.setRelationshipConcept(relationshipConcept);
			}

		}

		return relationship;
	}
}
