package kr.gdg.uos.errclipse.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


public class PropertyController {
	final private String PROP_PATH = "errclipse.properties";
	private static PropertyController instance;
	
	public static PropertyController getInstance(){
		if(instance == null){
			synchronized (PropertyController.class) {
				if(instance == null)
					instance = new PropertyController();
			}
		}
		return instance;
	}
	
	 public String getPropertyValues(String keyName){
		 String ret = null;
		 
		 try{
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(PROP_PATH);
			props.load(new BufferedInputStream(fis));
			ret = props.getProperty(keyName);
			fis.close();
		 }catch (Exception e){
			 try{
				 Properties props = new Properties();
				 FileInputStream fis = new FileInputStream("/daemon/errclipse.properties");
				 props.load(new BufferedInputStream(fis));
					ret = props.getProperty(keyName);
					fis.close();
			 }catch(Exception ee){
				 System.out.print("error: cannot read the property values("+keyName+")");
			 }
		 }
		 
		 return ret;
	 }
	 
	 public void setPropertyValues(String keyName, String value){
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
