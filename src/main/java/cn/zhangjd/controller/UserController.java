package cn.zhangjd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhangjd.bean.Dept;
import cn.zhangjd.bean.DeptAndCompanyVo;
import cn.zhangjd.bean.ResponseResult;
import cn.zhangjd.bean.User;
import cn.zhangjd.service.iService.IDeptService;
import cn.zhangjd.service.iService.IUserService;

/**
 * 用户控制器类：查询修改用户信息，所有请求需登陆后方可执行
 */
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IDeptService deptService;
	/**
	 * 修改用户姓名的请求
	 * @param name 用户名字
	 * @param token 用户权限令牌
	 * @return json{state:成功失败}
	 */
	@RequestMapping("/updateName")
	public ResponseResult<Void> updateName(String name,@CookieValue("Authority") String token){
		ResponseResult<Void> json=new ResponseResult<Void>();
		try {
			userService.updateName(name,token);
			json.setState(1);
		} catch (Exception e) {
			json.setState(0);
		}
		return json;
	}

	/**
	 * 修改用户登陆密码的请求
	 * @param oldpwd 旧密码
	 * @param newpwd 新密码
	 * @param token 用户令牌
	 * @return json{state:成功失败}
	 */
	@RequestMapping("/updatePassword")
	public ResponseResult<Void> updatePassword(String oldpwd,String newpwd,@CookieValue("Authority") String token){
		ResponseResult<Void> json=new ResponseResult<Void>();
		try {
			userService.updatePwd(oldpwd,newpwd,token);
			json.setState(1);
		} catch (Exception e) {
			json.setState(0);
		}
		return json;
	}


	@RequestMapping("/updateDept")
	public ResponseResult<Void> updateDept(String phone,Integer company,Integer dept,@CookieValue("Authority") String token){
		ResponseResult<Void> json=new ResponseResult<Void>();
		try {
			userService.updateDept(phone,company, dept, token);
			json.setState(1);
		} catch (Exception e) {
			json.setState(0);
		}
		return json;
	}

	/**
	 * 修改头像请求
	 * @param head 新的头像路径
	 * @param token 用户令牌
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/updateHead")
	public ResponseResult<Void> updateHead(String head,@CookieValue("Authority") String token){
		ResponseResult<Void> json=new ResponseResult<Void>();
		try {
			userService.updateHead(head,token);
			json.setState(1);
		} catch (Exception e) {
			json.setState(0);
		}
		return json;
	}

	/**
	 * 修改用户绑定手机号：需管理员权限
	 * @param oldPhone 旧手机号
	 * @param phone 新的手机号
	 * @param token 管理员令牌
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/updatePhone")
	public ResponseResult<Void> updatePhone(String oldPhone,String phone,@CookieValue("Authority") String token){
		ResponseResult<Void> json=new ResponseResult<Void>();
		try {
			userService.updatePhone(oldPhone,phone,token);
			json.setState(1);
		} catch (Exception e) {
			json.setState(0);
		}
		return json;
	}

	/**
	 * 修改用户权限请求：需管理员权限
	 * @param grade 新的权限
	 * @param token 管理员令牌
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/updateGrade")
	public ResponseResult<Void> updateGrade(String phone,Integer grade,@CookieValue("Authority") String token){
		try {
			userService.updateGrade(phone,grade, token);
			return new ResponseResult<>(true);
		} catch (Exception e) {
			return new ResponseResult<>(false);
		}
	}

	/**
	 * 获取用户的非隐秘信息
	 * @param token 用户令牌
	 * @return json{state:成功失败，message:描述，user：用户的非隐秘信息}
	 */
	@RequestMapping("/getUserById")
	public ResponseResult<User> getUserById(@CookieValue("Authority") String token){
		try {
			User user=userService.getUserById(token);
			return new ResponseResult<User>(user!=null,user);
		}catch (Exception e) {
			return new ResponseResult<User>(false);
		}
	}
	/**
	 * 根据部门id获取部门信息
	 * @param dept_id 部门id
	 * @return json{state:成功失败，message:描述，dept：公司名，部门名}
	 */
	@RequestMapping("/getDeptByUser")
	public ResponseResult<DeptAndCompanyVo> getDeptByUser(Integer dept_id){
		try {
			DeptAndCompanyVo dept= deptService.getDeptByUser(dept_id);
			return new ResponseResult<DeptAndCompanyVo>(dept!=null,dept);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
}
