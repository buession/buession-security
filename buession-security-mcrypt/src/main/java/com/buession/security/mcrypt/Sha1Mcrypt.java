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

import java.nio.charset.Charset;
import java.security.Provider;

/**
 * SHA-1 加密对象
 *
 * @author Yong.Teng
 */
public final class Sha1Mcrypt extends AbstractMcrypt {

    public Sha1Mcrypt(){
        super(Algo.SHA1);
    }

    /**
     * @param provider
     *         信息摘要对象的提供者
     */
    public Sha1Mcrypt(final Provider provider){
        super(Algo.SHA1, provider);
    }

    /**
     * @param characterEncoding
     *         字符编码
     */
    public Sha1Mcrypt(final String characterEncoding){
        super(Algo.SHA1, characterEncoding);
    }

    /**
     * @param charset
     *         字符编码
     */
    public Sha1Mcrypt(final Charset charset){
        super(Algo.SHA1, charset);
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public Sha1Mcrypt(final String characterEncoding, final Provider provider){
        this(characterEncoding, null, provider);
    }

    /**
     * @param charset
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public Sha1Mcrypt(final Charset charset, final Provider provider){
        this(charset, null, provider);
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public Sha1Mcrypt(final String characterEncoding, final String salt){
        this(characterEncoding, salt, null);
    }

    /**
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public Sha1Mcrypt(final Charset charset, final String salt){
        this(charset, salt, null);
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     * @param provider
     *         信息摘要对象的提供者
     */
    public Sha1Mcrypt(final String characterEncoding, final String salt, final Provider provider){
        super(Algo.SHA1, characterEncoding, salt, provider);
    }

    /**
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     * @param provider
     *         信息摘要对象的提供者
     */
    public Sha1Mcrypt(final Charset charset, final String salt, final Provider provider){
        super(Algo.SHA1, charset, salt, provider);
    }

}
