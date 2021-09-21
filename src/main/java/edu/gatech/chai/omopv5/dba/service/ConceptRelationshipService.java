package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
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

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_concept_id_1")) {
					conceptRelationship.setConceptId1(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_concept_id_2")) {
					conceptRelationship.setConceptId2(rs.getLong(columnInfo));
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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

			if (columnInfo.equalsIgnoreCase(alias + "_concept_id_1")) {
				conceptRelationship.setConceptId1(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_concept_id_2")) {
				conceptRelationship.setConceptId2(rowResult.get(columnInfo).getLongValue());
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
