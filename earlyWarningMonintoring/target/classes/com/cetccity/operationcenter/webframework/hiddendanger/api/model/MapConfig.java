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
@XStreamAlias("Map")
public class MapConfig {

    @XStreamImplicit(itemFieldName="group")
    private List<TopGroup> topGroupList;

    @XStreamAsAttribute
    @XStreamAlias("label")
    private String label;

}
