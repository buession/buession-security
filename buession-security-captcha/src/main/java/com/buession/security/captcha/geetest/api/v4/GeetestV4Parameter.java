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
package com.buession.security.captcha.geetest.api.v4;

import com.buession.core.validator.Validate;
import com.buession.security.captcha.geetest.GeetestParameter;

import java.util.StringJoiner;

/**
 * 极验 V4 版本参数定义接口
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestV4Parameter implements GeetestParameter {

	private final static long serialVersionUID = 5519418826643028121L;

	/**
	 * 默认验证流水号参数名称
	 */
	public final static String DEFAULT_LOT_NUMBER = "lot_number";

	/**
	 * 默认验证输出信息参数名称
	 */
	public final static String DEFAULT_CAPTCHA_OUTPUT = "captcha_output";

	/**
	 * 默认验证通过标识参数名称
	 */
	public final static String DEFAULT_PASS_TOKEN = "pass_token";

	/**
	 * 默认验证通过时间戳参数名称
	 */
	public final static String DEFAULT_GEN_TIME = "gen_time";

	/**
	 * 验证流水号参数名称
	 */
	private String lotNumber = DEFAULT_LOT_NUMBER;

	/**
	 * 验证输出信息参数名称
	 */
	private String captchaOutput = DEFAULT_CAPTCHA_OUTPUT;

	/**
	 * 验证通过标识参数名称
	 */
	private String passToken = DEFAULT_PASS_TOKEN;

	/**
	 * 验证通过时间戳参数名称
	 */
	private String genTime = DEFAULT_GEN_TIME;

	/**
	 * 返回验证流水号参数名称
	 *
	 * @return 验证流水号参数名称
	 */
	public String getLotNumber(){
		return lotNumber;
	}

	/**
	 * 设置验证流水号参数名称
	 *
	 * @param lotNumber
	 * 		验证流水号参数名称
	 */
	public void setLotNumber(String lotNumber){
		if(Validate.hasText(lotNumber)){
			this.lotNumber = lotNumber;
		}
	}

	/**
	 * 返回验证输出信息参数名称
	 *
	 * @return 验证输出信息参数名称
	 */
	public String getCaptchaOutput(){
		return captchaOutput;
	}

	/**
	 * 设置验证输出信息参数名称
	 *
	 * @param captchaOutput
	 * 		验证输出信息参数名称
	 */
	public void setCaptchaOutput(String captchaOutput){
		if(Validate.hasText(captchaOutput)){
			this.captchaOutput = captchaOutput;
		}
	}

	/**
	 * 返回验证通过标识参数名称
	 *
	 * @return 验证通过标识参数名称
	 */
	public String getPassToken(){
		return passToken;
	}

	/**
	 * 设置验证通过标识参数名称
	 *
	 * @param passToken
	 * 		验证通过标识参数名称
	 */
	public void setPassToken(String passToken){
		if(Validate.hasText(passToken)){
			this.passToken = passToken;
		}
	}

	/**
	 * 返回验证通过时间戳参数名称
	 *
	 * @return 验证通过时间戳参数名称
	 */
	public String getGenTime(){
		return genTime;
	}

	/**
	 * 设置验证通过时间戳参数名称
	 *
	 * @param genTime
	 * 		验证通过时间戳参数名称
	 */
	public void setGenTime(String genTime){
		if(Validate.hasText(passToken)){
			this.genTime = genTime;
		}
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
