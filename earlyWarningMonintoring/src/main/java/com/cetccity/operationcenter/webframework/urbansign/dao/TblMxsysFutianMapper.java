package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TimeValueModel;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.TBL_MXSYS_FUTIAN;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TblMxsysFutianMapper {

    long getTBL_MXSYS_FUTIANCount(TBL_MXSYS_FUTIAN tBL_MXSYS_FUTIAN);

    List<TBL_MXSYS_FUTIAN> getTBL_MXSYS_FUTIAN(TBL_MXSYS_FUTIAN tBL_MXSYS_FUTIAN);

    List<NameValueTypeModel<Integer>> countTblMxsysFutianByProvince();

    List<TimeValueModel> countMoneyByMonth(@Param("beginYearMonth") String beginYearMonth);

}
