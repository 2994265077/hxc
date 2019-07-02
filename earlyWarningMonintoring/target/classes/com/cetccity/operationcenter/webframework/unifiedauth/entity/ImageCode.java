/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ImageCode
 * Author:   YHY
 * Date:     2019/4/22 10:43
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片验证码〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageCode {
    private Code code;
    private BufferedImage image;
}