package com.errclipse.rmi.connectionManagement;

import java.rmi.Naming;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.errclipse.rmi.interfaces.ErrorBin.SearchRequest;
import com.errclipse.rmi.interfaces.IRemoteMethods;

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
		SearchRequest sr = SearchRequest.newBuilder().setQuery("test_query")
				.setPageNumber(10)
				.setResultPerPage(20)
				.build();
		try{
			String serverUrl = "rmi://127.0.0.1/rmiServer";
			IRemoteMethods s = (IRemoteMethods)Naming.lookup(serverUrl);
			
			for(String str : s.getErrorRelationUrl(sr).getUrlsList()){
				System.out.println(str);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
