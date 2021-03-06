package com.justplay1994.github.db2es.service.db2es.impl;

import com.justplay1994.github.db2es.config.Oracle2esConfig;
import com.justplay1994.github.db2es.service.db.current.DatabaseNodeListInfo;
import com.justplay1994.github.db2es.service.db.operate.OracleOperate;
import com.justplay1994.github.db2es.service.db2es.Oracle2es;
import com.justplay1994.github.db2es.service.es.ESOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.justplay1994.github.db2es.service.db2es.impl
 * @Project: db2es
 * @Description:   //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/9/19 17:50
 * @Updater: huangzezhou
 * @Update_Date: 2018/9/19 17:50
 * @Update_Description: huangzezhou 补充
 **/

@Service
@Slf4j
public class Oracle2esImpl implements Oracle2es {

    @Autowired
    Oracle2esConfig oracle2esConfig;

    @Autowired
    OracleOperate oracleOperate;

    @Autowired
    ESOperate esOperate;

    @Override
    public String transfer() {
        long startTime = System.currentTimeMillis();

        DatabaseNodeListInfo.clean();
        oracleOperate.queryAllStructure();// 查询所有数据结构，阻塞

        oracleOperate.deleteTableWithoutGeo(); //删除没有经度和纬度字段的表

        esOperate.deleteAllConflict();//删除冲突索引
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("sleep error!\n",e);
        }
        esOperate.createMapping();// 创建索引映射，阻塞

//        oracleOperate.queryAllDataByPage();// 数据分页查询，将数据插入数据队列，数据队列生产者
        Thread oracleQueryThread = oracleOperate.createQueryAllDataByPage();//创建分页查询线程
        oracleQueryThread.start();

//        esOperate.esBulkGenerator();//将数据队列的数据出队，数据队列消费者。esBulk队列生产者。
        Thread esBulkGeneratorTread = esOperate.createEsBulkGeneratorTread();
        esBulkGeneratorTread.start();

//        esOperate.bulk(); //数据导入，消费者
        Thread bulkThread = esOperate.createBulkThread();
        bulkThread.start();

        while (true){
            if (oracleQueryThread.isAlive() || esBulkGeneratorTread.isAlive() || bulkThread.isAlive()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("Main sleep error!\n", e);
                }
            }else {
                break;
            }
        }

        long time = System.currentTimeMillis()-startTime;
        long m = (time/1000)/60;
        long s = time%60;

//        logger.info("\n\n============ Oracle2es finished! ==================start");
//        logger.info("*  total dbNumber:   " + DatabaseNodeListInfo.dbNumber);
//        logger.info("*  total tbNumber:   " + DatabaseNodeListInfo.tbNumber);
//        logger.info("*  total totalRowNumber:  " + DatabaseNodeListInfo.totalRowNumber);
//        logger.info("*  total failNumber: " + DatabaseNodeListInfo.failCount);
//        logger.info("*  total Time: " + m + "m" + s + "s");
//        logger.info("\n============ Oracle2es finished! ==================end\n\n");

        String result = "\n\n============ Oracle2es finished! ==================start"+"\n"+
        "*  total dbNumber:   " + DatabaseNodeListInfo.dbNumber+"\n"+
        "*  total tbNumber:   " + DatabaseNodeListInfo.tbNumber+"\n"+
        "*  total totalRowNumber:  " + DatabaseNodeListInfo.totalRowNumber+"\n"+
        "*  total failNumber: " + DatabaseNodeListInfo.failCount+"\n"+
        "*  total Time: " + m + "m" + s + "s"+"\n"+
        "============ Oracle2es finished! ==================end\n\n";
        log.info(result);
        return result;
    }
}
