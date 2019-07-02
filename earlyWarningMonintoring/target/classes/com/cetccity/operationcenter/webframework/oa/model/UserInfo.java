/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: UserInfo
 * Author:   YHY
 * Date:     2019/6/14 15:16
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.oa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/14
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends NameBase {
    private String deptId;
    private String loginName;
    private String email;
    private String officePhone;//办公电话
    private String mobile;//手机
    private String jobTitles;//职务
    private String jobLevel;//职级
    private String pwd;
    private Date updatetime;//最后更新时间
}