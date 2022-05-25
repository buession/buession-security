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
package com.buession.security.captcha.geetest.api.v3;

import com.buession.security.captcha.geetest.core.GeetestEnhencedResult;

import java.util.StringJoiner;

/**
 * 极验 V3 版二次校验返回结果
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestV3EnhencedResult implements GeetestEnhencedResult {

	private final static long serialVersionUID = 402465840048648582L;

	/**
	 * 验证结果标识，为”false”表示验证不通过
	 */
	private String seccode;

	/**
	 * 返回验证结果标识，为”false”表示验证不通过
	 *
	 * @return 验证结果标识，为”false”表示验证不通过
	 */
	public String getSeccode(){
		return seccode;
	}

	/**
	 * 设置验证结果标识
	 *
	 * @param seccode
	 * 		验证结果标识
	 */
	public void setSeccode(String seccode){
		this.seccode = seccode;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("seccode=" + seccode)
				.toString();
	}

}
