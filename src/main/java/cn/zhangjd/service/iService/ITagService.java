package cn.zhangjd.service.iService;

import java.util.List;

/**
 * 标签业务类的接口，规定标签业务应有的功能
 */
public interface ITagService {
    /**
     * 查询数据库所有标签名
     * @return List<String> 标签名集合
     */
    List<String> getAllTagName();

    /**
     * 根据id查询标签名
     * @param id 标签id
     * @return 标签名
     */
    String getTagNameById(Integer id);

    List<String> getByName(String text);

    void saveTag(String tag,Integer id);
}
