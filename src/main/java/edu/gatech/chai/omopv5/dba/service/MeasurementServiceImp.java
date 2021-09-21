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

import edu.gatech.chai.omopv5.model.entity.Measurement;

// TODO: Auto-generated Javadoc
/**
 * The Class MeasurementServiceImp.
 */
@Service
public class MeasurementServiceImp extends BaseEntityServiceImp<Measurement> implements MeasurementService {

	/**
	 * Instantiates a new measurement service imp.
	 */
	public MeasurementServiceImp() {
		super(Measurement.class);
	}

	@Override
	public Measurement construct(ResultSet rs, Measurement entity, String alias) {
		return MeasurementService._construct(rs, entity, alias);
	}

	@Override
	public Measurement construct(FieldValueList rowResult, Measurement entity, String alias, List<String> columns) {
		return MeasurementService._construct(rowResult, entity, alias, columns);
	}

}
