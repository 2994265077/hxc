package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.incident.MapDistanceModel;
import java.util.List;

public interface LoadMapNearbyResourcesMapper {
    //地铁站
    int selectDITIEZHAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectDITIEZHAN_001ObjBySquare(MapDistanceModel mapDistanceModel);
    //燃气站
    int selectRANQIZHAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectRANQIZHAN_001ObjBySquare(MapDistanceModel mapDistanceModel);
    //滑坡点
    int selectHUAPODIAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectHUAPODIAN_001ObjBySquare(MapDistanceModel mapDistanceModel);
    //气象监测站
    int selectQIXIANGJIANCEZHAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectQIXIANGJIANCEZHAN_001ObjBySquare(MapDistanceModel mapDistanceModel);
    //医疗机构信息
    int selectYILIAOJIGOU_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectYILIAOJIGOU_001ObjBySquare(MapDistanceModel mapDistanceModel);
    //物资仓库信息
    int selectWUZICANGKU_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectWUZICANGKU_001ObjBySquare(MapDistanceModel mapDistanceModel);
    //油气站信息
    int selectYOUQIZHAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectYOUQIZHAN_001ObjBySquare(MapDistanceModel mapDistanceModel);
}
