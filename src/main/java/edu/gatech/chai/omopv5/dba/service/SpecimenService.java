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
import edu.gatech.chai.omopv5.model.entity.Specimen;

/**
 * The Interface ObservationService.
 */
public interface SpecimenService extends IService<Specimen> {
	public static Specimen _construct(ResultSet rs, Specimen specimen, String alias) {
		if (specimen == null)
			specimen = new Specimen();

		if (alias == null || alias.isEmpty())
			alias = Specimen._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_specimen_id")) {
					specimen.setId(rs.getLong(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					specimen.setFPerson(fPerson);
				} else if (columnInfo.equalsIgnoreCase("specimenConcept_concept_id")) {
					Concept specimenConcept = ConceptService._construct(rs, null, "specimenConcept");
					specimen.setSpecimenConcept(specimenConcept);
				} else if (columnInfo.equalsIgnoreCase("specimenTypeConcept_concept_id")) {
					Concept specimenTypeConcept = ConceptService._construct(rs, null, "specimenTypeConcept");
					specimen.setSpecimenTypeConcept(specimenTypeConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_date")) {
					specimen.setSpecimenDate(rs.getDate(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_datetime")) {
					specimen.setSpecimenDateTime(rs.getTimestamp(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
					specimen.setQuantity(rs.getDouble(columnInfo));
				} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
					Concept unitConcept = ConceptService._construct(rs, null, "unitConcept");
					specimen.setUnitConcept(unitConcept);
				} else if (columnInfo.equalsIgnoreCase("anatomicSiteConcept_concept_id")) {
					Concept anatomicSiteConcept = ConceptService._construct(rs, null, "anatomicSiteConcept");
					specimen.setAnatomicSiteConcept(anatomicSiteConcept);
				} else if (columnInfo.equalsIgnoreCase("disease_status_concept_id")) {
					Concept diseaseStatusConcept = ConceptService._construct(rs, null, "diseaseStatusConcept");
					specimen.setDiseaseStatusConcept(diseaseStatusConcept);
				} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_source_id")) {
					specimen.setSpecimenSourceId(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_source_value")) {
					specimen.setSpecimenSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
					specimen.setUnitSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_anatomic_site_source_value")) {
					specimen.setAnatomicSiteSourceValue(rs.getString(columnInfo));
				} else if (columnInfo.equalsIgnoreCase(alias + "_disease_status_source_value")) {
					specimen.setDiseaseStatusSourceValue(rs.getString(columnInfo));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return specimen;
	}

	public static Specimen _construct(FieldValueList rowResult, Specimen specimen, String alias, List<String> columns) {
		if (specimen == null)
			specimen = new Specimen();

		if (alias == null || alias.isEmpty())
			alias = Specimen._getTableName();

		for (String columnInfo : columns) {
			if (rowResult.get(columnInfo).isNull()) continue;

			if (columnInfo.equalsIgnoreCase(alias + "_specimen_id")) {
				specimen.setId(rowResult.get(columnInfo).getLongValue());
			} else if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
				FPerson fPerson = FPersonService._construct(rowResult, null, "fPerson", columns);
				specimen.setFPerson(fPerson);
			} else if (columnInfo.equalsIgnoreCase("specimenConcept_concept_id")) {
				Concept specimenConcept = ConceptService._construct(rowResult, null, "specimenConcept", columns);
				specimen.setSpecimenConcept(specimenConcept);
			} else if (columnInfo.equalsIgnoreCase("specimenTypeConcept_concept_id")) {
				Concept specimenTypeConcept = ConceptService._construct(rowResult, null, "specimenTypeConcept", columns);
				specimen.setSpecimenTypeConcept(specimenTypeConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_date")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2Date(dateString);
				if (date != null) {
					specimen.setSpecimenDate(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_datetime")) {
				String dateString = rowResult.get(columnInfo).getStringValue();
				Date date = SqlUtil.string2DateTime(dateString);
				if (date != null) {
					specimen.setSpecimenDateTime(date);
				}
			} else if (columnInfo.equalsIgnoreCase(alias + "_quantity")) {
				specimen.setQuantity(rowResult.get(columnInfo).getDoubleValue());
			} else if (columnInfo.equalsIgnoreCase("unitConcept_concept_id")) {
				Concept unitConcept = ConceptService._construct(rowResult, null, "unitConcept", columns);
				specimen.setUnitConcept(unitConcept);
			} else if (columnInfo.equalsIgnoreCase("AnatomicSiteConcept_concept_id")) {
				Concept anatomicSiteConcept = ConceptService._construct(rowResult, null, "anatomicSiteConcept", columns);
				specimen.setAnatomicSiteConcept(anatomicSiteConcept);
			} else if (columnInfo.equalsIgnoreCase("diseaseStatusConcept_concept_id")) {
				Concept diseaseStatusConcept = ConceptService._construct(rowResult, null, "diseaseStatusConcept", columns);
				specimen.setDiseaseStatusConcept(diseaseStatusConcept);
			} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_source_id")) {
				specimen.setSpecimenSourceId(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_specimen_source_value")) {
				specimen.setSpecimenSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_unit_source_value")) {
				specimen.setUnitSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_anatomic_site_source_value")) {
				specimen.setAnatomicSiteSourceValue(rowResult.get(columnInfo).getStringValue());
			} else if (columnInfo.equalsIgnoreCase(alias + "_disease_status_source_value")) {
				specimen.setDiseaseStatusSourceValue(rowResult.get(columnInfo).getStringValue());
			}
		}
		return specimen;
	}

}