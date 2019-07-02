package com.cetccity.operationcenter.webframework.web.dao.log;

import com.cetccity.operationcenter.webframework.web.log.entity.ApiLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.web.dao.log
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/4/16 15:40
 * @Updater: huangzezhou
 * @Update_Date: 2019/4/16 15:40
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface ApiLogMapper {

    void insert(ApiLog apiLog);

    List<ApiLog> select();

}
