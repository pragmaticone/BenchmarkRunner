/**
 * 
 */
package com.boundless.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Soumya Sengupta
 * 
 */
public class GeoserverCleaner extends GeoserverCommunicator {
	final static Logger logger = LoggerFactory
			.getLogger(ShapefileBasedDataStoreCreator.class);

	private String workspaceName;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boundless.benchmark.BenchmarkComponent#process()
	 */
	@Override
	public Object process() throws Exception {
		String workspaceName = this.getWorkspaceName();

		// If the workspace exists, remove from Geoserver so that we start
		// from a clean slate.
		if (!this.checkIfWorkspaceExists(workspaceName)) {
			logger.info("Workspace " + workspaceName + " exists.");
			if (!this.deleteWorkspace(workspaceName)) {
				throw new Exception("Workspace " + workspaceName
						+ " could not be deleted.");
			} else {
				logger.info("Workspace " + workspaceName + " was deleted.");
			}
		} else {
			logger.info("Workspace " + workspaceName + " does not exists.");
		}

		return new Object();
	}
}
