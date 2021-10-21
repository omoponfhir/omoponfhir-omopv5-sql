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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGPoolingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.gatech.chai.omopv5.dba.config.DatabaseConfiguration;
import edu.gatech.chai.omopv5.dba.service.FObservationViewService;
import edu.gatech.chai.omopv5.dba.service.FPersonService;
import edu.gatech.chai.omopv5.dba.service.MeasurementService;
import edu.gatech.chai.omopv5.dba.service.ObservationService;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FObservationView;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Measurement;
import edu.gatech.chai.omopv5.model.entity.Observation;

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
	
	@Autowired
	private FObservationViewService fObservationViewService;

	@Autowired
	private ObservationService observationService;

	@Autowired
	private MeasurementService measurementService;

	@Test
	public void personTableTest() {
//		ds = new PGPoolingDataSource();
//		ds.setDataSourceName("Test SqlRendered SQL");
//		ds.setServerName("localhost");
//		ds.setPortNumber(5432);
//		ds.setDatabaseName("postgres");
//		ds.setCurrentSchema("omop_v5");
//		ds.setUser("omop_v5");
//		ds.setPassword("i3lworks");
//		ds.setMaxConnections(10);		
//		databaseConfiguration.setDataSource(ds);
//		databaseConfiguration.setSqlRenderTargetDialect("postgresql");		

		databaseConfiguration.setSqlRenderTargetDialect("bigquery");
		databaseConfiguration.setBigQueryDataset(System.getenv("BIGQUERYDATASET"));
		databaseConfiguration.setBigQueryProject(System.getenv("BIGQUERYPROJECT"));

//		List<FPerson> outFperson = fPersonService.searchWithoutParams(0, 10, null);
		
//		logger.debug("Total Returned FPerson: "+outFperson.size());
//		System.out.println("Total Returned FPerson: "+outFperson.size());
//		for (FPerson fperson : outFperson) {
//			System.out.println(fperson.toString());
//		}

		List<FObservationView> outFObs = fObservationViewService.searchWithoutParams(0, 10, null);
		logger.debug("Total Returned FObservationView: "+outFObs.size());
		for (FObservationView fObservationView : outFObs) {
			System.out.println(fObservationView.toString());
		}

//		assert(outFperson.size() > 0);
//		assert(outFObs.size() > 0);

		
//		FPerson fPerson = new FPerson();
//		fPerson.setGivenName1("GBQ");
//		fPerson.setFamilyName("Testing");
//		fPerson.setYearOfBirth(2015);
//		FPerson retFPerson = fPersonService.create(fPerson);
//		System.out.println(retFPerson.toString());

//		Observation observation = new Observation();
//		observation.setObservationConcept(new Concept(3012030L));
//		FPerson fPerson = new FPerson();
//		fPerson.setId(2L);
//		observation.setFPerson(fPerson);
//		observation.setObservationTypeConcept(new Concept(38000280L));
//		Date date = null;
//		try {
//			date = new SimpleDateFormat("yyyy-MM-dd").parse("2138-07-17");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		observation.setObservationDate(date);
//		
//		Observation retObs = observationService.create(observation);
//		System.out.println(retObs.toString());


		Measurement measurement = new Measurement();
		measurement.setMeasurementConcept(new Concept(3026314L));
		FPerson fPerson = new FPerson();
		fPerson.setId(3L);
		measurement.setFPerson(fPerson);
		measurement.setMeasurementTypeConcept(new Concept(0L));
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2138-07-17 14:35:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		measurement.setMeasurementDate(date);
		measurement.setMeasurementDateTime(date);
		
		Measurement retVal = measurementService.create(measurement);
		System.out.println(retVal.toString());

		
		//		List<ParameterWrapper> params = new ArrayList<ParameterWrapper>();
//		ParameterWrapper paramWrapper = new ParameterWrapper();
//		paramWrapper.setParameterType("String");
//		paramWrapper
//				.setParameters(Arrays.asList("familyName", "givenName1", "givenName2", "prefixName", "suffixName"));
//		paramWrapper.setOperators(Arrays.asList("like", "like", "like", "like", "like"));
//		paramWrapper.setValues(Arrays.asList("Hello"));
//		paramWrapper.setRelationship("or");
//
//		params.add(paramWrapper);
//		List<FPerson> out = fPersonService.searchWithParams(0, 10, params, null);
//		
//		logger.debug("Total Returned FPerson: "+out.size());
//		
//		FPerson newPerson = null;
//		for (FPerson fperson : out) {
//			newPerson = fperson;
//			System.out.println(fperson.toString());
//		}
//
//		assert(out.size() > 0);
//
//		newPerson.setFamilyName("Hello");
//		newPerson.setGivenName1("Update");
////		newPerson.setId(null);
//		FPerson returnedPerson = fPersonService.update(newPerson);
//		System.out.println(returnedPerson.toString());
    }
}
