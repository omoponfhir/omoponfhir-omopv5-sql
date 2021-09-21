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

import edu.gatech.chai.omopv5.model.entity.Relationship;

// TODO: Auto-generated Javadoc
/**
 * The Class RelationshipServiceImp.
 */
@Service
public class RelationshipServiceImp extends BaseEntityServiceImp<Relationship> implements RelationshipService{

	/**
	 * Instantiates a new relationship service imp.
	 */
	public RelationshipServiceImp() {
		super(Relationship.class);
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.RelationshipService#findById(java.lang.String)
	 */
	@Override
	public Relationship findById(String id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.RelationshipService#removeById(java.lang.String)
	 */
	@Override
	public String removeById(String id) {
		return null;
	}

	@Override
	public Relationship findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relationship update(Relationship entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relationship construct(ResultSet rs, Relationship entity, String alias) {
		return RelationshipService._construct(rs, entity, alias);
	}

	@Override
	public Relationship construct(FieldValueList rowResult, Relationship entity, String alias, List<String> columns) {
		return RelationshipService._construct(rowResult, entity, alias, columns);
	}
	
}
