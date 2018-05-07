package com.talent.tools.snl;

import org.springframework.util.StringUtils;

/**
 * 一句话简要描述
 * @author wangdj
 * 2017年11月23日
 */
public class SnlNode
{
    private String snlId;
    
    private int snlType;

    private String snlTpyeName; 
    
    private String snlCode;
    
    private String nameCn;
    
    private String fullNameCn;
    
    private String shortNameCn;
    
    private String nameUs;
    
    private String shortNameUs;
    
    private String parentCode;
    
    private int depth;
    
    private int isLeaf;
    
    private int dictId;
    
    private String creator;
    
    private String addTime;
    
    private String updateTime;
    
    private String updateUser;
    
    public String getSqlScipt()
    {
        StringBuffer bf = new StringBuffer();
        bf.append("insert into base_snl(snl_id, snl_type, snl_type_name, name_cn, full_name_cn, short_name_cn, ");
        if (!StringUtils.isEmpty(this.snlCode))
        {
        	bf.append("snl_code, ");
        }
        if (!StringUtils.isEmpty(this.nameUs))
        {
        	bf.append("name_us, ");
        }
        if (!StringUtils.isEmpty(this.shortNameCn))
        {
        	bf.append("short_name_us, ");
        }
        bf.append("parent_code, depth, is_leaf, dict_id, creator, add_time, update_user, update_time) values ");
        bf.append("('" + this.snlId + "'," + this.snlType + ",'" + this.snlTpyeName + "','" + this.nameCn + "','");
        bf.append(this.fullNameCn + "','" + this.shortNameCn + "'," );
        if (!StringUtils.isEmpty(snlCode))
        {
        	bf.append("'" + this.snlCode + "',");
        }
        if (!StringUtils.isEmpty(this.nameUs))
        {
        	bf.append("'" + this.nameUs + "',");
        }
        if (!StringUtils.isEmpty(this.shortNameCn))
        {
        	bf.append("'" + this.shortNameCn + "',");
        }
        bf.append("'" + this.parentCode + "'," + this.depth + ", " + this.isLeaf + ", " + this.dictId + ",'" + this.creator + "','");
        bf.append(this.addTime + "','" + this.updateUser + "','" + this.updateTime +  "');");
        return bf.toString();
    }
    
    public String getSqlScipt4Value(String id, String valueTypeId, String valueTypeName)
    {
    	StringBuffer bf = new StringBuffer();
        bf.append("insert into base_snl_rule_value(value_id, snl_id, snl_code, dict_id, is_legitimate, creator, add_time, update_user, update_time");
        if (!StringUtils.isEmpty(valueTypeId))
        {
        	bf.append(", valuetype_id");
        }
        
        if (!StringUtils.isEmpty(valueTypeName))
        {
        	bf.append(", valuetype_name");
        }
        bf.append(") values ");
        
        bf.append("('" + id + "','" + this.snlId + "','" + this.snlCode + "'," + this.dictId + ", 1,'");
        bf.append(this.creator + "','" + this.addTime + "','" + this.updateUser + "','" + this.updateTime +  "'");
        if (!StringUtils.isEmpty(valueTypeId))
        {
        	bf.append(",'" + valueTypeId + "'");
        }
        
        if (!StringUtils.isEmpty(valueTypeName))
        {
        	bf.append(",'" + valueTypeName + "'");
        }
        bf.append(");");
        
        return bf.toString();
    }
    
    public String getSnlId()
    {
        return snlId;
    }

    public void setSnlId(String snlId)
    {
        this.snlId = snlId;
    }

    public int getSnlType()
    {
        return snlType;
    }

    public void setSnlType(int snlType)
    {
        this.snlType = snlType;
    }

    public String getSnlTpyeName()
    {
        return snlTpyeName;
    }

    public void setSnlTpyeName(String snlTpyeName)
    {
        this.snlTpyeName = snlTpyeName;
    }

    public String getSnlCode()
    {
        return snlCode;
    }

    public void setSnlCode(String snlCode)
    {
        this.snlCode = snlCode;
    }

    public String getNameCn()
    {
        return nameCn;
    }

    public void setNameCn(String nameCn)
    {
        this.nameCn = nameCn;
    }

    public String getFullNameCn()
    {
        return fullNameCn;
    }

    public void setFullNameCn(String fullNameCn)
    {
        this.fullNameCn = fullNameCn;
    }

    public String getShortNameCn()
    {
        return shortNameCn;
    }

    public void setShortNameCn(String shortNameCn)
    {
        this.shortNameCn = shortNameCn;
    }

    public String getNameUs()
    {
        return nameUs;
    }

    public void setNameUs(String nameUs)
    {
        this.nameUs = nameUs;
    }

    public String getShortNameUs()
    {
        return shortNameUs;
    }

    public void setShortNameUs(String shortNameUs)
    {
        this.shortNameUs = shortNameUs;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public int getIsLeaf()
    {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf)
    {
        this.isLeaf = isLeaf;
    }

    public int getDictId()
    {
        return dictId;
    }

    public void setDictId(int dictId)
    {
        this.dictId = dictId;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getAddTime()
    {
        return addTime;
    }

    public void setAddTime(String addTime)
    {
        this.addTime = addTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

	@Override
	public String toString() {
		return "SnlNode [snlId=" + snlId + ", snlType=" + snlType + ", snlTpyeName=" + snlTpyeName + ", snlCode="
				+ snlCode + ", nameCn=" + nameCn + ", fullNameCn=" + fullNameCn + ", shortNameCn=" + shortNameCn
				+ ", nameUs=" + nameUs + ", shortNameUs=" + shortNameUs + ", parentCode=" + parentCode + ", depth="
				+ depth + ", isLeaf=" + isLeaf + ", dictId=" + dictId + ", creator=" + creator + ", addTime=" + addTime
				+ ", updateTime=" + updateTime + ", updateUser=" + updateUser + "]";
	}
    
}
