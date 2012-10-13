package com.errclipse.orm.connector;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.errclipse.orm.bin.*;



public class ConnectToORM {

    private static SqlSessionFactory sqlSessionFactory;
    static{
    	if(sqlSessionFactory == null){
            String resource = "com/errclipse/orm/xml/mybatis-conf.xml";
    		try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}    		
    	}
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
    		System.out.println(lang_id);
    		session.commit();
    		session.close();
    	}
        return lang_id;
	}

	public static int getLibID(String level_key,String lib_desc) {
    	int _id = -1;
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		_id = session.selectOne("selectLang", lib_desc);
    	}catch(NullPointerException e){
    		LibraryBin bin = new LibraryBin(level_key,lib_desc,0);
    		session.insert("insertLang", bin);
    		_id = bin.getLib_id();
    	}finally{
    		System.out.println(_id);
    		session.commit();
    		session.close();
    	}
        return _id;
	}

	public static int getMethodID(String level_key,String method_desc) {
		int _id = -1;
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		_id = session.selectOne("selectLang", method_desc);
    	}catch(NullPointerException e){
    		MethodBin bin = new MethodBin(level_key,method_desc,0);
    		session.insert("insertLang", bin);
    		_id = bin.getMethod_id();
    	}finally{
    		System.out.println(_id);
    		session.commit();
    		session.close();
    	}
        return _id;
	}

	public static int getErrorID(String level_key, String error_desc) {
		int _id = -1;
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		_id = session.selectOne("selectLang", error_desc);
    	}catch(NullPointerException e){
    		ErrorBin bin = new ErrorBin(level_key,error_desc,0);
    		session.insert("insertLang", bin);
    		_id = bin.getError_id();
    	}finally{
    		System.out.println(_id);
    		session.commit();
    		session.close();
    	}
        return _id;
	}

	public List<String> getSolutions(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
