package cn.zhangjd.mapper;

import cn.zhangjd.bean.Accessory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

public interface AccessoryMapper {
    @Insert("insert into accessory(path,state,filename,create_time,create_user) values(#{path},#{state},#{filename},now(),#{create_user})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(Accessory accessory);
    @Select("select * from accessory where id=#{id}")
    Accessory selectById(Integer id);
    @Delete("delete from accessory where id=#{id}")
    void delById(Integer id);
    @Update("update accessory set state=1 where path=#{path}")
    void updateState(String path);
}
