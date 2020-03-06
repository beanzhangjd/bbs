package cn.zhangjd.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 内容实体
 */
public class Content implements Serializable{
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer id;
	private Integer create_user;
	private String create_time;
	private Integer modify_user;
	private String modify_time;
	private Integer post_id;
	private Integer storey;
	private String text;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = sdf.format(create_time);
	}
	public Integer getModify_user() {
		return modify_user;
	}
	public void setModify_user(Integer modify_user) {
		this.modify_user = modify_user;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = sdf.format(modify_time);
	}
	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public Integer getStorey() {
		return storey;
	}
	public void setStorey(Integer storey) {
		this.storey = storey;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
