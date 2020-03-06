package cn.zhangjd.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.zhangjd.bean.Content;
import cn.zhangjd.bean.Contents;

public interface ContentMapper {
	@Insert("insert into content values(null,#{create_user},now(),#{modify_user},now(),#{post_id},#{storey},#{text})")
	void insert(Content content);
	@Select("select count(distinct post_id) from content where create_user=#{create_user}")
	Integer selectByUser(Integer create_user);
	@Select("select c.id id,u.name name,u.head head,d.company_no company,d.dept dept,c.create_time create_time,c.text text from content c join user u on c.create_user=u.id join dept d on u.dept_id=d.id where c.post_id=#{post_id};")
	List<Contents> selectByPostId(Integer post_id); 
//	@Insert("call addContent(#{create_user},#{post_id},#{text},#{storey},#{path},#{message})")
//	void addContent(Integer create_user,Integer post_id,String text,Integer storey, String path,String message);
}
