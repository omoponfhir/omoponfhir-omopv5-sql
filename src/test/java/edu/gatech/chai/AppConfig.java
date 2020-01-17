package edu.gatech.chai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.gatech.chai.omopv5.dba.service.FPersonService;
import edu.gatech.chai.omopv5.dba.service.FPersonServiceImp;
import edu.gatech.chai.omopv5.jpa.dao.DatabaseConfiguration;
import edu.gatech.chai.omopv5.jpa.dao.DatabaseConfigurationImpl;
import edu.gatech.chai.omopv5.jpa.dao.QueryEntityDao;
import edu.gatech.chai.omopv5.jpa.dao.QueryEntityDaoImpl;

@Configuration
public class AppConfig {
	@Bean
	public FPersonService getFPersonService() {
		return new FPersonServiceImp();
	}

	@Bean
	public DatabaseConfiguration getDatabaseConfiguration() {
		return new DatabaseConfigurationImpl();
	}

	@Bean
	public QueryEntityDao getqueryEntityDao() {
		return new QueryEntityDaoImpl();
	}
}
