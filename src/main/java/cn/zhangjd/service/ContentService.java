package cn.zhangjd.service;

import java.util.ArrayList;
import java.util.List;

import cn.zhangjd.mapper.AccessoryMapper;
import cn.zhangjd.mapper.PostMapper;
import cn.zhangjd.service.iService.IContentService;
import cn.zhangjd.util.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhangjd.bean.Content;
import cn.zhangjd.bean.Contents;
import cn.zhangjd.mapper.ContentMapper;
import cn.zhangjd.mapper.DeptMapper;
import cn.zhangjd.util.JwtUtil;
@Service
public class ContentService implements IContentService {
	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private AccessoryMapper accessoryMapper;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private DeptMapper deptMapper;
	@Override
	public Integer getByCreateUser(String token) {
		Integer id=Integer.parseInt(JwtUtil.checkToken("user", token)[0]);
		return contentMapper.selectByUser(id);
	}
	@Override
	public List<Contents> getContentsByPost(Integer id) {
		List<Contents> contents= contentMapper.selectByPostId(id);
		for (Contents content : contents) {
			content.setCompany(deptMapper.selectName(Integer.parseInt(content.getCompany())));
		}
		return contents;
	}
	@Override
	public void addContent(Integer id, String text, String token) {
		Content content=new Content();
		content.setPost_id(id);
		content.setText(text);
		Integer user_id=Integer.parseInt(JwtUtil.checkToken("user", token)[0]);
		content.setCreate_user(user_id);
		content.setModify_user(user_id);
		contentMapper.insert(content);
		List<String> paths= FilesUtil.getPath(text);
		if(paths!=null&&paths.size()>0){
			for (String path : paths) {
				accessoryMapper.updateState(path);
			}
		}
		postMapper.updateEnd(id,user_id);
	}
}
