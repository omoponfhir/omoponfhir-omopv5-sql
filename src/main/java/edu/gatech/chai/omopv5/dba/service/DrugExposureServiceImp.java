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

import edu.gatech.chai.omopv5.model.entity.DrugExposure;

// TODO: Auto-generated Javadoc
/**
 * The Class DrugExposureServiceImp.
 */
@Service
public class DrugExposureServiceImp extends BaseEntityServiceImp<DrugExposure>
		implements DrugExposureService {
	
	/**
	 * Instantiates a new drug exposure service imp.
	 */
	public DrugExposureServiceImp() {
		super(DrugExposure.class);
	}

	@Override
	public DrugExposure construct(ResultSet rs, DrugExposure entity, String alias) {
		return DrugExposureService._construct(rs, entity, alias);
	}

	@Override
	public DrugExposure construct(FieldValueList rowResult, DrugExposure entity, String alias, List<String> columns) {
		return DrugExposureService._construct(rowResult, entity, alias, columns);
	}

}
