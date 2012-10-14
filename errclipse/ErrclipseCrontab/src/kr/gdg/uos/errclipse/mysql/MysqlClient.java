package kr.gdg.uos.errclipse.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import LogWriter.LogWriter;

public class MysqlClient {
	private String hostAddr;
	private String hostId;
	private String hostPw;
	private String shcemaName;
	
	Connection conn = null;
	
	public MysqlClient(String addr, String id, String pw, String schema){
		hostAddr = addr;
		hostId = id;
		hostPw = pw;
		shcemaName = schema;
	}
	
	public Boolean Initialize() throws IOException{
		try {
			conn = 
					DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s", 
							hostAddr, shcemaName, hostId, hostPw));
		} catch (SQLException e) {
			LogWriter writer = LogWriter.getInstance();
			writer.write("SQLException: " + e.getMessage(), true);
			writer.write("SQLState: "+e.getSQLState(), true);
			writer.write("VendorError: "+e.getErrorCode(), true);
			
			return false;
		}
		return true;
	}
	
	public Object select(String query) throws IOException{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<HashMap<String, Object>> list = new ArrayList();
		
		try{
			pstmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//pstmt = conn.prepareStatement(query);
			
			if(pstmt.execute(query)){
				rs = pstmt.getResultSet();
			}
			
			
			if(rs == null)
				return null;
			
			for(int i=0;rs.next();i++){
				ResultSetMetaData rsMetaData = rs.getMetaData();
				HashMap<String, Object> map = new HashMap<String, Object>();
				for(int j=1;j<=rsMetaData.getColumnCount();j++){
					map.put(rsMetaData.getColumnName(j), rs.getObject(j));
				}
				list.add(map);
			}
			
			
		}catch(SQLException e){
			LogWriter writer = LogWriter.getInstance();
			writer.write("SQLException: " + e.getMessage(), true);
			writer.write("SQLState: "+e.getSQLState(), true);
			writer.write("VendorError: "+e.getErrorCode(), true);
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){ }
			}
			
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e) { }
			}
		}
		
		return list;
		
	}
	public int insert(String targetTable, Map<String, Object> values) throws IOException{
		String query = "";
		
		String cond = "";
		String val = "";
		
		for(Entry<String, Object> entry : values.entrySet()){
			cond += entry.getKey() + ", ";
			if(entry.getValue().getClass().getName() == "java.lang.String")
			{
				val += "\""+entry.getValue() +"\", ";
			}else {
				val += entry.getValue()+", ";
			}
		}
		
		cond = cond.substring(0,cond.length()-2);
		val = val.substring(0, val.length()-2);
		
		query = "INSERT INTO "+targetTable + "("+cond+") VALUES("+val+")";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			
		} catch (SQLException e) {
			LogWriter writer = LogWriter.getInstance();
			writer.write("SQLException: " + e.getMessage(), true);
			writer.write("SQLState: "+e.getSQLState(), true);
			writer.write("VendorError: "+e.getErrorCode(), true);
		}finally{
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) { }
		}
		
		return 0;
	}
	
	public Boolean update(String targetTable, Map<String, Object> values, String targetColumn, Object targetValue) throws IOException{
		String query = "";
		
		String cond = "";
		
		query = "UPDATE "+targetTable+" SET ";
		
		for(Entry<String, Object> entry : values.entrySet()){
			cond += entry.getKey() + " = ";
			if(entry.getValue().getClass().getName() == "java.lang.String")
			{
				cond += "\""+entry.getValue() +"\", ";
			}else {
				cond += entry.getValue()+", ";
			}
			
		}
		
		cond = cond.substring(0,cond.length()-2);
		
		query += cond + " WHERE "+targetColumn+" = ";
		
		if(targetValue.getClass().getName() == "java.lang.String")
			query += "\""+targetValue+"\"";
		else
			query += targetValue;
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			
		} catch (SQLException e) {
			LogWriter writer = LogWriter.getInstance();
			writer.write("SQLException: " + e.getMessage(), true);
			writer.write("SQLState: "+e.getSQLState(), true);
			writer.write("VendorError: "+e.getErrorCode(), true);
		    return false;
		}finally{
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) { }
		}
		
		return true;
	}
	
	public Boolean delete(String targetTable, String targetColumn, Object targetValue) throws IOException{
		String query = "DELETE FROM "+targetTable + " WHERE "+targetColumn+" = ";
		
		if(targetValue.getClass().getName() == "java.lang.String"){
			query += "\""+targetValue+"\"";
		}else{
			query += targetValue;
		}
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			
		} catch (SQLException e) {
			LogWriter writer = LogWriter.getInstance();
			writer.write("SQLException: " + e.getMessage(), true);
			writer.write("SQLState: "+e.getSQLState(), true);
			writer.write("VendorError: "+e.getErrorCode(), true);
		    return false;
		}finally{
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) { }
		}
		
		return true;
	}
	
}
