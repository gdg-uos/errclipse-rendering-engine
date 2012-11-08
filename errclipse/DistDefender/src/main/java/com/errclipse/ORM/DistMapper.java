package com.errclipse.ORM;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.errclipse.ORM.bin.ChildPropBin;

public interface DistMapper {
	@Select("SELECT * from child_prop")
	List<ChildPropBin> getChildPropList(); 
	
}
