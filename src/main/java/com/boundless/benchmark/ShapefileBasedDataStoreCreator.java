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
	private static String WORKSPACE = "shapefile-based-ws";
	private static String DATASTORE = "shapefile-based-ds";
	private static String URL = "http://localhost/~ssengupta/example-data.zip";

	final static Logger logger = LoggerFactory
			.getLogger(ShapefileBasedDataStoreCreator.class);

	public Object process() throws Exception {
		// If the workspace exists, remove from Geoserver so that we start
		// from a clean slate.
		if (!this.checkIfWorkspaceExists(WORKSPACE)) {
			logger.info("Workspace " + WORKSPACE + " exists.");
			if (!this.deleteWorkspace(WORKSPACE)) {
				throw new Exception("Workspace " + WORKSPACE
						+ " could not be deleted.");
			} else {
				logger.info("Workspace " + WORKSPACE + " was deleted.");
			}
		} else {
			logger.info("Workspace " + WORKSPACE + " does not exists.");
		}

		// Need the workspace before we can create a data store.
		if (!this.createWorkspace(WORKSPACE)) {
			logger.info("Workspace " + WORKSPACE + " could not be created.");
		} else {
			logger.info("Workspace " + WORKSPACE + " was created.");
		}

		// Now that the workspace exists, create the data store.
		if (!this.createDataStore(WORKSPACE, DATASTORE, URL)) {
			logger.info("Data store " + DATASTORE + " could not be created.");
		} else {
			logger.info("Data store " + DATASTORE + " was created.");
		}

		return new Object();
	}

	@Override
	public String getId() {
		return this.getClass().getCanonicalName();
	}
}
