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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.geetest.api.v3;

import com.buession.security.captcha.geetest.GeetestInitResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 极验 V3 版本初始化结果
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestV3InitResponse implements GeetestInitResponse {

	private final static long serialVersionUID = 2447877854812523971L;

	private boolean success;

	private String gt;

	private String challenge;

	@JsonProperty(value = "new_captcha")
	private Boolean newCaptcha;

	public boolean getSuccess(){
		return success;
	}

	public boolean isSuccess(){
		return getSuccess();
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public String getGt(){
		return gt;
	}

	public void setGt(String gt){
		this.gt = gt;
	}

	public String getChallenge(){
		return challenge;
	}

	public void setChallenge(String challenge){
		this.challenge = challenge;
	}

	public Boolean getNewCaptcha(){
		return newCaptcha;
	}

	public void setNewCaptcha(Boolean newCaptcha){
		this.newCaptcha = newCaptcha;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("success=" + success)
				.add("gt=" + gt)
				.add("challenge=" + challenge)
				.add("newCaptcha=" + newCaptcha)
				.toString();
	}

}
