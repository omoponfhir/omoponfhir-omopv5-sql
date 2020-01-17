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

public class ConditionOccurrence extends BaseEntity {

	private Long id;
	private FPerson fPerson;
	private Concept conceptId;
	private Date startDate;
	private Date endDate;
	private Concept typeConceptId;
	private String stopReason;
	private Provider provider;
	private VisitOccurrence visitOccurrence;
	private String conditionSourceValue;
	private Concept sourceConceptId;

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

	public Concept getConceptId() {
		return conceptId;
	}

	public void setConceptId(Concept conceptId) {
		this.conceptId = conceptId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Concept getTypeConceptId() {
		return typeConceptId;
	}

	public void setTypeConceptId(Concept typeConceptId) {
		this.typeConceptId = typeConceptId;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
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

	public String getConditionSourceValue() {
		return conditionSourceValue;
	}

	public void setConditionSourceValue(String conditionSourceValue) {
		this.conditionSourceValue = conditionSourceValue;
	}

	public Concept getSourceConceptId() {
		return sourceConceptId;
	}

	public void setSourceConceptId(Concept sourceConceptId) {
		this.sourceConceptId = sourceConceptId;
	}

	@Override
	public Long getIdAsLong() {
		return getId();
	}

	@Override
	public String getColumnName(String columnVariable) {
		return ConditionOccurrence._getColumnName(columnVariable);
	}

	public static String _getColumnName(String columnVariable) {
		if ("id".equals(columnVariable))
			return "condition_occurrence.condition_occurrence_id";

		if ("fPerson".equals(columnVariable))
			return "condition_occurrence.person_id";

		if ("conceptId".equals(columnVariable))
			return "condition_occurrence.condition_concept_id";

		if ("startDate".equals(columnVariable))
			return "condition_occurrence.condition_start_date";

		if ("endDate".equals(columnVariable))
			return "condition_occurrence.condition_end_date";

		if ("typeConceptId".equals(columnVariable))
			return "condition_occurrence.condition_type_concept_id";

		if ("stopReason".equals(columnVariable))
			return "condition_occurrence.stop_reason";

		if ("provider".equals(columnVariable))
			return "condition_occurrence.provider_id";

		if ("visitOccurrence".equals(columnVariable))
			return "condition_occurrence.visit_occurrence_id";

		if ("conditionSourceValue".equals(columnVariable))
			return "condition_occurrence.condition_source_value";

		if ("sourceConceptId".equals(columnVariable))
			return "condition_occurrence.condition_source_concept_id";

		return null;
	}

	@Override
	public String getTableName() {
		return ConditionOccurrence._getTableName();
	}

	public static String _getTableName() {
		return "condition_occurrence";
	}

	@Override
	public String getForeignTableName(String foreignVariable) {
		return ConditionOccurrence._getForeignTableName(foreignVariable);
	}
	
	public static String _getForeignTableName(String foreignVariable) {
		if ("fPerson".equals(foreignVariable))
			return FPerson._getTableName();
		
		if ("visitOccurrence".equals(foreignVariable))
			return VisitOccurrence._getTableName();
		
		if ("conceptId".equals(foreignVariable) || "typeConceptId".equals(foreignVariable)
				|| "sourceConceptId".equals(foreignVariable))
			return Concept._getTableName();

		return null;
	}

	public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
		return "select * from condition_occurrence ";
	}

}
