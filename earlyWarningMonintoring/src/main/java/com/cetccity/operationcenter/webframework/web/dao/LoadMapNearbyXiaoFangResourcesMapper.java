package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.incident.MapDistanceModel;
import java.util.List;

public interface LoadMapNearbyXiaoFangResourcesMapper {
    //避难场所信息
    int selectBINANCHANGSUO_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectBINANCHANGSUO_001ObjBySquare(MapDistanceModel mapDistanceModel);

    //消防站信息
    int selectXIAOFANGZHAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectXIAOFANGZHAN_001ObjBySquare(MapDistanceModel mapDistanceModel);

    //危化品企业信息
    int selectWEIHUAPINQIYE_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectWEIHUAPINQIYE_001ObjBySquare(MapDistanceModel mapDistanceModel);

    //危化点信息
    int selectWEIHUADIAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectWEIHUADIAN_001ObjBySquare(MapDistanceModel mapDistanceModel);

    //取水点信息
    int selectQUSHUIDIAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectQUSHUIDIAN_001ObjBySquare(MapDistanceModel mapDistanceModel);

    //天然取水点信息
    int selectTIANRANQUSHUIDIAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectTIANRANQUSHUIDIAN_001ObjBySquare(MapDistanceModel mapDistanceModel);

    //消火栓信息
    int selectXIAOHUOSHUAN_001NumBySquare(MapDistanceModel mapDistanceModel);

    List<LoadMap> selectXIAOHUOSHUAN_001ObjBySquare(MapDistanceModel mapDistanceModel);
}
