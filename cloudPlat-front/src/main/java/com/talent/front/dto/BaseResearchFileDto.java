package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "isDel" })
public class BaseResearchFileDto extends BaseResearchFile {

    private static final long serialVersionUID = -7872045092117705556L;

    private String institutionName;
    
    private String userName;
    
	public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getInstitutionName()
    {
        return institutionName;
    }

    public void setInstitutionName(String institutionName)
    {
        this.institutionName = institutionName;
    }

    @Override
	public String toString() {
		return "BaseResearchFile ["
		 		+ "this.fileId=" + this.getFileId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.fileName=" + this.getFileName() + ", "
		 		+ "this.filePath=" + this.getFilePath() + ", "
		 		+ "this.classifyId=" + this.getClassifyId() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}