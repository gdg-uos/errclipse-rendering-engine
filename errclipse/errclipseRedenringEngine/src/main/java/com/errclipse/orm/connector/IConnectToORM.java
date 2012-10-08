package com.errclipse.orm.connector;

import java.util.List;

public interface IConnectToORM {
	void initConnection();
	String getLangID(String lang_name);
	String getLibID(String lib_name);
	String getMethodID(String method_name);
	String getErrorID(String error_name);
	List<String> getSolutions(String key);
}
