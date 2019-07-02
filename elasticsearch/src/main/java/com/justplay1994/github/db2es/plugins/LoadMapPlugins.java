package com.justplay1994.github.db2es.plugins;

import com.justplay1994.github.db2es.config.LoadMap;
import com.justplay1994.github.db2es.config.Oracle2esConfig;
import com.justplay1994.github.db2es.config.PluginsConfig;
import com.justplay1994.github.db2es.service.db2es.Oracle2es;
import com.justplay1994.github.db2es.util.LoadMyUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.justplay1994.github.db2es.plugins
 * @Project: db2es
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/22 16:17
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/22 16:17
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

/**
 * 落图插件
 * 只导入需要落图的数据。 所以将oracle2es配置中的表，更改为loadmap的表
 */
@Aspect
@Component
public class LoadMapPlugins {

    @Autowired
    Oracle2esConfig oracle2esConfig;

    @Autowired
    LoadMap loadMap;

    @Autowired
    PluginsConfig pluginsConfig;

    public static Map<String ,String> hasTableNameType = new HashMap<String, String>();

    @Pointcut(value = "execution(* com.justplay1994.github.db2es.service.db.operate.impl.OracleOperateImpl.queryAllStructure())")
    public void cut(){

    }

    @Before("cut()")
    public void before(){
        if (pluginsConfig.isLoadMap()) {
            StringBuilder result = new StringBuilder();
            for (Object key : loadMap.keySet()) {
                if(!"31".equals(String.valueOf(key).substring(0,2))){
                    continue;
                }
                if(String.valueOf(key).contains("@")){
                    hasTableNameType.put(String.valueOf(key).split("@")[0].split("\\.")[1], "1");
                }else{
                    hasTableNameType.put(String.valueOf(key).split("\\.")[1], "0");
                }
                String str = String.valueOf(key).split("@")[0];
                String tbName = str.split("\\.")[1];
                if (isStartWithNumber(tbName)) continue;
                result.append(oracle2esConfig.getOwner() + "." + tbName + ",");
            }
            result.delete(result.length() - 1, result.length());
            oracle2esConfig.setJustReadTB(String.valueOf(result));
        }
    }
    private boolean isStartWithNumber(String string){
        return '0' <= string.charAt(0) && string.charAt(0) <= '9';
    }
}
