package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationship;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceptRelationshipServiceImp.
 */
@Service
public class ConceptRelationshipServiceImp extends BaseEntityServiceImp<ConceptRelationship>
		implements ConceptRelationshipService {

	/**
	 * Instantiates a new concept relationship service imp.
	 */
	public ConceptRelationshipServiceImp() {
		super(ConceptRelationship.class);
	}

	@Override
	public ConceptRelationship findById(Long conceptId1) {
		return null;
	}

	@Override
	public ConceptRelationship find(Concept concept1, Concept concept2, String reationshipId) throws Exception {
		List<ParameterWrapper> paramList = new ArrayList<ParameterWrapper>();

		ParameterWrapper parameterWrapper = new ParameterWrapper();
		parameterWrapper.setParameterType("Long");
		parameterWrapper.setParameters(Arrays.asList("concept1.id", "concept2.id"));
		parameterWrapper.setOperators(Arrays.asList("=", "="));
		parameterWrapper.setValues(Arrays.asList(String.valueOf(concept1.getId()), String.valueOf(concept2.getId())));
		parameterWrapper.setRelationship("and");
		paramList.add(parameterWrapper);

		ParameterWrapper parameterWrapper2 = new ParameterWrapper();
		parameterWrapper2.setParameterType("String");
		parameterWrapper2.setParameters(Arrays.asList("relationshipId"));
		parameterWrapper2.setOperators(Arrays.asList("="));
		parameterWrapper2.setValues(Arrays.asList(reationshipId));
		parameterWrapper2.setRelationship("and");
		paramList.add(parameterWrapper2);

		List<ConceptRelationship> conceptRelationships = searchWithParams(0, 0, paramList, null);
		if (conceptRelationships != null && !conceptRelationships.isEmpty()) {
			return conceptRelationships.get(0);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.ConceptRelationshipService#removeById(edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK)
	 */
	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptRelationship update(ConceptRelationship entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptRelationship construct(ResultSet rs, ConceptRelationship entity, String alias) throws SQLException {
		return ConceptRelationshipService._construct(rs, entity, alias);
	}

	@Override
	public ConceptRelationship construct(FieldValueList rowResult, ConceptRelationship entity, String alias,
			List<String> columns) {
		return ConceptRelationshipService._construct(rowResult, entity, alias, columns);
	}

}