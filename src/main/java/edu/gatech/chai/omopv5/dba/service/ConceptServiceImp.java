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
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.Concept;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceptServiceImp.
 */
@Service
public class ConceptServiceImp extends BaseEntityServiceImp<Concept> implements ConceptService {

	/**
	 * Instantiates a new concept service imp.
	 */
	public ConceptServiceImp() {
		super(Concept.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.gatech.chai.omopv5.dba.service.ConceptService#getIngredient(edu.gatech.
	 * chai.omopv5.model.entity.Concept)
	 */
	public List<Concept> getIngredient(Concept concept) {

		List<Concept> concepts = new ArrayList<Concept>();

		if ("Ingredient".equals(concept.getConceptClassId())) {
			// This is ingredient. Just return empty list
			return concepts;
		}

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = null;
		if ("NDC".equals(concept.getVocabularyId())) {
			// Use JPQL
			sql = "select c " + "FROM Concept src " + "JOIN ConceptRelationship cr on src.id = cr.conceptId1 "
					+ "AND cr.relationship_id = 'Maps to' " + "AND cr.invalid_reason is null "
					+ "JOIN Concept tar on cr.conceptId2 = tar.id " + "AND tar.standardConcept = 'S' "
					+ "AND tar.invalidReason is null " + "JOIN ConceptAncestor ca ON ca.ancestorConcept = tar.id "
					+ "JOIN Concept c ON ca.ancestorConcept = c.id " + "WHERE src.conceptCode = :med_code "
					+ "AND 'NDC' = src.vocabulary " + "AND c.vocabulary = 'RxNorm' "
					+ "AND c.conceptClass = 'Ingredient' " + "AND src.invalidReason is null";
		} else if ("RxNorm".equals(concept.getVocabularyId())) {
			// when RxNorm.
			sql = "select c " + "FROM Concept src " + "JOIN ConceptAncestor ca ON ca.descendantConcept = src.id "
					+ "JOIN Concept c ON ca.ancestorConcept = c.id " + "WHERE src.conceptCode = :med_code "
					+ "AND 'RxNorm' = src.vocabulary " + "AND c.vocabulary = 'RxNorm' "
					+ "AND c.conceptClass = 'Ingredient' " + "AND src.invalidReason is null "
					+ "AND c.invalidReason is null";
		} else {
			return concepts;
		}

		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);

		Concept entity;
		try {
			if (getQueryEntityDao().isBigQuery()) {
				TableResult result = getQueryEntityDao().runBigQuery(sql);
				List<String> columns = listOfColumns(sql);
//				System.out.println("++++++++++++++++++++++++++++++++++++++++");
//				System.out.println(sql);
//				System.out.println("++++++++++++++++++++++++++++++++++++++++");
//				for (String column : columns) {
//					System.out.println(column);
//				}
				for (FieldValueList row : result.iterateAll()) {
					entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						break;
					}
				}
			} else {

				ResultSet rs = getQueryEntityDao().runQuery(sql);

				while (rs.next()) {
					entity = ConceptService._construct(rs, null, "c");
					if (entity != null) {
						concepts.add(entity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return concepts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.gatech.chai.omopv5.dba.service.ConceptService#getLargestId()
	 */
	@Override
	public Long getLargestId() {
		return null;
	}

	@Override
	public Concept update(Concept entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concept construct(ResultSet rs, Concept entity, String alias) {
		return ConceptService._construct(rs, entity, alias);
	}

	@Override
	public Concept construct(FieldValueList rowResult, Concept entity, String alias, List<String> columns) {
		return ConceptService._construct(rowResult, entity, alias, columns);
	}

}
