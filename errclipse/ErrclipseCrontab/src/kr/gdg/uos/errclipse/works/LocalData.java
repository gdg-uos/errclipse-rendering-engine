package kr.gdg.uos.errclipse.works;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONValue;

import kr.gdg.uos.errclipse.mysql.MysqlClient;

public class LocalData {
	final private String PROP_PATH = "errclipse.properties";
	
	public boolean getDataFromMySQL(){
		String serverUrl = getPropertyValues("server_url");
		String dbId = getPropertyValues("db_id");
		String dbPw = getPropertyValues("db_pw");
		String schemaName = getPropertyValues("project_name");
		
		if(dbPw.equals("null"))
			dbPw = "";
		
		MysqlClient client = new MysqlClient(serverUrl, dbId, dbPw, schemaName);
		client.Initialize();
		
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)client.select("select * from language");
		
		for(int i=0;i<list.size(); i++){
			System.out.println(MakeInsertData(("language"), list.get(i)));
		}
		
		
		 
		 return true;
	 }
	
	private String MakeInsertData(String targetTable, Map<String, Object> data){
		Map<String, Object> ret = new HashMap<String, Object>();
		
		ret.put("targetTable", targetTable);
		ret.put("values", data);
		
		
		String jsonText = JSONValue.toJSONString(ret);
		
		return jsonText;
	}
	 
	 private String getPropertyValues(String keyName){
		 String ret = null;
		 
		 try{
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(PROP_PATH);
			props.load(new BufferedInputStream(fis));
			ret = props.getProperty(keyName);
			fis.close();
		 }catch (Exception e){
			 System.out.print("error: cannot read the property values("+keyName+")");
		 }
		 
		 return ret;
	 }
	 
	 private void setPropertyValues(String keyName, String value){
		 try{
			 Properties props = new Properties();
			 FileInputStream fis = new FileInputStream(PROP_PATH);
			 props.load(new BufferedInputStream(fis));
			 props.setProperty(keyName, value);
			 props.store(new FileOutputStream(PROP_PATH), "");
			 fis.close();
		 } catch(Exception e){
			 System.out.println("error: cannot write the new property values");
		 }
	 }
}
