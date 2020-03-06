package cn.zhangjd.service.iService;

import java.util.List;

import cn.zhangjd.bean.Contents;

/**
 * 内容的业务接口，规定一系列方法
 */
public interface IContentService {
	/**
	 * 获取我回复的帖子数量
	 * @param token 用户令牌
	 * @return 我回复的帖子数量
	 */
	Integer getByCreateUser(String token);
	/**
	 * 获取一个帖子里的回复
	 * @param id 帖子id
	 * @return 回复集合
	 */
	List<Contents> getContentsByPost(Integer id);

	/**
	 * 添加一条回复
	 * @param id 帖子id
	 * @param text 回复内容
	 * @param token 用户令牌
	 */
	void addContent(Integer id, String text, String token);

}
