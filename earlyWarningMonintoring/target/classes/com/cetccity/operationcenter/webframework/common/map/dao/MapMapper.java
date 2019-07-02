/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MapMapper
 * Author:   YHY
 * Date:     2019/6/3 14:47
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.map.dao;

import com.cetccity.operationcenter.webframework.common.map.entity.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
public interface MapMapper {

    String getId();

    long save(@Param("group") Group group);

    long update(@Param("group")Group group);

    List<Group> queryAll();

    long deleteAll();

    long deleteByPri(@Param("object_id") String objectId);

    long countByPri(@Param("object_id") String objectId);

}