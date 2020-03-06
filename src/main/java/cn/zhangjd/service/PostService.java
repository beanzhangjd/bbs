package cn.zhangjd.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cn.zhangjd.service.iService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhangjd.bean.Post;
import cn.zhangjd.bean.PostUserListBean;
import cn.zhangjd.bean.User;
import cn.zhangjd.mapper.PostMapper;
import cn.zhangjd.mapper.UserMapper;
import cn.zhangjd.util.JwtUtil;

@Service
public class PostService implements IPostService {
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public Integer getByUid(String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		Integer id=Integer.parseInt(strs[0]);
		return postMapper.selectByCreateUser(id);
	}
	@Override
	public Integer getByCTime() {
		return postMapper.selectByCreateTime(sdf.format(new Date()));
	}
	@Override
	public Integer getByState() {
		return postMapper.selectByState();
	}
	@Override
	public Integer getByOTime() {
		return postMapper.selectByOverTime(sdf.format(new Date()));
	}
	@Override
	public Integer getByKnow() {
		return postMapper.selectToKnow();
	}
	@Override
	public List<PostUserListBean> getPostByModule(Integer module_id,Integer pno) {
		if(pno==null||pno==0){
			pno=1;
		}
		//System.out.println(module_id);
		List<PostUserListBean> list=postMapper.selectPostByModule(module_id,(pno-1)*30,30);
		return updateTitle(list);
	}
	@Override
	public List<PostUserListBean> getMyPost(String token,Integer pno) {
		if(pno==null||pno==0){
			pno=1;
		}
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		List<PostUserListBean> list=postMapper.selectByUid(user.getId(),(pno-1)*30,30);
		return updateTitle(list);
	}
	@Override
	public List<PostUserListBean> getMyReply(String token,Integer pno) {
		if(pno==null||pno==0){
			pno=1;
		}
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		List<PostUserListBean> list=postMapper.selectByContent(user.getId(),(pno-1)*30,30);
		return updateTitle(list);
	}
	@Override
	public Post getPostById(Integer id) {
		return postMapper.selectById(id);
	}
	@Override
	public Post addPost(Integer module_id, String token,String title) {
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		Post post=new Post();
		post.setCreate_user(user.getId());
		post.setTitle(title);
		post.setModule_id(module_id);
		post.setState(1);
		postMapper.insert(post);
		return post;
	}
	@Override
	public Integer selectCountByModule(Integer id) {
		return postMapper.selectCountByModule(id);
	}

	@Override
	public List<String> getTitle(String text) {
		return postMapper.selectTitle(text);
	}

	@Override
	public Integer getNumByTitle(String text) {
		return postMapper.getNumByTitle(text);
	}

	@Override
	public List<PostUserListBean> getTitleList(String text,Integer pno) {
		if(pno==null||pno==0){
			pno=1;
		}
		return postMapper.getTitleList(text,(pno-1)*30,30);
	}

	@Override
	public void setTop(Integer id, String token) {
		String[] strs=JwtUtil.checkToken("user", token);
		User user=userMapper.selectById(Integer.parseInt(strs[0]));
		Post post=postMapper.selectById(id);
		if(user.getGrade()>5){
			if (post.getLv()==1){
				postMapper.updateLv(2,id);
			}else if (post.getLv()==2){
				postMapper.updateLv(1,id);
			}
		}
	}


	@Override
	public List<PostUserListBean> getKnow(Integer pno) {
		if(pno==null||pno==0){
			pno=1;
		}
		return postMapper.selectKnowList((pno-1)*30,30);
	}
	private List<PostUserListBean> updateTitle(List<PostUserListBean> beans){
		List<PostUserListBean> newList=new LinkedList<PostUserListBean>();
		for (PostUserListBean bean:beans) {
			if (bean.getLv()==2){
				bean.setTitle("【置顶贴】"+bean.getTitle());
				newList.add(bean);
				continue;
			}
//			if(bean.getLv()==3){
//				bean.setTitle("【公告】"+bean.getTitle());
//				newList.add(bean);
//				continue;
//			}
			if (bean.getState()<5){
				bean.setTitle("【未结帖】"+bean.getTitle());
				newList.add(bean);
				continue;
			}
			newList.add(bean);
		}
		return newList;
	}

}
