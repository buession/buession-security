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
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * 对象加密通用方法
 *
 * @author Yong.Teng
 */
public abstract class AbstractMcrypt implements Mcrypt {

    private final static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    /**
     * 请求算法的名称
     */
    private Algo algo;

    /**
     * 加密密钥
     */
    private String salt;

    /**
     * 字符串编码
     */
    private Charset charset = Charset.defaultCharset();

    /**
     * 信息摘要对象的提供者
     */
    private Provider provider = null;

    private final static Logger logger = LoggerFactory.getLogger(AbstractMcrypt.class);

    public AbstractMcrypt(){
    }

    /**
     * @param algo
     *         请求算法的名称
     */
    public AbstractMcrypt(final Algo algo){
        this.algo = algo;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param provider
     *         信息摘要对象的提供者
     */
    public AbstractMcrypt(final Algo algo, final Provider provider){
        this.algo = algo;
        this.provider = provider;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param characterEncoding
     *         字符编码
     */
    public AbstractMcrypt(final Algo algo, final String characterEncoding){
        this.algo = algo;
        if(Validate.hasText(characterEncoding) == true){
            this.charset = Charset.forName(characterEncoding);
        }
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param charset
     *         字符编码
     */
    public AbstractMcrypt(final Algo algo, final Charset charset){
        this.algo = algo;
        this.charset = charset;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param characterEncoding
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public AbstractMcrypt(final Algo algo, final String characterEncoding, final Provider provider){
        this(algo, characterEncoding);
        this.provider = provider;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param charset
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public AbstractMcrypt(final Algo algo, final Charset charset, final Provider provider){
        this(algo, charset);
        this.provider = provider;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public AbstractMcrypt(final Algo algo, final String characterEncoding, final String salt){
        this(algo, characterEncoding);
        this.salt = salt;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public AbstractMcrypt(final Algo algo, final Charset charset, final String salt){
        this(algo, charset);
        this.salt = salt;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     * @param provider
     *         信息摘要对象的提供者
     */
    public AbstractMcrypt(final Algo algo, final String characterEncoding, final String salt, final Provider provider){
        this(algo, characterEncoding, salt);
        this.provider = provider;
    }

    /**
     * @param algo
     *         请求算法的名称
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     * @param provider
     *         信息摘要对象的提供者
     */
    public AbstractMcrypt(final Algo algo, final Charset charset, final String salt, final Provider provider){
        this(algo, charset, salt);
        this.provider = provider;
    }

    /**
     * 返回请求算法的名称
     *
     * @return 返回请求算法的名称
     */
    @Override
    public Algo getAlgo(){
        return algo;
    }

    /**
     * 设置请求算法的名称
     *
     * @param algo
     *         请求算法的名称
     */
    @Override
    public void setAlgo(final Algo algo){
        this.algo = algo;
    }

    /**
     * 返回加密密钥
     *
     * @return 加密密钥
     */
    @Override
    public String getSalt(){
        return salt;
    }

    /**
     * 设置加密密钥
     *
     * @param salt
     *         加密密钥
     */
    @Override
    public void setSalt(final String salt){
        this.salt = salt;
    }

    /**
     * 获取字符串编码
     *
     * @return 字符串编码
     */
    @Override
    public Charset getCharset(){
        return charset;
    }

    /**
     * 设置字符串编码
     *
     * @param charset
     *         字符串编码
     */
    @Override
    public void setCharset(final Charset charset){
        this.charset = charset;
    }

    /**
     * 返回此信息摘要对象的提供者
     *
     * @return 信息摘要对象的提供者
     */
    @Override
    public Provider getProvider(){
        return provider;
    }

    /**
     * 设置信息摘要对象的提供者
     *
     * @param provider
     *         信息摘要对象的提供者
     */
    @Override
    public void setProvider(final Provider provider){
        this.provider = provider;
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
        Assert.isNull(object, "Mcrypt encode object could not be null");
        Assert.isNull(algo, "Algo could not be null");

        try{
            MessageDigest messageDigest = provider == null ? MessageDigest.getInstance(algo.getName()) :
                    MessageDigest.getInstance(algo.getName(), provider);

            if(object instanceof char[]){
                return encode(new String((char[]) object), messageDigest);
            }else if(object instanceof byte[]){
                return encode(new String((byte[]) object, charset), messageDigest);
            }else{
                return encode(object.toString(), messageDigest);
            }
        }catch(final NoSuchAlgorithmException e){
            logger.error(e.getMessage());
            throw new SecurityException(e);
        }
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
        Assert.isNull(algo, "Algo could not be null");
        throw new UnsupportedOperationException("Algo '" + algo + "' unsupported decode");
    }

    protected final static String object2String(final Object object){
        Assert.isNull(object, "Mcrypt encode object could not be null");

        if(object instanceof char[]){
            return new String((char[]) object);
        }else if(object instanceof byte[]){
            return new String((byte[]) object);
        }else{
            return object.toString();
        }
    }

    protected String getRealSalt(){
        String salt = getSalt();
        return salt == null ? "" : salt;
    }

    /**
     * 字符串加密
     *
     * @param str
     *         需要加密的字符串
     * @param messageDigest
     *         实现指定摘要算法的 MessageDigest 对象
     *
     * @return 加密后的字符串
     */
    private String encode(String str, final MessageDigest messageDigest){
        if(StringUtils.isEmpty(salt) == false){
            messageDigest.reset();

            if(charset == null){
                messageDigest.update(salt.getBytes());
            }else{
                messageDigest.update(salt.getBytes(charset));
            }
        }

        if(charset == null){
            messageDigest.update(str.getBytes());
        }else{
            messageDigest.update(str.getBytes(charset));
        }

        logger.debug("Mcrypt encode string <{}> by algo <{}>, salt <{}>", str, algo, salt);

        return getFormattedText(messageDigest.digest());
    }

    /**
     * @param bytes
     *
     * @return formatted string
     */
    private static String getFormattedText(byte[] bytes){
        final StringBuilder buffer = new StringBuilder(bytes.length * 2);

        for(int j = 0; j < bytes.length; j++){
            buffer.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buffer.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }

        return buffer.toString();
    }

}
