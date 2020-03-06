package cn.zhangjd.mapper;




import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import cn.zhangjd.bean.User;


public interface UserMapper {
	/**
	 * 向用户表添加一条数据
	 * @param user
	 */
	@Insert("insert into user(name,phone,password,dept_id,grade,head) values(#{name},#{phone},#{password},#{dept_id},#{grade},#{head})")
	void insert(User user);
	
	/**
	 * 根据用户绑定手机查询用户
	 */
	@Select("select * from user where phone=#{phone}")
	User selectByPhone(String phone);
	/**
	 * 根据用户id查询用户
	 */
	@Select("select * from user where id=#{id}")
	User selectById(Integer id);
	/**
	 * 根据用户id修改属性
	 */
	@Update("update user set name=#{name} where id=#{id}")
	void updateName(User user);
	@Update("update user set password=#{password} where id=#{id}")
	void updatePwd(User user);
	@Update("update user set dept_id=#{dept_id} where id=#{id}")
	void updateDept(User user);
	@Update("update user set head=#{head} where id=#{id}")
	void updateHead(User user);
	/**
	 * 暂定
	 */
	@Update("update user set phone=#{phone} where id=#{id}")
	void updatePhone(User user);
	@Update("update user set grade=#{grade} where id=#{id}")
	void updateGrade(User user);
}
