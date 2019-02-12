package com.checkcode.cc.codetest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Copyright (C) 2019
 * @Description: TODO
 * @Author dp_blue
 * @Date 2019-02-11 14:57
 */
@RestController
public class Test {
    
    @RequestMapping("/reloadCode")
    public String reload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CheckCodeController ccll = new CheckCodeController();
        BufferedImage image = ccll.getImage();
        String textCode = ccll.getText();
        System.out.println(textCode);
        //获取图片相对路径
        String path = this.getClass().getClassLoader().getResource("./static/1.jpg").getPath();
        CheckCodeController.output(image, new FileOutputStream(path));
        return textCode;
    }
}
