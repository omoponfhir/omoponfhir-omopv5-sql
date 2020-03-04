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

import edu.gatech.chai.omopv5.model.entity.CareSite;
import edu.gatech.chai.omopv5.model.entity.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class CareSiteServiceImp.
 */
@Service
public class CareSiteServiceImp extends BaseEntityServiceImp<CareSite> implements CareSiteService {
	
	/**
	 * Instantiates a new care site service imp.
	 */
	public CareSiteServiceImp() {
		super(CareSite.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.CareSiteService#searchByLocation(edu.gatech.chai.omopv5.model.entity.Location)
	 */
	@Transactional(readOnly = true)
	public CareSite searchByLocation(Location location) {
		String query = "SELECT t FROM CareSite t WHERE location_id like :value";
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.CareSiteService#searchByNameAndLocation(java.lang.String, edu.gatech.chai.omopv5.model.entity.Location)
	 */
	@Transactional(readOnly = true)
	public CareSite searchByNameAndLocation(String careSiteName, Location location) {
		String queryString = "SELECT t FROM CareSite t WHERE";
		
		// Construct where clause here.
		String where_clause = "";
		if (careSiteName != null)  {
			where_clause = "careSiteName like :cName";
		}
		
		if (location != null) {
			if (where_clause == "") where_clause = "location = :location";
			else where_clause += " AND location = :location";
		}
		
		queryString += " "+where_clause;
		System.out.println("Query for FPerson"+queryString);		
		return null;	
	}

	@Override
	public CareSite findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareSite> searchByColumnString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareSite> searchByColumnString(String column, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareSite> searchWithParams(int fromIndex, int toIndex, List<ParameterWrapper> paramList, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareSite> searchWithoutParams(int fromIndex, int toIndex, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareSite create(CareSite entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareSite update(CareSite entity) {
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
	public CareSite construct(ResultSet rs, CareSite entity, String alias) {
		return CareSiteService._construct(rs, entity, alias);
	}

}
