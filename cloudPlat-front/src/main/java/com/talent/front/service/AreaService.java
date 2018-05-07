package com.talent.front.service;

import java.util.List;
import java.util.Map;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.AreaDto;
import com.talent.front.entity.Area;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：省市区服务接口类
 */
public interface AreaService extends BaseService<Area> {

	AreaDto updateObjectByPrimaryKeySelective(Area area);

	PageResult<AreaDto> pageListDto(PageObject pageObject);

	List<AreaDto> pageListCache(PageObject pageObject);

	List<AreaDto> selectDtoByParentId(String id);

	List<AreaDto> selectDtoByParentIdLevel(Map<String, Object> map);

	AreaDto selectAreaDto(Map<String, Object> paramMap);

}