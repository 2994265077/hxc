/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityTreeUtil
 * Author:   YHY
 * Date:     2019/5/31 16:52
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.util;

import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RegionNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.StreetNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/31
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class CommunityTreeUtil {

    private static Properties streetLayIdProperties;
    private static Properties streetCoordinatesProperties;

   static {
        streetLayIdProperties = new Properties();
        streetCoordinatesProperties = new Properties();
        try {
            streetLayIdProperties.load(new ClassPathResource("streetLayId.properties").getInputStream());
            streetCoordinatesProperties.load(new ClassPathResource("streetCoordinates.properties").getInputStream());
        } catch (IOException e) {
            log.error("", e);
        }

    }

    @Getter
    @AllArgsConstructor
    public enum StreetLayId {
        YUAN_LING("园岭", "440304002", "layer_arcgis_basemap_yuanling"),
        SHA_TOU("沙头", "440304004", "layer_arcgis_basemap_shatou"),
        MEI_LIN("梅林", "440304006", "layer_arcgis_basemap_meilin"),
        NAN_YUAN("南园", "440304001", "layer_arcgis_basemap_nanyuan"),
        XIANG_MI_HU("香蜜湖", "440304005", "layer_arcgis_basemap_xiangmihu"),
        HUA_FU("华富", "440304008", "layer_arcgis_basemap_huafu"),
        HUA_QIANG_BEI("华强北", "440304009", "layer_arcgis_basemap_huaqiangbei"),
        FU_TIAN("福田", "440304003", "layer_arcgis_basemap_futian"),
        LIAN_HUA("莲花", "440304007", "layer_arcgis_basemap_lianhua"),
        FU_BAO("福保", "440304010", "layer_arcgis_basemap_fubao"),
        FU_TAIN_QU("福田区", "440304", "layer_arcgis_basemap_futainqu");
        private String label;
        private String code;
        private String id;

        public static String getFutianId() {
            return FU_TAIN_QU.id;
        }

        public static String getId(String code) {
            StreetLayId streetLayId = cache.get(code);
            return Objects.nonNull(streetLayId) ? streetLayId.getId() : "";
        }

        public static Map<String, StreetLayId> cache = Arrays.stream(values()).collect(Collectors.toMap(o -> o.getCode(), o -> o));

    }

    public static List<RegionNode> communityInfoTree(List<CommunityInfo> communityInfos) {
        return communityInfoTree(communityInfos, false);
    }

    public static List<RegionNode> communityInfoTree(List<CommunityInfo> communityInfos, boolean fillLayId) {
        Map<String, List<CommunityInfo>> groupByRegion = communityInfos.stream()
                .collect(Collectors.groupingBy(CommunityInfo::getRegionCode));
        Map<String, List<CommunityInfo>> groupByStreet = communityInfos.stream()
                .collect(Collectors.groupingBy(CommunityInfo::getStreetCode));
        List<RegionNode> regionNodes = communityInfos.stream()
                .collect(Collectors.toMap(CommunityInfo::getRegionCode, CommunityInfo::getRegionName, (o1, o2) -> o1))
                .entrySet()
                .stream()
                .map(entry -> {
                    RegionNode regionNode = RegionNode.builder()
                            .regionCode(entry.getKey())
                            .regionName(entry.getValue())
                            .objectId(entry.getKey())
                            .build();
                    // 该区下的所有社区
                    if (fillLayId) {
                        regionNode.setLayId(streetLayIdProperties.getProperty(regionNode.getRegionCode()));
                    }
                    List<CommunityInfo> communityInfosFromRegion = groupByRegion.get(entry.getKey()).stream().distinct().collect(Collectors.toList());
                    List<StreetNode> streetNodes = communityInfosFromRegion
                            .stream()
                            .map(communityInfo -> {
                                StreetNode streetNode = StreetNode.builder()
                                        .streetCode(communityInfo.getStreetCode())
                                        .streetName(communityInfo.getStreetName())
                                        .objectId(communityInfo.getStreetCode())
                                        .build();
                                if (fillLayId) {
                                    streetNode.setLayId(streetLayIdProperties.getProperty(streetNode.getStreetCode()));
                                    String property = streetCoordinatesProperties.getProperty((streetNode.getStreetCode()),"");
                                    String[] split = property.split(",");
                                    streetNode.setCoordinates(Arrays.asList(split));
                                }
                                List<CommunityNode> communityNodes = groupByStreet.get(communityInfo.getStreetCode())
                                        .stream()
                                        .distinct()
                                        .map(communityInfoFromStreet ->
                                             CommunityNode.builder()
                                                    .objectId(communityInfoFromStreet.getObjectId())
                                                    .communityCode(communityInfoFromStreet.getCommunityCode())
                                                    .communityName(communityInfoFromStreet.getCommunityName())
                                                    .build()
                                        ).collect(Collectors.toList());
                                streetNode.setCommunityNodes(communityNodes);
                                return streetNode;
                            })
                            .distinct()
                            .collect(Collectors.toList());
                    regionNode.setStreetNodes(streetNodes);
                    return regionNode;
                })
                .collect(Collectors.toList());
        return regionNodes;
    }
}