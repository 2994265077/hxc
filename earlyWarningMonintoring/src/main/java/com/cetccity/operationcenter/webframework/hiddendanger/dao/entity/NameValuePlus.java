/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: NameValuePlus
 * Author:   YHY
 * Date:     2019/5/7 10:26
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/7
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameValuePlus  extends NameValueTypeModel<Integer> implements Serializable {
    private String type;
    private String layerid;
}