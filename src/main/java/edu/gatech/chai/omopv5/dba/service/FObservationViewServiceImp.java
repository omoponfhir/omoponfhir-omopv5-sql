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
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.model.entity.FObservationView;

// TODO: Auto-generated Javadoc
/**
 * The Class FObservationViewServiceImp.
 */
@Service
public class FObservationViewServiceImp extends BaseEntityServiceImp<FObservationView> implements FObservationViewService {

	/**
	 * Instantiates a new f observation view service imp.
	 */
	public FObservationViewServiceImp() {
		super(FObservationView.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FObservationViewService#findDiastolic(java.lang.Long, java.lang.Long, java.util.Date, java.lang.String)
	 */
	public FObservationView findDiastolic(Long conceptId, Long personId, Date date, String time) {
		return null;
	}

	@Override
	public FObservationView findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FObservationView> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FObservationView> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FObservationView> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList,
			String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FObservationView> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FObservationView create(FObservationView entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FObservationView update(FObservationView entity) {
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
	public FObservationView construct(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}
