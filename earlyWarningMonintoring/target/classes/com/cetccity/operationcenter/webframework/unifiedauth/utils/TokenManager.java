package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel;
import io.jsonwebtoken.Claims;

import javax.validation.constraints.NotNull;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.utils
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: yhy
 * @Create_Date: 2019/4/19 13:59
 * @Updater: yhy
 * @Update_Date: 2019/4/19 13:59
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
public interface TokenManager<T> {

    /**
     * 功能描述: <br>
     * 〈生成token〉
     *
     * @param subject
     * @return:com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel
     * @Author:yhy
     * @Date: 2019/4/19 14:05
     */
    TokenModel token(@NotNull T subject);

    /**
     * 功能描述: <br>
     * 〈解析token〉
     *
     * @param token
     * @return:T
     * @Author:yhy
     * @Date: 2019/4/19 14:05
     */
    Claims deToken(@NotNull String token);

    /**
     * 功能描述: <br>
     * 〈刷新token〉
     *
     * @param token
     * @return:com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel
     * @Author:yhy
     * @Date: 2019/4/19 14:05
     */
    TokenModel refresh(@NotNull String token);

    /**
     * 功能描述: <br>
     * 〈移除一个token（退出登录，让token失效）〉
     *
     * @param token
     * @return: boolean
     * @Author:dongxin
     * @Date: 2019/4/19 14:05
     */
    TokenModel evict(@NotNull String token);

    /**
     * 功能描述: <br>
     * 〈判断token是否可用〉
     *
     * @param token
     * @return:boolean  可用返回true， 否则返回false
     * @Author:yhy
     * @Date: 2019/4/19 16:38
     */
    boolean available(@NotNull String token);

}
