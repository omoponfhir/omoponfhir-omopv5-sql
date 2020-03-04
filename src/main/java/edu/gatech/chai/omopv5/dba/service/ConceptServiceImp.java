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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.ConceptService#getIngredient(edu.gatech.chai.omopv5.model.entity.Concept)
	 */
	@Transactional(readOnly = true)
	public List<Concept> getIngredient(Concept concept) {
		
		List<Concept> concepts = new ArrayList<Concept>();
		
		if ("Ingredient".equals(concept.getConceptClass())) {
			// This is ingredient. Just return empty list
			return concepts;
		}
		
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		
		String sql = null;
		if ("NDC".equals(concept.getVocabulary())) {
			// Use JPQL
			sql = "select c "
					+ "FROM Concept src "
					+ "JOIN ConceptRelationship cr on src.id = cr.conceptId1 "
			        + "AND cr.relationship_id = 'Maps to' "
			        + "AND cr.invalid_reason is null "
			        + "JOIN Concept tar on cr.conceptId2 = tar.id "
			        + "AND tar.standardConcept = 'S' "
			        + "AND tar.invalidReason is null "
			        + "JOIN ConceptAncestor ca ON ca.ancestorConcept = tar.id "
			        + "JOIN Concept c ON ca.ancestorConcept = c.id "
			        + "WHERE src.conceptCode = :med_code "
			        + "AND 'NDC' = src.vocabulary "
			        + "AND c.vocabulary = 'RxNorm' "
			        + "AND c.conceptClass = 'Ingredient' "
			        + "AND src.invalidReason is null";
		} else if ("RxNorm".equals(concept.getVocabulary())) {
			// when RxNorm.
			sql = "select c "
					+ "FROM Concept src " 
					+ "JOIN ConceptAncestor ca ON ca.descendantConcept = src.id "
					+ "JOIN Concept c ON ca.ancestorConcept = c.id "
					+ "WHERE src.conceptCode = :med_code "
					+ "AND 'RxNorm' = src.vocabulary "
					+ "AND c.vocabulary = 'RxNorm' "
					+ "AND c.conceptClass = 'Ingredient' "
					+ "AND src.invalidReason is null "
					+ "AND c.invalidReason is null";
		} else {
			return concepts;
		}
		
		sql = renderedSql(sql, parameterList, valueList);

		System.out.println(sql);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(sql);

			while (rs.next()) {
				Concept entity = ConceptService._construct(rs, null, "c");
				if (entity != null) {
					concepts.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return concepts;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.ConceptService#getLargestId()
	 */
	@Override
	public Long getLargestId() {
		return null;
	}

	@Override
	public Concept findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Concept> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Concept> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Concept> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Concept> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concept create(Concept entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concept update(Concept entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSize(List<ParameterWrapper> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concept construct(ResultSet rs, Concept entity, String alias) {
		return ConceptService._construct(rs, entity, alias);
	}

}
