package cn.zhangjd.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 附件实体
 */
public class Accessory implements Serializable {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer id;
    private String path;
    private Integer content_id;
    private String message;
    private Integer state;
    private String filename;
    private String create_time;
    private Integer create_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
