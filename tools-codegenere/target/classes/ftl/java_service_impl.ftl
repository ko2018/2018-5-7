package ${table.packageServiceImpl};

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheSpeList;
import ${table.basePackageRoot}.database.BaseDao;
import ${table.basePackageRoot}.model.PageObject;
import ${table.basePackageRoot}.model.PageResult;
import ${table.basePackageRoot}.service.BaseServiceImpl;
import ${table.packageDao}.${table.classDomain}Dao;
import ${table.packageDto}.${table.classDomain}Dto;
import ${table.packageDomain}.${table.classDomain};
import ${table.packageService}.${table.classDomain}Service;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：${now?string("yyyy-MM-dd")} <br/>
 * 描述：${table.annotation}服务实现类
 */
@Service
public class ${table.classDomain}ServiceImpl extends BaseServiceImpl<${table.classDomain}> implements ${table.classDomain}Service {
	private static final Logger logger = LoggerFactory.getLogger(${table.classDomain}ServiceImpl.class);

	@Autowired
	private ${table.classDomain}Dao ${table.classDomainVar}Dao;

	@Override
	public BaseDao<${table.classDomain}> getBaseDao() {
		return this.${table.classDomainVar}Dao;
	}
	
	@CacheSpeObject(value = "${table.classDomainVar}", key = "#${table.pkName}")
	@Override
	public ${table.classDomain}Dto selectDtoByPrimaryKey(String ${table.pkName}) {
		return this.${table.classDomainVar}Dao.selectDtoByPrimaryKey(${table.pkName});
	}
	
	@CacheSpeObject(value = "${table.classDomainVar}", key = "#${table.classDomainVar}.<#list table.columnList as item><#if item.pk =='Y'>${item.propertyName}</#if></#list>", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public ${table.classDomain}Dto updateDtoByPrimaryKeySelective(${table.classDomain} ${table.classDomainVar}) {
		this.${table.classDomainVar}Dao.updateByPrimaryKeySelective(${table.classDomainVar});
		return this.${table.classDomainVar}Dao.selectDtoByPrimaryKey(${table.classDomainVar}.get<#list table.columnList as item><#if item.pk =='Y'>${item.propertyName?cap_first}</#if></#list>());
	}
	
	@Override
	public PageResult<${table.classDomain}Dto> pageListDto(PageObject pageObject) {
		long totalCount = this.${table.classDomainVar}Dao.pageCountDto(pageObject);
		PageResult<${table.classDomain}Dto> pageResult = new PageResult<${table.classDomain}Dto>();
		pageResult.setPageObjectAndTotal(pageObject, totalCount);
		if (totalCount > 0) {
			List<${table.classDomain}Dto> ${table.classDomainVar}DtoList = ((${table.classDomain}ServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(${table.classDomainVar}DtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<${table.classDomain}Dto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.${table.classDomainVar}Dao.pageCountDtoJoin(pageObject);
		PageResult<${table.classDomain}Dto> pageResult = new PageResult<${table.classDomain}Dto>();
		pageResult.setPageObjectAndTotal(pageObject, totalCount);
		if (totalCount > 0) {
			List<${table.classDomain}Dto> ${table.classDomainVar}DtoList = this.${table.classDomainVar}Dao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(${table.classDomainVar}DtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<${table.classDomain}Dto> pageListCache(PageObject pageObject) {
		return this.${table.classDomainVar}Dao.pageListDto(pageObject);
	}

}