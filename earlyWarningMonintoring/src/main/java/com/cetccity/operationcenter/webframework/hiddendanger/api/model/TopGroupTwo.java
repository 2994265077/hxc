package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("group")
public class TopGroupTwo {

    @XStreamImplicit(itemFieldName="group")
    private List<LoadAttributeMapTwo> loadAttributeMapTwoList;

    @XStreamAsAttribute
    @XStreamAlias("table")
    private String table;

    @XStreamAsAttribute
    @XStreamAlias("filter")
    private String filter;

    @XStreamAsAttribute
    @XStreamAlias("label")
    private String label;

    @XStreamAsAttribute
    @XStreamAlias("id")
    private String id;

    @XStreamAlias("pid")
    @XStreamAsAttribute
    private String pid;

    @XStreamAsAttribute
    @XStreamAlias("type")
    private String type;

    @XStreamAsAttribute
    @XStreamAlias("icon")
    private String icon;

    @XStreamAsAttribute
    @XStreamAlias("iconArray")
    private String iconArray;
    /*@XStreamAsAttribute
    @XStreamAlias("show")
    private String show;

    @XStreamAsAttribute
    @XStreamAlias("bm")
    private String bm;

    @XStreamAsAttribute
    @XStreamAlias("icon")
    private String icon;

    @XStreamAsAttribute
    @XStreamAlias("function")
    private String function;

    @XStreamAsAttribute
    @XStreamAlias("url")
    private String url;*/

}
