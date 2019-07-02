package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.TableauEntity;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/11 9:35
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/11 9:35
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public interface TableauSheetMapper {

    List<TableauEntity> queryAll();

    int insert(TableauEntity tableauEntity);

    int delete(int object_id);
}
