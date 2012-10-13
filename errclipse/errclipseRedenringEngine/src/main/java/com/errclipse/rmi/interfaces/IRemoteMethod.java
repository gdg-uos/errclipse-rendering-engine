package com.errclipse.rmi.interfaces;

import java.rmi.RemoteException;

import com.errclipse.rmi.interfaces.ErrorBin.SearchRequest;
import com.errclipse.rmi.interfaces.urlList.errorRelationUrls;

public interface IRemoteMethod {

	boolean updateProjectAssets(GProjectSetups projectSetups) throws RemoteException;
	errorRelationUrls getErrorRelationUrl(SearchRequest errorQuery) throws RemoteException;
	
}
