package com.errclipse.rmi.connectionManagement;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

public class registerServer {
	Logger logger = Logger.getLogger(getClass());

	public void initiateServer() throws Exception{

		/* init ORM */
		Registry rmiRegistry = LocateRegistry.createRegistry(1099);
		rmiRegistry = LocateRegistry.getRegistry();
		remoteMethods rmm = new remoteMethods();
		rmiRegistry.rebind("rmiServer",rmm);
		logger.info("rmi server thread started");

	}
}
