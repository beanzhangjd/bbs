package cn.zhangjd.mapper;

import cn.zhangjd.bean.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签表的mapper映射器
 */
public interface TagMapper {
    /**
     * 查询所有标签名
     * @return 标签名集合
     */
    @Select("select name from tag")
    List<String> selectAllName();
    /**
     * 查询单个表名
     * @param id
     * @return 标签名
     */
    @Select("select name from tag where id=#{id}")
    String selectNameById(Integer id);

    /**
     * 向标签库添加一行数据
     * @param tag 标签
     */
    @Insert("insert into tag(name) values(#{name})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertTag(Tag tag);
    @Select("select name from tag where name like concat('%',#{name},'%')")
    List<String> selectByName(String name);
    @Select("select * from tag where name=#{name}")
    Tag selectTagByName(String name);
    @Insert("insert into post_tag(post_id,tag_id) values(#{post_id},#{tag_id})")
    void saveTag(@Param("post_id") Integer post_id,@Param("tag_id") Integer tag_id);
}
