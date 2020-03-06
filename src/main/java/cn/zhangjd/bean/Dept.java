package cn.zhangjd.bean;

import java.io.Serializable;

/**
 * 部门实体
 */
public class Dept implements Serializable {
	private Integer id;
	private String dept;
	private Integer company_no;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getCompany_no() {
		return company_no;
	}
	public void setCompany_no(Integer company_no) {
		this.company_no = company_no;
	}

	
}
