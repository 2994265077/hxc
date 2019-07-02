package com.cetccity.operationcenter.webframework.core.tools;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.tools
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:41 2019-03-19
 * Updater:     heliangming
 * Update_Date：11:41 2019-03-19
 * 更新描述:    heliangming 补充
 **/
@UtilityClass
public class ListToPageInfoUtil {

    /**
     * PageInfo 转 MyPageInfoModel
     * @param t list实体
     * @param pageNum 页码
     * @param pageSize 行数
     * @return
     */
    public MyPageInfoModel<?> listToPageInfo(List<?> t, int pageNum, int pageSize) {
        MyPageInfoModel<List<?>> pageInfo = new MyPageInfoModel<>();
        int pages;
        if (t.size() % pageSize == 0) {
            pages = t.size() / pageSize;
        } else {
            pages = t.size() / pageSize + 1;
        }
        t.stream().limit(pageSize);
        pageInfo.setTotal(t.size());
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPages(pages);
        pageInfo.setList(t.stream().skip((pageNum - 1) * pageSize).
                limit(pageSize).
                collect(toList()));
        return pageInfo;
    }
}
