/**
 * 
 */
package com.boundless.benchmark;


/**
 * @author Soumya Sengupta
 * 
 */
public abstract class AbstractBenchmarkComponent implements IBenchmarkComponent {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boundless.benchmark.IBenchmarkComponent#getId()
	 */
	@Override
	public String getId() {
		return this.getClass().getCanonicalName();
	}
}
