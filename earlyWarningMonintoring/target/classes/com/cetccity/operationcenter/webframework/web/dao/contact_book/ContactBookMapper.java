package com.cetccity.operationcenter.webframework.web.dao.contact_book;

import com.cetccity.operationcenter.webframework.web.model.contact_book.ContactBook;
import com.cetccity.operationcenter.webframework.web.model.contact_book.ContactBookExample;

import java.util.List;

public interface ContactBookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContactBook record);

    int insertSelective(ContactBook record);

    List<ContactBook> selectByExample(ContactBookExample example);

    ContactBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContactBook record);

    int updateByPrimaryKey(ContactBook record);
}