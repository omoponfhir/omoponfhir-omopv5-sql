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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FObservationView;
import edu.gatech.chai.omopv5.model.entity.FPerson;

/**
 * The Class FObservationViewServiceImp.
 */
@Service
public class FObservationViewServiceImp extends BaseEntityServiceImp<FObservationView>
		implements FObservationViewService {

	@Value("${schema.data}")
	private String dataSchema;

	/**
	 * Instantiates a new f observation view service imp.
	 */
	public FObservationViewServiceImp() {
		super(FObservationView.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.gatech.chai.omopv5.dba.service.FObservationViewService#findDiastolic(java
	 * .lang.Long, java.lang.Long, java.util.Date, java.lang.String)
	 */
	public FObservationView findDiastolic(Long conceptId, Long personId, Date date, Date time) {
		String myTable = getSqlTableName();
		String myDataSchema = "";
		if (dataSchema != null && !dataSchema.isBlank()) {
			myDataSchema = dataSchema + ".";
		}
		String myRootTable = myDataSchema + myTable;

		String select_from = constructSqlSelectWithoutWhere(myRootTable);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateValue = "cast('" + dateFormat.format(date) + "' as date)";

		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTimeValue = "cast('" + dateFormat.format(time) + "' as datetime)";

		String where = FObservationView._getColumnName("observationConcept") + "=" + conceptId 
				+ " and " + FObservationView._getColumnName("fPerson") + "=" + personId 
				+ " and " + FObservationView._getColumnName("observationDate") + "=" + dateValue 
				+ " and " + FObservationView._getColumnName("observationDateTime") + "=" + dateTimeValue;

		String sql = select_from + " where " + where;
		FObservationView entity = null;
		
		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(sql);
				List<String> columns = listOfColumns(sql);
				for (FieldValueList row : result.iterateAll()) {
					entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						break;
					}
				}
			} else {
				// ResultSet rs = getQueryEntityDao().runQuery(sql);
				List<FObservationView> myEntities = runQuery(sql, null, getSqlTableName());

				// if (rs.next()) {
				// 	entity = construct(rs, null, getSqlTableName());
				// }
				if (myEntities != null && !myEntities.isEmpty()) {
					entity = myEntities.get(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return entity;
	}

	@Override
	public FObservationView update(FObservationView entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FObservationView construct(ResultSet rs, FObservationView entity, String alias) throws SQLException {
		return FObservationViewService._construct(rs, entity, alias);
	}

	@Override
	public FObservationView construct(FieldValueList rowResult, FObservationView entity, String alias,
			List<String> columns) {
		return FObservationViewService._construct(rowResult, entity, alias, columns);
	}

}
