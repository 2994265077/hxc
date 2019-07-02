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
public class TopGroup {

    @XStreamImplicit(itemFieldName="group")
    private List<TopGroupTwo> topGroupTwoList;

    @XStreamAsAttribute
    @XStreamAlias("label")
    private String label;

    @XStreamAsAttribute
    @XStreamAlias("id")
    private String id;

}
