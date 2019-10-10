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

/**
 * @author Yong.Teng
 */

import com.buession.core.utils.Assert;
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
 * AES 加密对象
 *
 * @author Yong.Teng
 */
public final class AESMcrypt extends AbstractMcrypt {

    public final static int KEY_LENGTH = 16;

    private static Cipher cipher = null;

    private final static Logger logger = LoggerFactory.getLogger(AESMcrypt.class);

    public AESMcrypt(){
        super(Algo.AES);
        initCipher();
    }

    /**
     * @param provider
     *         信息摘要对象的提供者
     */
    public AESMcrypt(final Provider provider){
        super(Algo.AES, provider);
        initCipher();
    }

    /**
     * @param characterEncoding
     *         字符编码
     */
    public AESMcrypt(final String characterEncoding){
        super(Algo.AES, characterEncoding);
        initCipher();
    }

    /**
     * @param charset
     *         字符编码
     */
    public AESMcrypt(final Charset charset){
        super(Algo.AES, charset);
        initCipher();
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public AESMcrypt(final String characterEncoding, final Provider provider){
        this(characterEncoding, null, provider);
    }

    /**
     * @param charset
     *         字符编码
     * @param provider
     *         信息摘要对象的提供者
     */
    public AESMcrypt(final Charset charset, final Provider provider){
        this(charset, null, provider);
    }

    /**
     * @param characterEncoding
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public AESMcrypt(final String characterEncoding, final String salt){
        this(characterEncoding, salt, null);
    }

    /**
     * @param charset
     *         字符编码
     * @param salt
     *         加密密钥
     */
    public AESMcrypt(final Charset charset, final String salt){
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
    public AESMcrypt(final String characterEncoding, final String salt, final Provider provider){
        super(Algo.AES, characterEncoding, salt, provider);
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
    public AESMcrypt(final Charset charset, final String salt, final Provider provider){
        super(Algo.AES, charset, salt, provider);
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

        logger.debug("Mcrypt encode string <{}> by algo <AES>, salt <{}>", object, getRealSalt());

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

        logger.debug("Mcrypt decode string <{}> by algo <AES>, salt <{}>", cs, getRealSalt());

        return result == null ? null : new String(result);
    }

    private final static Cipher initCipher(){
        if(cipher == null){
            try{
                cipher = Cipher.getInstance(Algo.AES.getName());
            }catch(NoSuchAlgorithmException e){
                logger.error(e.getMessage());
            }catch(NoSuchPaddingException e){
                logger.error(e.getMessage());
            }
        }

        return cipher;
    }

    private final Key getKey(){
        StringBuffer sb = new StringBuffer();
        String salt = getSalt();

        int saltLength = salt.length();
        if(saltLength < KEY_LENGTH){
            sb.append(salt);
            for(int i = 1; i <= KEY_LENGTH - saltLength; i++){
                sb.append(" ");
            }
        }else if(saltLength > KEY_LENGTH){
            sb.append(salt, 0, KEY_LENGTH);
        }else{
            sb.append(salt);
        }

        return new SecretKeySpec(sb.toString().getBytes(getCharset()), Algo.AES.getName());
    }

    private final byte[] encode(final Key key, final byte[] data){
        Assert.isNull(data, "Mcrypt decode object could not be null");

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
        Assert.isNull(data, "Mcrypt decode object could not be null.");

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