package com.errclipse;

import com.errclipse.rmi.connectionManagement.registerServer;


public class runner {
    public static void main(String args[]) throws Exception{
    	

		System.out.println("start");
		registerServer rs = new registerServer();
		rs.initiateServer();
		System.out.println("rmi server start");
    	
//    	ErrorQuery q = ErrorQuery.newBuilder().
//    			setLanguageName("java").
//    			setLibraryName("test").
//    			setMethodName("test_mthod").
//    			setErrorName("test_erro").build();
//    	
//    	try {
//			remoteMethods rm = new remoteMethods();
//			SolutionResultList slr = rm.getErrorSolution(q);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
    }
}
