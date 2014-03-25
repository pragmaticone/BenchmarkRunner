/**
 * 
 */
package com.boundless.benchmark;


/**
 * @author Soumya Sengupta
 * 
 */
public interface IBenchmarkComponent {
	public String getId();

	public Object process() throws Exception;
}
