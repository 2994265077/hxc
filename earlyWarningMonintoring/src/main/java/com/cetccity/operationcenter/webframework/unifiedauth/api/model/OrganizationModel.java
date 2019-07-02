package com.cetccity.operationcenter.webframework.unifiedauth.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.controller
 * @Project: futian1
 * @Description: //组织架构
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 15:01
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 15:01
 * @Update_Description: huangzezhou 补充
 **/

/**
 * 1.权限在角色下面，2.角色在组织结构下面。
 * 组织机构是层级关系，那么角色也是层级关系。 新增的额外的角色没有层级关系。
 * 那其实是这样：
 * 首先组织架构能绑定权限。组织架构一定是固定的，为什么？因为他是数据维度的切分，而数据没有对应的字段做支撑。
 * 其次角色能绑定权限。
 *
 * 补充讨论：
 * 如果组织架构是功能的区分，那么也可以。但是还是和角色区分开来。
 * 如果非要把角色绑定到组织架构下面，那就是这样：
 *
 * 先创建组织架构，然后将权限绑定给组织架构。
 * 然后创建的子组织架构，就只能限定在父组织架构的权限范围当中去选择。
 * 接着创建的角色，只能在其挂靠的组织架构的权限范围内去选择和绑定。
 *
 * 困难点在于，指定权限的时候，就需要制定一系列最小组织架构的权限。否则不能满足上述条件。那么组织架构就被限制了.
 * 为什么这么说，因为，组织架构的父节点需要包含所有子节点的权限。如果每一个都一样就没有意义了。
 * 那么还有另外一种说法：双重权限校验。
 *
 * 不是需要固定组织架构，而是设计出一套符合组织架构的权限。
 * 设计原则：可能的最小层级进行设计，因为权限一旦固定，则无法动态修改，而这个就限制了组织架构的划分。那其实权限的设计，就是暗示支持组织架构的可能性，
 * 也就是说组织架构不可能是变化的。不如固定。
 *
 * 综上：
 * 1. 组织架构需要定义其含义，也就是节点的含义。才能做到控制。而且需要时包含关系的含义。
 * 2. 权限+组织架构来判定。
 * 那就没有必要有组织机构了。
 */
@Data
public class OrganizationModel {
    String name;    //组织名
    String id;      //组织编号
    String level;   //级别：根节点为"",其他节点为"0-0-0"每一层编号用'-'隔开
}
