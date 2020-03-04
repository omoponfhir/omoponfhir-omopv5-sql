package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;

import edu.gatech.chai.omopv5.model.entity.ConceptRelationship;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConceptRelationshipService.
 */
public interface ConceptRelationshipService extends IService<ConceptRelationship> {

	/**
	 * Find by id.
	 *
	 * @param conceptRelationshipPk the concept relationship pk
	 * @return the concept relationship
	 */
	public ConceptRelationshipPK findById(ConceptRelationshipPK conceptRelationshipPk);

	/**
	 * Removes the by id.
	 *
	 * @param conceptRelationshipPk the concept relationship pk
	 */
	public void removeById(ConceptRelationshipPK conceptRelationshipPk);

	public static ConceptRelationship _construct(ResultSet rs, ConceptRelationship conceptRelationship, String alias) {
		if (conceptRelationship == null)
			conceptRelationship = new ConceptRelationship();

		if (alias == null || alias.isEmpty())
			alias = ConceptRelationship._getTableName();

		return conceptRelationship;
	}
}
