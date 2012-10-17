package com.errclipse;

import com.errclipse.rmi.connectionManagement.RegisterServer;


public class runner {
    public static void main(String args[]) throws Exception{
		System.out.println("start");
		RegisterServer rs = new RegisterServer();
		rs.initiateServer();
		System.out.println("rmi server start");
    }
}
