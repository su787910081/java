package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@RestController
public class PicUploadController {

    /*
     * 图片上传逻辑: 1. 判断 扩展名 jpg, png, gif 2. 判断是否木马(正确思路应该是引入第三方jar 包的api 判断) 3.
     * 生成两个路径: 磁盘路径、url相对路径访问地址 D:\apache-tomcat-8.0.52\webapps\ROOT\ +
     * images\2018\07\26\1111.jpg yyyy/mm/dd url:
     * http://localhost:8080/images\2018\07\26\1111.jpg 4. 图片文件重命名, currentTime
     * + 3位随机数 5. 保存 6. 封装picUploadResult 返回，中途任何问题都导致返回对象的error=1
     */
    @RequestMapping("pic/upload")
    public PicUploadResult uploadPics(MultipartFile uploadFile) {
        // 构造一个返回的空对象
        PicUploadResult result = new PicUploadResult();
        // 判断后缀
        String oldFileName = uploadFile.getOriginalFilename();
        // 截取后缀
        String extFileName = oldFileName
                .substring(oldFileName.lastIndexOf("."));

        // 正则判断合法性
        if (!extFileName.matches("^.(jpg|png|gif)$")) {
            result.setError(1);
            return result;
        }

        try {
            // 判断木马, BufferedImage 判断是否有宽高
            BufferedImage bufImage = ImageIO.read(uploadFile.getInputStream());
            // getHeight 没有抛异常，则说明不是木马
            result.setHeight(bufImage.getHeight() + "");
            result.setWidth(bufImage.getWidth() + "");

            // 生成路径
            String dir = "/images/"
                    + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                    + "/";
            // 拼接磁盘路径，拼接URL 路径
            String path = "D:/apache-tomcat-8.0.52/webapps/ROOT/" + dir;
            String urlPath = "http://localhost:8080/" + dir; 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
