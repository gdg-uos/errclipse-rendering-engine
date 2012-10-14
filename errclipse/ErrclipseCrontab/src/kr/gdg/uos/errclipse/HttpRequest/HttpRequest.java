package kr.gdg.uos.errclipse.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
	
	public HttpRequest(){
		
	}
	
	public String send(String target, String data){
		URL url;
		try {
			url = new URL(target);
			URLConnection conn = url.openConnection();
			
			conn.setDoOutput(true);
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			
			if(data != null){
				osw.write(data);
				osw.flush();
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String res = "";
			String line;
			
			while((line = br.readLine()) != null){
				res += line;
			}
			osw.close();
			br.close();
			
			return res;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}
