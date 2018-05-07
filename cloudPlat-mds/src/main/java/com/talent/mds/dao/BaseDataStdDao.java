package com.talent.mds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.mds.dto.BaseDataStdDto;
import com.talent.mds.entity.BaseDataStd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：标准数据表类（Dao层）
 */
@Mapper
public interface BaseDataStdDao extends BaseDao<BaseDataStd> {

	BaseDataStdDto selectDtoByPrimaryKey(String datastdId);

	List<BaseDataStdDto> pageListDto(PageObject pageObject);

}