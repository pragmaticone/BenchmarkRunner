/**
 * 
 */
package com.boundless.benchmark;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Soumya Sengupta
 * 
 */
public class DatabaseBasedDataStoreCreator extends GeoserverCommunicator {
	final static Logger logger = LoggerFactory
			.getLogger(DatabaseBasedDataStoreCreator.class);

	private String workspaceName, datastoreName, shapefileZipLocation;

	private JdbcTemplate jdbcTemplate;

	/**
	 * @return the workspaceName
	 */
	public String getWorkspaceName() {
		return workspaceName;
	}

	/**
	 * @param workspaceName
	 *            the workspaceName to set
	 */
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}

	/**
	 * @return the datastoreName
	 */
	public String getDatastoreName() {
		return datastoreName;
	}

	/**
	 * @param datastoreName
	 *            the datastoreName to set
	 */
	public void setDatastoreName(String datastoreName) {
		this.datastoreName = datastoreName;
	}

	/**
	 * @return the shapefileZipLocation
	 */
	public String getShapefileZipLocation() {
		return shapefileZipLocation;
	}

	/**
	 * @param shapefileZipLocation
	 *            the shapefileZipLocation to set
	 */
	public void setShapefileZipLocation(String shapefileZipLocation) {
		this.shapefileZipLocation = shapefileZipLocation;
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boundless.benchmark.IBenchmarkComponent#process()
	 */
	@Override
	public Object process() throws Exception {
		return new Object();
	}

}
