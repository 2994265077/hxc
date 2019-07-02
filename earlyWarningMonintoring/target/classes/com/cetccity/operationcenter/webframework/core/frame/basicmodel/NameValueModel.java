package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NameValueModel {

    private String name;

    private String value;

}
