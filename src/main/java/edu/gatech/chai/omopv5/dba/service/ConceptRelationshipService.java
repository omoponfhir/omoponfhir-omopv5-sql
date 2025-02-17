package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationship;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConceptRelationshipService.
 */
public interface ConceptRelationshipService extends IService<ConceptRelationship> {

	/**
	 * Find by id.
	 *
	 * @param conceptId1 the concept relationship pk
	 * @return the concept relationship
	 */
	public ConceptRelationship findById(Long conceptId1);

	public ConceptRelationship find(Concept concept1, Concept concept2, String reationshipId) throws Exception;
	
	/**
	 * Removes the by id.
	 *
	 * @param conceptId1 the concept relationship pk
	 */
	public Long removeById(Long conceptId1);

	public static ConceptRelationship _construct(ResultSet rs, ConceptRelationship conceptRelationship, String alias) throws SQLException {
		if (conceptRelationship == null)
			conceptRelationship = new ConceptRelationship();

		if (alias == null || alias.isEmpty())
			alias = ConceptRelationship._getTableName();

		ResultSetMetaData metaData = rs.getMetaData();
		int totalColumnSize = metaData.getColumnCount();
		for (int i = 1; i <= totalColumnSize; i++) {
			String columnInfo = metaData.getColumnName(i);

			if (columnInfo.equalsIgnoreCase("concept1_concept_id")) {
				Concept concept1 = ConceptService._construct(rs, null, "concept1");
				conceptRelationship.setConcept1(concept1);
			} else if (columnInfo.equalsIgnoreCase("concept2_concept_id")) {
				Concept concept2 = ConceptService._construct(rs, null, "concept2");
				conceptRelationship.setConcept2(concept2);
			} else if (columnInfo.equalsIgnoreCase(alias + "_relationship_id")) {
				conceptRelationship.setRelationshipId(rs.getString(columnInfo));
			} else if (columnInfo.equalsIgnoreCase(alias + "_valid_start_date")) {
				conceptRelationship.setValidStartDate(rs.getDate(columnInfo));
			} else if (columnInfo.equalsIgnoreCase(alias + "_valid_end_date")) {
				conceptRelationship.setValidEndDate(rs.getDate(columnInfo));
			} else if (columnInfo.equalsIgnoreCase(alias + "_invalid_reason")) {
				conceptRelationship.setInvalidReason(rs.getString(columnInfo));
			}

		}

		return conceptRelationship;
	}

	public static ConceptRelationship _construct(FieldValueList rowResult, ConceptRelationship conceptRelationship,
			String alias, List<String> columns) {
		if (conceptRelationship == null)
			conceptRelationship = new ConceptRelationship();

		if (alias == null || alias.isEmpty())
			alias = ConceptRelationship._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

		// } else if (columnInfo.equalsIgnoreCase("conditionConcept_concept_id")) {
		// 	Concept conditionConcept = ConceptService._construct(rowResult, null, "conditionConcept", columns);
		// 	conditionOccurrence.setConditionConcept(conditionConcept);


			if (columnInfo.equalsIgnoreCase("concept1_concept_id")) {
				Concept concept1 = ConceptService._construct(rowResult, null, "concpet1", columns);
				conceptRelationship.setConcept1(concept1);
			} else if (columnInfo.equalsIgnoreCase("concpet2_concept_id")) {
				Concept concept2 = ConceptService._construct(rowResult, null, "concept2", columns);
				conceptRelationship.setConcept2(concept2);
			} else if (columnInfo.equalsIgnoreCase(alias + "_relationship_id")) {
				conceptRelationship.setRelationshipId(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_valid_start_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					conceptRelationship.setValidStartDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_valid_end_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					conceptRelationship.setValidEndDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_invalid_reason")) {
				conceptRelationship.setInvalidReason(rowResult.get(columnInfo).getStringValue());
			}
		}

		return conceptRelationship;
	}

}