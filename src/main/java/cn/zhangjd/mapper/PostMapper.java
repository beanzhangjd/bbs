package cn.zhangjd.mapper;


import java.util.List;

import org.apache.ibatis.annotations.*;

import cn.zhangjd.bean.Post;
import cn.zhangjd.bean.PostUserListBean;


public interface PostMapper {
	/**
	 * 向数据库添加一个帖子
	 */
	@Insert("insert into post(title,create_user,create_time,module_id,end_user,end_time,state,lv) values(#{title},#{create_user},now(),#{module_id},#{create_user},now(),#{state},1)")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	void insert(Post post);
	/**
	 *查询当前用户创建的帖子数量 
	 */
	@Select("select count(*) from post where create_user=#{create_user}")
	Integer selectByCreateUser(Integer create_user);
	/**
	 * 查询当天发帖数量
	 */
	@Select("select count(*) from post where date(create_time)=#{create_time}")
	Integer selectByCreateTime(String create_time);
	/**
	 * 查询当天结贴数
	 */
	@Select("select count(*) from post where date(over_time)=#{over_time}")
	Integer selectByOverTime(String over_time);
	/**
	 * 查询目前未结贴数
	 */
	@Select("select count(*) from post where state=0 or state=1")
	Integer selectByState();
	/**
	 * 查询知识库数量
	 */
	@Select("select count(*) from post where state=9")
	Integer selectToKnow();
	//按模块查询
	@Select("select p.id pid,p.create_user uid,p.state state,p.title title,p.create_time create_time,p.end_time end_time,u.name uname,count(c.id) num,p.lv lv from post p left join user u on p.create_user=u.id left join content c on p.id=c.post_id  where p.module_id=#{module_id} group by pid order by lv desc,state,create_time desc limit #{pno},#{pNum};")
	List<PostUserListBean> selectPostByModule(@Param("module_id") Integer module_id,@Param("pno") Integer pno, @Param("pNum") Integer pNum);
	@Select("SELECT p.id pid,p.create_user uid,p.state state,p.title title,p.create_time create_time,p.end_time end_time,u.name uname,COUNT(c.id) num,p.lv lv FROM post p LEFT JOIN USER u ON p.create_user=u.id LEFT JOIN content c ON p.id=c.post_id  WHERE p.create_user=#{create_user} GROUP BY pid ORDER BY lv DESC,state,create_time DESC LIMIT #{pno},#{pNum};")
	List<PostUserListBean> selectByUid(@Param("create_user")Integer create_user,@Param("pno")Integer pno,@Param("pNum")Integer pNum);
	@Select("select * from post where id=#{id}")
	Post selectById(Integer id);
	@Select("select p.id pid,p.create_user uid,p.state state,p.title title,p.create_time create_time,p.end_time end_time,u.name uname,count(c.id) num,p.lv lv from post p left join user u on p.create_user=u.id left join content c on p.id=c.post_id  where c.create_user=#{id} group by pid order by lv desc,state,create_time desc limit #{pno},#{pNum};")
	List<PostUserListBean> selectByContent(@Param("id")Integer id,@Param("pno")Integer pno,@Param("pNum")Integer pNum);
	@Select("select count(*) from post where module_id=#{id}")
	Integer selectCountByModule(Integer id);
	@Update("update post set state=#{state},over_time=now()  where id=#{id};")
	void updataState(@Param("id") Integer id,@Param("state") Integer state);
	@Select("select title from post where title like concat('%',#{title},'%')")
	List<String> selectTitle(String title);
	@Select("select p.id pid,p.create_user uid,p.state state,p.title title,p.create_time create_time,p.end_time end_time,u.name uname,count(c.id) num,p.lv lv from post p left join user u on p.create_user=u.id left join content c on p.id=c.post_id  where p.state=9 group by pid order by lv desc,state,create_time desc limit #{pno},#{pNum};")
	List<PostUserListBean> selectKnowList(@Param("pno")Integer pno,@Param("pNum")Integer pNum);
	@Select("select count(*) from post where title like concat('%',#{title},'%')")
	Integer getNumByTitle(String title);
	@Select("select p.id pid,p.create_user uid,p.state state,p.title title,p.create_time create_time,p.end_time end_time,u.name uname,count(c.id) num from post p left join user u on p.create_user=u.id left join content c on p.id=c.post_id  where p.title like concat('%',#{title},'%')  group by pid order by state,create_time desc limit #{pno},#{pNum};")
	List<PostUserListBean> getTitleList(@Param("title") String title,@Param("pno")Integer pno,@Param("pNum")Integer pNum);
	@Update("update post set end_user=#{end_user},end_time=now() where id=#{id}")
	void updateEnd(@Param("id") Integer id,@Param("end_user") Integer end_user);
	@Update("update post set lv=#{lv} where id=#{id}")
	void updateLv(@Param("lv") Integer lv,@Param("id") Integer id);
}
