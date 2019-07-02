/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ImageUtils
 * Author:   YHY
 * Date:     2019/4/22 9:51
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.Code;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成验证码图片〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
public class ImageUtils {
    private final static String codes[] = {
            "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "0", "1",
            "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "o", "v",
            "w", "x", "y", "z", "A", "B", 
            "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", 
            "O", "P", "Q", "R", "S", "T", 
            "O", "V", "W", "X", "Y", "Z"
    };

    private final static Integer DEFAULT_CODE_SIZE = 4;

    public static ImageCode getImageCode(int width, int height) {
        if (width <= 0) width = 60;
        if (height <= 0) height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics2D  graphics = image.createGraphics();
        Random random = new Random();
        // 设定背景色
        graphics.setColor(getRandColor(200, 250));
        // 画布
        graphics.fillRect(0, 0, width, height);
        // 字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        // 随机产生干扰线
        for (int i = 0; i < 20; i++) {
            graphics.setColor(getRandColor(0, 200));
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            graphics.drawLine(x1, height, x2, 0);

        }
        //取随机产生的码
        // 这里不要使用并发填充，需保证顺序，不要使用parallelStream
        String finalCode = IntStream.range(0, DEFAULT_CODE_SIZE)
                .mapToObj(index -> {
                    int add = random.nextInt(60);
                    add -= 30;
                    String code = codes[(int) (codes.length * Math.random())];
                    // 将认证码显示到图象中
                    graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                    // 直接生成
                    // 设置随便码在背景图图片上的位置
                    int inter = (image.getWidth() - 40) / 4;
                    graphics.drawString(code,  inter * index + 20, image.getHeight()/2);
                    return code;
                })
                .reduce(
                        new StringBuilder(),
                        (strBuilder, code) -> strBuilder.append(code),
                        (strBuilder1, strBuilder2) -> strBuilder1.append(strBuilder2)
                )
                .toString();

        // 释放图形上下文
        graphics.dispose();
        return ImageCode
                .builder()
                .image(image)
                .code(Code.builder().value(finalCode).build())
                .build();
    }
    //给定范围获得随机颜色
    static Color getRandColor(int lower, int higher) {
        Random random = new Random();
        if (lower > 255) lower = 255;
        if (higher > 255) higher = 255;
        int r = lower + random.nextInt(higher - lower);
        int g = lower + random.nextInt(higher - lower);
        int b = lower + random.nextInt(higher - lower);
        return new Color(r, g, b);
    }
}