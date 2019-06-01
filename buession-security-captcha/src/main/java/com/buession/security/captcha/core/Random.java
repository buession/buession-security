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
package com.buession.security.captcha.core;

import java.util.Arrays;

/**
 * @author Yong.Teng
 */
public class Random {

    private static final java.util.Random RANDOM = new java.util.Random();

    private final static char LOWER[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private final static char UPPER[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'G', 'K', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private final static char DIGIT[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char ALPHA[] = Arrays.copyOf(LOWER, LOWER.length + UPPER.length);

    private static final char ALNUM[] = Arrays.copyOf(LOWER, LOWER.length + UPPER.length + DIGIT.length);

    static{
        System.arraycopy(UPPER, 0, ALPHA, LOWER.length, UPPER.length);

        System.arraycopy(UPPER, 0, ALNUM, LOWER.length, UPPER.length);
        System.arraycopy(DIGIT, 0, ALNUM, LOWER.length + UPPER.length, DIGIT.length);
    }

    public final static int num(int min, int max){
        return min + RANDOM.nextInt(max - min);
    }

    public final static int num(int num){
        return RANDOM.nextInt(num);
    }

    public final static char lower(){
        return LOWER[num(0, LOWER.length)];
    }

    public final static char upper(){
        return UPPER[num(0, UPPER.length)];
    }

    public final static char digit(){
        return DIGIT[num(0, DIGIT.length)];
    }

    public final static char alpha(){
        return ALPHA[num(0, ALPHA.length)];
    }

    public final static char alnum(){
        return ALNUM[num(0, ALNUM.length)];
    }

    public final static char[] generator(int length, RandomType type){
        if(length < 0){
            throw new IllegalArgumentException("The captcha string length could not be 0 or negative.");
        }

        char[] cs = new char[length];

        StringBuilder sb = new StringBuilder();

        if(type == Random.RandomType.LOWER){
            for(int i = 0; i < length; i++){
                cs[i] = lower();
            }
        }else if(type == RandomType.UPPER){
            for(int i = 0; i < length; i++){
                cs[i] = upper();
            }
        }else if(type == RandomType.DIGIT){
            for(int i = 0; i < length; i++){
                cs[i] = digit();
            }
        }else if(type == RandomType.ALPHA){
            for(int i = 0; i < length; i++){
                cs[i] = alpha();
            }
        }else if(type == RandomType.ALNUM){
            for(int i = 0; i < length; i++){
                cs[i] = alnum();
            }
        }

        return cs;
    }

    public final static char[] generator(int length){
        return generator(length, RandomType.ALNUM);
    }

    public enum RandomType {

        LOWER,

        UPPER,

        DIGIT,

        ALPHA,

        ALNUM;
    }

}
