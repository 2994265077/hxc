/*
package com.cetc.cloud.datasynch.provider.template.impl;

import com.cetc.cloud.datasynch.provider.controller.ScheduleController;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

*/
/**
 * 工程包名:   com.cetc.cloud.datasynch.provider.template.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:21 2019-07-23
 * Updater:     heliangming
 * Update_Date：16:21 2019-07-23
 * 更新描述:    heliangming 补充
 **//*

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScheduleRunnableTest extends TestCase {

    @Autowired
    ScheduleController scheduleController;

    @Test
    public void testRun() throws Exception {
        HashMap<String, String> res =  scheduleController.startScheduleJobByJobId(508);
        log.info("-->"+res);
    }

}
*/
