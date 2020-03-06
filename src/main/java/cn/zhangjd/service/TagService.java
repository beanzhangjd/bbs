package cn.zhangjd.service;

import cn.zhangjd.bean.Tag;
import cn.zhangjd.mapper.TagMapper;
import cn.zhangjd.service.iService.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagService implements ITagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<String> getAllTagName() {
        return tagMapper.selectAllName();
    }

    @Override
    public String getTagNameById(Integer id) {
        return tagMapper.selectNameById(id);
    }
    @Override
    public List<String> getByName(String text) {
        return tagMapper.selectByName(text);
    }

    @Override
    public void saveTag(String tag,Integer id) {
        Tag tag1=tagMapper.selectTagByName(tag);
        if (tag1==null){
            tag1=new Tag();
            tag1.setName(tag);
            tagMapper.insertTag(tag1);
        }
        tagMapper.saveTag(id,tag1.getId());
    }
}
