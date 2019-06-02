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
package com.buession.security.mcrypt.utils;

import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 */
public class ParseUtils {

    private ParseUtils(){

    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buffer
     *         需要转换的字节
     *
     * @return 十六进制字符串
     */
    public final static String byte2Hex(byte buffer[]){
        StringBuffer sb = new StringBuffer();

        for(byte b : buffer){
            String hex = Integer.toHexString(b & 0xFF);

            if(hex.length() == 1){
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr
     *         十六进制字符串
     *
     * @return 二进制
     */
    public final static byte[] hex2Byte(String hexStr){
        if(hexStr.length() < 1){
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];

        for(int i = 0; i < hexStr.length() / 2; i++){
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);

            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    /**
     * 将任意对象转换成 byte[]
     *
     * @param o
     *         待转换对象
     *
     * @return 转换结果
     */
    public final static byte[] object2Byte(Object o){
        if(o == null){
            return null;
        }

        if(o instanceof char[]){
            return new String((char[]) o).getBytes();
        }else if(o instanceof byte[]){
            return (byte[]) o;
        }else{
            return o.toString().getBytes();
        }
    }

    /**
     * 将任意对象转换成 byte[]
     *
     * @param o
     *         待转换对象
     * @param charset
     *         字符集
     *
     * @return 转换结果
     */
    public final static byte[] object2Byte(Object o, final Charset charset){
        if(o == null){
            return null;
        }

        if(o instanceof char[]){
            return new String((char[]) o).getBytes(charset);
        }else if(o instanceof byte[]){
            return (byte[]) o;
        }else{
            return o.toString().getBytes();
        }
    }

}
