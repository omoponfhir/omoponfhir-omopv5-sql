package edu.gatech.chai.omopv5.jpa.dao;

import javax.sql.DataSource;

public interface DatabaseConfiguration {
	public void setSqlRenderTargetDialect(String targetDialect);
	public String getSqlRenderTargetDialect();
	public javax.sql.DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
	public void setBigQueryDataset(String dataset);
	public String getBigQueryDataset();
	public void setBigQueryProject(String project);
	public String getBigQueryProject();
}
