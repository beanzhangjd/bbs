package cn.zhangjd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhangjd.bean.Dept;
import cn.zhangjd.bean.ResponseResult;
import cn.zhangjd.bean.User;
import cn.zhangjd.service.iService.IDeptService;
import cn.zhangjd.service.iService.IUserService;
import cn.zhangjd.util.JwtUtil;
/**
 * 无需任何权限的控制器类：用来用户注册，登陆，重置密码
 */
@Controller
public class DefaultController {
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping("/")
	public String showIndex() {
		return "redirect:/api/showIndex";
	}

	/**
	 * 展示登陆页面
	 */
	@RequestMapping("/showLogin")
	public String showLogin() {
		return "/api/login.html";
	}

	/**
	 * 展示注册页面
	 */
	@RequestMapping("/showRegister")
	public String showRegister() {
		return "/api/register.html";
	}
	/**
	 * 展示重置密码页面
	 */
	@RequestMapping("/showUpdatePwd")
	public String showUpdatePwd() {
		return "/api/updatepwd.html";
	}

	@Autowired
	private IUserService userService;

	/**
	 * 注册请求
	 * @param name 姓名
	 * @param phone 手机号
	 * @param password 登陆密码
	 * @param check_code 验证码
	 * @param company 公司id
	 * @param dept 部门id
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/register")
	@ResponseBody
	public ResponseResult<Void>  register(String name,String phone,String password,String check_code,Integer company,Integer dept) {
		ResponseResult<Void> json=new ResponseResult<Void>();
		try {
			userService.register(name, phone, password, check_code, company, dept);
			json.setState(1);
		} catch (Exception e) {
			json.setState(0);
		}
		return json;
	}

	/**
	 * 登陆请求：成功发放信息令牌
	 * @param phone 手机号
	 * @param password 登陆密码
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResponseResult<String> login(String phone,String password){
		try{
			User user=userService.login(phone, password);
			String[] val= {user.getId().toString(),user.getName(),user.getPhone(),user.getGrade().toString()};
			String token=JwtUtil.setToken("user",val);
			return new ResponseResult<String>(user!=null,token);
		}catch (Exception e){
			return new ResponseResult<String>(false);
		}
	}

	/**
	 * 展示所有公司名供注册者选择
	 * @return json{state:成功失败，message:描述 List<Dept>集合中每个元素为一个公司标号和名字}
	 */
	@RequestMapping("/showCompany")
	@ResponseBody
	public ResponseResult<List<Dept>> showCompany(){
		try{
			List<Dept> depts=deptService.getCompany();
			return new ResponseResult<List<Dept>>(depts!=null&&depts.size()>0,depts);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 展示选中公司下的部门信息
	 * @param company_no 公司标号
	 * @return json{state:成功失败，message:描述 List<Dept>集合中的每个元素是部门标号和名字}
	 */

	@RequestMapping("/showDeptByCompany")
	@ResponseBody
	public ResponseResult<List<Dept>> showDeptByCompany(Integer company_no){
		try {
			List<Dept> depts=deptService.getDeptByCompany(company_no);
			return new ResponseResult<List<Dept>>(depts!=null&&depts.size()>0,depts);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
}
