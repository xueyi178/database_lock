package com.lock.mapper;

import com.lock.entity.Browse;

public interface BrowseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Browse record);

    int insertSelective(Browse record);

    Browse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browse record);

    int updateByPrimaryKey(Browse record);
}