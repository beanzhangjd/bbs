package cn.zhangjd.controller;

import java.util.ArrayList;
import java.util.List;

import cn.zhangjd.bean.Accessory;
import cn.zhangjd.service.iService.IAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import cn.zhangjd.bean.Contents;
import cn.zhangjd.bean.ResponseResult;
import cn.zhangjd.bean.Result;
import cn.zhangjd.service.iService.IContentService;

/**
 * 帖子回复内容和附件的控制器类
 */
@RestController
@RequestMapping("/api")
public class ContentController {
	@Autowired
	private IContentService contentService;
	@Autowired
	private IAccessoryService accessoryService;

	/**
	 * 获取一个帖子中所有的回复信息
	 * @param id 帖子id
	 * @return json{state:成功失败，message:描述List<Contents>展示一个帖子的所有回复}
	 */
	@RequestMapping("/showContentsByPost")
	public ResponseResult<List<Contents>> showContentsByPost(Integer id){
		try {
			List<Contents> contents=contentService.getContentsByPost(id);
			return new ResponseResult<List<Contents>>(contents!=null&&contents.size()>0,contents);
		}catch (Exception e){
			return new ResponseResult(false);
		}
	}

	/**
	 * 向帖子添加一个新回复
	 * @param id 帖子id
	 * @param token 用户令牌
	 * @param text 回复内容
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/addContent")
	public ResponseResult<Void> addContent(Integer id,@CookieValue("Authority") String token,String text){
		try {
			contentService.addContent(id,text,token);
			return new ResponseResult<Void>(true);
		} catch (Exception e) {
			return new ResponseResult<Void>(false);
		}

	}
	/**
	 * 上传图片
	 * @param files 图片列表
	 * @param token 用户令牌
	 * @return json{errno:成功失败，List<path>上传后的路径}
	 */
	@RequestMapping("/uploadImg")
	public Result uploadImg(@RequestParam("data")List<MultipartFile> files,@CookieValue("Authority") String token){
		Result json=new Result();
		List<String> strings=new ArrayList<String>();
		try {
			for (int i = 0; i <files.size() ; i++) {
				Accessory accessory= accessoryService.addAccessory(files.get(i),token);
				strings.add(accessory.getPath());
			}
			json.setErrno(0);
			json.setData(strings);
		} catch (Exception e) {
			e.printStackTrace();
			json.setErrno(1);
		}
		return json;
	}
	/**
	 * 上传附件
	 * @param files 附件列表：暂未实现多文件上传
	 * @param token 用户令牌
	 * @return json{state:成功失败，message:描述List<Accesspry>附件列表信息}
	 */
	@RequestMapping("uploadFile")
	public ResponseResult<List<Accessory>> uploadFile(@RequestParam("file") List<MultipartFile> files,@CookieValue("Authority") String token){
		try{
			return new ResponseResult<List<Accessory>>(files!=null||files.size()>0,update(files,token));
		}catch (Exception e){
			return new ResponseResult<List<Accessory>>(false);
		}
	}
	/**
	 * 上传声音视频
	 * @param files 文件集合
	 * @param token 用户令牌
	 * @return json{state:成功失败，message:描述List<Accessory>附件集合}
	 */
	@RequestMapping("uploadVideo")
	public ResponseResult<List<Accessory>> uploadVideo(@RequestParam("file") List<MultipartFile> files,@CookieValue("Authority") String token){
		try{
			return new ResponseResult<List<Accessory>>(files!=null&&files.size()>0,update(files,token));
		}catch (Exception e){
			return new ResponseResult<List<Accessory>>(false);
		}
	}
	/**
	 * 删除一个附件
	 * @param id 附件id
	 * @return json{state:成功失败，message:描述}
	 */
	@RequestMapping("/delFile")
	public ResponseResult<Void> delFile(Integer id){
		try{
			accessoryService.delFileById(id);
			return new ResponseResult<Void>(true);
		}catch (Exception e){
			return new ResponseResult<Void>(false);
		}
	}
	private List<Accessory> update(List<MultipartFile> files,String token) throws Exception {
		List<Accessory> accessoryList=new ArrayList<Accessory>();
		for (int i = 0; i < files.size(); i++) {
			accessoryList.add(accessoryService.addAccessory(files.get(i),token));
		}
		return accessoryList;
	}
}
