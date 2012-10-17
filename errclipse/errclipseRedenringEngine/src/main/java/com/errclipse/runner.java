package com.errclipse;

import com.errclipse.rmi.connectionManagement.registerServer;


public class runner {
    public static void main(String args[]) throws Exception{
		System.out.println("start");
		registerServer rs = new registerServer();
		rs.initiateServer();
		System.out.println("rmi server start");
    }
}
