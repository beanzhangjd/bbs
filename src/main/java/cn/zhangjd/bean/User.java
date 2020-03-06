package cn.zhangjd.bean;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;
/**
 * user表的实体类
 * @author Administrator
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 用户绑定手机号
	 */
	private String phone;
	/**
	 * 用户登录密码
	 */
	private String password;

	/**
	 * 用户部门
	 */
	private Integer dept_id;
	/**
	 * 用户头像路径
	 */
	private String head;
	/**
	 * 用户权限等级
	 */
	private Integer grade;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

}
