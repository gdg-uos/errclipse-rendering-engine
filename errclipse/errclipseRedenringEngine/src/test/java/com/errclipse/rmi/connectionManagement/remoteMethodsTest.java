package com.errclipse.rmi.connectionManagement;

import java.rmi.Naming;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.errclipse.rmi.interfaces.IRemoteMethod;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.ErrorQuery;

public class remoteMethodsTest {

	@Before
	public void setUp() throws Exception {

		System.out.println("start");
		registerServer rs = new registerServer();
		rs.initiateServer();
		System.out.println("rmi server start");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		ErrorQuery q = ErrorQuery.newBuilder().setLanguageName("c").setLibraryName("test").setMethodName("test_method").setErrorName("test_erro").build();

		try{
			String serverUrl = "rmi://127.0.0.1:1099/rmiServer";
			IRemoteMethod s = (IRemoteMethod)Naming.lookup(serverUrl);
			s.getErrorSolution(q);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
