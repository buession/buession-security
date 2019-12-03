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

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 */
public class TempTest {

    @Test
    public void test(){
        Sha512Mcrypt sha512Mcrypt = new Sha512Mcrypt(StandardCharsets.UTF_8, "bQbyCowWYouzBVTujmZr");
        String secret = "e2Sbg68MYBFfn6BEnuSyEhldCB2P1xej1WuLOH5W";
        String signature = "ND3MahI1e9AB0TDn";
        long timestamp = 1574776747;

        System.out.println(sha512Mcrypt.encode(secret + signature + timestamp));

        Sha512Mcrypt sha512Mcrypt2 = new Sha512Mcrypt(StandardCharsets.UTF_8);
        String key2 = "bQbyCowWYouzBVTujmZr";
        String secret2 = "e2Sbg68MYBFfn6BEnuSyEhldCB2P1xej1WuLOH5W";
        String signature2 = "ND3MahI1e9AB0TDn";
        long timestamp2 = 1574776747;

        System.out.println(sha512Mcrypt2.encode(key2 + secret2 + signature2 + timestamp2));
        System.out.println("");
    }

}
