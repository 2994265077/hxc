package com.cetc.cloud.datasynch.provider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：数据脱敏工具
 * Created by luolinjie on 2018/8/29.
 */
public class DesensitizationUtil {

    private static Logger logger = LoggerFactory.getLogger(DesensitizationUtil.class);

    public static void main(String[] args) {
        String name = personNameTransformer("");
        System.out.println(name);
    }

    public static List<String> splitList = new ArrayList<>();

    static {
        splitList.add("省");
        splitList.add("市");
        splitList.add("县");
        splitList.add("州");
        splitList.add("区");
        splitList.add("街道");
        splitList.add("大道");
        splitList.add("街");
        splitList.add("社区");
        splitList.add("派出所");
        splitList.add("路");
        splitList.add("口岸");
        splitList.add("中学");
        splitList.add("医院");
        splitList.add("广场");
        splitList.add("村");
        splitList.add("花园");
        splitList.add("苑");
        splitList.add("大厦");
        splitList.add("小学");
        splitList.add("酒店");
        splitList.add("隧道");
        splitList.add("旅馆");
        splitList.add("河");
        splitList.add("水库");
        splitList.add("山");
        splitList.add("园");
        splitList.add("场");
        splitList.add("立交");
        splitList.add("有限");
        splitList.add("基地");
        splitList.add("站");
        splitList.add("学校");
        splitList.add("公园");
    }

    public static List<String> replaceList = new ArrayList<>();

    static {
        replaceList.add("深圳");
        replaceList.add("深");
        replaceList.add("驻港部队");
        replaceList.add("广州");
        replaceList.add("福田");
        replaceList.add("华强北");
        replaceList.add("福保");
        replaceList.add("南园");
        replaceList.add("莲花");
        replaceList.add("沙头");
        replaceList.add("香蜜湖");
        replaceList.add("园岭");
        replaceList.add("华富");
        replaceList.add("益田");
        replaceList.add("竹林");
        replaceList.add("竹子林");
        replaceList.add("梅林");
        replaceList.add("沙嘴");
        replaceList.add("石厦");
        replaceList.add("罗湖");
        replaceList.add("新洲");
        replaceList.add("皇岗");
        replaceList.add("沙尾");
        replaceList.add("彩虹");
        replaceList.add("新田");
        replaceList.add("明月");
        replaceList.add("梅河");
        replaceList.add("福南");
        replaceList.add("紫荆");
        replaceList.add("莲花北");
        replaceList.add("通新岭");
        replaceList.add("金地");
        replaceList.add("香安");
        replaceList.add("上梅");
        replaceList.add("东海");
        replaceList.add("孖岭");
        replaceList.add("新阁");
        replaceList.add("沙埔头");
        replaceList.add("田面");
        replaceList.add("福华");
        replaceList.add("福安");
        replaceList.add("福山");
        replaceList.add("福新");
        replaceList.add("香岭");
        replaceList.add("香蜜");
        replaceList.add("黄木岗");
        replaceList.add("华林");
        replaceList.add("华红");
        replaceList.add("圩镇");
        replaceList.add("巴登");
        replaceList.add("渔农");
        replaceList.add("福中");
        replaceList.add("莲花二村");
        replaceList.add("金城");
        replaceList.add("鹏盛");
        replaceList.add("龙尾");
        replaceList.add("上沙");
        replaceList.add("农园");
        replaceList.add("华航");
        replaceList.add("岗厦");
        replaceList.add("梅岭");
        replaceList.add("梅林一村");
        replaceList.add("海滨");
        replaceList.add("红荔");
        replaceList.add("莲花一村");
        replaceList.add("华山");
        replaceList.add("彩田");
        replaceList.add("景华");
        replaceList.add("梅富");
        replaceList.add("沙咀");
        replaceList.add("翰岭");
        replaceList.add("新兴");
        replaceList.add("下梅");
        replaceList.add("南天");
        replaceList.add("园西");
        replaceList.add("新华");
        replaceList.add("新沙");
        replaceList.add("梅亭");
        replaceList.add("滨河");
        replaceList.add("狮岭");
        replaceList.add("玉田");
        replaceList.add("翠湾");
        replaceList.add("荔村");
        replaceList.add("赤尾");
        replaceList.add("长城");
        replaceList.add("金碧");
        replaceList.add("下沙");
        replaceList.add("南华");
        replaceList.add("园东");
        replaceList.add("天安");
        replaceList.add("梅京");
        replaceList.add("梅岗");
        replaceList.add("梅都");
        replaceList.add("水围");
        replaceList.add("滨江");
        replaceList.add("福强");
        replaceList.add("福民");
        replaceList.add("香梅");
        replaceList.add("上林");
        replaceList.add("东园");
        replaceList.add("口岸");
        replaceList.add("康欣");
        replaceList.add("新港");
        replaceList.add("景田");
        replaceList.add("梅丰");
        replaceList.add("竹园");
        replaceList.add("莲花三村");
        replaceList.add("锦龙");
        replaceList.add("梅观");

    }


    /**
     * 人名 转换器
     *
     * @param personName
     * @return
     */
    public static String personNameTransformer(String personName) {
        String ret = "";
        if (personName == null) {
            logger.info("personName: null value");
            return null;
        } else if (personName.contains(",")) {
            String[] split = personName.split(",");
            int i = 0;
            for (String p : split) {
                if (i != split.length) {
                    ret += p.substring(0, 1);
                    ret += ",";
                }
                i++;
            }
            return ret;
        } else {
            char[] chars = personName.toCharArray();
            for (int i = 1; i < chars.length; i++) {
                chars[i] = '*';
            }
            return new String(chars);
        }
    }


    /**
     * 企业名称 转换器
     *
     * @param enterpriseName
     * @return
     */
    public static String enterpriseNameTransformer(String enterpriseName) {
        if (enterpriseName == null) {
            logger.info("enterpriseName: null value");
            return null;
        } else {
            if (enterpriseName.length() > 3) {
                return generatePartialStar(enterpriseName, 3, enterpriseName.length() - 2);
            } else {
                return generateFullStar(enterpriseName);
            }
        }
    }

    /**
     * 电话号码 转换器
     *
     * @param telephoneNum
     * @return
     */
    public static String telephoneNumformer(String telephoneNum) {
        String s = null;
        if (telephoneNum == null) {
            logger.info("telephoneNum: null value");
            return null;
        } else if (telephoneNum.length() == 11) {
            s = generatePartialStar(telephoneNum, 3, 7);
        } else if (telephoneNum.length() == 8) {
            s = generatePartialStar(telephoneNum, 4, -1);
        } else {
            s = generatePartialStar(telephoneNum, 4, -1);
        }
        return s;
    }

    /**
     * QQ号码 转换器
     */
    public static String qqNumberTransformer(String qqNumber) {
        if (qqNumber == null) {
            logger.info("qqNumber: null value");
            return null;
        } else {
            return generatePartialStar(qqNumber, 3, -1);
        }
    }

    /**
     * 身份证号 转换器
     */
    public static String idCardnumtransformer(String idCardNum) {
        if (idCardNum == null) {
            logger.info("IDcardNum: null value");
            return null;
        } else {
            if (idCardNum.length() > 15) {
                return generatePartialStar(idCardNum, 6, 14);
            } else if (idCardNum.length() > 10) {
                return generatePartialStar(idCardNum, 4, 10);
            } else {
                return generateFullStar(idCardNum);
            }
        }
    }

    /**
     * 详细地址 转换器
     */
    public static String detailAddressTransformer(String detailAddress) {
        if (detailAddress == null) {
            logger.info("detailAddress:null value");
            return "*";
        } else {
            //split
            for (String splitWord : splitList) {
                if (detailAddress.contains(splitWord)) {
                    int idx = detailAddress.indexOf(splitWord);//获取词的index下标
                    int length = detailAddress.length();
                    //将词之前的2个字符替换为*
                    try {
                        if (idx >= 2&&length>5) {
                            detailAddress = "**" + detailAddress.substring(idx, length - 2) + "**";
                        }
                    } catch (Exception e) {
                        return generateFullStar(detailAddress);
                    }
                }
            }
            //replace
            for (String replaceWord : replaceList) {
                detailAddress = detailAddress.replaceAll(replaceWord, "**");
            }

            return detailAddress;
        }

    }

    /**
     * 标准地址  转换器
     */
    public static String standardAddressTransformer(String standardAddress) {
        if (standardAddress == null) {
            logger.info("standardAddress:null value");
            return null;
        } else {
            return generateFullStar(standardAddress);
        }
    }

    /**
     * 经纬度 转换器
     */
    public static String coordinateValueTransformer(String coordinateValue) {
        if (coordinateValue == null) {
            logger.info("coordinateValue can not be null");
            return null;
        } else {
            return null;
//            return generateFullStar(coordinateValue);
        }
    }


    private static String generateFullStar(String srcString) {
        if (srcString == null) {
            logger.info("srcString:null value");
            return null;
        } else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < srcString.length(); i++) {
                s.append("*");
            }
            return s.toString();
        }
    }

    private static String generatePartialStar(String srcString, int starStartPosition, int starEndPosition) {
        char[] chars = srcString.toCharArray();
        int endPosition = chars.length;
        if (starEndPosition < chars.length && starEndPosition > starStartPosition) {
            endPosition = starEndPosition;
        }

        for (int i = starStartPosition; i < endPosition; i++) {
            chars[i] = '*';
        }
        return new String(chars);
    }


}
