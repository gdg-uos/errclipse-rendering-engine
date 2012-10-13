package com.errclipse.rmi.connectionManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.errclipse.rmi.interfaces.ErrorBin.SearchRequest;
import com.errclipse.rmi.interfaces.GProjectSetups;
import com.errclipse.rmi.interfaces.IRemoteMethod;
import com.errclipse.rmi.interfaces.urlList.errorRelationUrls;

public class remoteMethods extends UnicastRemoteObject implements IRemoteMethod {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2048951723708899448L;

	public remoteMethods() throws RemoteException {}
	
	public boolean updateProjectAssets(GProjectSetups projectSetups) throws RemoteException {
		/* TODO trigger Cron to get Latest GAE data */
		
		return false;
	}
	
	public errorRelationUrls getErrorRelationUrl(SearchRequest errorQuery) throws RemoteException {

        /* TODO using mybatis to retrieve related urls */
		

        errorRelationUrls relateUrl = errorRelationUrls.newBuilder()
        		.addUrls("test").addUrls("test2").addUrls("test3")
        		.setErrorId(1)
                .build();

        return relateUrl;  //To change body of implemented methods use File | Settings | File Templates.
    }
	
	public int testMethod() throws RemoteException{
		return 0;
				
	}
}
