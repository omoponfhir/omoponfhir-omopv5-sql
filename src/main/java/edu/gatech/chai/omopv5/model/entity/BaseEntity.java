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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
				String sortItem = getColumnName(items[0]).replace(".", "_") + " " + items[1];
				if (sortClause == null || sortClause.isEmpty()) {
					sortClause = sortItem;
				} else {
					sortClause = sortClause.concat(", " + sortItem);
				}
			}
		}

		if (sortClause != null && !sortClause.isEmpty()) {
			sortClause = "ORDER BY " + sortClause;
		}

		return sortClause;
	}

	@Override
	public String toString() {
		return "id: " + this.getIdAsLong() + "\n";
	}

}
