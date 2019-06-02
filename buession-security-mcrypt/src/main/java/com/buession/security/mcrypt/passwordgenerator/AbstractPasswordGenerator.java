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
package com.buession.security.mcrypt.passwordgenerator;

import java.util.Random;

/**
 * 密码生成器抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractPasswordGenerator implements PasswordGenerator {

    private final static char[] CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '=', '{',
            '}', '[', ']', '|', '\\', '/', ':', ';', '<', '>', '?', ',', '.'};

    /**
     * 生成随机密码
     *
     * @param length
     *         长度
     *
     * @return 随机密码
     */
    @Override
    public String generatorRandomPassword(final int length){
        if(length <= 0){
            throw new IllegalArgumentException("Password length cloud less than or equal to 0.");
        }

        StringBuffer sb = new StringBuffer(length);
        Random random = new Random();

        for(int i = 0; i < length; i++){
            int j = random.nextInt(CHARS.length);

            sb.append(CHARS[j]);
        }

        return sb.toString();
    }

    /**
     * 密码加密
     *
     * @param password
     *         原始密码
     * @param salt
     *         salt
     *
     * @return 加密后的密码
     */
    @Override
    public byte[] digestEncoded(final byte[] password, final byte[] salt){
        return digestEncoded(new String(password), new String(salt)).getBytes();
    }

}
