package ${table.packageDao};

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ${table.basePackageRoot}.database.BaseDao;
import ${table.basePackageRoot}.model.PageObject;
import ${table.packageDto}.${table.classDomain}Dto;
import ${table.packageDomain}.${table.classDomain};

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：${now?string("yyyy-MM-dd")} <br/>
 * 描述：${table.annotation}类（Dao层）
 */
 @Mapper
public interface ${table.classDomain}Dao extends BaseDao<${table.classDomain}> {

	${table.classDomain}Dto selectDtoByPrimaryKey(String ${table.pkName});

	List<${table.classDomain}Dto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<${table.classDomain}Dto> pageListDtoJoin(PageObject pageObject);

}