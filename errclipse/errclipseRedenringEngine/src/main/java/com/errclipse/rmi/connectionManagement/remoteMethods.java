package com.errclipse.rmi.connectionManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.errclipse.orm.connector.ConnectToORM;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.ErrorQuery;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.Solution;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.SolutionResultList;
import com.errclipse.rmi.interfaces.IRemoteMethod;

public class remoteMethods extends UnicastRemoteObject implements IRemoteMethod {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2048951723708899448L;

	public remoteMethods() throws RemoteException {}

	@Override
	public SolutionResultList getErrorSolution(ErrorQuery query) throws RemoteException {
		ConnectToORM.getLangID(query.getLanguageName());
		
		SolutionResultList result = SolutionResultList.newBuilder()
				.setIsSolutionFind(true)
				.setSolutionList(0, Solution.newBuilder().setDecs("test").build()).build();		
		return result;
	}
	
}
