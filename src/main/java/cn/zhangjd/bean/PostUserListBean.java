package cn.zhangjd.bean;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 展示帖子列表
 */
public class PostUserListBean implements Serializable {
	private SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
	private Integer id;
	private Integer uid;
	private Integer pid;
	private String uname;
	private Integer num;
	private Integer state;
	private String title;
	private String create_time;
	private String end_time;
	private Integer lv;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = sdf.format(create_time);
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = sdf.format(end_time);
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getLv() {
		return lv;
	}

	public void setLv(Integer lv) {
		this.lv = lv;
	}
}
