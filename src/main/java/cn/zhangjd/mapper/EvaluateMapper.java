package cn.zhangjd.mapper;

import cn.zhangjd.bean.Evaluate;
import org.apache.ibatis.annotations.Insert;

public interface EvaluateMapper {
    @Insert("insert into evaluate(score) values(#{score})")
    void insertEvaluate(Evaluate evaluate);
}
