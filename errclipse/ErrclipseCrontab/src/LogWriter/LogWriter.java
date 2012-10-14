package LogWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import kr.gdg.uos.errclipse.properties.PropertyController;

public class LogWriter {
	private static LogWriter instance;
	
	private String filePath = null;
	public void setPath(String path){
		filePath = path;
	}
	
	public LogWriter(){
	}
	
	public void write(String text, boolean isAppend) throws IOException{
		if(filePath == null){
			setPath(PropertyController.getInstance().getPropertyValues("logpath"));
		}
		FileWriter fw = new FileWriter(filePath, isAppend);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write((new Date()).toString()+": "+text);
		bw.newLine();
		bw.close();
		fw.close();
	}
	
	public static LogWriter getInstance(){
		if(instance == null){
			synchronized (LogWriter.class) {
				if(instance == null)
					instance = new LogWriter();
			}
		}
		return instance;
	}
	
	
}
