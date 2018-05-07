package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：文档表类
 */
public class BaseDoc implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //文档id
    private String docId;

    //机构ID
    private String institutionId;

    //课题Id(科研管理id)
    private String researchId;

    //文档规模
    private Integer docRows;

    //文档名称
    private String docName;

    //原始文件名称（防止重复上传）
    private String docSourceName;

    //文档路径
    private String docPath;

    //文档类型
    private String doctypeId;

    //文档类型名称
    private String doctypeName;

    //文档状态
    private String docStatus;

    //数据条数
    private Integer docCount;

    //是否完成属性映射
    private String isMapping;

    //归一化（属性映射）版本号(机构标准术语版本Id)
    private String institutionSnlVersionId;

    //是否完成合法值定义
    private String isLegal;

    //是否完成数据持久化（存入mysql）
    private String isPersistence;

    //
    private String isClean;

    //是否重新清洗(clean表触发)
    private String isReclean;

    //是否存入es
    private String isEs;

    //版本号（乐观锁）
    private Integer versionId;

    //
    private String docMd5;

    //重复数据条数
    private Integer docRepeatCount;

    //未完成属性映射字符串
    private String mappingStr;

    //未完成合法值定义字符串
    private String legalStr;

    //删除状态
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得文档id
     * @return String
     */
    public String getDocId(){
        return this.docId;
    }

    /**
     * 设置文档id
     * @param docId  文档id
     */
    public void setDocId(String docId){
        this.docId = docId;
    }
    /**
     * 获得机构ID
     * @return String
     */
    public String getInstitutionId(){
        return this.institutionId;
    }

    /**
     * 设置机构ID
     * @param institutionId  机构ID
     */
    public void setInstitutionId(String institutionId){
        this.institutionId = institutionId;
    }
    /**
     * 获得课题Id(科研管理id)
     * @return String
     */
    public String getResearchId(){
        return this.researchId;
    }

    /**
     * 设置课题Id(科研管理id)
     * @param researchId  课题Id(科研管理id)
     */
    public void setResearchId(String researchId){
        this.researchId = researchId;
    }
    /**
     * 获得文档规模
     * @return Integer
     */
    public Integer getDocRows(){
        return this.docRows;
    }

    /**
     * 设置文档规模
     * @param docRows  文档规模
     */
    public void setDocRows(Integer docRows){
        this.docRows = docRows;
    }
    /**
     * 获得文档名称
     * @return String
     */
    public String getDocName(){
        return this.docName;
    }

    /**
     * 设置文档名称
     * @param docName  文档名称
     */
    public void setDocName(String docName){
        this.docName = docName;
    }
    /**
     * 获得原始文件名称（防止重复上传）
     * @return String
     */
    public String getDocSourceName(){
        return this.docSourceName;
    }

    /**
     * 设置原始文件名称（防止重复上传）
     * @param docSourceName  原始文件名称（防止重复上传）
     */
    public void setDocSourceName(String docSourceName){
        this.docSourceName = docSourceName;
    }
    /**
     * 获得文档路径
     * @return String
     */
    public String getDocPath(){
        return this.docPath;
    }

    /**
     * 设置文档路径
     * @param docPath  文档路径
     */
    public void setDocPath(String docPath){
        this.docPath = docPath;
    }
    /**
     * 获得文档类型
     * @return String
     */
    public String getDoctypeId(){
        return this.doctypeId;
    }

    /**
     * 设置文档类型
     * @param doctypeId  文档类型
     */
    public void setDoctypeId(String doctypeId){
        this.doctypeId = doctypeId;
    }
    /**
     * 获得文档类型名称
     * @return String
     */
    public String getDoctypeName(){
        return this.doctypeName;
    }

    /**
     * 设置文档类型名称
     * @param doctypeName  文档类型名称
     */
    public void setDoctypeName(String doctypeName){
        this.doctypeName = doctypeName;
    }
    /**
     * 获得文档状态
     * @return String
     */
    public String getDocStatus(){
        return this.docStatus;
    }

    /**
     * 设置文档状态
     * @param docStatus  文档状态
     */
    public void setDocStatus(String docStatus){
        this.docStatus = docStatus;
    }
    /**
     * 获得数据条数
     * @return Integer
     */
    public Integer getDocCount(){
        return this.docCount;
    }

    /**
     * 设置数据条数
     * @param docCount  数据条数
     */
    public void setDocCount(Integer docCount){
        this.docCount = docCount;
    }
    /**
     * 获得是否完成属性映射
     * @return String
     */
    public String getIsMapping(){
        return this.isMapping;
    }

    /**
     * 设置是否完成属性映射
     * @param isMapping  是否完成属性映射
     */
    public void setIsMapping(String isMapping){
        this.isMapping = isMapping;
    }
    /**
     * 获得归一化（属性映射）版本号(机构标准术语版本Id)
     * @return String
     */
    public String getInstitutionSnlVersionId(){
        return this.institutionSnlVersionId;
    }

    /**
     * 设置归一化（属性映射）版本号(机构标准术语版本Id)
     * @param institutionSnlVersionId  归一化（属性映射）版本号(机构标准术语版本Id)
     */
    public void setInstitutionSnlVersionId(String institutionSnlVersionId){
        this.institutionSnlVersionId = institutionSnlVersionId;
    }
    /**
     * 获得是否完成合法值定义
     * @return String
     */
    public String getIsLegal(){
        return this.isLegal;
    }

    /**
     * 设置是否完成合法值定义
     * @param isLegal  是否完成合法值定义
     */
    public void setIsLegal(String isLegal){
        this.isLegal = isLegal;
    }
    /**
     * 获得是否完成数据持久化（存入mysql）
     * @return String
     */
    public String getIsPersistence(){
        return this.isPersistence;
    }

    /**
     * 设置是否完成数据持久化（存入mysql）
     * @param isPersistence  是否完成数据持久化（存入mysql）
     */
    public void setIsPersistence(String isPersistence){
        this.isPersistence = isPersistence;
    }
    /**
     * 获得
     * @return String
     */
    public String getIsClean(){
        return this.isClean;
    }

    /**
     * 设置
     * @param isClean  
     */
    public void setIsClean(String isClean){
        this.isClean = isClean;
    }
    /**
     * 获得是否重新清洗(clean表触发)
     * @return String
     */
    public String getIsReclean(){
        return this.isReclean;
    }

    /**
     * 设置是否重新清洗(clean表触发)
     * @param isReclean  是否重新清洗(clean表触发)
     */
    public void setIsReclean(String isReclean){
        this.isReclean = isReclean;
    }
    /**
     * 获得是否存入es
     * @return String
     */
    public String getIsEs(){
        return this.isEs;
    }

    /**
     * 设置是否存入es
     * @param isEs  是否存入es
     */
    public void setIsEs(String isEs){
        this.isEs = isEs;
    }
    /**
     * 获得版本号（乐观锁）
     * @return Integer
     */
    public Integer getVersionId(){
        return this.versionId;
    }

    /**
     * 设置版本号（乐观锁）
     * @param versionId  版本号（乐观锁）
     */
    public void setVersionId(Integer versionId){
        this.versionId = versionId;
    }
    /**
     * 获得
     * @return String
     */
    public String getDocMd5(){
        return this.docMd5;
    }

    /**
     * 设置
     * @param docMd5  
     */
    public void setDocMd5(String docMd5){
        this.docMd5 = docMd5;
    }
    /**
     * 获得重复数据条数
     * @return Integer
     */
    public Integer getDocRepeatCount(){
        return this.docRepeatCount;
    }

    /**
     * 设置重复数据条数
     * @param docRepeatCount  重复数据条数
     */
    public void setDocRepeatCount(Integer docRepeatCount){
        this.docRepeatCount = docRepeatCount;
    }
    /**
     * 获得未完成属性映射字符串
     * @return String
     */
    public String getMappingStr(){
        return this.mappingStr;
    }

    /**
     * 设置未完成属性映射字符串
     * @param mappingStr  未完成属性映射字符串
     */
    public void setMappingStr(String mappingStr){
        this.mappingStr = mappingStr;
    }
    /**
     * 获得未完成合法值定义字符串
     * @return String
     */
    public String getLegalStr(){
        return this.legalStr;
    }

    /**
     * 设置未完成合法值定义字符串
     * @param legalStr  未完成合法值定义字符串
     */
    public void setLegalStr(String legalStr){
        this.legalStr = legalStr;
    }
    /**
     * 获得删除状态
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置删除状态
     * @param deleteStatus  删除状态
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
    }
    /**
     * 获得创建者
     * @return String
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建者
     * @param creator  创建者
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    /**
     * 获得创建时间
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置创建时间
     * @param addTime  创建时间
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得更新时间
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime  更新时间
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.docId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.docId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDoc ["
		 		+ "this.docId=" + this.docId + ", "
		 		+ "this.institutionId=" + this.institutionId + ", "
		 		+ "this.researchId=" + this.researchId + ", "
		 		+ "this.docRows=" + this.docRows + ", "
		 		+ "this.docName=" + this.docName + ", "
		 		+ "this.docSourceName=" + this.docSourceName + ", "
		 		+ "this.docPath=" + this.docPath + ", "
		 		+ "this.doctypeId=" + this.doctypeId + ", "
		 		+ "this.doctypeName=" + this.doctypeName + ", "
		 		+ "this.docStatus=" + this.docStatus + ", "
		 		+ "this.docCount=" + this.docCount + ", "
		 		+ "this.isMapping=" + this.isMapping + ", "
		 		+ "this.institutionSnlVersionId=" + this.institutionSnlVersionId + ", "
		 		+ "this.isLegal=" + this.isLegal + ", "
		 		+ "this.isPersistence=" + this.isPersistence + ", "
		 		+ "this.isClean=" + this.isClean + ", "
		 		+ "this.isReclean=" + this.isReclean + ", "
		 		+ "this.isEs=" + this.isEs + ", "
		 		+ "this.versionId=" + this.versionId + ", "
		 		+ "this.docMd5=" + this.docMd5 + ", "
		 		+ "this.docRepeatCount=" + this.docRepeatCount + ", "
		 		+ "this.mappingStr=" + this.mappingStr + ", "
		 		+ "this.legalStr=" + this.legalStr + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}