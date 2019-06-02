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

import com.buession.core.utils.Assert;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * base64 编码、解码
 *
 * @author Yong.Teng
 */
public final class Base64Mcrypt extends AbstractMcrypt {

    private final static Logger logger = LoggerFactory.getLogger(Base64Mcrypt.class);

    public Base64Mcrypt(){
        super(Algo.BASE64);
    }

    /**
     * @param characterEncoding
     *         字符编码
     */
    public Base64Mcrypt(final String characterEncoding){
        super(Algo.BASE64, characterEncoding);
    }

    /**
     * @param charset
     *         字符编码
     */
    public Base64Mcrypt(final Charset charset){
        super(Algo.BASE64, charset);
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public Base64Mcrypt(final String characterEncoding, final String salt){
        super(Algo.BASE64, characterEncoding, salt);
    }

    /**
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public Base64Mcrypt(final Charset charset, final String salt){
        super(Algo.BASE64, charset, salt);
    }

    /**
     * 对象加密
     *
     * @param object
     *         需要加密的字符串
     *
     * @return 加密后的字符串
     */
    @Override
    public String encode(final Object object){
        Assert.isNull(object, "Mcrypt encode object could not be null.");

        logger.debug("Mcrypt encode string <{}> by algo <base64>, salt <{}>", object, getSalt());

        return Base64.encodeBase64String((object2String(object) + (getSalt() == null ? "" : getSalt())).getBytes
                (getCharset()));
    }

    /**
     * 字符串解密
     * 该方法需要提供信息摘要算法支持双向解密才可用
     *
     * @param cs
     *         要被解密的 char 值序列
     *
     * @return 解密后的字符串
     */
    @Override
    public String decode(final CharSequence cs){
        Assert.isNull(cs, "Mcrypt decode object could not be null.");
        logger.debug("Mcrypt decode string <{}> by algo <base64>, salt <{}>", cs, getSalt());
        return new String(Base64.decodeBase64(cs.toString()), getCharset());
    }

}