package com.errclipse.rmi.connectionManagement;

import com.errclipse.orm.connector.ConnectToORM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ormtest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ConnectToORM.getLangID("java");
	}

}
