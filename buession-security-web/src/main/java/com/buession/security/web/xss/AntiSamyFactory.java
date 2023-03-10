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
package com.buession.security.web.xss;

import com.buession.core.validator.Validate;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 * @since 2.2.0
 */
public class AntiSamyFactory {

	private final static Logger logger = LoggerFactory.getLogger(AntiSamyFactory.class);

	private final AntiSamy antiSamy;

	private AntiSamyFactory(final Policy policy){
		antiSamy = policy == null ? new AntiSamy() : new AntiSamy(policy);
	}

	public static AntiSamyFactory getInstance(final Policy policy){
		return new AntiSamyFactory(policy);
	}

	public List<String> clean(List<String> values){
		if(Validate.isNotEmpty(values)){
			return values.stream().map(this::clean).collect(Collectors.toList());
		}

		return values;
	}

	public Set<String> clean(Set<String> values){
		if(Validate.isNotEmpty(values)){
			return values.stream().map(this::clean).collect(Collectors.toSet());
		}

		return values;
	}

	public String[] clean(String[] values){
		if(Validate.isNotEmpty(values)){
			for(int i = 0; i < values.length; i++){
				values[i] = clean(values[i]);
			}
		}

		return values;
	}

	public String clean(String value){
		final CleanResults cleanResults;

		try{
			cleanResults = antiSamy.scan(value);
			return cleanResults.getCleanHTML();
		}catch(ScanException e){
			logger.warn("AntiSamy scan error: {}.", e.getMessage(), e);
		}catch(PolicyException e){
			logger.warn("AntiSamy scan policy error{}.", e.getMessage(), e);
		}

		return value;
	}

}
