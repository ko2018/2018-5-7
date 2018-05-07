package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchFileLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档操作日志表Dto类
 */
@JsonInclude(Include.NON_NULL)
public class BaseResearchFileLogDto extends BaseResearchFileLog {

    private static final long serialVersionUID = -9010060614745081900L;

    private String fileName;
    
    private String fileTag;
    
    private String classifyId;
    
    private String classifyName;
    
	public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileTag()
    {
        return fileTag;
    }

    public void setFileTag(String fileTag)
    {
        this.fileTag = fileTag;
    }

    public String getClassifyId()
    {
        return classifyId;
    }

    public void setClassifyId(String classifyId)
    {
        this.classifyId = classifyId;
    }

    public String getClassifyName()
    {
        return classifyName;
    }

    public void setClassifyName(String classifyName)
    {
        this.classifyName = classifyName;
    }

    @Override
	public String toString() {
		return "BaseResearchFileLog ["
		 		+ "this.logId=" + this.getLogId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.fileId=" + this.getFileId() + ", "
		 		+ "this.operatorType=" + this.getOperatorType() + ", "
		 		+ "this.operatorId=" + this.getOperatorId() + ", "
		 		+ "this.operateTime=" + this.getOperateTime() + ", "
		+ "]";   
	}

}