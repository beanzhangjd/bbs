package cn.zhangjd.controller;

import cn.zhangjd.bean.ResponseResult;
import cn.zhangjd.service.iService.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评价的控制器类封装了用户评价帖子或内容的请求
 */
@RestController
@RequestMapping("/api")
public class EvaluateController {
    @Autowired
    private IEvaluateService evaluateService;
    @RequestMapping("/saveScore")
    public ResponseResult<Void> saveScore(Integer id,Integer score){
        try {
            evaluateService.addEvaluate(id,score);
            return  new ResponseResult<Void>(true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult<Void>(false);
        }
    }
}
