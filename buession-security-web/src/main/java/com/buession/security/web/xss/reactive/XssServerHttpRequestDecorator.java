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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.xss.reactive;

import com.buession.core.validator.Validate;
import com.buession.security.web.xss.Options;
import com.buession.security.web.xss.factory.CleanXssFactory;
import com.buession.security.web.xss.factory.EscapeXssFactory;
import com.buession.security.web.xss.factory.XssFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 * @since 2.3.3
 */
public class XssServerHttpRequestDecorator extends ServerHttpRequestDecorator {

	private final XssFactory xssFactory;

	public XssServerHttpRequestDecorator(ServerHttpRequest delegate, Options options) {
		super(delegate);
		xssFactory = options.getPolicy() == Options.Policy.ESCAPE ? new EscapeXssFactory(options) : new CleanXssFactory(
				options);
	}

	@Override
	public MultiValueMap<String, String> getQueryParams() {
		MultiValueMap<String, String> queryParams = super.getQueryParams();

		if(Validate.isNotEmpty(queryParams)){
			MultiValueMap<String, String> newQueryParams = new LinkedMultiValueMap<>(queryParams.size());

			queryParams.forEach((name, value)->newQueryParams.put(name, handle(value)));

			return newQueryParams;
		}

		return queryParams;
	}

	private List<String> handle(final List<String> source) {
		return source.stream().map((value)->Validate.isBlank(value) ? value : xssFactory.handle(value)).collect(
				Collectors.toList());
	}

}
