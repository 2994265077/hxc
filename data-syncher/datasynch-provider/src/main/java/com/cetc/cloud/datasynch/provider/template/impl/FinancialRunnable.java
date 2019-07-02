package com.cetc.cloud.datasynch.provider.template.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cetc.cloud.datasynch.api.model.FinancialModel;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.mapper.FinancialMapper;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import com.cetc.cloud.datasynch.provider.util.HttpClientUtil2;
import com.cetc.cloud.datasynch.provider.util.UnicodeUtils;
import com.cetc.cloud.datasynch.provider.util.entity.GetModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by Administrator on 2019/6/13.
 */
@Slf4j
public class FinancialRunnable implements OuterJobRunnableTemplate {
    private FinancialMapper financialMapper;
    private HashMap<Integer, String> urls;

    public FinancialRunnable() {
        this.financialMapper = financialMapper;
        urls = new LinkedHashMap<>();
//        urls.put(1, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3315");
//        urls.put(2, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3351");
//        urls.put(3, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3352");
//        urls.put(4, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3315");
//        urls.put(5, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4112");
//        urls.put(6, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4104");
//        urls.put(7, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4152");
//        urls.put(8, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3304");
//        urls.put(9, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4954");
//        urls.put(10, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=2006");
//        urls.put(11, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3420");
//        urls.put(12, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3717");
//        urls.put(13, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=2745");
//        urls.put(14, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3419");
//        urls.put(15, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3421");
//        urls.put(16, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=3420");
//        urls.put(17, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4234");
//        urls.put(18, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4225");
//        urls.put(19, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4229");
//        urls.put(20, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4226");
//        urls.put(21, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4227");
//        urls.put(22, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4233");
//        urls.put(23, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4232");
//        urls.put(24, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4228");
//        urls.put(25, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=4230");
        urls.put(26, "http://10.190.55.1/api/front_map/construct_data?index_id=25600&compare_id=&period_id=2018%2F12&region=&industry_id=&time_level=3&word=&district_code=&custom_level=&is_rc=1");
        urls.put(27, "http://10.190.55.1/api/front_map/construct_data?index_id=25599&compare_id=&period_id=&region=&industry_id=&time_level=3&word=&district_code=&custom_level=&is_rc=1");
//        urls.put(28, "http://10.190.55.1/api/front_map/company_top_ten?index_id=25130&compare_id=&period_id=&region=&industry_id=&time_level=3&word=&district_code=&islineandbar=1&isheaderShow=0&is_rc=1");
        urls.put(29, "http://10.190.55.1/api/front_map/company_top_ten?index_id=25139&compare_id=&period_id=2017%2F12&region=&industry_id=&time_level=3&word=&district_code=&islineandbar=1&isheaderShow=0&is_rc=1");
//        urls.put(30, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=5005");
//        urls.put(31, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=5073");
//        urls.put(32, "http://10.190.55.1/api/current_choose/get_macro_snapshot_data?user-token=bda71f&id=5074");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("FinancialRunnable");
        //读取URL列表,发起Http请求
        Set<Integer> keySet = urls.keySet();

        for (Integer key : keySet) {
            GetModel getModel = new GetModel();
            getModel.setUrl(HttpClientUtil2.getUrlAndParamsFromURL(urls.get(key)).getString(CommonInstance.HTTP_URL_KEY_NAME));
            getModel.setParams(HttpClientUtil2.getUrlAndParamsFromURL(urls.get(key)).getJSONObject(CommonInstance.HTTP_QUERY_PARAMS_KEY_NAME));
            JSONObject header = new JSONObject();
            header.put("cookie", "ci_session=08685ca1ee9aa72a9038ddf080947b539bea0e67; Admin-Token=limujun; szstat_uid=432; szstat_username=%E6%9D%8E%E6%9C%A8%E4%BF%8A; szstat_color=1; szstat_name=limujun");

            getModel.setHeader(header);
            JSONObject jsonRes = HttpClientUtil2.doGetWithGetModel(getModel);
            if (jsonRes.getIntValue("code") != 200) {
                log.error("failed on updating FINANCIAL_FUTIAN :objectId=" + key);
                continue;
            }
            UpdateWrapper<FinancialModel> wrapper = new UpdateWrapper<>();
            wrapper.lambda()
                    .eq(FinancialModel::getObjectId, key);
            FinancialModel financialModel = new FinancialModel();
            financialModel.setObjectId(key);
            financialModel.setYjjcUpdateTime(new Date());
            financialModel.setJsonValue(StringEscapeUtils.unescapeJava(UnicodeUtils.unicode2String(jsonRes.toJSONString())));
            int update = financialMapper.update(financialModel, wrapper);
            if (update > 0) {
                log.info("successfully updated FINANCIAL_FUTIAN,key:" + key + ",value:" + StringEscapeUtils.unescapeJava(UnicodeUtils.unicode2String(jsonRes.toJSONString())));
            }
        }

    }
}
