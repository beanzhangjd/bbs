package cn.zhangjd.bean;

import java.io.Serializable;

/**
 * 帖子与标签关联表
 */
public class PostAndTag implements Serializable {
    private Integer id;
    private Integer post_id;
    private Integer tag_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}
