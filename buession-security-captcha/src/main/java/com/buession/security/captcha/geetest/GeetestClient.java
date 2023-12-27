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
package com.buession.security.captcha.geetest;

import com.buession.httpclient.HttpClient;
import com.buession.security.captcha.CaptchaClient;
import com.buession.security.captcha.core.Manufacturer;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface GeetestClient extends CaptchaClient {

	void setHttpClient(HttpClient httpClient);

	@Override
	default Manufacturer getManufacturer() {
		return Manufacturer.GEETEST;
	}

	/**
	 * 返回是否为 V3 版本
	 *
	 * @return true / false
	 *
	 * @since 2.3.2
	 */
	default boolean isV3() {
		return "v3".equalsIgnoreCase(getVersion());
	}

	/**
	 * 返回是否为 V3 版本
	 *
	 * @return true / false
	 *
	 * @since 2.3.2
	 */
	default boolean isV4() {
		return "v4".equalsIgnoreCase(getVersion());
	}

}
