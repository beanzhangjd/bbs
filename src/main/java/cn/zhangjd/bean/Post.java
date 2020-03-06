package cn.zhangjd.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子实体
 */
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Integer create_user;
	private Date create_time;
	private Integer state;
	private Integer module_id;
	private Integer end_user;
	private Date end_time;
	private Integer lv;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getEnd_user() {
		return end_user;
	}
	public void setEnd_user(Integer end_user) {
		this.end_user = end_user;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}
	public Integer getLv() {
		return lv;
	}
	public void setLv(Integer lv) {
		this.lv = lv;
	}
}
