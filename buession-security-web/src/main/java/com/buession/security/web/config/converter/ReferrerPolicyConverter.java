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
package com.buession.security.web.config.converter;

import com.buession.core.converter.Converter;
import com.buession.security.web.config.ReferrerPolicy;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;

/**
 * ReferrerPolicy 策略转换
 *
 * @param <S>
 * 		原始对象
 * @param <T>
 * 		目标对象
 *
 * @author Yong.Teng
 * @see ReferrerPolicy.Policy
 * @see ReferrerPolicyHeaderWriter.ReferrerPolicy
 * @see ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy
 * @since 2.1.0
 */
public interface ReferrerPolicyConverter<S, T> extends Converter<S, T> {

	/**
	 * 原生 ReferrerPolicy 策略类型转换为 {@link ReferrerPolicy.Policy}
	 *
	 * @param <S>
	 * 		原生 ReferrerPolicy 策略类型
	 *
	 * @author Yong.Teng
	 * @see ReferrerPolicyHeaderWriter.ReferrerPolicy
	 * @see ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy
	 * @since 2.1.0
	 */
	interface NativeReferrerPolicyConverter<S> extends ReferrerPolicyConverter<S, ReferrerPolicy.Policy> {

	}

	/**
	 * {@link ReferrerPolicy.Policy} 策略类型转换为原生 ReferrerPolicy 类型
	 *
	 * @param <T>
	 * 		原生 ReferrerPolicy 策略类型
	 *
	 * @author Yong.Teng
	 * @see ReferrerPolicyHeaderWriter.ReferrerPolicy
	 * @see ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy
	 * @since 2.1.0
	 */
	interface ToNativeReferrerPolicyConverter<T> extends ReferrerPolicyConverter<ReferrerPolicy.Policy, T> {

	}

}
