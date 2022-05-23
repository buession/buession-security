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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.geetest.api.v4;

import com.buession.security.captcha.core.RequestData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 极验 V4 版本请求数据
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestV4RequestData implements RequestData {

	/**
	 * 验证流水号
	 */
	@JsonProperty(value = "lot_number")
	private String lotNumber;

	/**
	 * 验证输出信息
	 */
	@JsonProperty(value = "captcha_output")
	private String captchaOutput;

	/**
	 * 验证通过标识
	 */
	@JsonProperty(value = "pass_token")
	private String passToken;

	/**
	 * 验证通过时间戳
	 */
	@JsonProperty(value = "gen_time")
	private String genTime;

	/**
	 * 返回验证流水号
	 *
	 * @return 验证流水号
	 */
	public String getLotNumber(){
		return lotNumber;
	}

	/**
	 * 设置验证流水号
	 *
	 * @param lotNumber
	 * 		验证流水号
	 */
	public void setLotNumber(String lotNumber){
		this.lotNumber = lotNumber;
	}

	/**
	 * 返回验证输出信息
	 *
	 * @return 验证输出信息
	 */
	public String getCaptchaOutput(){
		return captchaOutput;
	}

	/**
	 * 设置验证输出信息
	 *
	 * @param captchaOutput
	 * 		验证输出信息
	 */
	public void setCaptchaOutput(String captchaOutput){
		this.captchaOutput = captchaOutput;
	}

	/**
	 * 返回验证通过标识
	 *
	 * @return 验证通过标识
	 */
	public String getPassToken(){
		return passToken;
	}

	/**
	 * 设置验证通过标识
	 *
	 * @param passToken
	 * 		验证通过标识
	 */
	public void setPassToken(String passToken){
		this.passToken = passToken;
	}

	/**
	 * 返回验证通过时间戳
	 *
	 * @return 验证通过时间戳
	 */
	public String getGenTime(){
		return genTime;
	}

	/**
	 * 设置验证通过时间戳
	 *
	 * @param genTime
	 * 		验证通过时间戳
	 */
	public void setGenTime(String genTime){
		this.genTime = genTime;
	}

	@JsonIgnore
	@Override
	public String getClientIp(){
		return null;
	}

	@JsonIgnore
	@Override
	public void setClientIp(String clientIp){

	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("lotNumber=" + lotNumber)
				.add("captchaOutput=" + captchaOutput)
				.add("passToken=" + passToken)
				.add("genTime=" + genTime)
				.toString();
	}

}
