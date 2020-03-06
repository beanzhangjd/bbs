package cn.zhangjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhangjd.service.iService.IContentService;
import cn.zhangjd.service.iService.IPostService;

/**
 * nav导航栏展示的今日结帖数等信息
 */
@RestController
@RequestMapping("/api")
public class NavController {
	@Autowired
	private IPostService postService;
	@Autowired
	private IContentService contentService;
	/**
	 * 获取当前用户发贴数量
	 * @param token 用户令牌
	 * @return 用户发帖数
	 */
	@RequestMapping("/getByUser")
	public Integer getByUser(@CookieValue("Authority")String token) {
		return postService.getByUid(token);
	}
	/**
	 * 获取今日发帖数量
	 * @return 今日发帖数
	 */
	@RequestMapping("/getByCTime")
	public Integer getByCTime() {
		return postService.getByCTime();
	}
	/**
	 * 获取现未结帖数
	 * @return 未结帖数
	 */
	@RequestMapping("/getByState")
	public Integer getByState() {
		return postService.getByState();
	}
	/**
	 * 获取今日结帖数
	 * @return 今日结帖数
	 */
	@RequestMapping("/getByOTime")
	public Integer getByOTime() {
		return postService.getByOTime();
	}
	/**
	 * 获取知识库数量
	 * @return 知识库数
	 */
	@RequestMapping("/getByKnow")
	public Integer getByKnow() {
		return postService.getByKnow();
	}
	/**
	 * 获取当前用户回复的帖子数
	 * @param token 用户令牌
	 * @return 回复帖子数
	 */
	@RequestMapping("/getByContent")
	public Integer getByContent(@CookieValue("Authority")String token) {
		return contentService.getByCreateUser(token);
	}
}
