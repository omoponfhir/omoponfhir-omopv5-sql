package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.Death;
import edu.gatech.chai.omopv5.model.entity.FPerson;

public interface DeathService extends IService<Death> {
	public static Death _construct(ResultSet rs, Death death, String alias) {
		if (death == null) death = new Death();
		
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
				}

				if (columnInfo.equalsIgnoreCase(alias + "_death_date")) {
					death.setDeathDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_death_datetime")) {
					death.setDeathDateTime(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("deathTypeConcept_concept_id")) {
					Concept deathTypeConcept = ConceptService._construct(rs, null, "deathTypeConcept");
					death.setDeathTypeConcept(deathTypeConcept);
				}

				if (columnInfo.equalsIgnoreCase("causeConcept_concept_id")) {
					Concept causeConcept = ConceptService._construct(rs, null, "causeConcept");
					death.setCauseConcept(causeConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_cause_source_value")) {
					death.setCauseSourceValue(rs.getString(columnInfo));
				}


				if (columnInfo.equalsIgnoreCase("causeSourceConcept_concept_id")) {
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

}
