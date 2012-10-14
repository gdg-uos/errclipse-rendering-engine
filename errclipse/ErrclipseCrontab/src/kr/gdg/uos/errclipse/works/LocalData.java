package kr.gdg.uos.errclipse.works;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.gdg.uos.errclipse.HttpRequest.HttpRequest;
import kr.gdg.uos.errclipse.mysql.MysqlClient;
import kr.gdg.uos.errclipse.properties.PropertyController;

import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import LogWriter.LogWriter;

public class LocalData {
	private MysqlClient client;
	List<HashMap<String, Object>> mappingInformation;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getDataFromMySQL() throws ParseException, IOException{
		PropertyController property = PropertyController.getInstance();
		
		String serverUrl = property.getPropertyValues("server_url");
		String dbId = property.getPropertyValues("db_id");
		String dbPw = property.getPropertyValues("db_pw");
		String schemaName = property.getPropertyValues("project_name");
		
		if(dbPw.equals("null"))
			dbPw = "";
		
		client = new MysqlClient(serverUrl, dbId, dbPw, schemaName);
		client.Initialize();
		
		List<HashMap<String, Object>> clientData;
		List<HashMap<String,Object>> serverData;
		
		List<String> tableList = new ArrayList();
		
		tableList.add("language");
		tableList.add("library");
		tableList.add("method");
		tableList.add("error");
		tableList.add("solution");
		
		for(int i=0;i<tableList.size();i++){
			clientData = (List<HashMap<String, Object>>)client.select("select * from "+tableList.get(i));
			serverData =  getCurrentDesc(tableList.get(i));
			mappingInformation = mappingProc(tableList.get(i), clientData, serverData);
		}
			 
		 return true;
	 }
	
	
	
	@SuppressWarnings("unchecked")
	private List<HashMap<String,Object>> mappingProc(String type, List<HashMap<String, Object>> localData, List<HashMap<String, Object>> serverData) throws IOException{
		List<HashMap<String, Object>> mappingData = (List<HashMap<String, Object>>)client.select("select * from mapping WHERE table_name = \""+type+"\""); 
		List<HashMap<String, Object>> newMappingData = new ArrayList<HashMap<String,Object>>(); 
		for(int i=0;i<localData.size();i++){
			boolean isExist = false;
			for(int j=0;j<serverData.size();j++){
				if(localData.get(i).get(type+"_desc").toString().equalsIgnoreCase(serverData.get(j).get(type+"_desc").toString()) && // 같은 desc을 갖고 잇는게 있는가.
						!(localData.get(i).get(type+"_id").toString().equalsIgnoreCase(serverData.get(j).get(type+"_id").toString()))){ //그런데 id는 다른가.
					int k=0;
					isExist = true;
					for(k=0;k<mappingData.size();k++){
						if(mappingData.get(k).get("gae_id").toString() == serverData.get(j).get(type+"_id")){
							break;
						}
						if(k == mappingData.size()){
							HashMap<String, Object> mapper = new HashMap<String, Object>();
							mapper.put("table_name", type);
							mapper.put("gae_id", serverData.get(j).get(type+"_id"));
							mapper.put("local_id", localData.get(i).get(type+"_id"));
							
							newMappingData.add(mapper);
						}
					}
				}
			}
			if(!isExist){
				HashMap<String,Object> newData = localData.get(i);
//				newData.remove(type+"_id");
				
				String jsonString = MakeInsertData(type, newData);
				
				HttpRequest req = new HttpRequest();
				LogWriter writer = LogWriter.getInstance();
				writer.write(jsonString, true);
				
				System.out.println(jsonString);
				req.send("http://errclipse.appspot.com/Insert", jsonString);
			}
		}
		
		for(int i=0;i<newMappingData.size();i++){
			client.insert("mapping", newMappingData.get(i));
		}
		
		if(mappingData.addAll(newMappingData))
			return mappingData;
		else
			return null;
		
		
	}
	
	private List<HashMap<String, Object>> getCurrentDesc(String targetTable) throws ParseException{
		HttpRequest req = new HttpRequest();
		String data = req.send("http://errclipse.appspot.com/SelectAll/"+targetTable, null);

		JSONParser parser = new JSONParser();
//		Object obj = parser.parse(data);
//		Object obj2 = parser.parse(data2);
		
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> ret = (List<HashMap<String, Object>>)parser.parse(data);
		return ret;
		
	}
	private String MakeInsertData(String targetTable, Map<String, Object> data){
		Map<String, Object> ret = new HashMap<String, Object>();
		
		ret.put("targetTable", targetTable);
		ret.put("values", data);
		
		
		String jsonText = JSONValue.toJSONString(ret);
		
		return jsonText;
	}
	 

}
