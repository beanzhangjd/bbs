package cn.zhangjd.service.iService;

import java.util.List;

import cn.zhangjd.bean.Post;
import cn.zhangjd.bean.PostUserListBean;

/**
 * 帖子表的业务接口
 */
public interface IPostService {
	/**
	 * 查询我发的帖子数量
	 */
	Integer getByUid(String token);
	/**
	 * 查询今天发帖子数量
	 */
	Integer getByCTime();
	/**
	 * 查询未结帖子数量
	 */
	Integer getByState();
	/**
	 * 查询今天结帖数量
	 */
	Integer getByOTime();
	/**
	 * 查询知识库帖子数量
	 */
	Integer getByKnow();
	/**
	 * 获取一个模块中的帖子列表
	 * @param module_id 模块id
	 * @param pno 页码
	 * @return 帖子列表集合
	 */
	List<PostUserListBean> getPostByModule(Integer module_id,Integer pno);
	/**
	 * 获取我发帖子列表
	 * @param token 用户令牌
	 * @param pno 页码
	 * @return 帖子列表集合
	 */
	List<PostUserListBean> getMyPost(String token,Integer pno);
	/**
	 * 获取我发的帖子列表
	 * @param token 用户令牌
	 * @param pno 页码
	 * @return 帖子列表集合
	 */
	List<PostUserListBean> getMyReply(String token,Integer pno);
	/**
	 * 获取一个帖子的信息
	 * @param id 帖子id
	 * @return 一个帖子信息
	 */
	Post getPostById(Integer id);

	/**
	 * 添加一个帖子信息
	 * @param module_id 模块id
	 * @param token 用户令牌
	 * @param title 帖子标题
	 * @return 添加的帖子信息
	 */
	Post addPost(Integer module_id, String token,String title);

	/**
	 * 获取知识库中的帖子列表
	 * @return
	 */
	List<PostUserListBean> getKnow(Integer pno);

	/**
	 * 获取模块中帖子总量
	 * @param id 模块id
	 * @return 这个模块帖子数量
	 */
	Integer selectCountByModule(Integer id);


	List<String> getTitle(String text);

	Integer getNumByTitle(String text);

	List<PostUserListBean> getTitleList(String text,Integer pno);

    void setTop(Integer id, String token);
}
