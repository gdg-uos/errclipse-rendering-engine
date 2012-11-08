package com.errclipse.ORM;

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

import com.errclipse.ORM.bin.ChildPropBin;


public class ConnectORM {
	private static SqlSessionFactory sqlSessionFactory;
	private static Logger logger = Logger.getLogger(ConnectORM.class);
	
	static{
		if(sqlSessionFactory == null){
			logger.info("create new Sql Session");
			Properties props = new Properties();
			String resource = "com/errclipse/ORM/batis-config.xml";
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
	private static String getMybatisProp(){
		ClassLoader propetyLoader = Thread.currentThread().getContextClassLoader();
		if(propetyLoader == null){
			propetyLoader = ClassLoader.getSystemClassLoader();
		}
		return propetyLoader.getResource("BatisDB.properties").toString().split("file:")[1];
	}
	
	public static List<ChildPropBin> getChildProp(){
		SqlSession session = sqlSessionFactory.openSession();
		List<ChildPropBin> list = null;
		try{
			DistMapper mapper = session.getMapper(DistMapper.class);
			list = mapper.getChildPropList();
		}catch(NullPointerException e){
			System.out.println("Null value from ORM");
		}finally{
			session.close();
		}
		return list;
	}
}
