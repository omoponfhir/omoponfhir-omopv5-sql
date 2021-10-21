package edu.gatech.chai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.gatech.chai.omopv5.dba.config.DatabaseConfiguration;
import edu.gatech.chai.omopv5.dba.config.DatabaseConfigurationImpl;
import edu.gatech.chai.omopv5.dba.service.FObservationViewService;
import edu.gatech.chai.omopv5.dba.service.FObservationViewServiceImp;
import edu.gatech.chai.omopv5.dba.service.FPersonService;
import edu.gatech.chai.omopv5.dba.service.FPersonServiceImp;
import edu.gatech.chai.omopv5.dba.service.MeasurementService;
import edu.gatech.chai.omopv5.dba.service.MeasurementServiceImp;
import edu.gatech.chai.omopv5.dba.service.ObservationService;
import edu.gatech.chai.omopv5.dba.service.ObservationServiceImp;

@Configuration
public class AppConfig {
	@Bean
	public FPersonService getFPersonService() {
		return new FPersonServiceImp();
	}

	@Bean
	public FObservationViewService getFObservationViewService() {
		return new FObservationViewServiceImp();
	}

	@Bean
	public ObservationService getObservationService() {
		return new ObservationServiceImp();
	}

	@Bean
	public MeasurementService getMeasurementService() {
		return new MeasurementServiceImp();
	}

	@Bean
	public DatabaseConfiguration getDatabaseConfiguration() {
		return new DatabaseConfigurationImpl();
	}
}
