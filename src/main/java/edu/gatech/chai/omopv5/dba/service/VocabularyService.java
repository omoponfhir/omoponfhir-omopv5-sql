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
import edu.gatech.chai.omopv5.model.entity.Vocabulary;

// TODO: Auto-generated Javadoc
/**
 * The Interface VocabularyService.
 */
public interface VocabularyService extends IService<Vocabulary> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the vocabulary
	 */
	public Vocabulary findById(String id);

	/**
	 * Removes the by id.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String removeById(String id);

	public static Vocabulary _construct(ResultSet rs, Vocabulary vocabulary, String alias) {
		if (vocabulary == null)
			vocabulary = new Vocabulary();

		if (alias == null || alias.isEmpty())
			alias = Vocabulary._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_id")) {
					vocabulary.setId(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_name")) {
					vocabulary.setVocabularyName(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_reference")) {
					vocabulary.setVocabularyReference(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_version")) {
					vocabulary.setVocabularyVersion(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("vocabularyConcept_concept_id")) {
					Concept vocabularyConcept = ConceptService._construct(rs, null, "vocabularyConcept");
					vocabulary.setVocabularyConcept(vocabularyConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return vocabulary;
	}

	public static Vocabulary _construct(FieldValueList rowResult, Vocabulary vocabulary, String alias,
			List<String> columns) {
		if (vocabulary == null)
			vocabulary = new Vocabulary();

		if (alias == null || alias.isEmpty())
			alias = Vocabulary._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_id")) {
				vocabulary.setId(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_name")) {
				vocabulary.setVocabularyName(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_reference")) {
				vocabulary.setVocabularyReference(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_vocabulary_version")) {
				vocabulary.setVocabularyVersion(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("vocabularyConcept_concept_id")) {
				Concept vocabularyConcept = ConceptService._construct(rowResult, null, "vocabularyConcept", columns);
				vocabulary.setVocabularyConcept(vocabularyConcept);
			}

		}

		return vocabulary;
	}
}
