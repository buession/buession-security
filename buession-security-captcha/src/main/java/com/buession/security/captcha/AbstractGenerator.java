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
package com.buession.security.captcha;

import com.buession.security.captcha.core.Random;

import java.awt.*;

/**
 * 验证码生成器抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractGenerator implements Generator {

    protected final static int COLOR_MAX_RGB_VALUE = 255;

    protected int width = 150;

    protected int height = 40;

    protected int length = 5;

    protected Font font = new Font("Verdana", Font.ITALIC | Font.BOLD, 28);

    /**
     * 构造函数
     */
    public AbstractGenerator(){
    }

    /**
     * 构造函数
     *
     * @param font
     *         字体
     */
    public AbstractGenerator(Font font){
        this.font = font;
    }

    /**
     * 构造函数
     *
     * @param length
     *         随机验证码长度
     */
    public AbstractGenerator(int length){
        this.length = length;
    }

    /**
     * 构造函数
     *
     * @param length
     *         随机验证码长度
     * @param font
     *         字体
     */
    public AbstractGenerator(int length, Font font){
        this(length);
        this.font = font;
    }

    /**
     * 构造函数
     *
     * @param width
     *         宽度
     * @param height
     *         高度
     */
    public AbstractGenerator(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * 构造函数
     *
     * @param width
     *         宽度
     * @param height
     *         高度
     * @param font
     *         字体
     */
    public AbstractGenerator(int width, int height, Font font){
        this(width, height);
        this.font = font;
    }

    /**
     * 构造函数
     *
     * @param width
     *         宽度
     * @param height
     *         高度
     * @param length
     *         随机验证码长度
     */
    public AbstractGenerator(int width, int height, int length){
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * 构造函数
     *
     * @param width
     *         宽度
     * @param height
     *         高度
     * @param length
     *         随机验证码长度
     * @param font
     *         字体
     */
    public AbstractGenerator(int width, int height, int length, Font font){
        this(width, height, length);
        this.font = font;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }

    protected static Color color(int fc, int bc){
        if(fc > COLOR_MAX_RGB_VALUE){
            fc = COLOR_MAX_RGB_VALUE;
        }

        if(bc > COLOR_MAX_RGB_VALUE){
            bc = COLOR_MAX_RGB_VALUE;
        }

        int r = fc + Random.num(bc - fc);
        int g = fc + Random.num(bc - fc);
        int b = fc + Random.num(bc - fc);

        return new Color(r, g, b);
    }

}
