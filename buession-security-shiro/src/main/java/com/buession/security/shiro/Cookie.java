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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.shiro;

import com.buession.security.core.SameSite;

import java.io.Serializable;

/**
 * @author Yong.Teng
 */
public class Cookie implements Serializable {

	private final static long serialVersionUID = -4209730152769024787L;

	private String name;

	private String domain;

	private String path;

	private int maxAge;

	private boolean secure;

	private boolean httpOnly;

	private SameSite sameSite;

	public Cookie(){
	}

	public Cookie(String name){
		this.name = name;
	}

	public Cookie(String name, int maxAge){
		this.name = name;
		this.maxAge = maxAge;
	}

	public Cookie(String name, boolean secure){
		this.name = name;
		this.secure = secure;
	}

	public Cookie(String name, int maxAge, boolean secure){
		this(name, maxAge);
		this.secure = secure;
	}

	public Cookie(String name, int maxAge, boolean secure, boolean httpOnly){
		this(name, maxAge, secure);
		this.httpOnly = httpOnly;
	}

	public Cookie(String name, String domain){
		this.name = name;
		this.domain = domain;
	}

	public Cookie(String name, String domain, int maxAge){
		this(name, domain);
		this.maxAge = maxAge;
	}

	public Cookie(String name, String domain, boolean secure){
		this(name, domain);
		this.secure = secure;
	}

	public Cookie(String name, String domain, int maxAge, boolean secure){
		this(name, domain, maxAge);
		this.secure = secure;
	}

	public Cookie(String name, String domain, String path){
		this(name, domain);
		this.path = path;
	}

	public Cookie(String name, String domain, String path, int maxAge){
		this(name, domain, path);
		this.maxAge = maxAge;
	}

	public Cookie(String name, String domain, String path, boolean secure){
		this(name, domain, path);
		this.secure = secure;
	}

	public Cookie(String name, String domain, String path, boolean secure, boolean httpOnly){
		this(name, domain, path, secure);
		this.httpOnly = httpOnly;
	}

	public Cookie(String name, String domain, String path, int maxAge, boolean secure){
		this(name, domain, path, maxAge);
		this.secure = secure;
	}

	public Cookie(String name, String domain, String path, int maxAge, boolean secure, boolean httpOnly){
		this(name, domain, path, maxAge, secure);
		this.httpOnly = httpOnly;
	}

	public Cookie(String name, String domain, String path, int maxAge, boolean secure, boolean httpOnly, SameSite
			sameSite){
		this(name, domain, path, maxAge, secure, httpOnly);
		this.sameSite = sameSite;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
		this.domain = domain;
	}

	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
	}

	public int getMaxAge(){
		return maxAge;
	}

	public void setMaxAge(int maxAge){
		this.maxAge = maxAge;
	}

	public boolean isSecure(){
		return getSecure();
	}

	public boolean getSecure(){
		return secure;
	}

	public void setSecure(boolean secure){
		this.secure = secure;
	}

	public boolean isHttpOnly(){
		return getHttpOnly();
	}

	public boolean getHttpOnly(){
		return httpOnly;
	}

	public void setHttpOnly(boolean httpOnly){
		this.httpOnly = httpOnly;
	}

	public SameSite getSameSite(){
		return sameSite;
	}

	public void setSameSite(SameSite sameSite){
		this.sameSite = sameSite;
	}
}
