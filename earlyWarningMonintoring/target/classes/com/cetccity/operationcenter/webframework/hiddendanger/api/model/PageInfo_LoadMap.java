package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import lombok.Data;
import java.util.List;

@Data
public class PageInfo_LoadMap {

    private MyPageInfoModel pageInfo;

    private List<String> ID;

 }
