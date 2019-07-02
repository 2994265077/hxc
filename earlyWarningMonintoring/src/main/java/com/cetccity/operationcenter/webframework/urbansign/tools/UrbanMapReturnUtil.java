package com.cetccity.operationcenter.webframework.urbansign.tools;

import com.cetccity.operationcenter.webframework.urbansign.api.model.MapLegalPerson;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UrbanMapReturnUtil {

    @Autowired
    StreetTool streetTool;

    public List<MapLegalPerson> loadMapModelOfStreet(List<COMMUNITY_CODE> Street_list,Map<String,Integer> map_hight_vaule){
        List<MapLegalPerson> mapLegalPerson_list = new ArrayList<MapLegalPerson>();
        List<Map.Entry<String,Integer>> list = new ArrayList(map_hight_vaule.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        int max = Integer.valueOf(list.get(0).getValue());  //最大值
        int min = Integer.valueOf(list.get(list.size()-1).getValue());//最小值
        int num;
        for(COMMUNITY_CODE cOMMUNITY_CODE:Street_list){
            MapLegalPerson mapLegalPerson = new MapLegalPerson();
            num = map_hight_vaule.get(cOMMUNITY_CODE.getSTREET_CODE());
            mapLegalPerson.setStreetName(cOMMUNITY_CODE.getSTREET_NAME());
            mapLegalPerson.setId(cOMMUNITY_CODE.getSTREET_CODE());
            mapLegalPerson.setValue(num);
            mapLegalPerson.setLevel(streetTool.getLegalPersonRGB(max,min,num).getLevel());
            mapLegalPerson.setColor(streetTool.getLegalPersonRGB(max,min,num).getColor());
            mapLegalPerson.setMax(String.valueOf(max));
            mapLegalPerson.setMin(String.valueOf(min));
            mapLegalPerson_list.add(mapLegalPerson);
        }
        return mapLegalPerson_list;
    }

    public List<MapLegalPerson> loadMapModelOfCommunity(List<COMMUNITY_CODE> Street_community_list,Map<String,Integer> map_hight_vaule) {
        List<MapLegalPerson> mapLegalPerson_list = new ArrayList<MapLegalPerson>();
        List<Map.Entry<String,Integer>> list = new ArrayList(map_hight_vaule.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        int max = Integer.valueOf(list.get(0).getValue());  //最大值
        int min = Integer.valueOf(list.get(list.size()-1).getValue());//最小值
        int num;
        for (COMMUNITY_CODE cOMMUNITY_CODE : Street_community_list) {
            MapLegalPerson mapLegalPerson = new MapLegalPerson();
            num = map_hight_vaule.get(cOMMUNITY_CODE.getCOMMUNITY_CODE());
            mapLegalPerson.setStreetName(cOMMUNITY_CODE.getCOMMUNITY_NAME());
            mapLegalPerson.setId(cOMMUNITY_CODE.getCOMMUNITY_CODE());
            mapLegalPerson.setValue(num);
            mapLegalPerson.setLevel(streetTool.getLegalPersonRGB(max,min, num).getLevel());
            mapLegalPerson.setColor(streetTool.getLegalPersonRGB(max,min, num).getColor());
            mapLegalPerson.setMax(String.valueOf(max));
            mapLegalPerson.setMin(String.valueOf(min));
            mapLegalPerson_list.add(mapLegalPerson);
        }
        return mapLegalPerson_list;
    }

    public List tipContent(String[] key, String[] value, LinkedHashMap<String, String> map) {
        List result = new ArrayList();
        for (int i = 0; i < key.length; i++) {
            if (i == 0) {
                result.add(key[i]);
                result.add(map.get(value[1]));
            } else {
                result.add(key[i]);
                result.add(map.get(value[i]));
            }
        }
        return result;
    }
}
