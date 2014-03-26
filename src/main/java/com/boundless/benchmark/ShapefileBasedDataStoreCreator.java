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
public class ShapefileBasedDataStoreCreator extends GeoserverCommunicator {
	final static Logger logger = LoggerFactory
			.getLogger(ShapefileBasedDataStoreCreator.class);

	private String shapefileZipLocation, workspaceName, datastoreName;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boundless.benchmark.BenchmarkComponent#process()
	 */
	@Override
	public Object process() throws Exception {
		String shapefileZipLocation = this.getShapefileZipLocation();
		String workspaceName = this.getWorkspaceName();
		String datastoreName = this.getDatastoreName();

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

		// Need the workspace before we can create a data store.
		if (!this.createWorkspace(workspaceName)) {
			logger.info("Workspace " + workspaceName + " could not be created.");
		} else {
			logger.info("Workspace " + workspaceName + " was created.");
		}

		// Now that the workspace exists, create the data store.
		if (!this.createShapefileBackedDataStore(workspaceName, datastoreName,
				shapefileZipLocation)) {
			logger.info("Data store " + datastoreName
					+ " could not be created.");
		} else {
			logger.info("Data store " + datastoreName + " was created.");
		}

		return new Object();
	}
}
