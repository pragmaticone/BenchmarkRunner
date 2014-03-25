/**
 * 
 */
package com.boundless.benchmark;

import java.util.Properties;

/**
 * @author Soumya Sengupta
 * 
 */
public abstract class AbstractBenchmarkComponent implements IBenchmarkComponent {
	private Properties properties;

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
