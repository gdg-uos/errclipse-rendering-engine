package com.errclipse.rmi.connectionManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;

import com.errclipse.orm.bin.SolutionResultBin;
import com.errclipse.orm.connector.ConnectToORM;
import com.errclipse.rmi.interfaces.IRemoteMethod;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.ErrorQuery;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.Solution;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.SolutionResultList;

public class remoteMethods extends UnicastRemoteObject implements IRemoteMethod {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2048951723708899448L;

	public remoteMethods() throws RemoteException {}

	@Override
	public SolutionResultList getErrorSolution(ErrorQuery query) throws RemoteException {
		
		StringBuilder levelKeyBuilder = new StringBuilder();
		SolutionResultList.Builder result = SolutionResultList.newBuilder();
		List<SolutionResultBin> list;
		// get language_id 
		String _id = String.format("%03d", ConnectToORM.getLangID(query.getLanguageName()));
		levelKeyBuilder.append(_id);
		
		_id = String.format("%03d", ConnectToORM.getLibID(levelKeyBuilder.toString(), query.getLibraryName()));
		levelKeyBuilder.append(_id);

		_id = String.format("%03d", ConnectToORM.getMethodID(levelKeyBuilder.toString(), query.getMethodName()));
		levelKeyBuilder.append(_id);
		
		_id = String.format("%03d", ConnectToORM.getErrorID(levelKeyBuilder.toString(), query.getErrorName()));
		levelKeyBuilder.append(_id);
		
		list = ConnectToORM.getSolutions(levelKeyBuilder.toString());
		System.out.println(levelKeyBuilder.toString());
		if(list != null){
			result.setIsSolutionFind(true);
			for(int i=0;i<list.size();i++){
				result.addSolutionList( Solution.newBuilder()
						.setDecs(list.get(i).getSolution_desc())
						.setGlobalScore(0)
						.setLocalScore(0)
						.build() );
				System.out.println("find any : "+ list.get(i).getSolution_desc());
			}
		}	
		return result.build();
	}

	@Override
	public void recommendSolution(String level_key, int id)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewSolution(String level_key, String desc)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
