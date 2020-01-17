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
package edu.gatech.chai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGPoolingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.gatech.chai.omopv5.dba.service.FPersonService;
import edu.gatech.chai.omopv5.dba.service.ParameterWrapper;
import edu.gatech.chai.omopv5.jpa.dao.DatabaseConfiguration;
import edu.gatech.chai.omopv5.model.entity.FPerson;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class JUnitTest {
	private static final Logger logger = LoggerFactory.getLogger(JUnitTest.class);
	private PGPoolingDataSource ds;
	@Autowired
	private DatabaseConfiguration databaseConfiguration;

	@Autowired
	private FPersonService fPersonService;
	
	@Test
	public void personTableTest() {
		ds = new PGPoolingDataSource();
		ds.setDataSourceName("Test Person without Param");
		ds.setServerName("localhost");
		ds.setPortNumber(5432);
		ds.setDatabaseName("postgres");
		ds.setCurrentSchema("omop_v5");
		ds.setUser("omop_v5");
		ds.setPassword("i3lworks");
		ds.setMaxConnections(10);		
		databaseConfiguration.setDataSource(ds);
		databaseConfiguration.setSqlRenderTargetDialect("postgresql");		

		List<FPerson> out = fPersonService.searchWithoutParams(0, 10, null);
		
		logger.debug("Total Returned FPerson: "+out.size());
		for (FPerson fperson : out) {
			System.out.println(fperson.toString());
		}
		
		assert(out.size() > 0);
    }

	@Test
	public void personTableWithParamTest() {
		ds = new PGPoolingDataSource();
		ds.setDataSourceName("Test Person with Param");
		ds.setServerName("localhost");
		ds.setPortNumber(5432);
		ds.setDatabaseName("postgres");
		ds.setCurrentSchema("omop_v5");
		ds.setUser("omop_v5");
		ds.setPassword("i3lworks");
		ds.setMaxConnections(10);		
		databaseConfiguration.setDataSource(ds);
		databaseConfiguration.setSqlRenderTargetDialect("postgresql");		

		List<ParameterWrapper> params = new ArrayList<ParameterWrapper>();
		ParameterWrapper paramWrapper = new ParameterWrapper();
		paramWrapper.setParameterType("String");
		paramWrapper
				.setParameters(Arrays.asList("familyName", "givenName1", "givenName2", "prefixName", "suffixName"));
		paramWrapper.setOperators(Arrays.asList("like", "like", "like", "like", "like"));
		paramWrapper.setValues(Arrays.asList("Kirk"));
		paramWrapper.setRelationship("or");

		params.add(paramWrapper);
		List<FPerson> out = fPersonService.searchWithParams(0, 10, params, null);
		
		logger.debug("Total Returned FPerson: "+out.size());
		for (FPerson fperson : out) {
			System.out.println(fperson.toString());
		}
		
		assert(out.size() > 0);
    }
}
