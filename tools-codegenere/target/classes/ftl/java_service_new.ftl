package ${table.packageService};

import java.util.List;

import ${table.basePackageRoot}.service.BaseService;
import ${table.basePackageRoot}.model.PageObject;
import ${table.basePackageRoot}.model.PageResult;
import ${table.packageDto}.${table.classDomain}Dto;
import ${table.packageDomain}.${table.classDomain};

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：${now?string("yyyy-MM-dd")} <br/>
 * 描述：${table.annotation}服务接口类
 */
public interface ${table.classDomain}Service extends BaseService<${table.classDomain}>{

	/**
	* 以主鍵查询DTO
	*/
	${table.classDomain}Dto selectDtoByPrimaryKey(String ${table.pkName});

	/**
	* 更新model返回DTO
	*/
	${table.classDomain}Dto updateDtoByPrimaryKeySelective(${table.classDomain} ${table.classDomainVar});

	PageResult<${table.classDomain}Dto> pageListDto(PageObject pageObject);
	
	PageResult<${table.classDomain}Dto> pageListDtoJoin(PageObject pageObject);
	
	List<${table.classDomain}Dto> pageListCache(PageObject pageObject);

}