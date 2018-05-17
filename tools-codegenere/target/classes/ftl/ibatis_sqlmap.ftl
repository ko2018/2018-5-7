<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!--
${table.annotation}
User: Created by auto build
Date: ${now?string("yyyy-MM-dd HH:mm:ss")}
-->
<mapper namespace="${table.packageDao}.${table.classDomain}Dao" >
    <resultMap id="${table.classDomain}" type="${table.packageDomain}.${table.classDomain}">
        <#list table.columnList as item>
        <#if item.name=table.pk>
        	<id column="${item.name}" property="${item.propertyName}" jdbcType="VARCHAR" javaType="String"/>
        <#else>
        	<result column="${item.name}" property="${item.propertyName}" jdbcType="${item.ibatisType}" javaType="${item.javaType}"/>
        </#if>
        </#list>
    </resultMap>
    
    <resultMap id="${table.classDomain}Dto" type="${table.packageDto}.${table.classDomain}Dto">
        <#list table.columnList as item>
        <#if item.name=table.pk>
        	<id column="${item.name}" property="${item.propertyName}" jdbcType="VARCHAR" javaType="String"/>
        <#else>
        	<result column="${item.name}" property="${item.propertyName}" jdbcType="${item.ibatisType}" javaType="${item.javaType}"/>
        </#if>
        </#list>
    </resultMap>
    
    <resultMap id="${table.classDomain}DtoJoin" type="${table.packageDto}.${table.classDomain}Dto">
        <#list table.columnList as item>
        <#if item.name=table.pk>
        	<id column="${item.name}" property="${item.propertyName}" jdbcType="VARCHAR" javaType="String"/>
        <#else>
        	<result column="${item.name}" property="${item.propertyName}" jdbcType="${item.ibatisType}" javaType="${item.javaType}"/>
        </#if>
        </#list>
    </resultMap>

	<#assign list = statics["com.fuwenpan.tools.codegenere.util.BeanUtil"].createInstance("java.util.ArrayList")>
	<#list table.columnList as item>
		<#if item.ibatisType!="BLOB">
			<#assign b = list.add(item.name)/>
		</#if>
	</#list>

	<sql id="Base_Column_List" >
		<#list list as field>
		${field}<#if field_has_next>,</#if>
		</#list>  
	</sql>
	
	<sql id="Base_Column_List_Dto" >
		<#list list as field>
		${table.name}.${field}<#if field_has_next>,</#if>
		</#list>  
	</sql>
	
	<sql id="Base_Column_List_Dto_Join" >
		<#list list as field>
		${table.name}.${field}<#if field_has_next>,</#if>
		</#list>  
	</sql>
 
    <!--查询${table.annotation}-->
    <select id="selectByPrimaryKey" parameterType="java.lang.String" resultMap="${table.classDomain}">
		select
			<include refid="Base_Column_List" />
	<![CDATA[
		from ${table.name}
		where ${table.pk} = ${"#{"}${table.pkName},jdbcType=VARCHAR}
	 ]]>
    </select>
    
    <!--查询${table.annotation}Dto,根据需要，请添加字段-->
    <select id="selectDtoByPrimaryKey" parameterType="java.lang.String" resultMap="${table.classDomain}Dto">
		select
			<include refid="Base_Column_List_Dto" />
	<![CDATA[
		from ${table.name}
		where ${table.name}.${table.pk} = ${"#{"}${table.pkName},jdbcType=VARCHAR}
	 ]]>
    </select>
    
   <!--删除${table.annotation}--> 
	<delete id="deleteByPrimaryKey" parameterType="java.lang.String" >
		delete from ${table.name}
		where ${table.pk} = ${"#{"}${table.pkName},jdbcType=VARCHAR}
	</delete>
  
	<!--创建${table.annotation}-->
    <insert id="insert" parameterType="${table.packageDomain}.${table.classDomain}">
	<![CDATA[
		insert into ${table.name}(
		<#list table.columnList as item>
			${item.name}<#if item_has_next>,</#if>
		</#list>
		) values (
		<#list table.columnList as item>
			${"#{"}${item.propertyName},jdbcType=${item.ibatisType}}<#if item_has_next>,</#if>
		</#list>
		)
	]]>
    </insert>
    
     <!--创建${table.annotation}-->
    <insert id="insertSelective" parameterType="${table.packageDomain}.${table.classDomain}">
    	insert into ${table.name}
    	<trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.columnList as item>
			<if test="${item.propertyName} != null" >
				${item.name},
			</if>
		</#list>
    	</trim>
    	<trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list table.columnList as item>
			 <if test="${item.propertyName} != null" >
			   ${"#{"}${item.propertyName},jdbcType=${item.ibatisType}},
			  </if>
		</#list>
    	</trim>
    </insert>
    
    <!--更新${table.annotation}-->
	<update id="updateByPrimaryKey" parameterType="${table.packageDomain}.${table.classDomain}" >
		update  ${table.name}
		<set> 
	<#list table.columnList as item>
		<#if item.pk !='Y'>
			${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.ibatisType}},
		</#if>
	</#list>
		</set>
		where ${table.pk} =  ${"#{"}${table.pkName},jdbcType=VARCHAR}
	</update>
  
	<update id="updateByPrimaryKeySelective" parameterType="${table.packageDomain}.${table.classDomain}" >
  		update  ${table.name}
  		<set>
	<#list table.columnList as item>
		<#if item.pk !='Y'>
			<if test="${item.propertyName} != null" >
				${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.ibatisType}},
			</if>
		</#if>
	</#list>
  		</set>
		where ${table.pk} =  ${"#{"}${table.pkName},jdbcType=VARCHAR}
	</update>
  
  
  <!--${table.annotation}查询条件-->
  
	<sql id="pageWhere">
		<where>
  	<#list table.columnList as item>
  		<#if item.ibatisType!="BLOB" && item.ibatisType!="CLOB" && item.ibatisType!="TEXT" && item.pk!="Y">
			<#if item.ibatisType=="NUMERIC">
				 <!--${item.annotation}等于-->
				 <if test="queryCondition.${item.propertyName} != null" >
					and  ${table.name}.${item.name} = ${"#{"}queryCondition.${item.propertyName},jdbcType=NUMERIC} 
				 </if>
				  <!--${item.annotation}最小值-->
				  <if test="queryCondition.${item.propertyName}Min != null" >
					 and ${table.name}.${item.name} >= ${"#{"}queryCondition.${item.propertyName}Min,jdbcType=NUMERIC} 
				 </if>
				  <!--${item.annotation}最大值-->
				  <if test="queryCondition.${item.propertyName}Max != null" >
					 <![CDATA[
					and  ${table.name}.${item.name} <= ${"#{"}queryCondition.${item.propertyName}Max,jdbcType=NUMERIC} 
					 ]]>
				 </if>
			<#elseif item.ibatisType=="INTEGER">
				 <!--${item.annotation}等于-->
				 <if test="queryCondition.${item.propertyName} != null" >
					 and ${table.name}.${item.name} = ${"#{"}queryCondition.${item.propertyName},jdbcType=INTEGER} 
				 </if>
				  <!--${item.annotation}最小值-->
				  <if test="queryCondition.${item.propertyName}Min != null" >
					 and ${table.name}.${item.name} >= ${"#{"}queryCondition.${item.propertyName}Min,jdbcType=INTEGER} 
				 </if>				 
				  <!--${item.annotation}最大值-->
				  <if test="queryCondition.${item.propertyName}Max != null" >
					 <![CDATA[
					and  ${table.name}.${item.name} <= ${"#{"}queryCondition.${item.propertyName}Max,jdbcType=INTEGER} 
					 ]]>
				 </if>
			<#elseif item.ibatisType=="VARCHAR" || item.ibatisType=="TEXT" || item.ibatisType=="CLOB">
				 <if test="queryCondition.${item.propertyName} != null" >
					 and ${table.name}.${item.name} = ${"#{"}queryCondition.${item.propertyName},jdbcType=VARCHAR} 
				 </if>
				 <if test="queryCondition.${item.propertyName}Like != null" >
					and ${table.name}.${item.name} like "%"${"#{"}queryCondition.${item.propertyName}Like,jdbcType=VARCHAR}"%"
				 </if>
			 <#elseif item.ibatisType=="TIMESTAMP">
				  <if test="queryCondition.${item.propertyName} != null" >
					and  ${table.name}.${item.name} = ${"#{"}queryCondition.${item.propertyName},jdbcType=TIMESTAMP} 
				 </if>
				 <!--${item.annotation}开始-->
				 <if test="queryCondition.${item.propertyName}Begin != null" >
					and ${table.name}.${item.name}  >= str_to_date(${"#{"}queryCondition.${item.propertyName}Begin,jdbcType=TIMESTAMP},'%Y-%m-%d %T') 
				 </if>
				  <!--${item.annotation}结束-->
				 <if test="queryCondition.${item.propertyName}End != null" >
					<![CDATA[
					 and ${table.name}.${item.name} <= str_to_date(${"#{"}queryCondition.${item.propertyName}End,jdbcType=TIMESTAMP},'%Y-%m-%d %T') 
					  ]]>
				 </if>
			</#if>
  		</#if>
  		</#list>
  		<if test="queryCondition.qryWord != null and queryCondition.qryWord !=''" >
  		and (${table.name}.${table.pk} like "%"${"#{"}queryCondition.qryWord,jdbcType=VARCHAR}"%"
  			<#list table.columnList as item>
  				<#if item.pk!="Y">
  					<#if item.ibatisType=="VARCHAR" || item.ibatisType=="TEXT" || item.ibatisType=="CLOB">
  						or ${table.name}.${item.name} like "%"${"#{"}queryCondition.qryWord,jdbcType=VARCHAR}"%"
  					</#if>
  				</#if>
  			</#list>
  			)
		</if>
  	</where>
  </sql>  


    <!--${table.annotation}分页记录数-->
    <select id="pageCount" parameterType="${table.basePackageRoot}.model.PageObject" resultType="java.lang.Long">
        <![CDATA[
            select count(*) total from ${table.name}
        ]]>
        <include refid="pageWhere"/>
    </select>

    <!--${table.annotation}分页记录集-->
    <select id="pageList" parameterType="${table.basePackageRoot}.model.PageObject" resultMap="${table.classDomain}">
		select
			<include refid="Base_Column_List" />
		from ${table.name}
			<include refid="pageWhere"/>
		order by 
       <if test= "orderByClause!=null and orderByClause != ''">
       	${"${"}orderByClause},
       </if>
	   ${table.pk}
       <if test="pageIndex != null and pageSize!=null ">
       limit ${"#{"}pageIndex,jdbcType=INTEGER} , ${"#{"}pageSize,jdbcType=INTEGER} 
       </if>
    </select>
    
    <!--${table.annotation}分页记录数Dto-->
    <select id="pageCountDto" parameterType="${table.basePackageRoot}.model.PageObject" resultType="java.lang.Long">
        <![CDATA[
            select count(*) total from ${table.name}
        ]]>
        <include refid="pageWhere"/>
    </select>
    
    <!--${table.annotation}分页记录集Dto-->
    <select id="pageListDto" parameterType="${table.basePackageRoot}.model.PageObject" resultMap="${table.classDomain}Dto">
		select
			<include refid="Base_Column_List_Dto" />
		from ${table.name}
			<include refid="pageWhere"/>
		order by 
       <if test= "orderByClause!=null and orderByClause != ''">
       	${"${"}orderByClause},
       </if>
	   ${table.name}.${table.pk}
       <if test="pageIndex != null and pageSize!=null ">
       limit ${"#{"}pageIndex,jdbcType=INTEGER} , ${"#{"}pageSize,jdbcType=INTEGER} 
       </if>
    </select>
    
    <!--${table.annotation}分页记录数Dto  JOIN-->
    <select id="pageCountDtoJoin" parameterType="${table.basePackageRoot}.model.PageObject" resultType="java.lang.Long">
        <![CDATA[
            select count(*) total from ${table.name}
        ]]>
        <include refid="joinSql"/>
        <include refid="pageWhere"/>
    </select>
    
    <!--${table.annotation}分页记录集Dto  JOIN-->
    <select id="pageListDtoJoin" parameterType="${table.basePackageRoot}.model.PageObject" resultMap="${table.classDomain}DtoJoin">
		select
			<include refid="Base_Column_List_Dto_Join" />
		from ${table.name}
			<include refid="joinSql"/>
			<include refid="pageWhere"/>
		order by 
       <if test= "orderByClause!=null and orderByClause != ''">
       	${"${"}orderByClause},
       </if>
	   ${table.name}.${table.pk}
       <if test="pageIndex != null and pageSize!=null ">
       limit ${"#{"}pageIndex,jdbcType=INTEGER} , ${"#{"}pageSize,jdbcType=INTEGER} 
       </if>
    </select>
    
    <sql id="joinSql"></sql>
    
    <!--批量创建${table.annotation}-->
    <insert id="batchInsert" parameterType="java.util.List">   
		insert into ${table.name}(
		<#list table.columnList as item>
			${item.name}<#if item_has_next>,</#if>
		</#list>
		) values
		<foreach collection="list" item="item" index="index" separator=",">
		 (<#list table.columnList as item>
			${"#{"}item.${item.propertyName},jdbcType=${item.ibatisType}}<#if item_has_next>,</#if>
		</#list>)
		</foreach>
    </insert>

</mapper>