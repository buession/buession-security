/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.xss.servlet;

import com.buession.core.validator.Validate;
import com.buession.security.web.xss.AntiSamyFactory;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class XssServletRequestWrapper extends HttpServletRequestWrapper {

	private AntiSamyFactory antiSamyFactory;

	public XssServletRequestWrapper(HttpServletRequest request, Policy policy) throws FileNotFoundException{
		super(request);

		try{
			antiSamyFactory = AntiSamyFactory.getInstance(policy == null ? Policy.getInstance() : policy);
		}catch(PolicyException e){
			throw new FileNotFoundException("Policy file 'resources/antisamy.xml' not be found.");
		}
	}

	@Override
	public String getParameter(String name){
		String value = super.getParameter(name);
		return value == null ? null : antiSamyFactory.clean(value);
	}

	@Override
	public String[] getParameterValues(String name){
		String[] values = super.getParameterValues(name);

		if(Validate.isEmpty(values)){
			return values;
		}

		for(int i = 0; i < values.length; i++){
			values[i] = antiSamyFactory.clean(values[i]);
		}

		return values;
	}

	@Override
	public Map<String, String[]> getParameterMap(){
		Map<String, String[]> parameterMap = super.getParameterMap();

		if(Validate.isEmpty(parameterMap)){
			return parameterMap;
		}

		Map<String, String[]> result = new LinkedHashMap<>(parameterMap.size());

		parameterMap.forEach((name, value)->{
			result.put(name, antiSamyFactory.clean(value));
		});

		return result;
	}

}
