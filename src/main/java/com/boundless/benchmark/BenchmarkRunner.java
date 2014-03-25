/**
 * 
 */
package com.boundless.benchmark;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Soumya Sengupta
 * 
 */
public class BenchmarkRunner {
	final static Logger logger = LoggerFactory.getLogger(BenchmarkRunner.class);

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"benchmark-components.xml");
		@SuppressWarnings("unchecked")
		List<IBenchmarkComponent> components = (List<IBenchmarkComponent>) ctx
				.getBean("benchmark-components");

		logger.info("Starting benchmarking session...");
		try {
			for (IBenchmarkComponent component : components) {
				logger.info("Running: " + component.getId());
				component.process();
			}
		} catch (Exception e) {
			logger.info("Error while running benchmarking session, stack trace follows");
			e.printStackTrace();
		} finally {
			logger.info("Completed benchmarking session...");
		}
	}

}
