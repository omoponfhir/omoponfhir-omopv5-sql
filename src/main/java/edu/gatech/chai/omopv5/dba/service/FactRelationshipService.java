package edu.gatech.chai.omopv5.dba.service;

import java.util.List;

import edu.gatech.chai.omopv5.model.entity.BaseEntity;
import edu.gatech.chai.omopv5.model.entity.FactRelationship;
import edu.gatech.chai.omopv5.model.entity.Note;

// TODO: Auto-generated Javadoc
/**
 * The Interface FactRelationshipService.
 */
public interface FactRelationshipService extends IService<FactRelationship> {
	
	/**
	 * Search measurement using method.
	 *
	 * @param <V> the value type
	 * @param conceptId the concept id
	 * @return the list
	 */
	public <V extends BaseEntity> List<V> searchMeasurementUsingMethod(Long conceptId);

	/**
	 * Search measurement contains comments.
	 *
	 * @param conceptId the concept id
	 * @return the list
	 */
	public List<Note> searchMeasurementContainsComments(Long conceptId);

	/**
	 * Search fact relationship.
	 *
	 * @param domainConcept1 the domain concept 1
	 * @param factId1 the fact id 1
	 * @param domainConcept2 the domain concept 2
	 * @param factId2 the fact id 2
	 * @param relationshipId the relationship id
	 * @return the list
	 */
	public List<FactRelationship> searchFactRelationship(Long domainConcept1, Long factId1, Long domainConcept2,
			Long factId2, Long relationshipId);
}
