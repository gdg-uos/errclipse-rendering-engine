package com.errclipse.rmi.connectionManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.errclipse.rmi.interfaces.ErrorBin.SearchRequest;
import com.errclipse.rmi.interfaces.IRemoteMethods;
import com.errclipse.rmi.interfaces.urlList.errorRelationUrls;

public class remoteMethods extends UnicastRemoteObject implements IRemoteMethods{



	/**
	 * 
	 */
	private static final long serialVersionUID = -2048951723708899448L;

	public remoteMethods() throws RemoteException {}

	public errorRelationUrls getErrorRelationUrl(SearchRequest errorQuery) throws RemoteException {

        /* TODO using mybatis to retrieve related urls */

        errorRelationUrls relateUrl = errorRelationUrls.newBuilder()
        		.addUrls("test").addUrls("test2")
        		.setErrorId(1)
                .build();

        return relateUrl;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
