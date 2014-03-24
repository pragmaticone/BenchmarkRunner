/**
 * 
 */
package com.boundless.benchmark;

/**
 * @author Soumya Sengupta
 * 
 */
public interface BenchmarkComponent {
	public String getId();

	public Object process() throws Exception;
}
