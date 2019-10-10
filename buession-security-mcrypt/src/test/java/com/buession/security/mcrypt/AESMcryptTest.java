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

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class AESMcryptTest {

    @Test
    public void encode(){
        AESMcrypt aesMcrypt = new AESMcrypt();
        Map<String, String> data = new LinkedHashMap<>();

        data.put("ct", "FF");
        data.put("t", "FF");
        data.put("v", "V");
        data.put("callback", "");

        StringBuffer sb = new StringBuffer();

        data.forEach((key, value)->{
            if(sb.length() > 0){
                sb.append('&');
            }
            sb.append(key);
            sb.append('=');
            try{
                sb.append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        });

        aesMcrypt.setSalt("^c-d..7890123456");
        Base64Mcrypt base64Mcrypt = new Base64Mcrypt();
        System.out.println(URLEncoder.encode(base64Mcrypt.encode(aesMcrypt.encode(sb.toString()))));
    }

}
