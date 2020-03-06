package cn.zhangjd.bean;

import java.io.Serializable;

/**
 *模块实体
 */
public class Module implements Serializable {
	private Integer id;
	private String name;
	private String message;
	private String image;

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
