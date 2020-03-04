/*******************************************************************************
 * Copyright (c) 2019 Georgia Tech Research Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *******************************************************************************/
package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Note;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

/**
 * The Interface NoteService.
 */
public interface NoteService extends IService<Note> {
	public static Note _construct(ResultSet rs, Note note, String alias) {
		if (note == null) note = new Note();
		
		if (alias == null || alias.isEmpty())
			alias = Note._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_note_id")) {
					note.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					note.setFPerson(fPerson);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_note_date")) {
					note.setDate(rs.getDate(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_note_time")) {
					note.setTime(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("typeConcept_concept_id")) {
					Concept typeConcept = ConceptService._construct(rs, null, "typeConcept");
					note.setType(typeConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_note_text")) {
					note.setNoteText(rs.getString(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					note.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					note.setVisitOccurrence(visitOccurrence);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_note_source_value")) {
					note.setNoteSourceValue(rs.getString(columnInfo));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return note;
	}

}
