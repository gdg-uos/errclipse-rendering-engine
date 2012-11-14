package com.errclipse;


import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.errclipse.child.ORM.ConnectORM;
import com.errclipse.child.property.ChildPropertyBin;
import com.errclipse.child.property.ChildPropertyHandler;

public class ORMTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<ChildPropertyBin> list = ConnectORM.getChildProp(); 
		System.out.println(list.size());
		ChildPropertyBin prop = new ChildPropertyBin("kdbc", "test_url2", "test_toori2", "test_passwd2");
		boolean isSuccess = ChildPropertyHandler.addNewChild(prop);
		Assert.assertEquals(false, isSuccess);
	}

}
