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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ohdsi.sql.SqlRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Location;
import edu.gatech.chai.omopv5.model.entity.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class FPersonServiceImp.
 */
@Service
public class FPersonServiceImp extends BaseEntityServiceImp<FPerson> implements FPersonService {
	private static final Logger logger = LoggerFactory.getLogger(FPersonServiceImp.class);
	private boolean fTableExists;

	/**
	 * Instantiates a new f person service imp.
	 */
	public FPersonServiceImp() {
		super(FPerson.class);

		if ("False".equalsIgnoreCase(System.getenv("F_TABLE"))) {
			fTableExists = false;
		} else {
			fTableExists = true;
		}
	}

	public FPerson searchByNameAndLocation(String familyName, String given1Name, String given2Name, Location location) {
		String queryString = "select t from " + FPerson._getTableName() + " t WHERE";
		Map<String, String> columnList = new HashMap<String, String>();

		// Construct where clause here.
		String where_clause = "";
		if (familyName != null) {
			where_clause = FPerson._getColumnName("familyName") + " like :fname";
			columnList.put(":fname", familyName);
		}
		if (given1Name != null) {
			if (where_clause == "")
				where_clause = FPerson._getColumnName("givenName1") + " like :gname1";
			else
				where_clause += " AND " + FPerson._getColumnName("givenName1") + " like :gname1";
			columnList.put(":gname1", given1Name);
		}
		if (given2Name != null) {
			if (where_clause == "")
				where_clause = FPerson._getColumnName("givenName2") + " like :gname2";
			else
				where_clause += " AND " + FPerson._getColumnName("givenName2") + " like :gname2";
			columnList.put(":gname2", given1Name);
		}

		if (location != null) {
			if (where_clause == "")
				where_clause = FPerson._getColumnName("location") + " = :location";
			else
				where_clause += " AND " + FPerson._getColumnName("location") + " = :location";

			columnList.put(":location", location.getId().toString());
		}

		queryString += " " + where_clause;
		logger.debug("Query for FPerson" + queryString);
		logger.debug(
				FPerson._getColumnName("familyName") + ":" + familyName + " " + FPerson._getColumnName("givenName1")
						+ ":" + given1Name + " " + FPerson._getColumnName("givenName2") + ":" + given2Name);

		try {
			ResultSet rs = getQueryEntityDao().runQuery(queryString);

			while (rs.next()) {
				FPerson fp = construct(rs, null, "t");
				if (fp != null)
					return fp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Long removeById(Long id) {
		Long retVal = 0L;

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String sql = "delete from @table where @parameter=@value;";
		parameterList.add("table");
		parameterList.add("parameter");
		parameterList.add("value");

		valueList.add(getEntity().getTableName());
		valueList.add(getEntity().getColumnName("id"));
		valueList.add(id.toString());

		String[] parameters = new String[parameterList.size()];
		parameters = parameterList.toArray(parameters);

		String[] values = new String[valueList.size()];
		values = valueList.toArray(values);

		sql = SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");
		try {
			int ret = getQueryEntityDao().updateQuery(sql);
			retVal = Long.valueOf(ret);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (fTableExists) {
			// We just deleted f_person. Now, delete person table.
			sql = "delete from @table where @parameter=@value;";

			valueList.set(0, Person._getTableName());
			sql = SqlRender.renderSql(sql, parameters, values).replaceAll("\\s+", " ");

			try {
				int ret = getQueryEntityDao().updateQuery(sql);
				retVal = Long.valueOf(ret);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return retVal;
	}

//	@Override
//	public FPerson create(FPerson entity) {
//		Class<FPerson> clazz = getEntityClass();
//		
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			entity.get
//		}
//		return null;
//	}

	@Override
	public FPerson update(FPerson entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FPerson construct(ResultSet rs, FPerson entity, String alias) {
		return FPersonService._construct(rs, entity, alias);
	}

//	@Override
//	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
//		String sql = "select * from person mytablealias inner join f_person myftablealias ON mytablealias.person_id=myftablealias.person_id ";
//
//		return sql;
//	}

}
