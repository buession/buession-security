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
package com.buession.security.shiro.exception;

/**
 * @author Yong.Teng
 */
public class PrincipalInstanceException extends RuntimeException {

	private final static long serialVersionUID = 8772605457174103686L;

	public PrincipalInstanceException(Class<?> clazz, String idMethodName) {
		super(formatMessage(clazz, idMethodName));
	}

	public PrincipalInstanceException(Class<?> clazz, String idMethodName, Exception e) {
		super(formatMessage(clazz, idMethodName), e);
	}

	protected static String formatMessage(Class<?> clazz, String idMethodName) {
		final StringBuilder sb = new StringBuilder();

		sb.append(clazz.getName()).append(" must has getter for field: ").append(idMethodName).append("; ");
		sb.append("We need a field to identify this Cache Object. ");
		sb.append("So you need to defined an id field which you can get unique id to identify this principal. ");
		sb.append("For example, if you use UserInfo as Principal class, ");
		sb.append("the id field maybe userId, email, etc. For example, getUserId(), getEmail(), etc.");
		sb.append("Default value is \"id\", ")
				.append("that means your principal object has a method called \"getId()\".");

		return sb.toString();
	}

}
