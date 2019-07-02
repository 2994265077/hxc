package com.cetccity.operationcenter.webframework.core.frame.model;
/**********************************************************************
 *
 * Copyright (c) 2017 CETC Company
 * 中电科新型智慧城市研究院有限公司版权所有
 *
 * PROPRIETARY RIGHTS of CETC Company are involved in the
 * subject matter of this material. All manufacturing, reproduction, use,
 * and sales rights pertaining to this subject matter are governed by the
 * license agreement. The recipient of this software implicitly accepts
 * the terms of the license.
 * 本软件文档资料是中电科新型智慧城市研究院有限公司的资产，任何人士阅读和
 * 使用本资料必须获得相应的书面授权，承担保密责任和接受相应的法律约束。
 *
 *************************************************************************/

/**
 * 工程包名:   com.cetc.cloud.framework.api.model
 * 项目名称:   cetc-msg-plugin
 * 创建描述:   zhangliang 补充
 * Creator:     zhangliang
 * Create_Date: 2017/11/28
 * Updater:     zhangliang
 * Update_Date：2017/11/28
 * 更新描述:    zhangliang 补充
 **/
public class CetcCloudNeuronPongBody {

    private String applicationName;
    private String hostName;
    private String hostAddress;
    private long count;
    private double meanRate;
    private double _1MinuteRate;
    private double _5MinuteRate;
    private double _15MinuteRate;
    private double _75thPercentile;
    private double _95thPercentile;
    private double _98thPercentile;
    private double _99thPercentile;
    private double _99_99thPercentile;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double get_1MinuteRate() {
        return _1MinuteRate;
    }

    public void set_1MinuteRate(double _1MinuteRate) {
        this._1MinuteRate = _1MinuteRate;
    }

    public double get_5MinuteRate() {
        return _5MinuteRate;
    }

    public void set_5MinuteRate(double _5MinuteRate) {
        this._5MinuteRate = _5MinuteRate;
    }

    public double getMeanRate() {
        return meanRate;
    }

    public void setMeanRate(double meanRate) {
        this.meanRate = meanRate;
    }

    public double get_15MinuteRate() {
        return _15MinuteRate;
    }

    public void set_15MinuteRate(double _15MinuteRate) {
        this._15MinuteRate = _15MinuteRate;
    }

    public double get_75thPercentile() {
        return _75thPercentile;
    }

    public void set_75thPercentile(double _75thPercentile) {
        this._75thPercentile = _75thPercentile;
    }

    public double get_95thPercentile() {
        return _95thPercentile;
    }

    public void set_95thPercentile(double _95thPercentile) {
        this._95thPercentile = _95thPercentile;
    }

    public double get_98thPercentile() {
        return _98thPercentile;
    }

    public void set_98thPercentile(double _98thPercentile) {
        this._98thPercentile = _98thPercentile;
    }

    public double get_99thPercentile() {
        return _99thPercentile;
    }

    public void set_99thPercentile(double _99thPercentile) {
        this._99thPercentile = _99thPercentile;
    }

    public double get_99_99thPercentile() {
        return _99_99thPercentile;
    }

    public void set_99_99thPercentile(double _99_99thPercentile) {
        this._99_99thPercentile = _99_99thPercentile;
    }
}
