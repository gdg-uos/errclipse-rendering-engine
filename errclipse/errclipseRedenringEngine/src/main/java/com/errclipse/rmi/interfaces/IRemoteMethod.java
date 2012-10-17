package com.errclipse.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.errclipse.rmi.interfaces.GGeneralErrorClazz.ErrorQuery;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.SolutionResultList;



public interface IRemoteMethod extends Remote {
	SolutionResultList getErrorSolution(ErrorQuery query) throws RemoteException ;
	void recommendSolution(String level_key,int id) throws RemoteException ;
	void createNewSolution(String level_key, String desc) throws RemoteException ; 
	String getSolutionLevelKey(ErrorQuery query) throws RemoteException;
}
