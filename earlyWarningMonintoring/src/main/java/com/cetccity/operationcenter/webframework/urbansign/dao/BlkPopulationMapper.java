package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.BlkPopulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BlkPopulationMapper {

    Integer getPopulationCount(BlkPopulation bLK_POPULATION);

    List<NameValueTypeModel<Integer>> countPopulation(@Param("maxBirth") LocalDateTime maxBirth);

    List<NameValueTypeModel<Integer>> countPopulationByCommunity(@Param("maxBirth") LocalDateTime maxBirth, @Param("streetCode") @Nonnull String streetCode);

    List<NameValueDataModel<String>> countPopulationForDestiny(@Param("isCitizen") String isCitizen);

    List<NameValueDataModel<String>> countPopulationForDestinyByCommunity(@Param("isCitizen") String isCitizen, @Param("streetCode") @Nonnull String streetCode);

    List<BlkPopulation> getBLK_POPULATION(BlkPopulation bLK_POPULATION);

    List<BlkPopulation> getSFZH(BlkPopulation bLK_POPULATION);

}
