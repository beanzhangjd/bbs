package cn.zhangjd.bean;

import java.io.Serializable;

/**
 * josn实体
 * @param <T>
 */
public class ResponseResult<T> implements Serializable{

	private static final long serialVersionUID = 2744760061380493062L;
	private Integer state;
	private T data;
	public ResponseResult(){

	}
	public ResponseResult(boolean b){
		if (b){
			this.state = 1;
		}else {
			this.state = 0;
		}
	}
	public ResponseResult(boolean b,T data) {
		super();
		this.data = data;
		if (b){
			this.state = 1;
		}else {
			this.state = 0;
		}
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}


}
