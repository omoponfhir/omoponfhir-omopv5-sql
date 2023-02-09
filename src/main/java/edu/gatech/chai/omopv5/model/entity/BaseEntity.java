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
package edu.gatech.chai.omopv5.model.entity;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.dba.util.SqlUtil;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

public abstract class BaseEntity implements IBaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

	public Long getIdAsLong() {
		return null;
	}

	public String getSortClause(String sort) {
		String sortClause = null;
		if (sort != null && !sort.isEmpty()) {

			String[] sort_ = sort.split(",");
			for (int i = 0; i < sort_.length; i++) {
				String[] items = sort_[i].split(" ");
				String sortVariable = items[0];
  				String[] sortVarParts = sortVariable.split(".");

  				String sortItem = null;
  				if (sortVarParts.length == 2) {
  					// This is foreign variable
  					Field field;
  					try {
  						field = this.getClass().getDeclaredField(sortVarParts[0]);
  					} catch (NoSuchFieldException | SecurityException e) {
  						e.printStackTrace();

  						continue;
  					}

  					JoinColumn joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
  					if (joinColumnAnnotation == null) {
  						logger.error("JoinColumn is missing for this sort parameter, " + sortVariable);

  						continue;
  					}

  					// Now, check if the foreign table column exists and get real sql column name.
  //					Class<?> fTableClazz = field.getDeclaringClass();
  					Class<?> fTableClazz = field.getType();
  					String sqlColumnName = SqlUtil.getSqlColumnName(fTableClazz, sortVarParts[1]);
  					if (sqlColumnName == null) {
  						logger.error("Failed to get sql column name for table=" + fTableClazz.getCanonicalName()
  								+ " with attributeName=" + sortVariable);

  						continue;
  					}

  					// Now, we have SQL Column name. We need to use alias so we use right joined
  					// table.
  					// The default alias for JoinColumn is its variable name. But, we could have
  					// one-to-one mapping (f_person). In this case, we may have different alias,
  					// which is
  					// specified in JoinColumn annotation at table().
  					//
  					// fTableClazz should have a right class (updated with a class used in
  					// SqlUtil.getSqlColumnName.
  					String alias = sortVarParts[0];
  					String aliasInfo = joinColumnAnnotation.table();
  					if (aliasInfo != null && !aliasInfo.equals("")) {
  						Table referredTableAnnotation = fTableClazz.getDeclaredAnnotation(Table.class);
  						if (referredTableAnnotation != null) {
  							String referredTableName = referredTableAnnotation.name();
  							String[] aliasTables = aliasInfo.split(",");
  							for (String aliasTable : aliasTables) {
  								String[] tables = aliasTable.split(":");
  								if (tables[0].equals(referredTableName) && tables.length == 2) {
  									alias = tables[1];
  									break;
  								}
  							}
  						}
  					}

  					sortItem = alias + "_" + sqlColumnName.replace(".", "_") + " " + items[1];
  				} else {
  					sortItem = getColumnName(items[0]).replace(".", "_") + " " + items[1];
  				}
				
				if (sortClause == null || sortClause.isEmpty()) {
					sortClause = sortItem;
				} else {
					sortClause = sortClause.concat(", " + sortItem);
				}
			}
		}

		if (sortClause != null && !sortClause.isEmpty()) {
			sortClause = "ORDER BY " + sortClause;
		} else {
			String columnName = getColumnName("id");
			if (columnName == null || columnName.isEmpty()) {
				sortClause = "ORDER BY " + getFirstColumnName().replace(".", "_") + " asc";
			} else {
				sortClause = "ORDER BY " + columnName.replace(".", "_") + " asc";
			}
		}

		return sortClause;
	}

	@Override
	public String toString() {
		return "id: " + this.getIdAsLong() + "\n";
	}

}
