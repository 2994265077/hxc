package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.RestfulApi;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RestfulApiMapper {

    List<RestfulApi> select(RestfulApi restfulApi);

}
