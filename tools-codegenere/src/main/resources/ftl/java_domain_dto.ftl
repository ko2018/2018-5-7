package ${table.packageDto};

import ${table.packageDomain}.${table.classDomain};

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：${now?string("yyyy-MM-dd")} <br/>
 * 描述：${table.annotation}Dto类
 */
public class ${table.classDomain}Dto extends ${table.classDomain} {

	@Override
	public String toString() {
		return "${table.classDomain} ["
		 	<#list table.columnList as item>
		 		+ "this.${item.propertyName}=" + this.get${item.propertyName?cap_first}() + ", "
			</#list>
		+ "]";   
	}

}