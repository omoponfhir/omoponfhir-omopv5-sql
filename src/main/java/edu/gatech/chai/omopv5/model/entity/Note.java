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

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

@Table(name = "note")
public class Note extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="note_id_seq")
	@Column(name="note_id", nullable=false)
	private Long id;
	
	@JoinColumn(name="person_id", table="f_person:fPerson,person:person", nullable=false)
	private FPerson fPerson;
	
	@Column(name="note_date", nullable=false)
	private Date noteDate;
	
	@Column(name="note_datetime")
	private Date noteDateTime;
	
	@JoinColumn(name="note_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept noteTypeConcept;
	
	@JoinColumn(name="note_class_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept noteClassConcept;

	@Column(name="note_title")
	private String noteTitle;

	@Column(name="note_text")
	private String noteText;

	@JoinColumn(name="encoding_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept encodingConcept;

	@JoinColumn(name="language_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept languageConcept;

	@JoinColumn(name="provider_id")
	private Provider provider;
	
	@JoinColumn(name="visit_occurrence_id")
	private VisitOccurrence visitOccurrence;
	
	@Column(name="note_source_value")
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
	
	public Date getNoteDate() {
		return noteDate;
	}
	
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	public Date getNoteDateTime() {
		return noteDateTime;
	}
	
	public void setNoteDateTime(Date noteDateTime) {
		this.noteDateTime = noteDateTime;
	}
	
	public Concept getNoteTypeConcept() {
		return noteTypeConcept;
	}
	
	public void setNoteTypeConcept(Concept noteTypeConcept) {
		this.noteTypeConcept = noteTypeConcept;
	}
	
	public Concept getNoteClassConcept() {
		return noteClassConcept;
	}
	
	public void setNoteClassConcept(Concept noteClassConcept) {
		this.noteClassConcept = noteClassConcept;
	}
	
	public String getNoteTitle() {
		return noteTitle;
	}
	
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	
	public String getNoteText() {
		return noteText;
	}
	
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	
	public Concept getEncodingConcept() {
		return encodingConcept;
	}
	
	public void setEncodingConcept(Concept encodingConcept) {
		this.encodingConcept = encodingConcept;
	}
	
	public Concept getLanguageConcept() {
		return languageConcept;
	}
	
	public void setLanguageConcept(Concept languageConcept) {
		this.languageConcept = languageConcept;
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
	
	@Override
  	public String getFirstColumnName() {
  		return "note_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Note.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Note._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Note._getTableName() + "." + joinAnnotation.name();
					}

					System.out.println("ERROR: annotation is null for field=" + field.toString());
					return null;
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;

//		if ("id".equals(columnVariable)) 
//			return "note.note_id";
//
//		if ("fPerson".equals(columnVariable)) 
//			return "note.person_id";
//
//		if ("date".equals(columnVariable)) 
//			return "note.note_date";
//
//		if ("time".equals(columnVariable)) 
//			return "note.note_time";
//
//		if ("typeConcept".equals(columnVariable)) 
//			return "note.note_type_concept_id";
//
//		if ("noteText".equals(columnVariable)) 
//			return "note.note_text";
//
//		if ("provider".equals(columnVariable)) 
//			return "note.provider_id";
//
//		if ("visitOccurrence".equals(columnVariable)) 
//			return "note.visit_occurrence_id";
//
//		if ("noteSourceValue".equals(columnVariable)) 
//			return "note.note_source_value";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Note._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Note.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "note";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Note._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("noteTypeConcept".equals(foreignVariable)
				|| "noteClassConcept".equals(foreignVariable)
				|| "encodingConcept".equals(foreignVariable)
				|| "languageConcept".equals(foreignVariable))
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
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Note._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from note ";
	}

}
