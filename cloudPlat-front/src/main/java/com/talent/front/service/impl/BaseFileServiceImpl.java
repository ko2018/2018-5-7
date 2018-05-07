package com.talent.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.BaseFileDao;
import com.talent.front.dto.BaseFileDto;
import com.talent.front.entity.BaseFile;
import com.talent.front.service.BaseFileService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息附件表服务实现类
 */
@Service
public class BaseFileServiceImpl extends BaseServiceImpl<BaseFile> implements BaseFileService {
	private static final Logger logger = LoggerFactory.getLogger(BaseFileServiceImpl.class);

	@Autowired
	private BaseFileDao baseFileDao;

	@Override
	public BaseDao<BaseFile> getBaseDao() {
		return this.baseFileDao;
	}

	@CacheSpeObject(value = "baseFile", key = "#fileId")
	@Override
	public BaseFileDto selectDtoByPrimaryKey(String fileId) {
		return this.baseFileDao.selectDtoByPrimaryKey(fileId);
	}

	@CacheSpeObject(value = "baseFile", key = "#baseFile.fileId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseFileDto updateDtoByPrimaryKeySelective(BaseFile baseFile) {
		this.baseFileDao.updateByPrimaryKeySelective(baseFile);
		return this.baseFileDao.selectDtoByPrimaryKey(baseFile.getFileId());
	}

	@Override
	public PageResult<BaseFileDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseFileDao.pageCount(pageObject);
		PageResult<BaseFileDto> pageResult = new PageResult<BaseFileDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseFileDto> baseFileDtoList = ((BaseFileServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(baseFileDtoList);
		}
		return pageResult;
	}

	@Override
	public long getAllCount(PageObject pageObject) {
		return this.baseFileDao.pageCount(pageObject);
	}

	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseFileDto> pageListCache(PageObject pageObject) {
		return this.baseFileDao.pageListDto(pageObject);
	}

    @Override
    public List<BaseFileDto> getBaseFilesByIds(List<String> fileIds)
    {
        return this.baseFileDao.getBaseFilesByIds(fileIds);
    }

}