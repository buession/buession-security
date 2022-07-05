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
package com.buession.security.captcha;

import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.Manufacturer;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.core.InitResponse;

/**
 * 行为验证 Client
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface CaptchaClient {

	/**
	 * 验证初始化
	 *
	 * @param requestData
	 * 		请求数据
	 *
	 * @return 初始化结果
	 */
	default InitResponse initialize(RequestData requestData){
		return null;
	}

	/**
	 * 二次验证
	 *
	 * @param requestData
	 * 		请求数据
	 *
	 * @return 验证结果，成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 *
	 * @throws CaptchaException
	 * 		验证异常
	 */
	Status validate(RequestData requestData) throws CaptchaException;

	/**
	 * 返回厂商
	 *
	 * @return 厂商
	 */
	Manufacturer getManufacturer();

	/**
	 * 获取版本号
	 *
	 * @return 版本号
	 */
	String getVersion();

	/**
	 * 返回前端 JavaScript 库地址
	 *
	 * @return 前端 JavaScript 库地址
	 */
	String[] getJavaScript();

	/**
	 * 设置前端 JavaScript 库地址
	 *
	 * @param url
	 * 		前端 JavaScript 库地址
	 */
	void setJavaScript(String[] url);

}
