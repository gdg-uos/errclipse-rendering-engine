package com.errclipse.child.property;

import java.util.List;

import com.errclipse.child.ORM.ConnectORM;

public class ChildPropertyHandler {
	private static List<ChildPropertyBin> childProperties;
	
	public static List<ChildPropertyBin> getChildProperties() {
		return childProperties;
	}
	
	static{
		if(childProperties != null){
			childProperties = ConnectORM.getChildProp();
		}
	}
	
	/**
	 * retrieve child list;
	 * @return true if successfully update child list
	 */
	public static boolean resettingChildList(){
		childProperties = ConnectORM.getChildProp();
		if(childProperties.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @param prop
	 * @return
	 */
	public static boolean addNewChild(ChildPropertyBin prop) {
		boolean isSuccess = false;
		isSuccess = ConnectORM.addNewChildProperty(prop);
		return isSuccess;
	}
}
