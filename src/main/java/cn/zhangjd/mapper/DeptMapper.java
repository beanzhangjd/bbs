package cn.zhangjd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.zhangjd.bean.Dept;

public interface DeptMapper {
	@Select("select * from dept where id=#{id}")
	Dept selectById(Integer id);
	@Select("SELECT * FROM dept WHERE company_no IS NULL")
	List<Dept> selectAllCompany();
	@Select("select * from dept where company_no=#{company_no}")
	List<Dept> selectDept(Integer company_no);
	@Select("select dept from dept where id=#{id}")
	String selectName(Integer id);
}
