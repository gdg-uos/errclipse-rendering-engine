package com.errclipse.child.ORM;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.errclipse.child.property.ChildPropertyBin;


public class ConnectORM {
	private static SqlSessionFactory sqlSessionFactory;
	private static Logger logger = Logger.getLogger(ConnectORM.class);
	
	static{
		if(sqlSessionFactory == null){
			logger.info("create new Sql Session");
			Properties props = new Properties();
			String resource = "com/errclipse/child/ORM/batis-config.xml";
			try {
				FileInputStream propertyFileInput = new FileInputStream(getMybatisProp());
				props.load(propertyFileInput);
				InputStream inputStream = Resources.getResourceAsStream(resource);

				logger.info(String.format("Driver Info : %s ", props.getProperty("errclipse.db.driver")));
				logger.info(String.format("JDBC Url Info : %s ", props.getProperty("errclipse.db.url")));
				logger.info(String.format("User Info : %s ", props.getProperty("errclipse.db.username")));
				/* TODO dangerous, must change hash method md5 */
				logger.info(String.format("Passwd Info : %s ", props.getProperty("errclipse.db.username").toString().hashCode()));

				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,props);
				sqlSessionFactory.getConfiguration().addMapper(DistMapper.class);
				propertyFileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    		
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private static String getMybatisProp(){
		ClassLoader propetyLoader = Thread.currentThread().getContextClassLoader();
		if(propetyLoader == null){
			propetyLoader = ClassLoader.getSystemClassLoader();
		}
		return propetyLoader.getResource("BatisDB.properties").toString().split("file:")[1];
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<ChildPropertyBin> getChildProp(){
		SqlSession session = sqlSessionFactory.openSession();
		List<ChildPropertyBin> list = null;
		try{
			DistMapper mapper = session.getMapper(DistMapper.class);
			list = mapper.getChildPropList();
		}catch(NullPointerException e){
			list = new ArrayList<ChildPropertyBin>();
		}finally{
			session.commit();
			session.close();
		}
		return list;
	}
	/**
	 * 
	 * @param prop
	 * @return
	 */
	public static boolean addNewChildProperty(ChildPropertyBin prop){
		SqlSession session = sqlSessionFactory.openSession();
		boolean isSuccess = true;
		if(!isPropertyExist(prop)){
			try{
				DistMapper mapper = session.getMapper(DistMapper.class);
				mapper.insertNewPropTOTable(prop);
			}catch(NullPointerException e){
				isSuccess = false;
				session.rollback();
			}finally{
				session.commit();
				session.close();
			}
		}else{
			isSuccess = false;
		}
		return isSuccess;
	}
	/**
	 * 
	 * @param prop
	 * @return
	 */
	private static boolean isPropertyExist(ChildPropertyBin prop){
		SqlSession session = sqlSessionFactory.openSession();
		boolean isExist = true;
		String _id = ""; 
		try{
			DistMapper mapper = session.getMapper(DistMapper.class);
			 _id = mapper.isChildExist(prop.calculateChildKey());
		}catch(NullPointerException e){
			e.printStackTrace();
		}finally{
			session.commit();
			session.close();
		}
		if(_id == null){
			isExist = false;
		}else{
			isExist = true;
		}
		return isExist;
	}
}
