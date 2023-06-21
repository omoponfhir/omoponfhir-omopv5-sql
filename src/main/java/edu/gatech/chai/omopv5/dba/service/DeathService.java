package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.Death;
import edu.gatech.chai.omopv5.model.entity.FPerson;

public interface DeathService extends IService<Death> {
	public static Death _construct(ResultSet rs, Death death, String alias) {
		if (death == null)
			death = new Death();

		if (alias == null || alias.isEmpty())
			alias = Death._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					death.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase(alias + "_death_date")) {
					death.setDeathDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_death_datetime")) {
					death.setDeathDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("deathTypeConcept_concept_id")) {
					Concept deathTypeConcept = ConceptService._construct(rs, null, "deathTypeConcept");
					death.setDeathTypeConcept(deathTypeConcept);
				} else if (columnInfo.equalsIgnoreCase("causeConcept_concept_id")) {
					Concept causeConcept = ConceptService._construct(rs, null, "causeConcept");
					death.setCauseConcept(causeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_cause_source_value")) {
					death.setCauseSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("causeSourceConcept_concept_id")) {
					Concept causeSourceConcept = ConceptService._construct(rs, null, "causeSourceConcept");
					death.setDeathTypeConcept(causeSourceConcept);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return death;
	}

	public static Death _construct(FieldValueList rowResult, Death death, String alias, List<String> columns) {
		if (death == null)
			death = new Death();

		if (alias == null || alias.isEmpty())
			alias = Death._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				death.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase(alias + "_death_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					death.setDeathDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_death_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					death.setDeathDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("deathTypeConcept_concept_id")) {
				Concept deathTypeConcept = ConceptService._construct(rowResult, null, "deathTypeConcept", columns);
				death.setDeathTypeConcept(deathTypeConcept);
			} else if (columnInfo.equalsIgnoreCase("causeConcept_concept_id")) {
				Concept causeConcept = ConceptService._construct(rowResult, null, "causeConcept", columns);
				death.setCauseConcept(causeConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_cause_source_value")) {
				death.setCauseSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("causeSourceConcept_concept_id")) {
				Concept causeSourceConcept = ConceptService._construct(rowResult, null, "causeSourceConcept", columns);
				death.setDeathTypeConcept(causeSourceConcept);
			}
		}

		return death;
	}

}
