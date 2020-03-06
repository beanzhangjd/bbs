package cn.zhangjd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhangjd.bean.Module;
import cn.zhangjd.bean.Post;
import cn.zhangjd.bean.PostUserListBean;
import cn.zhangjd.bean.ResponseResult;
import cn.zhangjd.service.iService.IModuleService;
import cn.zhangjd.service.iService.IPostService;

/**
 * 帖子控制器类，提供添加修改帖子的功能
 */
@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private IPostService postService;
	@Autowired
	private IModuleService moduleService;
	/**
	 * 添加新的贴子
	 * @param id 帖子所在模块id
	 * @param token 用户令牌
	 * @param title 帖子标题
	 * @return json{state:成功失败，message:描述，post：创建的帖子的各种信息}
	 */
	@RequestMapping("/addPost")
	public ResponseResult<Post> addPost(Integer id,@CookieValue("Authority") String token,String title){
		try {
			Post post = postService.addPost(id, token, title);
			return new ResponseResult<Post>(true,post);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<Post>(false);
		}
	}
	/**
	 * 获取一页当前模块的帖子列表，一页30个
	 * @param id 模块id
	 * @param pno 页数
	 * @return json{state:成功失败，message:描述List<postList>展示列表所需的各个信息}
	 */
	@RequestMapping("/getPostByModule")
	public ResponseResult<List<PostUserListBean>> getPostByModule(Integer id,Integer pno){
		try{
			List<PostUserListBean> posts =postService.getPostByModule(id,pno);
			return new ResponseResult<List<PostUserListBean>>(posts!=null&&posts.size()>0,posts);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 展示知识库帖子列表 ps：暂未实现
	 * @return json{state:成功失败，message:描述
	 */
	@RequestMapping("/showKnow")
	public ResponseResult<List<PostUserListBean>> showKnow(Integer pno){
		try{
			List<PostUserListBean> posts=postService.getKnow(pno);
			return new ResponseResult<List<PostUserListBean>>(posts!=null&&posts.size()>0,posts);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 获取当前用户发的帖子列表
	 * @param token 用户令牌
	 * @param pno 页数
	 * @return json{state:成功失败，message:描述List<PostList>帖子列表展示的信息}
	 */
	@RequestMapping("/getMyPost")
	public ResponseResult<List<PostUserListBean>> getMyPost(@CookieValue("Authority") String token,Integer pno){
		try{
			List<PostUserListBean> posts =postService.getMyPost(token,pno);
			return new ResponseResult<List<PostUserListBean>>(posts!=null&&posts.size()>0,posts);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 获取我回复的帖子列表
	 * @param token 用户令牌
	 * @param pno 页码
	 * @return json{state:成功失败，message:描述List<PostList>展示帖子列表所需的各类信息}
	 */
	@RequestMapping("/getMyReply")
	public ResponseResult<List<PostUserListBean>> getMyReply(@CookieValue("Authority") String token,Integer pno){
		try{
			List<PostUserListBean> posts =postService.getMyReply(token,pno);
			return new ResponseResult<List<PostUserListBean>>(posts!=null&&posts.size()>0,posts);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 获取模块的帖子总数
	 * @param id 模块id
	 * @return 模块的帖子总数
	 */
	@RequestMapping("getNumByModule")
	public Integer getNumByModule(Integer id){
		return postService.selectCountByModule(id);
	}
	/**
	 * 获取所有模块集合展示在首页上
	 * @return json{state:成功失败，message:描述List<Module>所有模块信息}
	 */
	@RequestMapping("/getAllModule")
	public ResponseResult<List<Module>> getAllModule(){
		try{
			List<Module> modules =moduleService.getAll();
			return new ResponseResult<List<Module>>(modules!=null&&modules.size()>0,modules);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 获取单个模块的信息
	 * @param id 模块id
	 * @return json{state:成功失败，message:描述,Module模块的各类信息}
	 */
	@RequestMapping("/getModule")
	public ResponseResult<Module> getModule(Integer id){
		try {
			Module module =moduleService.getModule(id);
			return new ResponseResult<Module>(module!=null,module);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	/**
	 * 获取单个帖子的信息
	 * @param id 帖子id
	 * @return json{state:成功失败，message:描述，Post帖子信息}
	 */
	@RequestMapping("/getPostById")
	public ResponseResult<Post> getPostById(Integer id){
		try {
			Post post=postService.getPostById(id);
			return new ResponseResult<Post>(post!=null,post);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	@RequestMapping("/getTitle")
	public ResponseResult<List<String>> getTitle(String text){
		try{
			List<String> list=postService.getTitle(text);
			return new ResponseResult<List<String>>(list!=null&&list.size()>0,list);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	@RequestMapping("/getNumByTitle")
	public Integer getNumByTitle(String text){
		return postService.getNumByTitle(text);
	}
	@RequestMapping("/getTitleList")
	public ResponseResult<List<PostUserListBean>> getTitleList(String text,Integer pno){
		try {
			List<PostUserListBean> list=postService.getTitleList(text,pno);
			return new ResponseResult<List<PostUserListBean>>(list!=null,list);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}
	@RequestMapping("/setTop")
	public ResponseResult<Void> setTop(Integer id,@CookieValue("Authority") String token){
		try{
			postService.setTop(id,token);
			return new ResponseResult<Void>(true);
		}catch (Exception e){
			return new ResponseResult<Void>(false);
		}
	}
}
