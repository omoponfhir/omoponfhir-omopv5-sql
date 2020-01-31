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

import edu.gatech.chai.omopv5.model.entity.ProcedureOccurrence;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcedureOccurrenceServiceImp.
 */
@Service
public class ProcedureOccurrenceServiceImp extends BaseEntityServiceImp<ProcedureOccurrence>
		implements ProcedureOccurrenceService {

	/**
	 * Instantiates a new procedure occurrence service imp.
	 */
	public ProcedureOccurrenceServiceImp() {
		super(ProcedureOccurrence.class);
	}

	@Override
	public ProcedureOccurrence findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcedureOccurrence> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcedureOccurrence> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcedureOccurrence> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList,
			String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcedureOccurrence> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcedureOccurrence create(ProcedureOccurrence entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcedureOccurrence update(ProcedureOccurrence entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSize(List<ParameterWrapper> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcedureOccurrence construct(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
