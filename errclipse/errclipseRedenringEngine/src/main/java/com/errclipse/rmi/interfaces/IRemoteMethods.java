package com.errclipse.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.errclipse.rmi.interfaces.urlList.*;
import com.errclipse.rmi.interfaces.ErrorBin.*;

public interface IRemoteMethods extends Remote {
    errorRelationUrls getErrorRelationUrl(SearchRequest errorQuery) throws RemoteException;
}