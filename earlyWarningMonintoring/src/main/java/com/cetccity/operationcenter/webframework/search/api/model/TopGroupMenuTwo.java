package com.cetccity.operationcenter.webframework.search.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.api.model
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:01 2019-03-19
 * Updater:     heliangming
 * Update_Date：16:01 2019-03-19
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("group")
public class TopGroupMenuTwo {

    @XStreamAsAttribute
    @XStreamAlias("table")
    private String table;

    @XStreamAsAttribute
    @XStreamAlias("label")
    private String label;

    @XStreamAsAttribute
    @XStreamAlias("id")
    private String id;

    @XStreamAlias("pid")
    @XStreamAsAttribute
    private String pid;
}
