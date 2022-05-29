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

import com.buession.core.validator.Validate;
import com.buession.security.captcha.core.Parameter;

import java.util.StringJoiner;

/**
 * 阿里云行为验证参数定义
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliyunParameter implements Parameter {

	private final static long serialVersionUID = -1575628555899104535L;

	/**
	 * 默认验证码客户端验证回调的随机串参数名称
	 */
	public final static String DEFAULT_RAND_STR = "Randstr";

	/**
	 * 默认验证码客户端验证回调的票据参数名称
	 */
	public final static String DEFAULT_TICKET = "Ticket";

	/**
	 * 验证码客户端验证回调的随机串参数名称
	 */
	private String randStr = DEFAULT_RAND_STR;

	/**
	 * 验证码客户端验证回调的票据参数名称
	 */
	private String ticket = DEFAULT_TICKET;

	/**
	 * 返回验证码客户端验证回调的随机串参数名称
	 *
	 * @return 验证码客户端验证回调的随机串参数名称
	 */
	public String getRandStr(){
		return randStr;
	}

	/**
	 * 设置验证码客户端验证回调的随机串参数名称
	 *
	 * @param randStr
	 * 		验证码客户端验证回调的随机串参数名称
	 */
	public void setRandStr(String randStr){
		if(Validate.hasText(randStr)){
			this.randStr = randStr;
		}
	}

	/**
	 * 返回验证码客户端验证回调的票据参数名称
	 *
	 * @return 验证码客户端验证回调的票据参数名称
	 */
	public String getTicket(){
		return ticket;
	}

	/**
	 * 设置验证码客户端验证回调的票据参数名称
	 *
	 * @param ticket
	 * 		验证码客户端验证回调的票据参数名称
	 */
	public void setTicket(String ticket){
		if(Validate.hasText(ticket)){
			this.ticket = ticket;
		}
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("randStr=" + randStr)
				.add("ticket=" + ticket)
				.toString();
	}

}
