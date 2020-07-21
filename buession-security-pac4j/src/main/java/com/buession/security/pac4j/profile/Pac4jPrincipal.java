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
package com.buession.security.pac4j.profile;

import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class Pac4jPrincipal<T> {

	private T id;

	private String username;

	private String realName;

	public Pac4jPrincipal(){
	}

	public Pac4jPrincipal(T id, String username, String realName){
		this.id = id;
		this.username = username;
		this.realName = realName;
	}

	public T getId(){
		return id;
	}

	public void setId(T id){
		this.id = id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	@Override
	public int hashCode(){
		return Objects.hash(id, username, realName);
	}

	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}

		if(o == null || getClass() != o.getClass()){
			return false;
		}

		Pac4jPrincipal that = (Pac4jPrincipal) o;

		if(Objects.equals(id, that.id)){
			if(Objects.equals(username, that.username)){
				if(Objects.equals(realName, that.realName)){
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder(32);

		sb.append("id: ").append(id).append(", ");
		sb.append("username: ").append(username).append(", ");
		sb.append("realName: ").append(realName);

		return sb.toString();
	}

}
