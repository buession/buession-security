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
package com.buession.security.pac4j;

import com.buession.security.pac4j.profile.ProfileUtils;
import org.junit.jupiter.api.Test;
import org.pac4j.http.profile.RestProfile;

import java.util.StringJoiner;

/**
 *
 *
 * @author Yong.Teng
 * @since 1.0.0
 */
public class ProfileUtilsTest {

	@Test
	public void toObject() {
		RestProfile restProfile = new RestProfile();
		restProfile.setId("123");
		restProfile.addAttribute("email", "webmaster@buession.com");

		System.out.println(ProfileUtils.toObject(restProfile, User.class));
	}

	public final static class User {

		private String id;

		private String name;

		private String email;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
					.add("id='" + id + "'")
					.add("name='" + name + "'")
					.add("email='" + email + "'")
					.toString();
		}

	}

}
