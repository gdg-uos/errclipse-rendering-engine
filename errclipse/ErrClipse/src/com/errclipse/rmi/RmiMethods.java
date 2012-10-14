package com.errclipse.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import kr.uoscs.errclipse.HelperView;

import com.errclipse.rmi.interfaces.GGeneralErrorClazz.ErrorQuery;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.Solution;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.SolutionResultList;
import com.errclipse.rmi.interfaces.IRemoteMethod;

public class RmiMethods {
	static String serverUrl = "rmi://192.168.1.12:1099/rmiServer";
	static IRemoteMethod remoteMethod;
	static {
		try {
			remoteMethod = (IRemoteMethod)Naming.lookup(serverUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	public static List<Solution> searchSolution(String language, String library, String method, String error) throws RemoteException,NullPointerException{
		List<Solution> solutionDescList = new ArrayList<Solution>();
		ErrorQuery q = ErrorQuery.newBuilder()
				.setLanguageName(language)
				.setLibraryName(library)
				.setMethodName(method)
				.setErrorName(error).build();
		
		HelperView.text1.setText("Complete");

		SolutionResultList resultList = remoteMethod.getErrorSolution(q);
		if(resultList.getIsSolutionFind()){
			for(int i=0;i<resultList.getSolutionListCount();i++){
				solutionDescList.add(resultList.getSolutionListList().get(i));
			}
		}
		if(solutionDescList.size() == 0){
			throw new NullPointerException();
		}
		return solutionDescList;
	}
	public static void insertNewSolution(String level_key, String desc) throws RemoteException{
		remoteMethod.createNewSolution(level_key, desc);
	}
	public static String getSolutionLevelKey(String language, String library, String method, String error) throws RemoteException,NullPointerException{
		ErrorQuery q = ErrorQuery.newBuilder()
				.setLanguageName(language)
				.setLibraryName(library)
				.setMethodName(method)
				.setErrorName(error).build();

		String level_key = remoteMethod.getSolutionLevelKey(q);
		if(level_key == null){
			throw new NullPointerException();
		}
		return level_key;	
	}

}
