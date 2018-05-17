package ${table.packageDomain};

import java.util.Date;
import ${table.basePackageRoot}.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：${now?string("yyyy-MM-dd")} <br/>
 * 描述：${table.annotation}类
 */
public class ${table.classDomain} implements BaseEntity {

	private static final long serialVersionUID = 1L;

    <#list table.columnList as item>
    //${item.annotation}
    private ${item.javaType} ${item.propertyName};<#--${item.annotation}-->

    </#list>

    <#list table.columnList as item>
    /**
     * 获得${item.annotation}
     * @return ${item.javaType}
     */
    public ${item.javaType} get${item.propertyName?cap_first}(){
        return this.${item.propertyName};
    }

    /**
     * 设置${item.annotation}
     * @param ${item.propertyName}  ${item.annotation}
     */
    public void set${item.propertyName?cap_first}(${item.javaType} ${item.propertyName}){
        this.${item.propertyName} = ${item.propertyName};
    }
    </#list>
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		<#list table.columnList as item>
			<#if item.pk =='Y'>
		this.${item.propertyName} = id;
			</#if>
		</#list>
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		<#list table.columnList as item>
			<#if item.pk =='Y'>
		return this.${item.propertyName};
			</#if>
		</#list>
	}
	
	
	@Override
	public String toString() {
		return "${table.classDomain} ["
		 	<#list table.columnList as item>
		 		+ "this.${item.propertyName}=" + this.${item.propertyName} + ", "
			</#list>
		+ "]";   
	}
}