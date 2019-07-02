/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: Map
 * Author:   YHY
 * Date:     2019/6/3 11:37
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.map.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Map")
public class Map extends Group{

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}