package cn.zhangjd.service.iService;

import java.util.List;

import cn.zhangjd.bean.Dept;
import cn.zhangjd.bean.DeptAndCompanyVo;

public interface IDeptService {

	List<Dept> getCompany();

	List<Dept> getDeptByCompany(Integer company_no);

	DeptAndCompanyVo getDeptByUser(Integer dept_id);

	

}
