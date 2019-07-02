package com.cetccity.operationcenter.webframework.unifiedauth.support;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.Code;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode;

/**
 * <验证码管理器>
 *
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.manager
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: yhy
 * @Create_Date: 2019/4/22 10:55
 * @Description:
 **/
public interface CodeManager {

    /**
     * 功能描述: <br>
     * 〈生成验证码,及图片〉
     *  自动生成id
     * @param
     * @return:com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode
     * @Author:dongxin
     * @Date: 2019/4/22 10:59
     */
    ImageCode supplier();

    /**
     * 功能描述: <br>
     * 〈生成验证码,及图片〉
     *
     * @param id 验证码id
     * @return:com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode
     * @Author:dongxin
     * @Date: 2019/4/22 10:59
     */
    ImageCode supplier(String id);

    /**
     * 功能描述: <br>
     * 〈生成验证码,及图片〉
     *  自动生成id
     * @param width 图片宽
     * @param height 图片高
     * @return:com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode
     * @Author:dongxin
     * @Date: 2019/4/22 11:08
     */
    ImageCode supplier(int width, int height);


    /**
     * 功能描述: <br>
     * 〈生成验证码,及图片〉
     *
     * @param id 验证码id
     * @param width 图片宽
     * @param height 图片高
     * @return:com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode
     * @Author:dongxin
     * @Date: 2019/4/22 11:08
     */
    ImageCode supplier(String id, int width, int height);

    /**
     * 功能描述: <br>
     * 〈校验验证码〉
     *
     * @param code
     * @return:boolean
     * @Author:dongxin
     * @Date: 2019/4/22 11:00
     */
    boolean validate(Code code);

}
