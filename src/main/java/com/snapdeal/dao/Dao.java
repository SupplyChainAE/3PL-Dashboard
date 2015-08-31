package com.snapdeal.dao;

import javax.sql.DataSource;

public class Dao {
	private DataSource dataSourceLocal;

	public DataSource getDataSourceLocal() {
		return dataSourceLocal;
	}

	public void setDataSourceLocal(DataSource dataSourceLocal) {
		this.dataSourceLocal = dataSourceLocal;
	}
}
