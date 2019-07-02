package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.urbansign.api.PublicSecurityApi;
import com.cetccity.operationcenter.webframework.urbansign.service.PublicSecurityService;
import com.cetccity.operationcenter.webframework.urbansign.api.model.PublicSecurityDetail;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicSecurityController implements PublicSecurityApi {

    @Autowired
    PublicSecurityService publicSecurityService;


    public NameDataModel publicSecurity() {
        NameDataModel nameDataModel = publicSecurityService.publicSecurity();
        return nameDataModel;
    }


    public PublicSecurityDetail publicSecurityDetail() {
        PublicSecurityDetail publicSecurityDetail = publicSecurityService.publicSecurityDetail();
        return publicSecurityDetail;
    }

}
