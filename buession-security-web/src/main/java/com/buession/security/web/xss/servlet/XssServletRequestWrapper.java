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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.xss.servlet;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class XssServletRequestWrapper extends HttpServletRequestWrapper {

	private AntiSamy antiSamy;

	private Policy policy;

	private final static Logger logger = LoggerFactory.getLogger(XssServletRequestWrapper.class);

	public XssServletRequestWrapper(HttpServletRequest request){
		super(request);
	}

	public Policy getPolicy() throws FileNotFoundException{
		if(policy == null){
			try{
				policy = Policy.getInstance();
			}catch(PolicyException e){
				throw new FileNotFoundException("Policy file 'resources/antisamy.xml' not be found.");
			}
		}

		return policy;
	}

	public void setPolicy(Policy policy){
		Assert.isNull(policy, "Xss policy cloud not be null.");
		this.policy = policy;
	}

	@Override
	public String getParameter(String name){
		String value = super.getParameter(name);
		return value == null ? null : xssClean(value);
	}

	@Override
	public String[] getParameterValues(String name){
		String[] values = super.getParameterValues(name);

		if(Validate.isEmpty(values)){
			return values;
		}

		for(int i = 0; i < values.length; i++){
			values[i] = xssClean(values[i]);
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
			result.put(name, xssClean(value));
		});

		return result;
	}

	protected String[] xssClean(String[] values){
		if(Validate.isNotEmpty(values)){
			for(int i = 0; i < values.length; i++){
				values[i] = xssClean(values[i]);
			}
		}

		return values;
	}

	protected String xssClean(String value){
		final CleanResults cleanResults;

		try{
			cleanResults = getAntiSamy().scan(value);
			return cleanResults.getCleanHTML();
		}catch(ScanException e){
			logger.warn("AntiSamy scan error: {}.", e.getMessage(), e);
		}catch(PolicyException e){
			logger.warn("AntiSamy scan policy error{}.", e.getMessage(), e);
		}

		return value;
	}

	protected AntiSamy getAntiSamy(){
		if(antiSamy == null){
			try{
				antiSamy = new AntiSamy(getPolicy());
			}catch(FileNotFoundException e){
				antiSamy = new AntiSamy();
				logger.error(e.getMessage());
			}
		}

		return antiSamy;
	}

}
