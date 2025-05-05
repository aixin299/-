package com.axx.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RandomValidateCodeUtil {
    /**
     * 验证码字符集
     */
    private final char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 字符数量
     */
    private static final int SIZE = 4;

    /**
     * 干扰线数量
     */
    private static final int LINES = 5;

    /**
     * 宽度
     */
    private static   final int WIDTH = 80;

    /**
     * 高度
     */
    private  static final int HEIGHT = 40;

    /**
     * 字体大小
     */
    private static final int FONT_SIZE = 30;

    /**
     * 生成随机验证码及图片
     * Object[0]：验证码字符串；
     * Object[1]：验证码图片。
     */
    public Object[] createImage() {
        StringBuffer sb = new StringBuffer();
        // 1.创建空白图片 创建一个不带透明色的对象
        BufferedImage image = new BufferedImage(
                WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i <SIZE; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(
                    null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            // 画字符
            graphic.drawString(
                    chars[n] + "", i * WIDTH / SIZE, HEIGHT*2/3);
            // 记录字符
            sb.append(chars[n]);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new Object[]{sb.toString(), image};
    };
    /**
     * 随机取色
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
        return color;
    }

    /**
     * 随机产生字母或者数字的验证码
     */
    public Object[] createMumAndChar() {
        // 这里用来存储
        StringBuffer sb = new StringBuffer();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色,绘制背景色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 生成4个验证码，随机字母或者数字
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            int index =  random.nextInt(str.length());
            char val = str.charAt(index);
            graphic.setColor(getRandomColor());
            graphic.setFont(new Font(
                    null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            graphic.drawString(String.valueOf(val),i * WIDTH / SIZE, HEIGHT*2/3);
            sb.append(val);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),
                    random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new Object[]{sb.toString(), image};
    }

    /**
     * 生成随机字符和数字 65-90 97-122 48-57
     */
    public static Object[] createMumAndCharTwo() {
        StringBuilder sb = new StringBuilder();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色,绘制背景色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        Random random = new Random();
        long val = 0L;
        for (int i = 0; i < SIZE; i++) {
            int type =  random.nextInt(3);
            // Math.random()*25 取值0-1 也就是0-25
            switch (type){
                case 0:
                    val=Math.round(Math.random()*25+65);
                    break;
                case 1:
                    val=Math.round(Math.random()*25+97);
                    break;
                default:
                    val=Math.round(Math.random()*9+48);

            }
            graphic.setColor(getRandomColor());
            graphic.setFont(new Font(
                    null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            graphic.drawString(String.valueOf((char)val),i * WIDTH / SIZE, HEIGHT*2/3);
            sb.append((char)val);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),
                    random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new Object[]{sb.toString(), image};
    }

}
