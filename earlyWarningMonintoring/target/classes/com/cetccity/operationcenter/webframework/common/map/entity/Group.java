/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: Group
 * Author:   YHY
 * Date:     2019/6/3 11:37
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.map.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @JsonProperty("object_id")
    private String objectId;
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "label", isAttribute = true)
    private String label;
    @JacksonXmlProperty(localName = "pid", isAttribute = true)
    private String pid;
    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;
    @JacksonXmlProperty(localName = "show", isAttribute = true)
    private String show;
    @JacksonXmlProperty(localName = "bm", isAttribute = true)
    private String bm;
    @JacksonXmlProperty(localName = "function", isAttribute = true)
    private String function;
    @JacksonXmlProperty(localName = "url", isAttribute = true)
    private String url;
    @JacksonXmlProperty(localName = "iconsize", isAttribute = true)
    @JsonProperty("icon_size")
    private String iconSize;
    @JacksonXmlProperty(localName = "icon", isAttribute = true)
    private String icon;
    @JacksonXmlProperty(localName = "table", isAttribute = true)
    private String table;
    @JacksonXmlProperty(localName = "query", isAttribute = true)
    private String query;
    @JacksonXmlProperty(localName = "filter", isAttribute = true)
    private String filter;
    @JsonProperty("parent_id")
    private String parentId;
    private String description;

    @JacksonXmlElementWrapper(useWrapping=false, localName = "group")
    public List<Group> group = new LinkedList<>();
}