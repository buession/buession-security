/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.aliyun;

import com.buession.security.captcha.core.EnhencedResult;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 阿里云二次校验返回结果
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliyunEnhencedResult implements EnhencedResult {

	private final static long serialVersionUID = 402465840048648582L;

	/**
	 * 调用返回编码
	 */
	private String code;

	/**
	 * 调用返回状态
	 */
	private Boolean success;

	private Data data;

	/**
	 * 返回调用返回编码
	 *
	 * @return 调用返回编码
	 */
	public String getCode(){
		return code;
	}

	/**
	 * 设置调用返回编码
	 *
	 * @param code
	 * 		调用返回编码
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * 返回调用返回状态
	 *
	 * @return 调用返回状态
	 */
	public Boolean getSuccess(){
		return success;
	}

	/**
	 * 设置调用返回状态
	 *
	 * @param success
	 * 		调用返回状态
	 */
	public void setSuccess(Boolean success){
		this.success = success;
	}

	/**
	 * 返回调用返回结果
	 *
	 * @return 调用返回结果
	 */
	public Data getData(){
		return data;
	}

	/**
	 * 设置调用返回结果
	 *
	 * @param data
	 * 		调用返回结果
	 */
	public void setData(Data data){
		this.data = data;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("code=" + code)
				.add("success=" + success)
				.add("data=" + data)
				.toString();
	}

	public final static class Data implements Serializable {

		private final static long serialVersionUID = -2831087419706976003L;

		/**
		 * 阿里云验证码结果编码
		 * <p>**100**：表示验证通过</p>
		 * <p>**900**：表示验证不通过</p>
		 */
		private String code;

		/**
		 * 返回阿里云验证码结果编码
		 *
		 * @return 阿里云验证码结果编码
		 * <p>**100**：表示验证通过</p>
		 * <p>**900**：表示验证不通过</p>
		 */
		public String getCode(){
			return code;
		}

		/**
		 * 设置阿里云验证码结果编码
		 *
		 * @param code
		 * 		阿里云验证码结果编码
		 */
		public void setCode(String code){
			this.code = code;
		}

		@Override
		public String toString(){
			return new StringJoiner(", ", "[", "]")
					.add("code=" + code)
					.toString();
		}
	}

}
