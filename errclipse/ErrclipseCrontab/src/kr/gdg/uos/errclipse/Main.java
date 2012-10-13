package kr.gdg.uos.errclipse;

import java.util.HashMap;

import kr.gdg.uos.errclipse.HttpRequest.HttpRequest;
import kr.gdg.uos.errclipse.mysql.MysqlClient;

import org.json.simple.JSONValue;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(Exception e){
			
		}
		
		HttpRequest req = new HttpRequest();
		
		String res = req.send("http://errclipse.appspot.com/", "hey you monkey");
		
		System.out.println(res);
		
		//write the code here (crontab)


		/* example
		 * 
		 *
		MysqlClient client = new MysqlClient("127.0.0.1", "root", "", "test");
		client.Initialize();
		
		Object result = client.select("select * From test");
	
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("val", 3);
		
		client.insert("test",map);
		map.remove("val");
		map.put("val", 2);
		client.update("test", map, "id", 5);
		
		client.delete("test", "id", 6);
	
		String jsonText = JSONValue.toJSONString(result);
		
		System.out.println(jsonText);
		
		HttpRequest req;
		
		req = new HttpRequest();
		
		req.send("http://abcd.efg/target/", jsonText);
		
		*
		*/
	}

}
