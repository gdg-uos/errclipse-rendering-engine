package com.errclipse.orm.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.errclipse.orm.bin.ErrorBin;
import com.errclipse.orm.bin.LangBin;
import com.errclipse.orm.bin.LibraryBin;
import com.errclipse.orm.bin.MethodBin;
import com.errclipse.orm.bin.SolutionBin;

public class ConnectToORM {

	private static SqlSessionFactory sqlSessionFactory;
	private static Logger logger = Logger.getLogger(ConnectToORM.class);
	static{
		if(sqlSessionFactory == null){
			logger.info("create new Sql Session");
			Properties props = new Properties();
			String resource = "com/errclipse/orm/connector/mybatis-conf.xml";
			try {
				FileInputStream propertyFileInput = new FileInputStream(getMybatisProp());
				props.load(propertyFileInput);
				InputStream inputStream = Resources.getResourceAsStream(resource);

				logger.info(String.format("Driver Info : %s ", props.getProperty("errclipse.db.driver")));
				logger.info(String.format("JDBC Url Info : %s ", props.getProperty("errclipse.db.url")));
				logger.info(String.format("User Info : %s ", props.getProperty("errclipse.db.username")));
				/* dangerous, must change hash method md5 */
				logger.info(String.format("Passwd Info : %s ", props.getProperty("errclipse.db.username").toString().hashCode()));

				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,props);
				propertyFileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    		
		}
	}
	private static String getMybatisProp(){
		ClassLoader propetyLoader = Thread.currentThread().getContextClassLoader();
		if(propetyLoader == null){
			propetyLoader = ClassLoader.getSystemClassLoader();
		}
		return propetyLoader.getResource("myBatis.properties").toString().split("file:")[1];
	}

	public static int getLangID(String language_desc) {
		int lang_id = -1;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			lang_id = session.selectOne("selectLang", language_desc);
		}catch(NullPointerException e){
			LangBin bin = new LangBin(language_desc,0);
			session.insert("insertLang", bin);
			lang_id = bin.getLang_id();
		}finally{
			session.commit();
			session.close();
		}
		return lang_id;
	}

	public static int getLibID(String level_key,String lib_desc) {
		int _id = -1;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			_id = session.selectOne("selectLib", lib_desc);
		}catch(NullPointerException e){
			LibraryBin bin = new LibraryBin(level_key,lib_desc,0);
			session.insert("insertLib", bin);
			_id = bin.getLib_id();
		}finally{
			session.commit();
			session.close();
		}
		return _id;
	}

	public static int getMethodID(String level_key,String method_desc) {
		int _id = -1;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			_id = session.selectOne("selectMethod", method_desc);
		}catch(NullPointerException e){
			MethodBin bin = new MethodBin(level_key,method_desc,0);
			session.insert("insertMethod", bin);
			_id = bin.getMethod_id();
		}finally{
			session.commit();
			session.close();
		}
		return _id;
	}

	public static int getErrorID(String level_key, String error_desc) {
		int _id = -1;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			_id = session.selectOne("selectError", error_desc);
		}catch(NullPointerException e){
			ErrorBin bin = new ErrorBin(level_key,error_desc,0);
			session.insert("insertError", bin);
			_id = bin.getError_id();
		}finally{
			session.commit();
			session.close();
		}
		return _id;
	}

	public static List<SolutionBin> getSolutions(String level_key) {
		SqlSession session = sqlSessionFactory.openSession();
		List<SolutionBin> solutionList = null;
		System.out.println(level_key);
		try{
			solutionList = session.selectList("selectSolution", level_key);
		}catch(NullPointerException e){
			System.out.println("error null list");

		}finally{
			session.commit();
			session.close();
		}
		return solutionList;
	}

	public static int insertNewSolution(String level_key, String desc){
		SqlSession session = sqlSessionFactory.openSession();
		SolutionBin newSolution = new SolutionBin(level_key,desc);
		
		int pk = session.insert("insertSolution", newSolution);
		
		session.commit();
		session.close();
		return pk;
	}
	public static void recommendSolution(int solution_id){
		SqlSession session = sqlSessionFactory.openSession();
		session.update("increaseSolutionScore",solution_id);
		session.commit();
		session.close();
	}
}