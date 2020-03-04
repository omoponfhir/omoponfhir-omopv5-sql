package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.model.entity.ConceptRelationship;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK;

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

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.ConceptRelationshipService#findById(edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK)
	 */
	@Override
	public ConceptRelationshipPK findById(ConceptRelationshipPK conceptRelationshipPk) {
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.ConceptRelationshipService#removeById(edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK)
	 */
	@Override
	public void removeById(ConceptRelationshipPK conceptRelationshipPk) {
	}

	@Override
	public ConceptRelationshipPK findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationship> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationship> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationship> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList,
			String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationship> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptRelationshipPK create(ConceptRelationship entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptRelationshipPK update(ConceptRelationship entity) {
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
	public ConceptRelationship construct(ResultSet rs, ConceptRelationship entity, String alias) {
		return ConceptRelationshipService._construct(rs, entity, alias);
	}

}
