package org.discovery.service;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author andrews.silva
 *
 */
public class AppTest extends TestCase {

	/**
	 * 
	 * @param testName
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * 
	 * @return
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * 
	 */
	public void testApp() {
		assertTrue(true);
	}
}
