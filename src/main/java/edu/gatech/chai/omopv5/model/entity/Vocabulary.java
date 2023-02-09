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
import java.util.List;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

/**
 * 
 * @author Myung Choi
 */
@Table(name = "vocabulary")
public class Vocabulary extends BaseEntity {
	
	@Id
	@Column(name="vocabulary_id", nullable=false)
	private String id;
	
	@Column(name="vocabulary_name", nullable=false)
	private String vocabularyName;
	
	@Column(name="vocabulary_reference", nullable=false)
	private String vocabularyReference;
	
	@Column(name="vocabulary_version")
	private String vocabularyVersion;
	
	@JoinColumn(name="vocabulary_concept_id", referencedColumnName="concept_id", nullable=false)
	private Concept vocabularyConcept;

	public Vocabulary() {
		super();
	}

	public Vocabulary(String id) {
		super();
		this.id = id;
	}
	
	public Vocabulary(String id, String vocabularyName) {
		super();
		this.id = id;
		this.vocabularyName = vocabularyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVocabularyName() {
		return vocabularyName;
	}

	public void setVocabularyName(String vocabularyName) {
		this.vocabularyName = vocabularyName;
	}

	public String getVocabularyReference() {
		return vocabularyReference;
	}
	
	public void setVocabularyReference(String vocabularyReference) {
		this.vocabularyReference = vocabularyReference;
	}

	public String getVocabularyVersion() {
		return vocabularyVersion;
	}
	
	public void setVocabularyVersion(String vocabularyVersion) {
		this.vocabularyVersion = vocabularyVersion;
	}
	
	public Concept getVocabularyConcept() {
		return vocabularyConcept;
	}
	
	public void setVocabularyConcept(Concept vocabularyConcept) {
		this.vocabularyConcept = vocabularyConcept;
	}

	// FIXME This is FHIR related. We may need to do this in the database. But, for quick
	// initial implementation, we do this. Later, we may extend vocabulary table.
	public String getSystemUri() {
		String uri = this.getVocabularyReference();
		
		if (id.equalsIgnoreCase("SNOMED")) uri = "http://snomed.info/sct";
		else if (id.equalsIgnoreCase("ICD9CM")) uri = "http://hl7.org/fhir/sid/icd-9-cm";
		else if (id.equalsIgnoreCase("ICD9Proc")) uri = "http://hl7.org/fhir/sid/icd-9-cm/procedure";
		else if (id.equalsIgnoreCase("CPT4")) uri = "http://www.ama-assn.org/go/cpt";
		else if (id.equalsIgnoreCase("HCPCS")) uri = "http://purl.bioontology.org/ontology/HCPCS";
		else if (id.equalsIgnoreCase("LOINC")) uri = "http://loinc.org";
		else if (id.equalsIgnoreCase("RxNorm")) uri = "http://www.nlm.nih.gov/research/umls/rxnorm";
		else if (id.equalsIgnoreCase("UCUM")) uri = "http://unitsofmeasure.org";
		else if (id.equalsIgnoreCase("NDC")) uri = "http://hl7.org/fhir/sid/ndc";
		
		return uri;
	}
	
	public void setIdNameBySystemUri(String uri) {
		if (uri.equalsIgnoreCase("http://snomed.info/sct")) {
			this.id = "SNOMED";
		} else if (uri.equalsIgnoreCase("http://hl7.org/fhir/sid/icd-9-cm")) {
			this.id = "ICD9CM";
		} else if (uri.equalsIgnoreCase("http://hl7.org/fhir/sid/icd-9-cm/procedure")) {
			this.id = "ICD9Proc";
		} else if (uri.equalsIgnoreCase("http://www.ama-assn.org/go/cpt")) {
			this.id = "CPT4";
		} else if (uri.equalsIgnoreCase("http://purl.bioontology.org/ontology/HCPCS")) {
			this.id = "HCPCS";
		} else if (uri.equalsIgnoreCase("http://loinc.org")) {
			this.id = "LOINC";
		} else if (uri.equalsIgnoreCase("http://www.nlm.nih.gov/research/umls/rxnorm")) {
			this.id = "RxNorm";			
		} else if (uri.equalsIgnoreCase("http://unitsofmeasure.org")) {
			this.id = "UCUM";
		} else if (uri.equalsIgnoreCase("http://hl7.org/fhir/sid/ndc")) {
			this.id = "NDC";
		} else {
			this.id = "Vocabulary";
		}
	}

	@Override
	public Long getIdAsLong() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return Vocabulary._getColumnName(columnVariable);
	}
	
	@Override
  	public String getFirstColumnName() {
  		return "vocabulary_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = Vocabulary.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Vocabulary._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Vocabulary._getTableName() + "." + joinAnnotation.name();
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
//			return "vocabulary.vocabulary_id";
//
//		if ("name".equals(columnVariable)) 
//			return "vocabulary.vocabulary_name";
//
//		if ("vocabularyReference".equals(columnVariable)) 
//			return "vocabulary.vocabulary_reference";
//
//		if ("vocabularyVersion".equals(columnVariable)) 
//			return "vocabulary.vocabulary_version";
//
//		if ("vocabularyConcept".equals(columnVariable)) 
//			return "vocabulary.vocabulary_concept_id";
//
//		return null;
	}

	@Override
	public String getTableName() {
		return Vocabulary._getTableName();
	}
	
	public static String _getTableName() {
		Table annotation = Vocabulary.class.getDeclaredAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "vocabulary";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return Vocabulary._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("vocabularyConcept".equals(foreignVariable)) 
			return Concept._getTableName();

		return null;
	}
	
	@Override
	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
		return Vocabulary._getSqlTableStatement(parameterList, valueList);
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from vocabulary ";
	}

}
