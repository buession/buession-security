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
package com.buession.security.shiro.converter;

import com.buession.core.converter.Converter;
import com.buession.security.core.SameSite;
import org.apache.shiro.web.servlet.Cookie;

/**
 * {@link SameSite} 转换为 {@link Cookie.SameSiteOptions}
 *
 * @author Yong.Teng
 * @since 1.3.3
 */
public class SameSiteToShiroSameSiteOptionsConverter implements Converter<SameSite, Cookie.SameSiteOptions> {

	@Override
	public Cookie.SameSiteOptions convert(SameSite sameSite){
		if(sameSite == null){
			return null;
		}

		switch(sameSite){
			case STRICT:
				return Cookie.SameSiteOptions.STRICT;
			case LAX:
				return Cookie.SameSiteOptions.LAX;
			case NONE:
				return Cookie.SameSiteOptions.NONE;
			default:
				return null;
		}
	}

}