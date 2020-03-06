package cn.zhangjd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * 视图控制类，根据接受请求向页面展示各个页面
 */
@Controller
@RequestMapping("/api")
public class ViewController {
	/**
	 * 展示首页html
	 */
	@RequestMapping("/showIndex")
	public String showIndex() {
		return "/api/index.html";
	}
	/**
	 * 向页面展示我的帖子html
	 */
	@RequestMapping("/showMypost")
	public String showMypost() {
		return "/api/myPost.html";
	}
	/**
	 * 向页面展示我回复的帖子html
	 */
	@RequestMapping("/showMyReply")
	public String showMyReply() {
		return "/api/myReply.html";
	}
	/**
	 * 展示设置个人信息html
	 */
	@RequestMapping("/showSetting")
	public String showSetting() {
		return "/api/setting.html";
	}

	/**
	 * 展示发新帖html
	 */
	@RequestMapping("/showQuestion")
	public String showQuestion() {
		return "/api/question.html";
	}

	/**
	 * 展示分类下的帖子列表html
	 */
	@RequestMapping("/showPostList")
	public String showPostList() {
		return "/api/postList.html";
	}

	/**
	 * 展示单个帖子内容html
	 */
	@RequestMapping("/showPostDetail")
	public String showPostDetail() {
		return "/api/postDetail.html";
	}

	/**
	 * 展示知识库html
	 */
	@RequestMapping("/showKnowledge")
	public String showKnowledge() {
		return "/api/knowledgebase.html";
	}
	@RequestMapping("/showTitle")
	public String showTitle() {
		return "/api/title.html";
	}
}
