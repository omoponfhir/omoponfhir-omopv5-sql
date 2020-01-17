package edu.gatech.chai.omopv5.dba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceptRelationshipServiceImp.
 */
@Service
public class ConceptRelationshipServiceImp extends BaseEntityServiceImp<ConceptRelationshipPK>
		implements ConceptRelationshipService {

	/**
	 * Instantiates a new concept relationship service imp.
	 */
	public ConceptRelationshipServiceImp() {
		super(ConceptRelationshipPK.class);
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
	public List<ConceptRelationshipPK> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationshipPK> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationshipPK> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList,
			String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptRelationshipPK> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptRelationshipPK create(ConceptRelationshipPK entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptRelationshipPK update(ConceptRelationshipPK entity) {
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
