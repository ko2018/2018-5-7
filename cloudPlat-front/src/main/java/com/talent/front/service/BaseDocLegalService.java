package com.talent.front.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDocLegalDto;
import com.talent.front.entity.BaseDocLegal;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档空值合法值记录表服务接口类
 */
public interface BaseDocLegalService extends BaseService<BaseDocLegal> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseDocLegalDto selectDtoByPrimaryKey(String docvalueId);

	/**
	 * 更新model返回DTO
	 */
	BaseDocLegalDto updateDtoByPrimaryKeySelective(BaseDocLegal baseDocLegal);

	PageResult<BaseDocLegalDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseDocLegalDto> pageListDtoJoin(PageObject pageObject);

	List<BaseDocLegalDto> pageListCache(PageObject pageObject);

	void addBaseDocLegal(BaseDocLegal baseDocLegal);

	BaseDocLegalDto getDtoByDictIdAndDocId(int dictId, String docId);

	void updateByCountAll(int countAll, String docId);

}