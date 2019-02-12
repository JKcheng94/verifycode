package com.checkcode.cc.codetest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @Copyright (C) 2019
 * @Description: TODO
 * @Author dp_blue
 * @Date 2019-02-11 13:54
 */

public class CheckCodeController {
    
    private int w = 70;
    private int h = 30;
    private String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Random r = new Random();
    private String text;
    /**
     * 验证码字符数
     */
    private int charNum = 4;
    /**
     * 背景颜色
     */
    private Color bgColor = new Color(188, 211, 210);
    
    /**
     * 字体
     */
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    
    /**
     * 生成随机一位字符
     */
    private char varifyCode() {
        int index = r.nextInt(code.length());
        return code.charAt(index);
    }
    
    /**
     * 生成随机颜色
     */
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }
    
    /**
     * 生成随机字体
     */
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 24;
        return new Font(fontName, style, size);
    }
    
    private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, w, h);
        return image;
    }
    
    private void drawLine(BufferedImage image){
        int num = 4;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for(int i = 0; i < num; i++){
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(randomColor());
            g2.drawLine(x1, y1, x2, y2);
        }
    }
    
    public BufferedImage getImage() {
        // 创建图片缓冲区
        BufferedImage image = createImage();
        // 得到绘制环境
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        // 装载生成的验证码文本
        StringBuilder sb = new StringBuilder();
        // 向图片画4个字符
        for(int i=0;i<charNum;i++) {
            // 随机生成一个字母
            String s = varifyCode() + "";
            sb.append(s);
            // 设置当前字母的x坐标
            float x = i * 1.0F * w / 4;
            // 设置随机字体
            g2.setFont(randomFont());
            // 设置随机颜色
            g2.setColor(randomColor());
            // 画图
            g2.drawString(s, x, h-5);
        }
        this.text = sb.toString();
        // 添加干扰线
        drawLine(image);
        return image;
        
    }
    
    /**
     * 返回验证码图片上的文本
     * @return
     */
    public String getText() {
        return text;
    }
    
    /**
     * 保存图片到指定的输出流
     * @param image
     * @param out
     * @throws IOException
     */
    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
    
    
}