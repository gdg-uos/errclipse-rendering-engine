package com.errclipse;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.errclipse.ORM.*;
import com.errclipse.ORM.bin.ChildPropBin;

public class ORMTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<ChildPropBin> list = ConnectORM.getChildProp(); 
		for(ChildPropBin bin : list){
			System.out.println(bin.getChild_id());
		}
	}

}
