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
	private Date date;
	
	@Column(name="note_time")
	private String time;
	
	@JoinColumn(name="note_type_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept typeConcept;
	
	@Column(name="note_text", nullable=false)
	private String noteText;

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
		try {
			Field field = Note.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Note._getTableName() + "." + annotation.name();
				} else {
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
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Note._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from note ";
	}

}
