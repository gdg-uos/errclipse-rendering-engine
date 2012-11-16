package kr.gdg.uos.errclipse;

import it.sauronsoftware.cron4j.Scheduler;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import kr.gdg.uos.errclipse.HttpRequest.HttpRequest;
import kr.gdg.uos.errclipse.properties.PropertyController;
import kr.gdg.uos.errclipse.works.LocalData;


public class Main {
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(Exception e){
			
		}
		
		Scheduler s = new Scheduler();
		
		PropertyController p = PropertyController.getInstance();
		
		String period = p.getPropertyValues("runnable_time");
		
		s.schedule(period, new Runnable() {
			
			@Override
			public void run() {
				LocalData localData = new LocalData();
				
				try {
					localData.getDataFromMySQL();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		s.start();
		
		
		
		
		
	}
	
	 

}
