package cn.zhangjd.mapper;





import java.util.List;

import org.apache.ibatis.annotations.Select;
import cn.zhangjd.bean.Module;

public interface ModuleMapper {
	@Select("select * from module where id=#{id}")
	Module selectById(Integer id);
	@Select("select * from module")
	List<Module> selectAll();
}
