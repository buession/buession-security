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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * PNG 图形验证码
 *
 * @author Yong.Teng
 */
public class PngGenerator extends AbstractGenerator {

    public final static String TYPE = "PNG";

    /**
     * background graphic alpha value
     **/
    private float bkAlpha = 0.7f;

    /**
     * validation code font alpha value
     **/
    private float fontAlpha = 0.7f;

    /**
     * oval stroke size
     **/
    private float ovalSize = 4.0f;

    /**
     * oval count. decide to draw how many ovals as background
     **/
    private int ovalCount = 20;

    /**
     * 构造函数
     */
    public PngGenerator(){
        super();
    }

    /**
     * 构造函数
     *
     * @param font
     *         字体
     */
    public PngGenerator(Font font){
        super(font);
    }

    /**
     * 构造函数
     *
     * @param length
     *         随机验证码长度
     */
    public PngGenerator(int length){
        super(length);
    }

    /**
     * 构造函数
     *
     * @param length
     *         随机验证码长度
     * @param font
     *         字体
     */
    public PngGenerator(int length, Font font){
        super(length, font);
    }

    /**
     * 构造函数
     *
     * @param width
     *         宽度
     * @param height
     *         高度
     */
    public PngGenerator(int width, int height){
        super(width, height);
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
    public PngGenerator(int width, int height, Font font){
        super(width, height, font);
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
    public PngGenerator(int width, int height, int length){
        super(width, height, length);
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
    public PngGenerator(int width, int height, int length, Font font){
        super(width, height, length, font);
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
     * @param bkAlpha
     *         背景透明度
     * @param fontAlpha
     *         字体透明度
     * @param ovalSize
     *         干扰线大小
     * @param ovalCount
     *         干扰线个数
     */
    public PngGenerator(int width, int height, int length, Font font, float bkAlpha, float fontAlpha, float ovalSize,
                        int ovalCount){
        super(width, height, length, font);
        this.bkAlpha = bkAlpha;
        this.fontAlpha = fontAlpha;
        this.ovalSize = ovalSize;
        this.ovalCount = ovalCount;
    }

    @Override
    public BufferedImage generatorImage(){
        return null;
    }

    /**
     * 输出验证码
     *
     * @param outputStream
     *         图形验证码流
     */
    @Override
    public void write(OutputStream outputStream){
        if(outputStream == null){
            return;
        }

        BufferedImage bufferedImage = generatorImage(Random.generator(length));

        try{
            ImageIO.write(bufferedImage, TYPE, outputStream);
            outputStream.flush();
        }catch(IOException e){
            //ignore
            e.printStackTrace();
        }
    }

    private BufferedImage generatorImage(char[] strs){
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        AlphaComposite ac3;
        Color color;
        int len = strs.length;

        // set background color to white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        // set the stroke to 4 pixel
        g2d.setStroke(new BasicStroke(ovalSize));
        // specify the background graphic's alpha channel
        ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, bkAlpha);
        g2d.setComposite(ac3);

        // draw random ovals
        for(int i = 0; i < ovalCount; i++){
            color = color(150, 250);
            g2d.setColor(color);
            g2d.drawOval(Random.num(width), Random.num(height), 10 + Random.num(10), 10 + Random.num(10));
        }

        g2d.setFont(font);

        // specify the validation code's alpha channel
        ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fontAlpha);
        g2d.setComposite(ac3);

        int h = height - ((height - font.getSize()) >> 1);
        int w = width / len, size = w - font.getSize() + 2;

        /* draw font */
        for(int i = 0; i < len; i++){
            // 对每个字符都用随机颜色
            color = new Color(20 + Random.num(110), 20 + Random.num(110), 20 + Random.num(110));
            g2d.setColor(color);

            // random rotate degree. -90 < degree < 90
            int degree = Random.num(90);
            degree = Random.num(2) == 0 ? -degree : degree;
            g2d.rotate(Math.toRadians(degree), (width - (len - i) * w) + w / 2, height / 2 + 2);
            g2d.drawString(strs[i] + "", (width - (len - i) * w) + size, h - 4);
            g2d.rotate(-Math.toRadians(degree), (width - (len - i) * w) + w / 2, height / 2 + 2);
        }

        g2d.dispose();

        return bi;
    }
}