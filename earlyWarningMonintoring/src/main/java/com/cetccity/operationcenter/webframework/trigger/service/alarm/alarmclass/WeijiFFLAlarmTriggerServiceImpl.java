package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.dao.YjjcQwjjSdmInfoVMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.StrDateRange;
import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass
 *
 *  卫计预警：
 *  >  卫计数据包含刀、枪字眼归为卫计刀枪类预警（knife）; 卫计数据包含发热字眼归类为卫计发热类预警（fever）;
 *  >  卫计数据包含腹泻字眼归为卫计腹泻类预警（diarrhea）; 卫计数据包含流感字眼归类为流感类预警（influenza）;
 *  >  刀枪类卫计数据每一条都生成新的报警数据; 发热类每日数量超过56，每日生成一条唯一预警;
 *  >  腹泻类每日数据量超过28，，每日生成一条唯一预警; 流感类每日数量超过5，每日生成一条唯一预警;
 *  预警逻辑：
 *  >  查询出指定日期（一般为今日）内所有卫计类数据，分别统计出knife、fever、diarrhea、influenza类卫计数据
 *  >  按条件生成刀枪类预警、发热类预警、腹泻类预警、流感类预警  然后union all  其中刀枪类为行数据预警，包含f_row_id
 *  >  将生成的预警列表发布
 *  >>   预警列表发布流程:
 *  >>   每条新生成的预警对象都需要查询今日是否已有对应的已发布预警  刀枪类根据f_row_id, alarm_type_lv2, time, 其他类根据alarm_type_lv2, time查询上次预警
 *  >>   比对新预警和已发布预警，刀枪类若存在对应已发布预警则不再预警，否则新增预警，其他类若存在对应已发布预警则更新预警信息，否则新增预警
 *
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:06 2019-03-27
 * Updater:     yhy
 * Update_Date：11:06 2019-03-27
 * 更新描述:    heliangming 补充
 **/
@Service
@Transactional
@Slf4j
public class WeijiFFLAlarmTriggerServiceImpl {
    @Autowired
    private Environment environment;

    @Autowired
    YjjcQwjjSdmInfoVMapper yjjcQwjjSdmInfoVMapper;

    @Autowired
    AlarmInformationV1Mapper alarmInformationMapper;

    private static final DateTimeFormatter DEFAUL_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //腹泻、发热、流感预警
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void triggerAlarm()throws Exception{
        doAlarm(LocalDate.now());
        cancleAlarm();
    }


    public String getOriginTableName() {
        return "YJJC_QWJJ_SDM_INFO_V";
    }


    /**
     * 功能描述: <br>
     * 〈根据卫计数据按日期发布预警〉
     *
     * @param countTime
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/4/26 10:32
     */
    public boolean doAlarm(LocalDate countTime) {
        // 查询今日数据
        String startOfday = countTime.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endOfDay = countTime.plusDays(1).atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<YjjcQwjjSdmInfoV> sourceList = querySource(startOfday, endOfDay);
        if (CollectionUtils.isEmpty(sourceList)) {
            return false;
        }
        // 最终需要发布的预警记录
        List<AlarmInformation> unionAlarmInformations = new LinkedList<>();
        // 卫计刀枪伤数据
        List<YjjcQwjjSdmInfoV> knifeSources = filterSource(sourceList, "刀", "枪");
        // 卫计发热数据
        List<YjjcQwjjSdmInfoV> feverSources = filterSource(sourceList, "发热");
        // 卫计腹泻数据
        List<YjjcQwjjSdmInfoV> diarrheaSources = filterSource(sourceList, "腹泻");
        // 卫计流感数据
        List<YjjcQwjjSdmInfoV> influenzaSources = filterSource(sourceList, "流感");

        if (knifeSources.size() > 0) {
            List<AlarmInformation> alarmInformations = generateKinfeAlarm(knifeSources);
            unionAlarmInformations.addAll(alarmInformations);
        }

        if (feverSources.size() >= 56) {
            AlarmInformation feverAlarm = generateFeverAlarm(feverSources);
            unionAlarmInformations.add(feverAlarm);
        }

        if (diarrheaSources.size() >= 28) {
            AlarmInformation diarrheaAlarm = generateDiarrheaAlarm(diarrheaSources);
            unionAlarmInformations.add(diarrheaAlarm);
        }

        if (influenzaSources.size() >= 5) {
            AlarmInformation influenzaAlarm = generateInfluenzaAlarm(influenzaSources);
            unionAlarmInformations.add(influenzaAlarm);
        }

        return releaseAlarm(unionAlarmInformations);
    }

    /**
     * 功能描述: <br>
     * 〈根据卫计局记录生成流感类报警〉
     *
     * @param influenzaSources 卫计局包含流感的记录
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:dongxin
     * @Date: 2019/4/25 16:57
     */
    public AlarmInformation generateInfluenzaAlarm(List<YjjcQwjjSdmInfoV> influenzaSources) {
        Optional<Date> max = influenzaSources
                .stream()
                .map(YjjcQwjjSdmInfoV::getDIAG_TIME)
                .filter(Objects::nonNull)
                .max(Date::compareTo);
        Instant instant = max.get().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        String mutiAddress = mutiAddress(influenzaSources);
        String mutiDetail = generateMutiDetail(localDateTime, mutiAddress, influenzaSources.size(), "流感");
        AlarmInformation alarmInformation = generateDefaultAlarm();
        alarmInformation.setRELEASE_TIME(max.get());
        alarmInformation.setCONTENTS("流感预警");
        alarmInformation.setALARM_LEVEL("特殊病情");
        alarmInformation.setALARM_TYPE_LV1("007");
        alarmInformation.setALARM_TYPE_LV2("007004");
        alarmInformation.setCHANNEL("卫计局");
        alarmInformation.setALARM_OBJECT(mutiDetail+"");
        return alarmInformation;
    }


    /**
     * 功能描述: <br>
     * 〈根据卫计局记录生成腹泻类报警〉
     *
     * @param diarrheaSources 卫计局包含腹泻的记录
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:dongxin
     * @Date: 2019/4/25 16:57
     */
    public AlarmInformation generateDiarrheaAlarm(List<YjjcQwjjSdmInfoV> diarrheaSources) {
        Optional<Date> max = diarrheaSources
                .stream()
                .map(YjjcQwjjSdmInfoV::getDIAG_TIME)
                .filter(Objects::nonNull)
                .max(Date::compareTo);
        Instant instant = max.get().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        String mutiAddress = mutiAddress(diarrheaSources);
        String mutiDetail = generateMutiDetail(localDateTime, mutiAddress, diarrheaSources.size(), "腹泻");
        AlarmInformation alarmInformation = generateDefaultAlarm();
        alarmInformation.setRELEASE_TIME(max.get());
        alarmInformation.setCONTENTS("腹泻预警");
        alarmInformation.setALARM_LEVEL("特殊病情");
        alarmInformation.setALARM_TYPE_LV1("007");
        alarmInformation.setALARM_TYPE_LV2("007002");
        alarmInformation.setCHANNEL("卫计局");
        alarmInformation.setALARM_OBJECT(mutiDetail+"");
        return alarmInformation;
    }

    /**
     * 功能描述: <br>
     * 〈根据卫计局记录生成发热类报警〉
     *
     * @param feverSources 卫计局包含发热的记录
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:dongxin
     * @Date: 2019/4/25 16:56
     */
    public AlarmInformation generateFeverAlarm(List<YjjcQwjjSdmInfoV> feverSources) {
        Optional<Date> max = feverSources
                .stream()
                .map(YjjcQwjjSdmInfoV::getDIAG_TIME)
                .filter(Objects::nonNull)
                .max(Date::compareTo);
        Instant instant = max.get().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);

        String mutiAddress = mutiAddress(feverSources);
        String mutiDetail = generateMutiDetail(localDateTime, mutiAddress, feverSources.size(), "发热");
        AlarmInformation alarmInformation = generateDefaultAlarm();
        alarmInformation.setRELEASE_TIME(max.get());
        alarmInformation.setCONTENTS("发热预警");
        alarmInformation.setALARM_LEVEL("特殊病情");
        alarmInformation.setALARM_TYPE_LV1("007");
        alarmInformation.setALARM_TYPE_LV2("007001");
        alarmInformation.setCHANNEL("卫计局");
        alarmInformation.setALARM_OBJECT(mutiDetail+"");
        return alarmInformation;
    }

    /**
     * 功能描述: <br>
     * 〈根据卫计局记录生成刀枪伤类报警〉
     *
     * @param knifeSources 卫计局包含刀枪伤的记录
     * @return:java.util.List<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:yhy
     * @Date: 2019/4/25 16:51
     */
    public List<AlarmInformation> generateKinfeAlarm(List<YjjcQwjjSdmInfoV> knifeSources) {
        return knifeSources.stream()
                .map(source -> {
                    AlarmInformation alarmInformation = generateDefaultAlarm();
                    try {
                        alarmInformation.setRELEASE_TIME(source.getDIAG_TIME());
                    } catch (Exception e) {
                        alarmInformation.setRELEASE_TIME(new Date());
                    }
                    alarmInformation.setCONTENTS("刀、枪伤预警");
                    alarmInformation.setALARM_LEVEL("特殊病情");
                    alarmInformation.setALARM_TYPE_LV1("007");
                    alarmInformation.setALARM_TYPE_LV2("007003");
                    alarmInformation.setCHANNEL("卫计局");
                    alarmInformation.setALARM_OBJECT(source.getORG_NAME()+"");    //主体名
                    alarmInformation.setF_OBJECT_ROW_ID(source.getOBJECT_ID());     //主体主键,事件没有主体，默认为-1
                    alarmInformation.setF_ROW_ID(source.getOBJECT_ID());     //主体主键,事件没有主体，默认为-1
                    alarmInformation.setJD84(source.getJD84());
                    alarmInformation.setWD84(source.getWD84());
                    alarmInformation.setADDRESS(source.getADDRESS());
                    alarmInformation.setREGION_CODE(source.getREGION_CODE());
                    alarmInformation.setSTREET_CODE(source.getSTREET_CODE());
                    alarmInformation.setSTREET_NAME(source.getSTREET_NAME());
                    alarmInformation.setCOMMUNITY_CODE(source.getCOMMUNITY_CODE());
                    alarmInformation.setCOMMUNITY_NAME(source.getCOMMUNITY_NAME());
                    return alarmInformation;
                })
                .collect(Collectors.toList());
    }



    /**
     * 功能描述: <br>
     * 〈生成默认卫计报警信息〉
     *
     * @param
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:yhy
     * @Date: 2019/4/25 16:48
     */
    public AlarmInformation generateDefaultAlarm() {
        AlarmInformation alarm = new AlarmInformation();
        //一般不变的指标
        alarm.setF_ROW_ID(-1d);       //数据外键关联object_id,一般是发生的事件
        alarm.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));           //事件ID
        alarm.setCANCEL_TIME(null);   //取消时间，默认为空
        alarm.setRELEASE_PERSON("预警监测平台");           //发布人
        alarm.setDISPOSAL_STATE(0);      //处置状态，默认0，未处置
        alarm.setSEND_STATE(0);      //发送状态，默认0，未发送
        alarm.setYJJC_CREATE_TIME(null);  //该条数据创建时间
        alarm.setYJJC_UPDATE_TIME(null);  //该条数据更新时间
        alarm.setALARM_STATE(1);
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        alarm.setALARM_OBJECT("无");    //主体名
        alarm.setF_OBJECT_ROW_ID(-1D);     //主体主键,事件没有主体，默认为-1
        alarm.setJD84("");
        alarm.setWD84("");
        alarm.setADDRESS("");
        alarm.setREGION_CODE("");
        alarm.setSTREET_CODE("");
        alarm.setSTREET_NAME("");
        alarm.setCOMMUNITY_CODE("");
        alarm.setCOMMUNITY_NAME("");
        alarm.setLDDM(null);
        return alarm;
    }

    /**
     * 功能描述: <br>
     * 〈聚合生成详情〉
     *
     * @param countDate 统计时间
     * @param address 事件发生地址
     * @param eventSize
     * @param type
     * @return:java.lang.String
     * @Author:yhy
     * @Date: 2019/4/25 14:17
     */
    private String generateMutiDetail(LocalDateTime countDate, String address, int eventSize, String type) {
        return MessageFormat.format("今日截至：{0}, 在{1}等地共计发生{2}起{3}病例", countDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")), address, eventSize, type);
    }

    /**
     * 功能描述: <br>
     * 〈从卫计信息数据中筛选出diag_name_inhos包含特定关键字的数据〉
     *
     * @param sourceList 卫计数据源信息
     * @param searchStrings 用于匹配的特定关键字
     * @return:java.util.List<com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV>
     * @Author:yhy
     * @Date: 2019/4/25 11:43
     */
    private List<YjjcQwjjSdmInfoV> filterSource(List<YjjcQwjjSdmInfoV> sourceList, String... searchStrings) {
        return sourceList
                .stream()
                .filter(source ->
                        Objects.nonNull(source.getDIAG_NAME_INHOS())
                                && StringUtils.containsAny(source.getDIAG_NAME_INHOS(), searchStrings)
                )
                .collect(Collectors.toList());
    }

    /**
     * 功能描述: <br>
     * 〈聚合医院地址〉
     *
     * @param sources
     * @return:java.lang.String
     * @Author:yhy
     * @Date: 2019/4/25 11:14
     */
    private String mutiAddress(List<YjjcQwjjSdmInfoV> sources) {
        // concat the string  YjjcQwjjSdmInfoV's ORG_NAME with '、'
        return sources
                .stream()
                .map(YjjcQwjjSdmInfoV::getORG_NAME)
                .distinct()
                .limit(3)
                .collect(Collectors.joining("、"));
    }

    public List<YjjcQwjjSdmInfoV> querySource(@NotNull String beginTime, @NotNull String endTime) {
        Assert.notNull(beginTime, "查询数据时开始时间不能为空");
        Assert.notNull(endTime, "查询数据时结束时间不能为空");
        return yjjcQwjjSdmInfoVMapper.querySourceDataByDateRange(beginTime, endTime);
    }

    /**
     * 功能描述: <br>
     * 〈根据时间查询最近的一次预警记录〉
     *
     * @param fRowId 预警关键源表外键
     * @param alarmTypeLv2 预警二级类型
     * @param beginTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间 yyyy-MM-dd HH:mm:ss
     * @return:java.util.Optional<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:yhy
     * @Date: 2019/4/25 16:13
     */
    public Optional<AlarmInformation> queryLastAlarm(Double fRowId, @NotNull String alarmTypeLv2, @NotNull String beginTime, @NotNull String endTime) {
        Assert.notNull(alarmTypeLv2, "查询上次数据时事件二级类型不能为空");
        Assert.notNull(beginTime, "查询数据时开始时间不能为空");
        Assert.notNull(endTime, "查询数据时结束时间不能为空");

        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        alarmInformation.setF_ROW_ID(fRowId);
        alarmInformation.setALARM_TYPE_LV2(alarmTypeLv2);

        StrDateRange strDateRange = new StrDateRange();
        strDateRange.setBegin(beginTime);
        strDateRange.setEnd(endTime);

        PageHelper.startPage(1, 1, false);
        List<AlarmInformation> alarmInformations = alarmInformationMapper.selectByAlarmInformationAndDateRange(alarmInformation, strDateRange);
        if (CollectionUtils.isEmpty(alarmInformations)) {
            return Optional.empty();
        }
        return Optional.ofNullable(alarmInformations.get(0));
    }

    /**
     * 功能描述: <br>
     * 〈查询今天的最近一次预警记录〉
     *
     * @param fRowId 预警关键源表外键
     * @param alarmTypeLv2 预警二级类型
     * @return:java.util.Optional<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:yhy
     * @Date: 2019/4/25 16:12
     */
    public Optional<AlarmInformation> queryTodayLastAlarm(Double fRowId, @NotNull String alarmTypeLv2) {
        String startOfToday = LocalDate.now().atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return queryLastAlarm(fRowId, alarmTypeLv2, startOfToday, now);
    }

    /**
     * 功能描述: <br>
     * 〈按日期发布预警〉
     *  根据卫计数据表统计出数据转化的预警对象， 和指定日期内的预警对象比较，决定是否需要预警，如需预警，是新增预警还是更新预警
     * @param alarmInformationList
     * @param localDate
     * @return:boolean
     * @Author:dongxin
     * @Date: 2019/4/26 10:07
     */
    public boolean releaseAlarm(List<AlarmInformation> alarmInformationList, LocalDate localDate) {
        Map<Boolean, List<AlarmInformation>> updateOrInsertListMap = alarmInformationList
                .parallelStream()
                .map(alarmInformation -> {
                    // 刀枪报警，查询上次预警是否存在，不存在就返回，否则返回空（返回空表示不需新增预警也不需更新）
                    if (Objects.nonNull(alarmInformation.getF_ROW_ID()) && alarmInformation.getF_ROW_ID() > 0) {
                        Optional<AlarmInformation> lastOptional =
                                queryLastAlarm(alarmInformation.getF_ROW_ID(),
                                        alarmInformation.getALARM_TYPE_LV2(),
                                        localDate.atStartOfDay().format(DEFAUL_TIME_FORMATTER),
                                        localDate.plusDays(1).atStartOfDay().format(DEFAUL_TIME_FORMATTER));
                        // 如果已发布预警，不需再发布，返回空预警对象
                        if (lastOptional.isPresent()) {
                            return Optional.empty();
                        }
                    } else {
                        // 发热、腹泻、流感类报警
                        // 如果上次预警已存在，则更新上次预警  （详情、地址和更新时间）  上次预警不存在返回新预警
                        Optional<AlarmInformation> lastOptional =
                                queryLastAlarm(null,
                                        alarmInformation.getALARM_TYPE_LV2(),
                                        localDate.atStartOfDay().format(DEFAUL_TIME_FORMATTER),
                                        localDate.plusDays(1).atStartOfDay().format(DEFAUL_TIME_FORMATTER));
                        if (lastOptional.isPresent()) {
                            AlarmInformation oldAlarmInformation = lastOptional.get();
                            AlarmInformation newAlarm = new AlarmInformation();
                            newAlarm.setOBJECT_ID(oldAlarmInformation.getOBJECT_ID());
                            newAlarm.setALARM_OBJECT(alarmInformation.getALARM_OBJECT());
                            return Optional.ofNullable(newAlarm);
                        }
                    }
                    return Optional.ofNullable(alarmInformation);
                })
                .filter(Optional::isPresent)
                .map(optional -> (AlarmInformation)optional.get())
                // 按是否有id分类决定是更新预警还是新发布预警
                .collect(Collectors.partitioningBy(alarmInformation -> Objects.isNull(alarmInformation.getOBJECT_ID())));

        List<AlarmInformation> insertList = updateOrInsertListMap.get(Boolean.TRUE);
        List<AlarmInformation> updateList = updateOrInsertListMap.get(Boolean.FALSE);
        boolean insertRes = insertAllAlarm(insertList);
        boolean updateRes = updateAllAlarm(updateList);

        return insertRes && updateRes;
    }

    /**
     * 功能描述: <br>
     * 〈根据卫计数据生成的预警记录 发布今日预警〉
     *
     * @param alarmInformationList
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/4/26 10:01
     */
    public boolean releaseAlarm(List<AlarmInformation> alarmInformationList) {
        return releaseAlarm(alarmInformationList, LocalDate.now());
    }

    /**
     * 功能描述: <br>
     * 〈新发布预警〉
     *
     * @param alarmInformations
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/4/26 10:02
     */
    public boolean insertAllAlarm(List<AlarmInformation> alarmInformations) {
        boolean success = true;
        for (AlarmInformation alarmInformation : alarmInformations) {
            int insert = alarmInformationMapper.insert(alarmInformation);
            success &= insert==1;
        }
        return success;
    }

    /**
     * 功能描述: <br>
     * 〈已发布预警，更新数据〉
     *
     * @param alarmInformations
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/4/26 10:03
     */
    public boolean updateAllAlarm(List<AlarmInformation> alarmInformations) {
        if (CollectionUtils.isNotEmpty(alarmInformations)) {
            int i = alarmInformationMapper.updateBatchSelected(alarmInformations);
            return i == alarmInformations.size();
        }
        return false;
    }

    /**
     * 功能描述: <br>
     * 〈7天前卫计类预警全部自动处置完成〉
     *
     * @param
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/4/25 17:14
     */
    public boolean cancleAlarm() {
        AlarmInformation condition = new AlarmInformation();
        condition.setALARM_TYPE_LV1("009");
        condition.setDISPOSAL_STATE(0);
        StrDateRange strDateRange = new StrDateRange();
        strDateRange.setBegin(LocalDateTime.now().minusYears(3).format(DEFAUL_TIME_FORMATTER));
        strDateRange.setEnd(LocalDate.now().atStartOfDay().format(DEFAUL_TIME_FORMATTER));
        List<AlarmInformation> alarmInformations = alarmInformationMapper.selectByAlarmInformationAndDateRange(condition, strDateRange);
        List<AlarmInformation> cacleAlarms = alarmInformations.stream()
                .map(alarmInformation -> {
                    AlarmInformation updateDetail = new AlarmInformation();
                    updateDetail.setOBJECT_ID(alarmInformation.getOBJECT_ID());
                    updateDetail.setDISPOSAL_STATE(2);
                    updateDetail.setALARM_STATE(0);
                    return updateDetail;
                })
                .collect(Collectors.toList());
        return updateAllAlarm(cacleAlarms);
    }

}
