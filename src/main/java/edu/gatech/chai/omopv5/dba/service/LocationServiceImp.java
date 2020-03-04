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
import org.springframework.transaction.annotation.Transactional;

import edu.gatech.chai.omopv5.model.entity.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationServiceImp.
 */
@Service
public class LocationServiceImp extends BaseEntityServiceImp<Location> implements LocationService {

	/**
	 * Instantiates a new location service imp.
	 */
	public LocationServiceImp() {
		super(Location.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.LocationService#searchByAddress(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public Location searchByAddress(String line1, String line2, String city, String state, String zipCode) {
		String query;
		List<Location> results;
		
		if (line2 != null) {
			query = "SELECT t FROM Location t WHERE address1 LIKE :line1 AND address2 LIKE :line2 AND city LIKE :city AND state LIKE :state AND zipCode LIKE :zip";
		} else { 
			query = "SELECT t FROM Location t WHERE address1 LIKE :line1 AND city LIKE :city AND state LIKE :state AND zipCode LIKE :zip";
		}

		return null;

	}

	@Override
	public Location findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location create(Location entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location update(Location entity) {
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
	public Location construct(ResultSet rs, Location entity, String alias) {
		return LocationService._construct(rs, entity, alias);
	}

}
