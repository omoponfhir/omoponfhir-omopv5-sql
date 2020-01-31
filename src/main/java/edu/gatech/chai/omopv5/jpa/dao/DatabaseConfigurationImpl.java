package edu.gatech.chai.omopv5.jpa.dao;

import javax.sql.DataSource;

//import org.springframework.stereotype.Component;

//@Component
public class DatabaseConfigurationImpl implements DatabaseConfiguration {

	private String targetDialect;
	private DataSource dataSource;

	@Override
	public String getSqlRenderTargetDialect() {
		return this.targetDialect;
	}

	@Override
	public void setSqlRenderTargetDialect(String targetDialect) {
		this.targetDialect = targetDialect;
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
