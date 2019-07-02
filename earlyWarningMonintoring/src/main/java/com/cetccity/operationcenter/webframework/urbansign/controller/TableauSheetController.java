package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.TableauSheetApi;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.TableauEntity;
import com.cetccity.operationcenter.webframework.urbansign.service.impl.TableauSheetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.controller
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/10 9:20
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/10 9:20
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class TableauSheetController implements TableauSheetApi {

    @Autowired
    TableauSheetServiceImpl tableauSheetService;

    @Override
    public HttpResponseModel<String> getTicket() {
        return tableauSheetService.getTicket();
    }

    @Override
    public HttpResponseModel<List<TableauEntity>> getList() {
        return tableauSheetService.getList();
    }

    @Override
    public HttpResponseModel<Boolean> insertSheet(TableauEntity model) {
        return tableauSheetService.insertSheet(model);
    }

    @Override
    public HttpResponseModel<Boolean> deleteSheet(int object_id) {
        return tableauSheetService.deleteSheet(object_id);
    }

}
