/**
 * 
 */
package com.boundless.benchmark;


/**
 * @author Soumya Sengupta
 * 
 */
public class GeoserverCleaner extends GeoserverCommunicator {
	private static String WORKSPACE = "shapefile-based-ws";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boundless.benchmark.BenchmarkComponent#getId()
	 */
	@Override
	public String getId() {
		return this.getClass().getCanonicalName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boundless.benchmark.BenchmarkComponent#process()
	 */
	@Override
	public Object process() throws Exception {
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

		return new Object();
	}
}
