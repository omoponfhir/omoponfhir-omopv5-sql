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
package edu.gatech.chai.omopv5.jpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.ohdsi.sql.SqlTranslate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QueryEntityDaoImpl implements QueryEntityDao {
	private static final Logger logger = LoggerFactory.getLogger(QueryEntityDaoImpl.class);
	private Connection connection;
	private DataSource ds;
	
	@Autowired
	DatabaseConfiguration databaseConfig;
	
	@PostConstruct
    private void postConstruct() {
		ds = databaseConfig.getDataSource();
	}
	
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
	
	private void getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = ds.getConnection();
			
			if (connection.getAutoCommit()) {
				try {
	 				connection.setAutoCommit(false);
	 			} catch (SQLFeatureNotSupportedException ignored){
	 			}
			}
		}
		
	}
	
	@Override
	public ResultSet runQuery(String query) throws SQLException {
		getConnection();
		
		// sql string is full completed string rendered by SqlRender.
		// Now, we translate this to attached database SQL.
		query = SqlTranslate.translateSql(query, databaseConfig.getSqlRenderTargetDialect());
		logger.debug("Query after SqlRender translate to "+databaseConfig.getSqlRenderTargetDialect()+": "+query);
		System.out.println("Query after SqlRender translate to "+databaseConfig.getSqlRenderTargetDialect()+": "+query);
		
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
//		connection.close();
		
		return rs;
	}

	@Override
	public Long updateQuery(String query) throws SQLException {
		getConnection();		
		
		// sql string is full completed string rendered by SqlRender.
		// Now, we translate this to attached database SQL.
		query = SqlTranslate.translateSql(query, databaseConfig.getSqlRenderTargetDialect());
		logger.debug("Query after SqlRender translate to "+databaseConfig.getSqlRenderTargetDialect()+": "+query);
		System.out.println("Query after SqlRender translate to "+databaseConfig.getSqlRenderTargetDialect()+": "+query);
		
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int affectedRows = stmt.executeUpdate();
		if (affectedRows == 0) {
			logger.debug("INSERT failed with "+query);
			if (connection != null) connection.close();
			
			return 0L;
		}
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {
            Long retVal = generatedKeys.getLong(1);
            connection.close();
            
            return retVal;
        }
        else {
        	if (connection != null) connection.close();
            throw new SQLException("INSERT failed, no ID generated, with "+query);
        }
	}

}
