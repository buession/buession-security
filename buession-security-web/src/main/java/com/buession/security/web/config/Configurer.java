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
package com.buession.security.web.config;

import java.util.StringJoiner;

/**
 * Web 安全适配配置类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Configurer {

	/**
	 * Http Basic 配置
	 */
	private HttpBasic httpBasic;

	/**
	 * Csrf 配置
	 */
	private Csrf csrf;

	/**
	 * Cors 配置
	 */
	private Cors cors;

	/**
	 * Frame Options 配置
	 */
	private FrameOptions frameOptions;

	/**
	 * Hsts 配置
	 */
	private Hsts hsts;

	/**
	 * Hpkp 配置
	 */
	private Hpkp hpkp;

	/**
	 * Content Security Policy 配置
	 */
	private ContentSecurityPolicy contentSecurityPolicy;

	/**
	 * Referrer Policy 配置
	 */
	private ReferrerPolicy referrerPolicy;

	/**
	 * XSS 配置
	 */
	private Xss xss;

	/**
	 * 登录表单配置
	 */
	private FormLogin formLogin;

	/**
	 * 构造函数
	 */
	public Configurer(){
	}

	/**
	 * 构造函数
	 *
	 * @param httpBasic
	 * 		Http Basic 配置
	 * @param csrf
	 * 		Csrf 配置
	 * @param cors
	 * 		Cors 配置
	 * @param frameOptions
	 * 		Frame Options 配置
	 * @param hsts
	 * 		Hsts 配置
	 * @param hpkp
	 * 		Hpkp 配置
	 * @param contentSecurityPolicy
	 * 		Content Security Policy 配置
	 * @param referrerPolicy
	 * 		Referrer Policy 配置
	 * @param xss
	 * 		XSS 配置
	 * @param formLogin
	 * 		登录表单配置
	 */
	public Configurer(HttpBasic httpBasic, Csrf csrf, Cors cors, FrameOptions frameOptions, Hsts hsts,
					  Hpkp hpkp, ContentSecurityPolicy contentSecurityPolicy,
					  ReferrerPolicy referrerPolicy, Xss xss, FormLogin formLogin){
		this.httpBasic = httpBasic;
		this.csrf = csrf;
		this.cors = cors;
		this.frameOptions = frameOptions;
		this.hsts = hsts;
		this.hpkp = hpkp;
		this.contentSecurityPolicy = contentSecurityPolicy;
		this.referrerPolicy = referrerPolicy;
		this.xss = xss;
		this.formLogin = formLogin;
	}

	/**
	 * 返回 Http Basic 配置
	 *
	 * @return Http Basic 配置
	 */
	public HttpBasic getHttpBasic(){
		return httpBasic;
	}

	/**
	 * 设置 Http Basic 配置
	 *
	 * @param httpBasic
	 * 		Http Basic 配置
	 */
	public void setHttpBasic(HttpBasic httpBasic){
		this.httpBasic = httpBasic;
	}

	/**
	 * 返回 Csrf 配置
	 *
	 * @return Csrf 配置
	 */
	public Csrf getCsrf(){
		return csrf;
	}

	/**
	 * 设置 Csrf 配置
	 *
	 * @param csrf
	 * 		Csrf 配置
	 */
	public void setCsrf(Csrf csrf){
		this.csrf = csrf;
	}

	/**
	 * 返回 Cors 配置
	 *
	 * @return Cors 配置
	 */
	public Cors getCors(){
		return cors;
	}

	/**
	 * 设置 Cors 配置
	 *
	 * @param cors
	 * 		Cors 配置
	 */
	public void setCors(Cors cors){
		this.cors = cors;
	}

	/**
	 * 返回 Frame Options 配置
	 *
	 * @return Frame Options 配置
	 */
	public FrameOptions getFrameOptions(){
		return frameOptions;
	}

	/**
	 * 设置 Frame Options 配置
	 *
	 * @param frameOptions
	 * 		Frame Options 配置
	 */
	public void setFrameOptions(FrameOptions frameOptions){
		this.frameOptions = frameOptions;
	}

	/**
	 * 返回 Hsts 配置
	 *
	 * @return Hsts 配置
	 */
	public Hsts getHsts(){
		return hsts;
	}

	/**
	 * 设置 Hsts 配置
	 *
	 * @param hsts
	 * 		Hsts 配置
	 */
	public void setHsts(Hsts hsts){
		this.hsts = hsts;
	}

	/**
	 * 返回 Hpkp 配置
	 *
	 * @return Hpkp 配置
	 */
	public Hpkp getHpkp(){
		return hpkp;
	}

	/**
	 * 设置 Hpkp 配置
	 *
	 * @param hpkp
	 * 		Hpkp 配置
	 */
	public void setHpkp(Hpkp hpkp){
		this.hpkp = hpkp;
	}

	/**
	 * 返回 Content Security Policy 配置
	 *
	 * @return Content Security Policy 配置
	 */
	public ContentSecurityPolicy getContentSecurityPolicy(){
		return contentSecurityPolicy;
	}

	/**
	 * 设置 Content Security Policy 配置
	 *
	 * @param contentSecurityPolicy
	 * 		Content Security Policy 配置
	 */
	public void setContentSecurityPolicy(ContentSecurityPolicy contentSecurityPolicy){
		this.contentSecurityPolicy = contentSecurityPolicy;
	}

	/**
	 * 返回 Referrer Policy 配置
	 *
	 * @return Referrer Policy 配置
	 */
	public ReferrerPolicy getReferrerPolicy(){
		return referrerPolicy;
	}

	/**
	 * 设置 Referrer Policy 配置
	 *
	 * @param referrerPolicy
	 * 		Referrer Policy 配置
	 */
	public void setReferrerPolicy(ReferrerPolicy referrerPolicy){
		this.referrerPolicy = referrerPolicy;
	}

	/**
	 * 返回 XSS 配置
	 *
	 * @return XSS 配置
	 */
	public Xss getXss(){
		return xss;
	}

	/**
	 * 设置 XSS 配置
	 *
	 * @param xss
	 * 		XSS 配置
	 */
	public void setXss(Xss xss){
		this.xss = xss;
	}

	/**
	 * 返回登录表单配置
	 *
	 * @return 登录表单配置
	 */
	public FormLogin getFormLogin(){
		return formLogin;
	}

	/**
	 * 设置登录表单配置
	 *
	 * @param formLogin
	 * 		登录表单配置
	 */
	public void setFormLogin(FormLogin formLogin){
		this.formLogin = formLogin;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "{", "}")
				.add("httpBasic=" + httpBasic)
				.add("csrf=" + csrf)
				.add("frameOptions=" + frameOptions)
				.add("hsts=" + hsts)
				.add("hpkp=" + hpkp)
				.add("contentSecurityPolicy=" + contentSecurityPolicy)
				.add("referrerPolicy=" + referrerPolicy)
				.add("xss=" + xss)
				.add("formLogin=" + formLogin)
				.toString();
	}

}
