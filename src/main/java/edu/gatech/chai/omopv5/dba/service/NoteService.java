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
import java.util.Date;
import java.util.List;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
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
		if (note == null)
			note = new Note();

		if (alias == null || alias.isEmpty())
			alias = Note._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_note_id")) {
					note.setId(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					note.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase(alias + "_note_date")) {
					note.setNoteDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_note_datetime")) {
					note.setNoteDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("noteTypeConcept_concept_id")) {
					Concept noteTypeConcept = ConceptService._construct(rs, null, "noteTypeConcept");
					note.setNoteTypeConcept(noteTypeConcept);
				} else if (columnInfo.equalsIgnoreCase("noteClassConcept_concept_id")) {
					Concept noteClassConcept = ConceptService._construct(rs, null, "noteClassConcept");
					note.setNoteClassConcept(noteClassConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_note_title")) {
					note.setNoteTitle(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_note_text")) {
					note.setNoteText(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("encodingConcept_concept_id")) {
					Concept encodingConcept = ConceptService._construct(rs, null, "encodingConcept");
					note.setEncodingConcept(encodingConcept);
				} else if (columnInfo.equalsIgnoreCase("languageConcept_concept_id")) {
					Concept languageConcept = ConceptService._construct(rs, null, "languageConcept");
					note.setLanguageConcept(languageConcept);
				} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					note.setProvider(provider);
				} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					note.setVisitOccurrence(visitOccurrence);
				} else if (columnInfo.equalsIgnoreCase(alias + "_note_source_value")) {
					note.setNoteSourceValue(rs.getString(columnInfo));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return note;
	}

	public static Note _construct(FieldValueList rowResult, Note note, String alias, List<String> columns) {
		if (note == null)
			note = new Note();

		if (alias == null || alias.isEmpty())
			alias = Note._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_note_id")) {
				note.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				note.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase(alias + "_note_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					note.setNoteDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_note_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					note.setNoteDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase("noteTypeConcept_concept_id")) {
				Concept noteTypeConcept = ConceptService._construct(rowResult, null, "noteTypeConcept", columns);
				note.setNoteTypeConcept(noteTypeConcept);
			} else if (columnInfo.equalsIgnoreCase("noteClassConcept_concept_id")) {
				Concept noteClassConcept = ConceptService._construct(rowResult, null, "noteClassConcept", columns);
				note.setNoteClassConcept(noteClassConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_note_title")) {
				note.setNoteTitle(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_note_text")) {
				note.setNoteText(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase("encodingConcept_concept_id")) {
				Concept encodingConcept = ConceptService._construct(rowResult, null, "encodingConcept", columns);
				note.setEncodingConcept(encodingConcept);
			} else if (columnInfo.equalsIgnoreCase("languageConcept_concept_id")) {
				Concept languageConcept = ConceptService._construct(rowResult, null, "languageConcept", columns);
				note.setLanguageConcept(languageConcept);
			} else if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
				Provider provider = ProviderService._construct(rowResult, null, "provider", columns);
				note.setProvider(provider);
			} else if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
				VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rowResult, null, "visitOccurrence", columns);
				note.setVisitOccurrence(visitOccurrence);
			} else if (columnInfo.equalsIgnoreCase(alias + "_note_source_value")) {
				note.setNoteSourceValue(rowResult.get(columnInfo).getStringValue());
			}
		}

		return note;
	}
}
