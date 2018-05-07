package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息附件表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "deleteStatus" })
public class BaseFileDto extends BaseFile {

	@Override
	public String toString() {
		return "BaseFile ["
		 		+ "this.fileId=" + this.getFileId() + ", "
		 		+ "this.fileName=" + this.getFileName() + ", "
		 		+ "this.filePath=" + this.getFilePath() + ", "
		 		+ "this.fileSize=" + this.getFileSize() + ", "
		 		+ "this.fileWidth=" + this.getFileWidth() + ", "
		 		+ "this.fileHeight=" + this.getFileHeight() + ", "
		 		+ "this.fileExt=" + this.getFileExt() + ", "
		 		+ "this.fileType=" + this.getFileType() + ", "
		 		+ "this.fileInfo=" + this.getFileInfo() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}