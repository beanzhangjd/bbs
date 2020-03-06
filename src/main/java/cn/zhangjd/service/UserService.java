package cn.zhangjd.service;




import cn.zhangjd.service.iService.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.zhangjd.bean.User;
import cn.zhangjd.ex.PhoneNotAvailableException;
import cn.zhangjd.mapper.DeptMapper;
import cn.zhangjd.mapper.UserMapper;
import cn.zhangjd.util.JwtUtil;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DeptMapper deptMapper;
	private String salt ="让我看到你们的双手";
	@Override
	public void register(String name, String phone, String password, String check_code, Integer company,Integer dept) {
		User user=new User();
		//判断验证码
		if(true) {
			
		}
		//如果手机号在数据库中已经存在
		if(userMapper.selectByPhone(phone)!=null) {
			throw new PhoneNotAvailableException("手机号已经注册");
		}
		String md5pwd=DigestUtils.md5Hex(password+salt);
		user.setName(name);
		user.setPhone(phone);
		user.setPassword(md5pwd);
		user.setDept_id(dept);
		user.setGrade(1);
		user.setHead("/image/head/default.jpg");
		userMapper.insert(user);
	}
	@Override
	public User login(String phone, String password) {
		User user=userMapper.selectByPhone(phone);
		String md5pwd=DigestUtils.md5Hex(password+salt);
		if(user!=null&&user.getPassword().equals(md5pwd)) {
			return user;
		}
		return null;
	}
	@Override
	public void updateName(String name, String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		user.setName(name);
		userMapper.updateName(user);
	}
	@Override
	public void updateDept(String phone,Integer company, Integer dept,String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User admin=userMapper.selectById(Integer.parseInt(strs[0]));
		if(admin.getGrade()>5){
			User user=userMapper.selectByPhone(phone);
			user.setDept_id(dept);
			userMapper.updateDept(user);
		}
	}
	@Override
	public void updateHead(String head,String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		user.setHead(head);
		userMapper.updateHead(user);
	}
	@Override
	public void updatePwd(String oldpwd, String newpwd, String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		String md5Old=DigestUtils.md5Hex(oldpwd+salt);
		String md5New=DigestUtils.md5Hex(newpwd+salt);
		if(md5Old.equals(user.getPassword())) {
			user.setPassword(md5New);
		}else {
			throw new RuntimeException("密码错误");
		}
		userMapper.updatePwd(user);
	}
	@Override
	public void updatePhone(String oldPhone,String phone,String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User admin=userMapper.selectById(Integer.parseInt(strs[0]));
		if (admin.getGrade()>5){
			User user=userMapper.selectByPhone(oldPhone);
			user.setPhone(phone);
			userMapper.updatePhone(user);
		}
	}
	@Override
	public void updateGrade(String phone,Integer grade, String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User admin=userMapper.selectById(Integer.parseInt(strs[0]));
		if (admin.getGrade()>5){
			User user=userMapper.selectByPhone(phone);
			user.setGrade(grade);
			userMapper.updateGrade(user);
		}
	}
	@Override
	public User getUserById(String token) {
		String[] str=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(str[0]));
		String phone=user.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		user.setPhone(phone);
		user.setPassword("*********");
		return user;
	}
	
	

}
