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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.config.converter.reactive;

import com.buession.security.web.config.ReferrerPolicy;
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;

/**
 * WebFlux ReferrerPolicy 策略转换
 *
 * @param <S>
 * 		原始对象
 * @param <T>
 * 		目标对象
 *
 * @author Yong.Teng
 * @see ReferrerPolicy.Policy
 * @see ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy
 * @since 2.1.0
 */
public interface ReferrerPolicyConverter<S, T>
		extends com.buession.security.web.config.converter.ReferrerPolicyConverter<S, T> {

	/**
	 * WebFlux 原生 ReferrerPolicy 策略类型 {@link ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy} 转换为 {@link ReferrerPolicy.Policy}
	 *
	 * @author Yong.Teng
	 * @see ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy
	 * @since 2.1.0
	 */
	class NativeReferrerPolicyConverter implements
			com.buession.security.web.config.converter.ReferrerPolicyConverter.NativeReferrerPolicyConverter<ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy> {

		@Override
		public ReferrerPolicy.Policy convert(final ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy source) {
			if(source == null){
				return null;
			}

			return switch(source){
				case NO_REFERRER -> ReferrerPolicy.Policy.NO_REFERRER;
				case NO_REFERRER_WHEN_DOWNGRADE -> ReferrerPolicy.Policy.NO_REFERRER_WHEN_DOWNGRADE;
				case SAME_ORIGIN -> ReferrerPolicy.Policy.SAME_ORIGIN;
				case ORIGIN -> ReferrerPolicy.Policy.ORIGIN;
				case STRICT_ORIGIN -> ReferrerPolicy.Policy.STRICT_ORIGIN;
				case ORIGIN_WHEN_CROSS_ORIGIN -> ReferrerPolicy.Policy.ORIGIN_WHEN_CROSS_ORIGIN;
				case STRICT_ORIGIN_WHEN_CROSS_ORIGIN -> ReferrerPolicy.Policy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN;
				case UNSAFE_URL -> ReferrerPolicy.Policy.UNSAFE_URL;
			};
		}

	}

	/**
	 * WebFlux {@link ReferrerPolicy.Policy} 策略类型转换为原生 ReferrerPolicy 类型 {@link ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy}
	 *
	 * @author Yong.Teng
	 * @see ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy
	 * @since 2.1.0
	 */
	class ToNativeReferrerPolicyConverter implements
			com.buession.security.web.config.converter.ReferrerPolicyConverter.ToNativeReferrerPolicyConverter<ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy> {

		@Override
		public ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy convert(final ReferrerPolicy.Policy source) {
			if(source == null){
				return null;
			}

			return switch(source){
				case NO_REFERRER -> ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.NO_REFERRER;
				case NO_REFERRER_WHEN_DOWNGRADE ->
						ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.NO_REFERRER_WHEN_DOWNGRADE;
				case SAME_ORIGIN -> ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.SAME_ORIGIN;
				case ORIGIN -> ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.ORIGIN;
				case STRICT_ORIGIN -> ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.STRICT_ORIGIN;
				case ORIGIN_WHEN_CROSS_ORIGIN ->
						ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.ORIGIN_WHEN_CROSS_ORIGIN;
				case STRICT_ORIGIN_WHEN_CROSS_ORIGIN ->
						ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN;
				case UNSAFE_URL -> ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.UNSAFE_URL;
			};
		}

	}

}
