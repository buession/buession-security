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

import com.buession.security.mcrypt.utils.ParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * RSA 加密对象
 *
 * @author Yong.Teng
 */
class RSAMcrypt extends AbstractMcrypt {

    private static Cipher cipher = null;

    private final static Logger logger = LoggerFactory.getLogger(RSAMcrypt.class);

    public RSAMcrypt(){
        super(Algo.RSA);
        initCipher();
    }

    /**
     * @param provider
     *         信息摘要对象的提供者
     */
    public RSAMcrypt(final Provider provider){
        super(Algo.RSA, provider);
        initCipher();
    }

    /**
     * @param characterEncoding
     *         字符编码
     */
    public RSAMcrypt(final String characterEncoding){
        super(Algo.RSA, characterEncoding);
        initCipher();
    }

    /**
     * @param charset
     *         字符编码
     */
    public RSAMcrypt(final Charset charset){
        super(Algo.RSA, charset);
        initCipher();
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public RSAMcrypt(final String characterEncoding, final Provider provider){
        this(characterEncoding, null, provider);
    }

    /**
     * @param charset
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public RSAMcrypt(final Charset charset, final Provider provider){
        this(charset, null, provider);
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public RSAMcrypt(final String characterEncoding, final String salt){
        this(characterEncoding, salt, null);
    }

    /**
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public RSAMcrypt(final Charset charset, final String salt){
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
    public RSAMcrypt(final String characterEncoding, final String salt, final Provider provider){
        super(Algo.RSA, characterEncoding, salt, provider);
        initCipher();
    }

    /**
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     * @param provider
     *         信息摘要对象的提供者
     */
    public RSAMcrypt(final Charset charset, final String salt, final Provider provider){
        super(Algo.RSA, charset, salt, provider);
        initCipher();
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
        Key key = getKey();
        if(key == null){
            return null;
        }

        byte[] result = encode(key, ParseUtils.object2Byte(object));

        logger.debug("RSAMcrypt encode string <{}> by algo <RSA>, salt <{}>", object, getSalt());

        return result == null ? null : ParseUtils.byte2Hex(result);
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
        Key key = getKey();
        if(key == null){
            return null;
        }

        byte[] result = decode(key, ParseUtils.hex2Byte(cs.toString()));

        logger.debug("RSAMcrypt decode string <{}> by algo <RSA>, salt <{}>", cs, getSalt());

        return result == null ? null : new String(result);
    }

    private final static Cipher initCipher(){
        if(cipher == null){
            try{
                cipher = Cipher.getInstance(Algo.RSA.getName());
            }catch(NoSuchAlgorithmException e){
                logger.error(e.getMessage());
            }catch(NoSuchPaddingException e){
                logger.error(e.getMessage());
            }
        }

        return cipher;
    }

    private final Key getKey(){
        String salt = getSalt();
        if(salt == null){
            salt = "";
        }

        int saltLength = salt.length();
        if(saltLength < 16){
            for(int i = 1; i <= 16 - saltLength; i++){
                salt += " ";
            }
        }else if(saltLength < 24){
            for(int i = 1; i <= 24 - saltLength; i++){
                salt += " ";
            }
        }else if(saltLength < 32){
            for(int i = 1; i <= 32 - saltLength; i++){
                salt += " ";
            }
        }else if(saltLength > 32){
            salt = salt.substring(0, 31);
        }

        return new SecretKeySpec(salt.getBytes(getCharset()), Algo.RSA.name());// 转换为RSA专用密钥
    }

    private final byte[] encode(final Key key, final byte[] data){
        if(data == null){
            throw new IllegalArgumentException("RSAMcrypt encode object could not be null");
        }

        try{
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            return cipher.doFinal(data);
        }catch(InvalidKeyException e){
            logger.error(e.getMessage());
        }catch(IllegalBlockSizeException e){
            logger.error(e.getMessage());
        }catch(BadPaddingException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    private final byte[] decode(final Key key, final byte[] data){
        if(data == null){
            throw new IllegalArgumentException("RSAMcrypt decode object could not be null");
        }

        try{
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器

            return cipher.doFinal(data); // 明文
        }catch(InvalidKeyException e){
            logger.error(e.getMessage());
        }catch(IllegalBlockSizeException e){
            logger.error(e.getMessage());
        }catch(BadPaddingException e){
            logger.error(e.getMessage());
        }

        return null;
    }

}