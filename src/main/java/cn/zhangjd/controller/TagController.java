package cn.zhangjd.controller;

import cn.zhangjd.bean.ResponseResult;
import cn.zhangjd.service.iService.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签的控制器类：提供了对标签控制的api
 */
@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private ITagService tagService;
    @RequestMapping("/getTag")
    public ResponseResult<List<String>> getTag(String text){
        try {
            List<String> list=tagService.getByName(text);
            return new ResponseResult<List<String>>(list!=null&&list.size()>0,list);
        }catch (Exception e){
            return new ResponseResult(false);
        }
    }
    @RequestMapping("/saveTag")
    public ResponseResult<Void> saveTag(String tag,Integer id){
        try{
            tagService.saveTag(tag,id);
            return new ResponseResult<Void>(true);
        }catch (Exception e){
            return new ResponseResult<Void>(false);
        }
    }
}
