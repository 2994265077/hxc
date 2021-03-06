package com.cetc.cloud.datasynch.provider.service.impl;

import com.cetc.cloud.datasynch.api.model.DsColumnCommentModel;
import com.cetc.cloud.datasynch.api.model.DsTableCommentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.service.impl
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/11/6 20:28
 * Updater:     by luolinjie
 * Update_Date: 2018/11/6
 * Update_Description: luolinjie 补充
 **/
@Service
@Slf4j
public class CommentManageService {
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String userName;

    public List<DsTableCommentModel> addTableCommentList(List<DsTableCommentModel> modelList) {
        List<DsTableCommentModel> resList = new ArrayList<DsTableCommentModel>();
        int successCount = 0;
        int failCount = 0;
        for (DsTableCommentModel model : modelList) {
            String SQL = "COMMENT ON TABLE \"" + userName + "\".\"" + model.getTableName() + "\" " +
                    "IS\t'" + model.getTableComment() + "'";
            try {
                primaryJdbcTemplate.update(SQL);
                resList.add(model);
            } catch (Exception e) {
                log.error(model.toString());
                failCount++;
            }
            successCount++;
        }
        log.info("\nFinished! \nsuccessCount:" + successCount + "\nfailCount:" + failCount);
        return resList;
    }

    public List<DsColumnCommentModel> addColumnCommentList(List<DsColumnCommentModel> modelList) {

        List<DsColumnCommentModel> resList = new ArrayList<DsColumnCommentModel>();

        int successCount = 0;
        int failCount = 0;
        for (DsColumnCommentModel model : modelList) {

            String SQL = "COMMENT ON COLUMN \"" + userName + "\".\"" + model.getTableName() + "\".\"" + model.getColumnName() + "\" " +
                    "IS '" + model.getColumnComment() + "'";

            try {
                primaryJdbcTemplate.update(SQL);
                resList.add(model);
            } catch (Exception e) {
                log.error(model.toString());
                e.printStackTrace();
                failCount++;
            }
            successCount++;
        }
        log.info("\n>>> finished add comment on table:\""+modelList.get(0).getTableName()
                +"\"\nsuccessed:"+successCount
                +"\nfailed:"+failCount);
        return resList;
    }
}
