package com.cetc.cloud.datasynch.provider.template.impl;

import com.cetc.cloud.datasynch.provider.config.SpringContextUtil;
import com.cetc.cloud.datasynch.provider.controller.RePullTableController;
import com.cetc.cloud.datasynch.provider.service.impl.*;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * Description：创建定时 执行计算 实例
 * Created by luolinjie on 2018/10/10.
 */
@Slf4j
public class SanxiaoCalcRunnable implements OuterJobRunnableTemplate {

    private DbOperateService dbOperateService;

    public SanxiaoCalcRunnable() {
        this.dbOperateService = (DbOperateService) SpringContextUtil.getBean("dbOperateService");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("SanxiaoCalcRunnable");
        //执行计算,输出匹配的三小场所id
        try {
            log.info("calculateHasTroubleSanXiao");
            calculateHasTroubleSanXiao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calculateHasTroubleSanXiao() throws SQLException {
        int counts = dbOperateService.getTableRowCounts("BLK_SANXIAO_PLACE");
        if (counts==0) {
            log.error("table BLK_SANXIAO_PLACE counts is 0 !!");
            return;
        }

        String sql0 = "update BLK_SANXIAO_PLACE set HAS_TROUBLE=0";
        int count0 = dbOperateService.oracleUpdateSql(sql0);
        log.info("reset HAS_TROUBLE=0,changed rows:" + count0);

        //获取有未处理事件的三小场所ID的list
        String getTroublePlaceIds = "SELECT DISTINCT a.\"ID\"\n" +
                "from BLK_SANXIAO_PLACE a,BLK_CHENGGUAN_EVENT b\n" +
                "WHERE a.name=b.EVENT_NAME\n" +
                "and a.ADDRESS=b.ADDRESS\n" +
                "and b.state=1";
        List<String> troublePlaceIdList = dbOperateService.oracleQueryList(getTroublePlaceIds);
        int totalSuccessCount = 0;
        for (int i = 0; i < troublePlaceIdList.size(); i++) {
            String sql = "update BLK_SANXIAO_PLACE set HAS_TROUBLE=1 where id='" + troublePlaceIdList.get(i) + "'";
            int count = dbOperateService.oracleUpdateSql(sql);
            if (count > 0) {
                totalSuccessCount++;
            }
        }
        log.info("\nCalculateHasTroubleSanXiao:success: " + totalSuccessCount + "\n");
    }

}


