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

import edu.gatech.chai.omopv5.model.entity.Provider;

// TODO: Auto-generated Javadoc
/**
 * The Class ProviderServiceImp.
 */
@Service
public class ProviderServiceImp extends BaseEntityServiceImp<Provider> implements ProviderService {

	/**
	 * Instantiates a new provider service imp.
	 */
	public ProviderServiceImp() {
		super(Provider.class);
	}

	@Override
	public Provider construct(ResultSet rs, Provider entity, String alias) {
		return ProviderService._construct(rs, entity, alias);
	}

	@Override
	public Provider construct(FieldValueList rowResult, Provider entity, String alias, List<String> columns) {
		return ProviderService._construct(rowResult, entity, alias, columns);
	}

}
