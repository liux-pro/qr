package com.example.qr.test;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class TestController {
    @GetMapping
    public void get(HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        QrCodeUtil.generate("张三", 300, 300, "png",response.getOutputStream());
    }

    @GetMapping("mix")
    public void get2(HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate("张三", 300, 300, "png",byteArrayOutputStream);
        ImgUtil.pressImage(
                new FileInputStream(FileUtil.file("bg.jpg")),
                response.getOutputStream(),
                ImgUtil.read(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())), //水印图片
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                1f
        );
    }


}
