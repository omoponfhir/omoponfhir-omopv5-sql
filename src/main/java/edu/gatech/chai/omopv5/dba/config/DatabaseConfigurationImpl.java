package edu.gatech.chai.omopv5.dba.config;

import javax.sql.DataSource;

//import org.springframework.stereotype.Component;

//@Component
public class DatabaseConfigurationImpl implements DatabaseConfiguration {

	private String targetDialect;
	private DataSource dataSource;
	private String bigQueryDataset;
	private String bigQueryProject;

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

	@Override
	public void setBigQueryDataset(String dataset) {
		this.bigQueryDataset = dataset;
	}

	@Override
	public String getBigQueryDataset() {
		return this.bigQueryDataset;
	}

	@Override
	public void setBigQueryProject(String project) {
		this.bigQueryProject = project;
	}

	@Override
	public String getBigQueryProject() {
		return this.bigQueryProject;
	}

}
