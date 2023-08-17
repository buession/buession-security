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
package com.buession.security.mcrypt;

import com.buession.security.crypto.Mode;
import com.buession.security.crypto.Padding;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 * @since 2.0.1
 */
public class AESMcryptTest {

	@Test
	public void encrypt() {
		AESMcrypt mcrypt = new AESMcrypt(StandardCharsets.UTF_8, "1234567887654321", Mode.ECB,
				Padding.ISO10126);
		System.out.println("1234567887654321" + mcrypt.encrypt("Abc12345678\u5206\u5272Abc12345678"));
	}

	@Test
	public void decrypt() {
		AESMcrypt mcrypt = new AESMcrypt(StandardCharsets.UTF_8, "1111111111111111", Mode.ECB,
				Padding.ISO10126);
		System.out.println(mcrypt.decrypt("xrnRAt3qsWnzJMvb5zPwCqnmH+FPWV58WpmyUQrEmAg="));
	}

}
