package com.errclipse.orm.connector;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class ConnectToORM implements IConnectToORM {

    String resource = "com/errclipse/orm/xml/sqlMapping.xml";
    Properties props = new Properties();

	

	public void initConnection() {
		props.put("driver" , "com.mysql.jdbc.Driver");
        props.put("url" , "jdbc:mysql://uoscs.com:3306/test_db");
        props.put("username" , "root");
        props.put("password" , "toori");
        
        SqlSession session = null;
        
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,props);
            session = sqlSessionFactory.openSession(false);

        } catch (IOException e){
            e.printStackTrace();
            return ;
        }
//        CityRecordBin cityRecordBin = new CityRecordBin("stephan","fuck");
//        int pk = session.insert("SqlSampleMapper.insertData",cityRecordBin);
//        System.out.println(pk);
        session.commit();
        session.close();

	}

	public String getLangID(String lang_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLibID(String lib_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMethodID(String method_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getErrorID(String error_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getSolutions(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
