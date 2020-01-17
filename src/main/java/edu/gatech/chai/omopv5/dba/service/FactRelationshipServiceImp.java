package edu.gatech.chai.omopv5.dba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gatech.chai.omopv5.model.entity.BaseEntity;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FactRelationship;
import edu.gatech.chai.omopv5.model.entity.Note;

// TODO: Auto-generated Javadoc
/**
 * The Class FactRelationshipServiceImp.
 */
@Service
public class FactRelationshipServiceImp extends BaseEntityServiceImp<FactRelationship>
		implements FactRelationshipService {

	/**
	 * Instantiates a new fact relationship service imp.
	 */
	public FactRelationshipServiceImp() {
		super(FactRelationship.class);
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FactRelationshipService#searchMeasurementUsingMethod(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public <V extends BaseEntity> List<V> searchMeasurementUsingMethod(Long domainId) {
		List<V> retVal = new ArrayList<V>();

		// Concept ID:
		// 21 = Measurement, 27 = Observation,
		// 44818800 = Using finding method
		// 58 = Type Concept, 26 = Note Type
		// 44818721 = Contains
		String query = "SELECT t FROM FactRelationship t WHERE domain_concept_id_1 = 21 AND relationship_concept_id = 44818800 AND fact_id_1 = :fact1";

		return retVal;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FactRelationshipService#searchMeasurementContainsComments(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public List<Note> searchMeasurementContainsComments(Long domainId) {
		List<Note> retVal = new ArrayList<Note>();

		// 44818721 = Contains
		String query = "SELECT t FROM FactRelationship t WHERE domain_concept_id_1 = 21 AND fact_id_1 = :fact1 AND domain_concept_id_2 = 26 AND relationship_concept_id = 44818721";

		return retVal;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FactRelationshipService#searchFactRelationship(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public List<FactRelationship> searchFactRelationship(Long domainConcept1, Long factId1, Long domainConcept2,
			Long factId2, Long relationshipId) {

		String query;
		List<FactRelationship> factRelationships = null;
		if (factId2 != null) {
			query = "SELECT t FROM FactRelationship t WHERE domain_concept_id_1 = :domain1 AND fact_id_1 = :fact1 AND domain_concept_id_2 = :domain2 AND fact_id_2 = :fact2 AND relationship_concept_id = :relationship";
		} else {
			query = "SELECT t FROM FactRelationship t WHERE domain_concept_id_1 = :domain1 AND fact_id_1 = :fact1 AND domain_concept_id_2 = :domain2 AND relationship_concept_id = :relationship";
		}

		return factRelationships;
	}

	@Override
	public FactRelationship findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FactRelationship> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FactRelationship> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FactRelationship> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList,
			String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FactRelationship> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FactRelationship create(FactRelationship entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FactRelationship update(FactRelationship entity) {
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

}
