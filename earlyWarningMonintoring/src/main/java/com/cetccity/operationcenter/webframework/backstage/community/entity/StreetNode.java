/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: StreetInfo
 * Author:   YHY
 * Date:     2019/5/28 15:43
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @create 2019/5/28
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreetNode {
    @JsonProperty("object_id")
    private String objectId;

    @JsonProperty("code")
    private String streetCode;

    @JsonProperty("name")
    private String streetName;

    @JsonProperty("lay_id")
    private String layId;

    private List<String> coordinates;

    @JsonProperty("children")
    private List<CommunityNode> communityNodes = new LinkedList();
}