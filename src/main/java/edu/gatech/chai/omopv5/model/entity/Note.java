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
package edu.gatech.chai.omopv5.model.entity;

import java.util.Date;
import java.util.List;

public class Note extends BaseEntity {
	private Long id;
	private FPerson fPerson;
	private Date date;
	private String time;
	private Concept typeConcept;
	private String noteText;
	private Provider provider;
	private VisitOccurrence visitOccurrence;
	private String noteSourceValue;

	public Note() {
		super();
	}
	
	public Note(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FPerson getFPerson() {
		return fPerson;
	}

	public void setFPerson(FPerson fPerson) {
		this.fPerson = fPerson;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public Concept getType() {
		return typeConcept;
	}
	
	public void setType(Concept typeConcept) {
		this.typeConcept = typeConcept;
	}
	
	public String getNoteText() {
		return noteText;
	}
	
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public VisitOccurrence getVisitOccurrence() {
		return visitOccurrence;
	}
	
	public void setVisitOccurrence(VisitOccurrence visitOccurrence) {
		this.visitOccurrence = visitOccurrence;
	}
	
	public String getNoteSourceValue() {
		return noteSourceValue;
	}
	
	public void setNoteSourceValue(String noteSourceValue) {
		this.noteSourceValue = noteSourceValue;
	}
	
	@Override
	public Long getIdAsLong() {
		return id;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Note._getColumnName(columnVariable);
	}
	
	public static String _getColumnName(String columnVariable) {
		if ("id".equals(columnVariable)) 
			return "note.note_id";

		if ("fPerson".equals(columnVariable)) 
			return "note.person_id";

		if ("date".equals(columnVariable)) 
			return "note.note_date";

		if ("time".equals(columnVariable)) 
			return "note.note_time";

		if ("typeConcept".equals(columnVariable)) 
			return "note.note_type_concept_id";

		if ("noteText".equals(columnVariable)) 
			return "note.note_text";

		if ("provider".equals(columnVariable)) 
			return "note.provider_id";

		if ("visitOccurrence".equals(columnVariable)) 
			return "note.visit_occurrence_id";

		if ("noteSourceValue".equals(columnVariable)) 
			return "note.note_source_value";

		return null;
	}

	@Override
	public String getTableName() {
		return Note._getTableName();
	}
	
	public static String _getTableName() {
		return "note";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Note._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("typeConcept".equals(foreignVariable))
			return Concept._getTableName();

		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();

		if ("provider".equals(foreignVariable))
			return Provider._getTableName();

		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();

		return null;
	}

	@Override
	public String getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return Note._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from note ";
	}

}
