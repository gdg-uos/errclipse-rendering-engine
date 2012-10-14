package com.errclipse;

import com.errclipse.rmi.connectionManagement.registerServer;

public class runner {
    public static void main(String args[]){
		System.out.println("start");
		registerServer rs = new registerServer();
		try {
			rs.initiateServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("rmi server start");
    	
    }
}
