package com.errclipse.rmi.connectionManagement;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

public class RegisterServer {
	
	static Logger logger = Logger.getLogger(RegisterServer.class);

	public static void initiateServer() throws Exception{
		Registry rmiRegistry = LocateRegistry.createRegistry(1099);
		rmiRegistry = LocateRegistry.getRegistry();
		
		RemoteMethods remoteMethod = new RemoteMethods();
		
		rmiRegistry.rebind("rmiServer",remoteMethod);
		logger.info("rmi server thread started");
	}
}
