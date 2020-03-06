package cn.zhangjd.service;

import cn.zhangjd.bean.Evaluate;
import cn.zhangjd.bean.Post;
import cn.zhangjd.mapper.EvaluateMapper;
import cn.zhangjd.mapper.PostMapper;
import cn.zhangjd.service.iService.IEvaluateService;
import cn.zhangjd.service.iService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateService implements IEvaluateService {
    @Autowired
    private EvaluateMapper evaluateMapper;
    @Autowired
    private PostMapper postMapper;
    @Override
    public void addEvaluate(Integer id, Integer score) {
        Evaluate evaluate=new Evaluate();
        evaluate.setPost_id(id);
        evaluate.setScore(score);
        evaluateMapper.insertEvaluate(evaluate);
        postMapper.updataState(id,6);
    }
}
