/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ImageController
 * Author:   YHY
 * Date:     2019/4/22 14:58
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode;
import com.cetccity.operationcenter.webframework.unifiedauth.support.CodeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
@RestController
@Api(tags = "图片验证码")
public class ImageController {
    @Autowired
    private CodeManager codeManager;

    @ApiOperation(value = "图片验证码", notes = "获取验证码图片")
    @GetMapping(value = "/code/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage image(HttpServletResponse httpServletResponse) {
        ImageCode imageCode = codeManager.supplier();
        Cookie cookie = new Cookie("Cetc_image_code_id", imageCode.getCode().getId());
        cookie.setMaxAge(300);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        BufferedImage image = imageCode.getImage();
        imageCode.setImage(null);
        return image;
    }

}