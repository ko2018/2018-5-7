package com.talent.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.BaseInstitutionSnlVersionDao;
import com.talent.front.dto.BaseInstitutionSnlDto;
import com.talent.front.dto.BaseInstitutionSnlVersionDto;
import com.talent.front.dto.BaseSnlDto;
import com.talent.front.entity.BaseInstitutionSnl;
import com.talent.front.entity.BaseInstitutionSnlVersion;
import com.talent.front.service.BaseInstitutionSnlService;
import com.talent.front.service.BaseInstitutionSnlVersionService;
import com.talent.front.service.BaseSnlService;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构标准术语版本服务实现类
 */
@Service
public class BaseInstitutionSnlVersionServiceImpl extends BaseServiceImpl<BaseInstitutionSnlVersion>
		implements BaseInstitutionSnlVersionService {
	private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionSnlVersionServiceImpl.class);

	@Autowired
	private BaseInstitutionSnlVersionDao baseInstitutionSnlVersionDao;

	@Autowired
	private BaseSnlService baseSnlService;

	@Autowired
	private BaseInstitutionSnlService baseInstitutionSnlService;

	@Override
	public BaseDao<BaseInstitutionSnlVersion> getBaseDao() {
		return this.baseInstitutionSnlVersionDao;
	}

	@CacheSpeObject(value = "baseInstitutionSnlVersion", key = "#institutionSnlVersionId")
	@Override
	public BaseInstitutionSnlVersionDto selectDtoByPrimaryKey(String institutionSnlVersionId) {
		return this.baseInstitutionSnlVersionDao.selectDtoByPrimaryKey(institutionSnlVersionId);
	}

	@CacheSpeObject(value = "baseInstitutionSnlVersion", key = "#baseInstitutionSnlVersion.institutionSnlVersionId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseInstitutionSnlVersionDto updateDtoByPrimaryKeySelective(
			BaseInstitutionSnlVersion baseInstitutionSnlVersion) {
		this.baseInstitutionSnlVersionDao.updateByPrimaryKeySelective(baseInstitutionSnlVersion);
		return this.baseInstitutionSnlVersionDao
				.selectDtoByPrimaryKey(baseInstitutionSnlVersion.getInstitutionSnlVersionId());
	}

	@Override
	public PageResult<BaseInstitutionSnlVersionDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseInstitutionSnlVersionDao.pageCount(pageObject);
		PageResult<BaseInstitutionSnlVersionDto> pageResult = new PageResult<BaseInstitutionSnlVersionDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseInstitutionSnlVersionDto> baseInstitutionSnlVersionDtoList = ((BaseInstitutionSnlVersionServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseInstitutionSnlVersionDtoList);
		}
		return pageResult;
	}

	@Override
	public PageResult<BaseInstitutionSnlVersionDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.baseInstitutionSnlVersionDao.pageCountDtoJoin(pageObject);
		PageResult<BaseInstitutionSnlVersionDto> pageResult = new PageResult<BaseInstitutionSnlVersionDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseInstitutionSnlVersionDto> baseInstitutionSnlVersionDtoList = this.baseInstitutionSnlVersionDao
					.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(baseInstitutionSnlVersionDtoList);
		}
		return pageResult;
	}

	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseInstitutionSnlVersionDto> pageListCache(PageObject pageObject) {
		return this.baseInstitutionSnlVersionDao.pageListDto(pageObject);
	}

	/**
	 * 判断版本是否存在
	 * 
	 * @param list
	 *            准备导入的术语集
	 * @param institutionId
	 *            机构ID
	 * @return
	 */
	@Override
	public String versionIsExist(List<BaseInstitutionSnl> list, String institutionId) {
		String institutionSnlVersionId = null;
		PageObject po = new PageObject();
		po.setPageSize(3000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("institutionId", institutionId);
		po.setQueryCondition(map);
		List<BaseInstitutionSnlVersionDto> versions = pageListDto(po).getQueryResult();
		logger.info("得到版本数：" + versions.size());

		boolean isExist = true;
		for (BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDto : versions) {
			map = new HashMap<String, Object>();
			map.put("institutionSnlVersionId", baseInstitutionSnlVersionDto.getInstitutionSnlVersionId());
			po.setQueryCondition(map);
			List<BaseInstitutionSnlDto> baseInstitutionSnlDtos = baseInstitutionSnlService.pageListDto(po)
					.getQueryResult();
			if (baseInstitutionSnlVersionDto.getFieldCount() != baseInstitutionSnlDtos.size()) {
				break;
			}
			Map<Integer, BaseInstitutionSnlDto> mapTmp = new HashMap<Integer, BaseInstitutionSnlDto>();
			for (BaseInstitutionSnlDto baseInstitutionSnlDto : baseInstitutionSnlDtos) {
				mapTmp.put(baseInstitutionSnlDto.getDictId(), baseInstitutionSnlDto);
			}
			for (BaseInstitutionSnl baseInstitutionSnl : list) {
				BaseInstitutionSnlDto tmp = mapTmp.get(baseInstitutionSnl.getDictId());
				if (tmp != null) {
					if (baseInstitutionSnl.getDocCvalue() != tmp.getDocCvalue()
							|| !baseInstitutionSnl.getSnlCode().equals(tmp.getSnlCode())
							|| !baseInstitutionSnl.getDocCname().equals(tmp.getDocCname())) {
						isExist = false;
						break;
					}
				} else {
					logger.info("错误：dictid：" + baseInstitutionSnl.getDictId());
					isExist = false;
					break;
				}
			}
			if (isExist) {
				institutionSnlVersionId = baseInstitutionSnlVersionDto.getInstitutionSnlVersionId();
				break;
			}
		}

		return institutionSnlVersionId;
	}

	/**
	 * 得到标准术语集的dictid 和 code Map
	 * 
	 * @return
	 */
	@Override
	public Map<String, Integer> getDictidAndCode() {
		PageObject po = new PageObject();
		po.setPageSize(3000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictIdMin", 1);
		po.setQueryCondition(map);
		List<BaseSnlDto> baseSnlDtos = baseSnlService.pageListDto(po).getQueryResult();
		logger.info("得到标准术语集数量：" + baseSnlDtos.size());
		Map<String, Integer> dictIdToCode = new HashMap<String, Integer>();
		for (BaseSnlDto bsd : baseSnlDtos) {
			String code = bsd.getSnlCode();
			if (!StringUtils.isEmpty(code)) {
				dictIdToCode.put(code.trim(), bsd.getDictId());
			}
		}
		return dictIdToCode;
	}

	/**
	 * 根据版本号得到 列号 与 机构标准术语对应表 对应关系
	 * 
	 * @param institutionSnlVersionId
	 * @return
	 */
	@Override
	public Map<Integer, BaseInstitutionSnlDto> getValueAndInstitutionSnl(String institutionSnlVersionId) {
		PageObject po = new PageObject();
		po.setPageSize(3000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("institutionSnlVersionId", institutionSnlVersionId);
		po.setQueryCondition(map);
		List<BaseInstitutionSnlDto> baseInstitutionSnlDtos = baseInstitutionSnlService.pageListDto(po).getQueryResult();
		logger.info("得到 机构标准术语对应表关系数量：" + baseInstitutionSnlDtos.size() + " 版本号为：" + institutionSnlVersionId);
		Map<Integer, BaseInstitutionSnlDto> valueAndInstitutionSnl = new HashMap<Integer, BaseInstitutionSnlDto>();
		for (BaseInstitutionSnlDto bisd : baseInstitutionSnlDtos) {
			Integer key = bisd.getDocCvalue();
			if (key != null) {
				valueAndInstitutionSnl.put(key, bisd);
			}
		}
		return valueAndInstitutionSnl;
	}

}