package cn.zhangjd.service;

import java.util.List;

import cn.zhangjd.service.iService.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhangjd.bean.Dept;
import cn.zhangjd.bean.DeptAndCompanyVo;
import cn.zhangjd.mapper.DeptMapper;
@Service
public class DeptService implements IDeptService {
	@Autowired
	private DeptMapper deptMapper;
	@Override
	public List<Dept> getCompany() {
		return deptMapper.selectAllCompany();
	}
	@Override
	public List<Dept> getDeptByCompany(Integer company_no) {
		
		return deptMapper.selectDept(company_no);
	}
	@Override
	public DeptAndCompanyVo getDeptByUser(Integer dept_id) {
		DeptAndCompanyVo dept=new DeptAndCompanyVo();
		Dept temp=deptMapper.selectById(dept_id);
		dept.setId(dept_id);
		dept.setDept(temp.getDept());
		dept.setCompany_no(temp.getCompany_no());
		dept.setCompany(deptMapper.selectById(temp.getCompany_no()).getDept());
		return dept; 
	}


}
