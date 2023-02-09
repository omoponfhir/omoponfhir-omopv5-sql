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
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.model.entity.Vocabulary;

// TODO: Auto-generated Javadoc
/**
 * The Class VocabularyServiceImp.
 */
@Service
public class VocabularyServiceImp extends BaseEntityServiceImp<Vocabulary> implements VocabularyService {
	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImp.class);
	
	/**
	 * Instantiates a new vocabulary service imp.
	 */
	public VocabularyServiceImp() {
		super(Vocabulary.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.VocabularyService#findById(java.lang.String)
	 */
	public Vocabulary findById(String id) {
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		String rootTableName = SqlUtil.getTableName(getEntityClass());
		String sql = constructSqlSelectWithoutWhere(rootTableName);
		sql = sql + " where @cname='@value'";
		parameterList.add("cname");
		parameterList.add("value");
		valueList.add(rootTableName + "." + getSqlTableColumnName("id"));
		valueList.add(id);

		sql = renderedSql(sql, parameterList, valueList);

		try {
			return readEntity(sql);
		} catch (Exception e) {
			logger.error("SqlRender:" + sql);
			e.printStackTrace();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.VocabularyService#removeById(java.lang.String)
	 */
	public String removeById(String id) {
		return null; //getEntityDao().delete(getEntityClass(), id);
	}

	@Override
	public Vocabulary findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vocabulary construct(ResultSet rs, Vocabulary entity, String alias) {
		return VocabularyService._construct(rs, entity, alias);
	}

	@Override
	public Vocabulary construct(FieldValueList rowResult, Vocabulary entity, String alias, List<String> columns) {
		return VocabularyService._construct(rowResult, entity, alias, columns);
	}

}
