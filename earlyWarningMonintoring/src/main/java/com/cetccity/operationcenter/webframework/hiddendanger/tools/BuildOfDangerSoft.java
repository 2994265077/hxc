package com.cetccity.operationcenter.webframework.hiddendanger.tools;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.BuildScoreMapper;
import com.cetccity.operationcenter.webframework.web.model.build.BuildOfDangerModel;
import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class BuildOfDangerSoft {

    @Autowired
    private BuildScoreMapper buildScoreMapper;

    public List<BuildOfDangerModel> getBuildScoreRank(){
        return buildScoreMapper.topNDangerScore(10, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    //绿-1、蓝-2、黄-3、橙-4、红-5
    public List<LoadMapBuildGrade> getBuildDangerLoadMap(){
        return buildScoreMapper.loadMap(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
