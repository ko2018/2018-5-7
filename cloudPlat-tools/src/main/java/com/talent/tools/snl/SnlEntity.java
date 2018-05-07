package com.talent.tools.snl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;



/**
 * 标准术语解析对象
 * @author wangdj
 * 2017年11月23日
 */
public class SnlEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String fullRelation;
    
    private String name;
    
    private String rootName;
    
    private String parentName;
    
    private String nodeName;
    
    private String code;
    
    private String dataType;
    
    public boolean isVerify()
    {
        if (StringUtils.isEmpty(this.fullRelation) || StringUtils.isEmpty(this.name) 
                || StringUtils.isEmpty(this.rootName) || StringUtils.isEmpty(this.code))
        {
            return false;
        }
        
        return true;
    }

	public String getFullRelation() {
		return fullRelation;
	}

	public void setFullRelation(String fullRelation) {
		this.fullRelation = fullRelation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public String toString() {
		return "SnlEntity [fullRelation=" + fullRelation + ", name=" + name + ", rootName=" + rootName + ", parentName="
				+ parentName + ", nodeName=" + nodeName + ", code=" + code + ", dataType=" + dataType + "]";
	}

}
