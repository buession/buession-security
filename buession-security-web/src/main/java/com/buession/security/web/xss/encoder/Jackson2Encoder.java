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
package com.buession.security.web.xss.encoder;

import com.buession.security.web.xss.Options;
import com.buession.security.web.xss.factory.CleanXssFactory;
import com.buession.security.web.xss.factory.EscapeXssFactory;
import com.buession.security.web.xss.factory.XssFactory;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Yong.Teng
 * @since 2.2.0
 */
public class Jackson2Encoder extends AbstractEncoder<JsonDeserializer<String>> {

	/**
	 * 构造函数
	 */
	public Jackson2Encoder() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param options
	 *        {@link Options}
	 *
	 * @since 2.3.3
	 */
	public Jackson2Encoder(final Options options) {
		super(options);
	}

	@Override
	public JsonDeserializer<String> runtime() {
		final XssFactory xssFactory = getOptions().getPolicy() == Options.Policy.ESCAPE ?
				new EscapeXssFactory(getOptions()) : new CleanXssFactory(getOptions());

		return new JsonDeserializer<String>() {

			@Override
			public Class<String> handledType() {
				return String.class;
			}

			@Override
			public String deserialize(JsonParser parser, DeserializationContext cxt)
					throws IOException, JacksonException {
				return xssFactory.handle(parser.getValueAsString());
			}

		};
	}

}
