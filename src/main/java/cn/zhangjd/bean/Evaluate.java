package cn.zhangjd.bean;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评价实体
 */
public class Evaluate implements Serializable {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Integer id;
    private Integer score;
    private Integer post_id;
    private Integer content_id;
    private String text;
    private String create_time;
    private Integer create_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = simpleDateFormat.format(create_time);
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }
}
