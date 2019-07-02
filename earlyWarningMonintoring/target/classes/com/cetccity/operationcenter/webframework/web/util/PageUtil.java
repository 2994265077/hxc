package com.cetccity.operationcenter.webframework.web.util;

import java.util.HashMap;
import java.util.List;

/**
 * Description：分页工具：将输入的list按照分页参数计算，返回截取后的结果
 * Created by luolinjie on 2018/7/12.
 */
public class PageUtil {

    public static HashMap<String, Object> getPagedResult(List<HashMap> data, int pageNum, int pageSize){
        HashMap<String,Object> result = new HashMap<String, Object>();
        if (pageNum<=0  ){
            result.put("error", "pageNum cannot be smaller than 1！");
            return result;
        }
        if ( pageSize<=0){
            result.put("error","pageSize cannot be smaller than 1！");
            return result;
        }

        int totalRecords = data.size();
        int fromIndex = (pageNum-1)*pageSize;
        int endIndex = pageNum*pageSize < data.size() ? pageNum*pageSize : data.size();

        data = data.subList(fromIndex,endIndex);
        data = TextFilterUtil.emptyOperate(data);
        int pageTotal = (totalRecords - 1) / pageSize + 1;
        result.put("data", data);

        result.put("totalPages", pageTotal);
        result.put("total", totalRecords);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }

}
