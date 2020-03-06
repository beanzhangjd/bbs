package cn.zhangjd.bean;

import java.io.Serializable;

/**
 * 标签实体类
 */
public class Tag implements Serializable {
    /**
     * 标签id
     */
    private Integer id;
    /**
     * 标签名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
