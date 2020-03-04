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

import edu.gatech.chai.omopv5.model.entity.Concept;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConceptService.
 */
public interface ConceptService extends IService<Concept> {

	/**
	 * Gets the ingredient.
	 *
	 * @param concept the concept
	 * @return the ingredient
	 */
	public List<Concept> getIngredient(Concept concept);

	/**
	 * Gets the largest id.
	 *
	 * @return the largest id
	 */
	public Long getLargestId();

	public static Concept _construct(ResultSet rs, Concept concept, String alias) {
		if (concept == null)
			concept = new Concept();

		if (alias == null || alias.isEmpty())
			alias = Concept._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_concept_id")) {
					concept.setId(rs.getLong(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_concept_name")) {
					concept.setName(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_domain_id")) {
					concept.setDomain(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_concept_class_id")) {
					concept.setConceptClass(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_standard_concept")) {
					String res = rs.getString(columnInfo);
					if (res != null) {
						concept.setStandardConcept(res.charAt(0));
					} else {
						concept.setStandardConcept(null);
					}
				}

				if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_id")) {
					concept.setVocabulary(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_concept_code")) {
					concept.setConceptCode(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_valid_start_date")) {
					concept.setValidStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "valid_end_date")) {
					concept.setValidEndDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "invalid_reason")) {
					concept.setInvalidReason(rs.getString(columnInfo));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return concept;
	}
}
