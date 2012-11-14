package com.errclipse.child.ORM;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.errclipse.child.property.ChildPropertyBin;

public interface DistMapper {
	@Select("SELECT * from child_prop")
	List<ChildPropertyBin> getChildPropList(); 
	@Insert("INSERT INTO child_prop ( db_driver, db_url, db_username, db_passwd, child_key) VALUES (#{db_driver},#{db_url},#{db_username},#{db_passwd},#{child_key} )")
	int insertNewPropTOTable(ChildPropertyBin prop);
	@Select("SELECT child_id FROM child_prop WHERE child_key=#{child_key}")
	String isChildExist(String child_key);
}
