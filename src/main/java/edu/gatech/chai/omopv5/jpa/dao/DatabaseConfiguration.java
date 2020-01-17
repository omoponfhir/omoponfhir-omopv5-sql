package edu.gatech.chai.omopv5.jpa.dao;

import javax.sql.DataSource;

public interface DatabaseConfiguration {
	public void setSqlRenderTargetDialect(String targetDialect);
	public String getSqlRenderTargetDialect();
	public javax.sql.DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
}
