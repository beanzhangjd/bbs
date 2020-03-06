package cn.zhangjd.service.iService;


import cn.zhangjd.bean.User;

public interface IUserService {
	/**
	 * 注册
	 * @param name 姓名
	 * @param phone 手机号
	 * @param password 登陆密码
	 * @param check_code 验证码
	 * @param company 公司标号
	 * @param dept 部门标号
	 */
	void register(String name, String phone, String password, String check_code, Integer company, Integer dept);

	/**
	 * 登陆
	 * @param phone 手机号
	 * @param password 密码
	 * @return 登陆的用户信息
	 */
	User login(String phone, String password);

	/**
	 * 修改姓名
	 * @param name 姓名
	 * @param token 用户令牌
	 */
	void updateName(String name,String token);

	/**
	 * 修改头像
	 * @param head 头像路径
	 * @param token 用户令牌
	 */
	void updateHead(String head,String token);
	/**
	 *修改密码
	 * @param oldpwd 旧密码
	 * @param newpwd 新密码
	 * @param token 用户令牌
	 */
	void updatePwd(String oldpwd, String newpwd, String token);

	/**
	 * 修改手机号
	 * @param oldPhone 旧手机号
	 * @param phone 新手机号
	 * @param token 管理员令牌
	 */
	void updatePhone(String oldPhone,String phone,String token);

	/**
	 * 修改权限
	 * @param phone 用户手机号
	 * @param grade 新权限
	 * @param token 管理员令牌
	 */
	void updateGrade(String phone,Integer grade,String token);

	/**
	 * 修改部门
	 * @param phone 用户手机号
	 * @param company 新公司标号
	 * @param dept 新部门标号
	 * @param token 管理员令牌
	 */
	void updateDept(String phone,Integer company, Integer dept, String token);
	/**
	 * 获取当前登陆用户的非私密信息
	 * @param token 用令牌
	 * @return 用户信息
	 */
	User getUserById(String token);
}
