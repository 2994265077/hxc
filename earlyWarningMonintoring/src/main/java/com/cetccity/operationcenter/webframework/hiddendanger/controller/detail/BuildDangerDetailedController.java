package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.BuildDangerDetailedApi;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BuildDetailService;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.BuildOfDangerSoft;
import com.cetccity.operationcenter.webframework.web.model.build.*;
import com.cetccity.operationcenter.webframework.web.service.QXFJ_BUILD_IMAGE_VService;
import com.cetccity.operationcenter.webframework.web.service.QXFJ_BUILD_ZTZR_VService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class BuildDangerDetailedController implements BuildDangerDetailedApi {

    @Autowired
    BuildDetailService buildDetailService;

    @Autowired
    BuildOfDangerSoft buildOfDangerSoft;

    @Autowired
    QXFJ_BUILD_ZTZR_VService qXFJ_BUILD_ZTZR_VService;

    @Autowired
    QXFJ_BUILD_IMAGE_VService qXFJ_BUILD_IMAGE_VService;

    public NameDataModel buildBasic(@PathVariable("tableName") String tableName, String buildid) {
        NameDataModel nameDataModel = buildDetailService.buildBasic(tableName,buildid);
        return nameDataModel;
    }

    public NameDataModel buildRisk(@PathVariable("tableName") String tableName, String buildid) {
        NameDataModel scoreTotleAttribute = buildDetailService.buildRisk(tableName,buildid);
        return scoreTotleAttribute;
    }

    public MyPageInfoModel buildHiddenDanger(@PathVariable("tableName") String tableName, String buildid, Integer pageNum, Integer pageSize) {
        MyPageInfoModel pageInfo = buildDetailService.buildHiddenDanger(buildid,pageNum,pageSize);
        return pageInfo;
    }

    public EnterpriseModel buildEnterprise(@PathVariable("tableName") String tableName, String buildid, Integer pageNum, Integer pageSize) {
        EnterpriseModel enterpriseModel = buildDetailService.buildEnterprise(buildid,pageNum,pageSize);
        return enterpriseModel;
    }

    public LayerNumData blkHouseDetailed(@PathVariable("tableName") String tableName, String buildid) {
        LayerNumData layerNumData = buildDetailService.buildHouse(tableName, buildid);
        return layerNumData;
    }

    /*高风险建筑列表  tableName= t_build_info_v*/
    public List<BuildOfDangerModel> buildDangerDetailed(@PathVariable("tableName") String tableName) {
        List<BuildOfDangerModel> buildOfDangerModel_list = buildOfDangerSoft.getBuildScoreRank();
        return buildOfDangerModel_list;
    }

    /*建筑面落图  tableName= t_build_info_v*/
    public List<LoadMapBuildGrade>  buildDangerLoadMap() {
        List<LoadMapBuildGrade> loadMapBuildGrade_list = buildOfDangerSoft.getBuildDangerLoadMap();
        return loadMapBuildGrade_list;
    }

    public LinkedHashMap buildResponsibility(String buildid) {
        LinkedHashMap map = qXFJ_BUILD_ZTZR_VService.getQXFJ_BUILD_ZTZR_V(buildid);
        return map;
    }

    public NameDataModel buildGraphicDrawings(String buildid) {
        NameDataModel nameDataModel = qXFJ_BUILD_IMAGE_VService.getBUILD_IMAGE(buildid);
        return nameDataModel;
    }

}
