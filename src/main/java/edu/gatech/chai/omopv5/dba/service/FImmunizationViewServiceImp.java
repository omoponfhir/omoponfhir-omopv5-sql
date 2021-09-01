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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FImmunizationView;
import edu.gatech.chai.omopv5.model.entity.FPerson;

/**
 * The Class FImmunizationViewServiceImp.
 */
@Service
public class FImmunizationViewServiceImp extends BaseEntityServiceImp<FImmunizationView>
		implements FImmunizationViewService {

	/**
	 * Instantiates a new f observation view service imp.
	 */
	public FImmunizationViewServiceImp() {
		super(FImmunizationView.class);
	}

	@Override
	public FImmunizationView update(FImmunizationView entity) {
		return null;
	}

	@Override
	public FImmunizationView construct(ResultSet rs, FImmunizationView entity, String alias) {
		return FImmunizationViewService._construct(rs, entity, alias);
	}

	@Override
	public FImmunizationView construct(FieldValueList rowResult, FImmunizationView entity, String alias,
			List<String> columns) {
		return FImmunizationViewService._construct(rowResult, entity, alias, columns);
	}

}
