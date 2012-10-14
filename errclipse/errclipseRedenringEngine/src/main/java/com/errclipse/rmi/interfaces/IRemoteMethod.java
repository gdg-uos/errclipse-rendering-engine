package com.errclipse.rmi.interfaces;

import java.rmi.RemoteException;

import com.errclipse.rmi.interfaces.GGeneralErrorClazz.*;


public interface IRemoteMethod {
	SolutionResultList getErrorSolution(ErrorQuery query) throws RemoteException;
}
