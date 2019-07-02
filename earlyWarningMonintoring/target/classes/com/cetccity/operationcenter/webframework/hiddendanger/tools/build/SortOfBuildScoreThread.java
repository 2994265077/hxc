package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;

public class SortOfBuildScoreThread implements Runnable{

    private BuildScoreTool buildScoreTool;
    private String buildid;


    public SortOfBuildScoreThread(String buildid,BuildScoreTool buildScoreTool) {
        this.buildid = buildid;
        this.buildScoreTool = buildScoreTool;
    }

    public void run() {
        BuildScoreModel buildScoreModel = buildScoreTool.calculationBuildScore(buildid);
        //BuildScoreSortConfig.map_buildScore.put(buildid,Integer.valueOf(buildScoreModel.getDangerScore()));
        return;
    }

}
