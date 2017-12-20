package com.xh.mybatis.demo.vo.response;


import lombok.Data;

import java.util.HashMap;

/**
 * @title: 响应基类
 * @description: desc
 * @author zhangjunjie
 * @date 2016年7月21日
 * 
 */
@Data
public class BaseResponse {

	private String ret;
	private String msg;
	private Object result;

	public BaseResponse(){
		this.result=new HashMap();
	}

	public BaseResponse(String ret, String msg){
		this.ret=ret;
		this.msg=msg;
		this.result=new HashMap();
	}
	public BaseResponse(String ret, String msg, Object result){
		this.ret=ret;
		this.msg=msg;
		this.result=result;
	}
}
