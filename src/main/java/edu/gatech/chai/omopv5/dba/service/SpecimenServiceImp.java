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
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.Specimen;

// TODO: Auto-generated Javadoc
/**
 * The Class ObservationServiceImp.
 */
@Service
public class SpecimenServiceImp extends BaseEntityServiceImp<Specimen> implements SpecimenService {

	/**
	 * Instantiates a new observation service imp.
	 */
	public SpecimenServiceImp() {
		super(Specimen.class);
	}

	@Override
	public Specimen construct(ResultSet rs, Specimen entity, String alias) {
		return SpecimenService._construct(rs, entity, alias);
	}

	@Override
	public Specimen construct(FieldValueList rowResult, Specimen entity, String alias, List<String> columns) {
		return SpecimenService._construct(rowResult, entity, alias, columns);
	}

}