package com.errclipse.rmi.connectionManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import com.errclipse.orm.bin.SolutionResultBin;
import com.errclipse.orm.connector.ConnectToORM;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.ErrorQuery;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.Solution;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.SolutionResultList;
import com.errclipse.rmi.interfaces.IRemoteMethod;

public class RemoteMethods extends UnicastRemoteObject implements IRemoteMethod {
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = -2048951723708899448L;

	public RemoteMethods() throws RemoteException {}

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
		logger.info("search key is " + levelKeyBuilder.toString());
		System.out.println(levelKeyBuilder.toString());
		if(list != null){
			logger.info(String.format("we found %d solution",list.size()));
			result.setIsSolutionFind(true);
			for(int i=0;i<list.size();i++){
				result.addSolutionList( Solution.newBuilder()
						.setDecs(list.get(i).getSolution_desc())
						.setGlobalScore(0)
						.setLocalScore(0)
						.build() );
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
		if(ConnectToORM.insertNewSolution(level_key, desc) == -1){
			logger.warn(String.format("problem with solution level_key %s, desc %s",level_key,desc));
		}else{
			logger.info(String.format("insert success solutions level_key %s, desc %s", level_key,desc));
		}
	}

	@Override
	public String getSolutionLevelKey(ErrorQuery query) throws RemoteException {
		StringBuilder levelKeyBuilder = new StringBuilder();
		
		String _id = String.format("%03d", ConnectToORM.getLangID(query.getLanguageName()));
		levelKeyBuilder.append(_id);
		
		_id = String.format("%03d", ConnectToORM.getLibID(levelKeyBuilder.toString(), query.getLibraryName()));
		levelKeyBuilder.append(_id);

		_id = String.format("%03d", ConnectToORM.getMethodID(levelKeyBuilder.toString(), query.getMethodName()));
		levelKeyBuilder.append(_id);
		
		_id = String.format("%03d", ConnectToORM.getErrorID(levelKeyBuilder.toString(), query.getErrorName()));
		levelKeyBuilder.append(_id);
		
		return levelKeyBuilder.toString();
	}
	
}
