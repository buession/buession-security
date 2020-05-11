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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.mcrypt;

import com.buession.core.utils.StringUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 */
public class TempTest {

	@Test
	public void test(){
		String key = "tfdTy9plMWKE0ubX2amN";
		String secret = "Vc5pEAzjlL1rhWEuVfY5V8lnwQgzhG4ErmU5r76X5Gxx9xZTZZ";
		String signature = "5ih0LxX12ubG3Cff82W1NqaXwlDkt79b";

		DiscuzMycrypt discuzMycrypt = new DiscuzMycrypt();
		System.out.println("Key: " + discuzMycrypt.encode(key));

		Sha512Mcrypt sha512Mcrypt = new Sha512Mcrypt(StandardCharsets.UTF_8, key);
		System.out.println("Token： " + sha512Mcrypt.encode(key + secret + StringUtils.reverse(signature)));
	}

}
