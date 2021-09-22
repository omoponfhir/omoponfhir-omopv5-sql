package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.BaseEntity;
import edu.gatech.chai.omopv5.model.entity.Concept;
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
	 * @param <V>       the value type
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
	 * @param factId1        the fact id 1
	 * @param domainConcept2 the domain concept 2
	 * @param factId2        the fact id 2
	 * @param relationshipId the relationship id
	 * @return the list
	 */
	public List<FactRelationship> searchFactRelationship(Long domainConcept1, Long factId1, Long domainConcept2,
			Long factId2, Long relationshipId);

	public static FactRelationship _construct(ResultSet rs, FactRelationship factRelationship, String alias) {
		if (factRelationship == null)
			factRelationship = new FactRelationship();

		if (alias == null || alias.isEmpty())
			alias = FactRelationship._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_domain_concept_id_1")) {
					factRelationship.setDomainConceptId1(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_fact_id_1")) {
					factRelationship.setFactId1(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_domain_concept_id_2")) {
					factRelationship.setDomainConceptId2(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_fact_id_2")) {
					factRelationship.setFactId2(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("relationshipConcept_concept_id")) {
					Concept relationshipConcept = ConceptService._construct(rs, null, "relationshipConcept");
					factRelationship.setRelationshipConcept(relationshipConcept);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return factRelationship;
	}

	public static FactRelationship _construct(FieldValueList rowResult, FactRelationship factRelationship, String alias,
			List<String> columns) {
		if (factRelationship == null)
			factRelationship = new FactRelationship();

		if (alias == null || alias.isEmpty())
			alias = FactRelationship._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_domain_concept_id_1")) {
				factRelationship.setDomainConceptId1(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_fact_id_1")) {
				factRelationship.setFactId1(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_domain_concept_id_2")) {
				factRelationship.setDomainConceptId2(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_fact_id_2")) {
				factRelationship.setFactId2(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("relationshipConcept_concept_id")) {
				Concept relationshipConcept = ConceptService._construct(rowResult, null, "relationshipConcept", columns);
				factRelationship.setRelationshipConcept(relationshipConcept);
			}

		}

		return factRelationship;
	}

}
