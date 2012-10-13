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
    		lang_id = session.insert("insertLang", bin);
//    		return lang_id;
    	}finally{
    		System.out.println(lang_id);
    		session.commit();
    		session.close();
    	}
        return lang_id;
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
