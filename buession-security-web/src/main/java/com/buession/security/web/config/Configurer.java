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
	 * 授权配置
	 *
	 * @since 4.0.0
	 */
	private AuthorizeExchange authorizeExchange;

	/**
	 * Content Security Policy 配置
	 */
	private ContentSecurityPolicy contentSecurityPolicy;

	/**
	 * Cors 配置
	 */
	private Cors cors;

	/**
	 * Csrf 配置
	 */
	private Csrf csrf;

	/**
	 * Feature Policy 配置
	 *
	 * @since 4.0.0
	 */
	private FeaturePolicy featurePolicy;

	/**
	 * 登录表单配置
	 */
	private FormLogin formLogin;

	/**
	 * Frame Options 配置
	 */
	private FrameOptions frameOptions;

	/**
	 * Hsts 配置
	 */
	private Hsts hsts;

	/**
	 * Http Basic 配置
	 */
	private HttpBasic httpBasic;

	/**
	 * 退出登录配置
	 *
	 * @since 4.0.0
	 */
	private Logout logout;

	/**
	 * Permissions Policy 配置
	 *
	 * @since 4.0.0
	 */
	private PermissionsPolicy permissionsPolicy;

	/**
	 * Referrer Policy 配置
	 */
	private ReferrerPolicy referrerPolicy;

	/**
	 * XSS 配置
	 */
	private Xss xss;

	/**
	 * 返回授权配置
	 *
	 * @return 授权配置
	 *
	 * @since 4.0.0
	 */
	public AuthorizeExchange getAuthorizeExchange() {
		return authorizeExchange;
	}

	/**
	 * 设置授权配置
	 *
	 * @param authorizeExchange
	 * 		授权配置
	 *
	 * @since 4.0.0
	 */
	public void setAuthorizeExchange(AuthorizeExchange authorizeExchange) {
		this.authorizeExchange = authorizeExchange;
	}

	/**
	 * 返回 Content Security Policy 配置
	 *
	 * @return Content Security Policy 配置
	 */
	public ContentSecurityPolicy getContentSecurityPolicy() {
		return contentSecurityPolicy;
	}

	/**
	 * 设置 Content Security Policy 配置
	 *
	 * @param contentSecurityPolicy
	 * 		Content Security Policy 配置
	 */
	public void setContentSecurityPolicy(ContentSecurityPolicy contentSecurityPolicy) {
		this.contentSecurityPolicy = contentSecurityPolicy;
	}

	/**
	 * 返回 Cors 配置
	 *
	 * @return Cors 配置
	 */
	public Cors getCors() {
		return cors;
	}

	/**
	 * 设置 Cors 配置
	 *
	 * @param cors
	 * 		Cors 配置
	 */
	public void setCors(Cors cors) {
		this.cors = cors;
	}

	/**
	 * 返回 Csrf 配置
	 *
	 * @return Csrf 配置
	 */
	public Csrf getCsrf() {
		return csrf;
	}

	/**
	 * 设置 Csrf 配置
	 *
	 * @param csrf
	 * 		Csrf 配置
	 */
	public void setCsrf(Csrf csrf) {
		this.csrf = csrf;
	}

	/**
	 * 返回 Feature Policy 配置
	 *
	 * @return Feature Policy 配置
	 *
	 * @since 4.0.0
	 */
	public FeaturePolicy getFeaturePolicy() {
		return featurePolicy;
	}

	/**
	 * 设置 Feature Policy 配置
	 *
	 * @param featurePolicy
	 * 		Feature Policy 配置
	 *
	 * @since 4.0.0
	 */
	public void setFeaturePolicy(FeaturePolicy featurePolicy) {
		this.featurePolicy = featurePolicy;
	}

	/**
	 * 返回登录表单配置
	 *
	 * @return 登录表单配置
	 */
	public FormLogin getFormLogin() {
		return formLogin;
	}

	/**
	 * 设置登录表单配置
	 *
	 * @param formLogin
	 * 		登录表单配置
	 */
	public void setFormLogin(FormLogin formLogin) {
		this.formLogin = formLogin;
	}

	/**
	 * 返回 Frame Options 配置
	 *
	 * @return Frame Options 配置
	 */
	public FrameOptions getFrameOptions() {
		return frameOptions;
	}

	/**
	 * 设置 Frame Options 配置
	 *
	 * @param frameOptions
	 * 		Frame Options 配置
	 */
	public void setFrameOptions(FrameOptions frameOptions) {
		this.frameOptions = frameOptions;
	}

	/**
	 * 返回 Hsts 配置
	 *
	 * @return Hsts 配置
	 */
	public Hsts getHsts() {
		return hsts;
	}

	/**
	 * 设置 Hsts 配置
	 *
	 * @param hsts
	 * 		Hsts 配置
	 */
	public void setHsts(Hsts hsts) {
		this.hsts = hsts;
	}

	/**
	 * 返回 Http Basic 配置
	 *
	 * @return Http Basic 配置
	 */
	public HttpBasic getHttpBasic() {
		return httpBasic;
	}

	/**
	 * 设置 Http Basic 配置
	 *
	 * @param httpBasic
	 * 		Http Basic 配置
	 */
	public void setHttpBasic(HttpBasic httpBasic) {
		this.httpBasic = httpBasic;
	}

	/**
	 * 返回退出登录配置
	 *
	 * @return 退出登录配置
	 *
	 * @since 4.0.0
	 */
	public Logout getLogout() {
		return logout;
	}

	/**
	 * 设置退出登录配置
	 *
	 * @param logout
	 * 		退出登录配置
	 *
	 * @since 4.0.0
	 */
	public void setLogout(Logout logout) {
		this.logout = logout;
	}

	/**
	 * 返回 Permissions Policy 配置
	 *
	 * @return Permissions Policy 配置
	 *
	 * @since 4.0.0
	 */
	public PermissionsPolicy getPermissionsPolicy() {
		return permissionsPolicy;
	}

	/**
	 * 设置 Permissions Policy 配置
	 *
	 * @param permissionsPolicy
	 * 		Permissions Policy 配置
	 *
	 * @since 4.0.0
	 */
	public void setPermissionsPolicy(PermissionsPolicy permissionsPolicy) {
		this.permissionsPolicy = permissionsPolicy;
	}

	/**
	 * 返回 Referrer Policy 配置
	 *
	 * @return Referrer Policy 配置
	 */
	public ReferrerPolicy getReferrerPolicy() {
		return referrerPolicy;
	}

	/**
	 * 设置 Referrer Policy 配置
	 *
	 * @param referrerPolicy
	 * 		Referrer Policy 配置
	 */
	public void setReferrerPolicy(ReferrerPolicy referrerPolicy) {
		this.referrerPolicy = referrerPolicy;
	}

	/**
	 * 返回 XSS 配置
	 *
	 * @return XSS 配置
	 */
	public Xss getXss() {
		return xss;
	}

	/**
	 * 设置 XSS 配置
	 *
	 * @param xss
	 * 		XSS 配置
	 */
	public void setXss(Xss xss) {
		this.xss = xss;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "Configurer = {", "}")
				.add("authorizeExchange=" + authorizeExchange)
				.add("contentSecurityPolicy=" + contentSecurityPolicy)
				.add("cors=" + cors)
				.add("csrf=" + csrf)
				.add("featurePolicy=" + featurePolicy)
				.add("formLogin=" + formLogin)
				.add("frameOptions=" + frameOptions)
				.add("hsts=" + hsts)
				.add("httpBasic=" + httpBasic)
				.add("logout=" + logout)
				.add("permissionsPolicy=" + permissionsPolicy)
				.add("referrerPolicy=" + referrerPolicy)
				.add("xss=" + xss)
				.toString();
	}

}
