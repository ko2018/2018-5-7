package com.talent.front.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.StaticsWeienBasicDao;
import com.talent.front.dto.StaticsWeienBasicDto;
import com.talent.front.entity.StaticsWeienBasic;
import com.talent.front.entity.StaticsWeienSearchCondition;
import com.talent.front.entity.StaticsweienSearchResultMain;
import com.talent.front.entity.StaticsweienSearchResultSet;
import com.talent.front.entity.StaticsweienSearchResultSub;
import com.talent.front.service.StaticsWeienBasicService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-12 <br/>
 * 描述：服务实现类
 */
@Service
public class StaticsWeienBasicServiceImpl extends BaseServiceImpl<StaticsWeienBasic> implements StaticsWeienBasicService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsWeienBasicServiceImpl.class);

	@Autowired
	private StaticsWeienBasicDao staticsWeienBasicDao;

	@Override
	public BaseDao<StaticsWeienBasic> getBaseDao() {
		return this.staticsWeienBasicDao;
	}
	
	@CacheSpeObject(value = "staticsWeienBasic", key = "#id")
	@Override
	public StaticsWeienBasicDto selectDtoByPrimaryKey(String id) {
		return this.staticsWeienBasicDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsWeienBasic", key = "#staticsWeienBasic.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsWeienBasicDto updateDtoByPrimaryKeySelective(StaticsWeienBasic staticsWeienBasic) {
		this.staticsWeienBasicDao.updateByPrimaryKeySelective(staticsWeienBasic);
		return this.staticsWeienBasicDao.selectDtoByPrimaryKey(staticsWeienBasic.getId());
	}
	
	public List<StaticsweienSearchResultSet>  scanweienResultSet(StaticsWeienSearchCondition condition){
		System.out.println(condition);
		List<StaticsweienSearchResultSet> resultList =  this.staticsWeienBasicDao.scanWeienList(condition);
		/*List<StaticsweienSearchResultSet> staticsSearchResultSet = new List<StaticsweienSearchResultSet>;
		staticsSearchResultSet.setParmList(resultList);*/
		return resultList;
	}
	
	@Override
	public List<StaticsweienSearchResultSet>  countDis(StaticsWeienSearchCondition condition){
		List<StaticsweienSearchResultSet> countList = new ArrayList<StaticsweienSearchResultSet>();
		StaticsweienSearchResultSet stat=new StaticsweienSearchResultSet();
		
		if(condition!=null) {
			Map<String,Object> parmMap=new HashMap<String,Object>();
			
			if(StringUtils.isNotBlank(condition.getDiseaseIds())) {
				//疾病
				String[] disIds=condition.getDiseaseIds().split(",");
				parmMap.put("disIds", disIds);
				//人群
				if(condition.getCrowdIds()!=null) {
					parmMap.put("crowdIds", condition.getCrowdIds());
				}
				//机构
				if(StringUtils.isNotBlank(condition.getGroupPro())) {
					String[] groupPro=condition.getGroupPro().split(",");
					parmMap.put("groupPro", groupPro);
				}
				//性别
				if(condition.getSex()!=null) {
					parmMap.put("sex", condition.getSex());
				}
				//年龄层
				if(StringUtils.isNotBlank(condition.getAgeLayer())) {
					String[] ageLayers=condition.getAgeLayer().split(",");
					parmMap.put("ageLayers", ageLayers);
				}
				
				if(StringUtils.isNotBlank(condition.getBeginDate())) {
					parmMap.put("beginDate", condition.getBeginDate());
				}
				if(StringUtils.isNotBlank(condition.getEndDate())) {
					parmMap.put("endDate", condition.getEndDate());
				}
				//每个疾病总的人数
				List<StaticsweienSearchResultMain> mainList=staticsWeienBasicDao.countDis(parmMap);
				
				String diseaseName = condition.getDiseaseNames();
				String[] diseaseNames=diseaseName.split(",");
				
				List<StaticsweienSearchResultMain> mlist=new ArrayList<StaticsweienSearchResultMain>();
				//选了几个疾病就构造几个对象，查到了人数就重新赋值
				for (String disName : diseaseNames) {
					StaticsweienSearchResultMain main=new StaticsweienSearchResultMain();
					main.setDiseaseName(disName);
					main.setSickCount(0);
					mlist.add(main);
				}
				for (StaticsweienSearchResultMain allStatMain : mlist) {
					for (StaticsweienSearchResultMain statMain : mainList) {
						if(allStatMain.getDiseaseName().equals(statMain.getDiseaseName())) {
							allStatMain.setSickCount(statMain.getSickCount());
						}
					}
				}
				stat.setMainList(mlist);
				
				//选取疾病个数
				if(disIds.length==2) {
					String[] disNum= {"2"};
					parmMap.put("disNum", disNum);
					List<StaticsweienSearchResultSub> subList=staticsWeienBasicDao.getDisPersonList(parmMap);
					
					StaticsweienSearchResultSub venn=new StaticsweienSearchResultSub();
					if(subList!=null && subList.size()>0) {
						//同时患两种疾病的名称
						venn.setDiseaseName(subList.get(0).getDiseaseName());
						//同时患有两种疾病的人数
						venn.setSickCount(subList.size());
						subList=new ArrayList<StaticsweienSearchResultSub>();
						subList.add(venn);
					}else {//没有同时患病的人数
						//同时患两种疾病的名称
						venn.setDiseaseName(condition.getDiseaseNames());
						//同时患有两种疾病的人数
						venn.setSickCount(0);
						subList=new ArrayList<StaticsweienSearchResultSub>();
						subList.add(venn);
					}
					stat.setSubList(subList);
					
				}else if(disIds.length==3) {
					String[] disNum= {"2","3"};
					parmMap.put("disNum", disNum);
					
					List<StaticsweienSearchResultSub> subList=staticsWeienBasicDao.getDisPersonList(parmMap);
					
					List<StaticsweienSearchResultSub> newSubList=constractList(condition);

					stat.setSubList(buildList(subList,newSubList));
				}else if(disIds.length==4) {
					String[] disNum= {"2","3","4"};
					parmMap.put("disNum", disNum);
					
					List<StaticsweienSearchResultSub> subList=staticsWeienBasicDao.getDisPersonList(parmMap);
					
					List<StaticsweienSearchResultSub> newSubList=constractList4(condition);
					
					stat.setSubList(buildList(subList,newSubList));
				}else if(disIds.length==5) {
					String[] disNum= {"2","3","4","5"};
					parmMap.put("disNum", disNum);
					
					List<StaticsweienSearchResultSub> subList=staticsWeienBasicDao.getDisPersonList(parmMap);
					
					List<StaticsweienSearchResultSub> newSubList=constractList5(condition);
					
					stat.setSubList(buildList(subList,newSubList));
				}
			}
		}
		countList.add(stat);	
		return countList;
	}
	
	/**
	 * 
	 * @param subList 数据库统计出来的疾病人数
	 * @param newSubList 韦恩图所有的情况
	 * @return
	 */
	private List<StaticsweienSearchResultSub> buildList(List<StaticsweienSearchResultSub> subList,List<StaticsweienSearchResultSub> newSubList) {
		
		for (StaticsweienSearchResultSub staticsweienSearchResultSub : newSubList) {
			String[] names=staticsweienSearchResultSub.getDiseaseName().split(",");
			Set<String> set1=new HashSet<String>();
			for (String disName : names) {
				set1.add(disName);
			}
			int i=1;
			for (StaticsweienSearchResultSub staticsweienSearchResult : subList) {
				String[] namess=staticsweienSearchResult.getDiseaseName().split(",");
				Set<String> set2=new HashSet<String>();
				for (String disName : namess) {
					set2.add(disName);
				}
				
				if(isSetEqual(set1, set2)) {
					staticsweienSearchResultSub.setSickCount(i++);
				}
			}
		}
		return newSubList;
	}
	
	//选三种疾病的情况
	private List<StaticsweienSearchResultSub> constractList(StaticsWeienSearchCondition condition){
		List<StaticsweienSearchResultSub> newSubList=new ArrayList<StaticsweienSearchResultSub>();
		
		String diseaseName = condition.getDiseaseNames();
		String[] diseaseNames=diseaseName.split(",");
		
		String name1=diseaseNames[0]+","+diseaseNames[1];
		StaticsweienSearchResultSub venn1=new StaticsweienSearchResultSub();
		venn1.setDiseaseName(name1);
		venn1.setSickCount(0);
		newSubList.add(venn1);
		
		String name2=diseaseNames[1]+","+diseaseNames[2];
		StaticsweienSearchResultSub venn2=new StaticsweienSearchResultSub();
		venn2.setDiseaseName(name2);
		venn2.setSickCount(0);
		newSubList.add(venn2);
		
		String name3=diseaseNames[0]+","+diseaseNames[2];
		StaticsweienSearchResultSub venn3=new StaticsweienSearchResultSub();
		venn3.setDiseaseName(name3);
		venn3.setSickCount(0);
		newSubList.add(venn3);
		
		String name4=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[2];
		StaticsweienSearchResultSub venn4=new StaticsweienSearchResultSub();
		venn4.setDiseaseName(name4);
		venn4.setSickCount(0);
		newSubList.add(venn4);
		
		return newSubList;
	}
	
	//选四种疾病的情况
	private List<StaticsweienSearchResultSub> constractList4(StaticsWeienSearchCondition condition){
		//直接加上三种病的情况
		List<StaticsweienSearchResultSub> newSubList=constractList( condition);
		
		String diseaseName = condition.getDiseaseNames();
		String[] diseaseNames=diseaseName.split(",");
		
		String name5=diseaseNames[0]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn5=new StaticsweienSearchResultSub();
		venn5.setDiseaseName(name5);
		venn5.setSickCount(0);
		newSubList.add(venn5);
		
		String name6=diseaseNames[1]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn6=new StaticsweienSearchResultSub();
		venn6.setDiseaseName(name6);
		venn6.setSickCount(0);
		newSubList.add(venn6);
		
		String name7=diseaseNames[2]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn7=new StaticsweienSearchResultSub();
		venn7.setDiseaseName(name7);
		venn7.setSickCount(0);
		newSubList.add(venn7);
		
		String name9=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn9=new StaticsweienSearchResultSub();
		venn9.setDiseaseName(name9);
		venn9.setSickCount(0);
		newSubList.add(venn9);
		
		String name10=diseaseNames[0]+","+diseaseNames[2]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn10=new StaticsweienSearchResultSub();
		venn10.setDiseaseName(name10);
		venn9.setSickCount(0);
		newSubList.add(venn10);
		
		String name11=diseaseNames[1]+","+diseaseNames[2]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn11=new StaticsweienSearchResultSub();
		venn11.setDiseaseName(name11);
		venn11.setSickCount(0);
		newSubList.add(venn11);
		
		String name4=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[2]+","+diseaseNames[3];
		StaticsweienSearchResultSub venn4=new StaticsweienSearchResultSub();
		venn4.setDiseaseName(name4);
		venn4.setSickCount(0);
		newSubList.add(venn4);
		
		return newSubList;
	}
		
	//选五种疾病的情况
	private List<StaticsweienSearchResultSub> constractList5(StaticsWeienSearchCondition condition){
		//直接加上四种病的情况
		List<StaticsweienSearchResultSub> newSubList=constractList4( condition);;
		
		String diseaseName = condition.getDiseaseNames();
		String[] diseaseNames=diseaseName.split(",");
		
		String name1=diseaseNames[0]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn1=new StaticsweienSearchResultSub();
		venn1.setDiseaseName(name1);
		venn1.setSickCount(0);
		newSubList.add(venn1);
		
		String name2=diseaseNames[1]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn2=new StaticsweienSearchResultSub();
		venn2.setDiseaseName(name2);
		venn2.setSickCount(0);
		newSubList.add(venn2);
		
		String name3=diseaseNames[2]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn3=new StaticsweienSearchResultSub();
		venn3.setDiseaseName(name3);
		venn3.setSickCount(0);
		newSubList.add(venn3);
		
		String name4=diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn4=new StaticsweienSearchResultSub();
		venn4.setDiseaseName(name4);
		venn4.setSickCount(0);
		newSubList.add(venn4);
		
		String name9=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn9=new StaticsweienSearchResultSub();
		venn9.setDiseaseName(name9);
		venn9.setSickCount(0);
		newSubList.add(venn9);
		
		String name10=diseaseNames[0]+","+diseaseNames[2]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn10=new StaticsweienSearchResultSub();
		venn10.setDiseaseName(name10);
		venn10.setSickCount(0);
		newSubList.add(venn10);
		
		String name11=diseaseNames[0]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn11=new StaticsweienSearchResultSub();
		venn11.setDiseaseName(name11);
		venn11.setSickCount(0);
		newSubList.add(venn11);
		
		String name12=diseaseNames[1]+","+diseaseNames[2]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn12=new StaticsweienSearchResultSub();
		venn12.setDiseaseName(name12);
		venn12.setSickCount(0);
		newSubList.add(venn12);
		
		String name13=diseaseNames[1]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn13=new StaticsweienSearchResultSub();
		venn13.setDiseaseName(name13);
		venn13.setSickCount(0);
		newSubList.add(venn13);
		
		String name14=diseaseNames[2]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn14=new StaticsweienSearchResultSub();
		venn14.setDiseaseName(name14);
		venn14.setSickCount(0);
		newSubList.add(venn14);
		
		String name15=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[2]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn15=new StaticsweienSearchResultSub();
		venn15.setDiseaseName(name15);
		venn15.setSickCount(0);
		newSubList.add(venn15);
		
		String name16=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn16=new StaticsweienSearchResultSub();
		venn16.setDiseaseName(name16);
		venn16.setSickCount(0);
		newSubList.add(venn16);
		
		String name17=diseaseNames[0]+","+diseaseNames[2]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn17=new StaticsweienSearchResultSub();
		venn17.setDiseaseName(name17);
		venn17.setSickCount(0);
		newSubList.add(venn17);
		
		String name18=diseaseNames[1]+","+diseaseNames[2]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn18=new StaticsweienSearchResultSub();
		venn18.setDiseaseName(name18);
		venn18.setSickCount(0);
		newSubList.add(venn18);

		String name19=diseaseNames[0]+","+diseaseNames[1]+","+diseaseNames[2]+","+diseaseNames[3]+","+diseaseNames[4];
		StaticsweienSearchResultSub venn19=new StaticsweienSearchResultSub();
		venn19.setDiseaseName(name19);
		venn19.setSickCount(0);
		newSubList.add(venn19);
		
		return newSubList;
	}
	
	@Override
	public PageResult<StaticsWeienBasicDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsWeienBasicDao.pageCountDto(pageObject);
		PageResult<StaticsWeienBasicDto> pageResult = new PageResult<StaticsWeienBasicDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsWeienBasicDto> staticsWeienBasicDtoList = ((StaticsWeienBasicServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(staticsWeienBasicDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsWeienBasicDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsWeienBasicDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsWeienBasicDto> pageResult = new PageResult<StaticsWeienBasicDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsWeienBasicDto> staticsWeienBasicDtoList = this.staticsWeienBasicDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsWeienBasicDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsWeienBasicDto> pageListCache(PageObject pageObject) {
		return this.staticsWeienBasicDao.pageListDto(pageObject);
	}
	
	public static boolean  isSetEqual(Set set1, Set set2){
		
		if(set1 == null || set2 == null || set1.size() != set2.size() || set1.size() == 0 || set2.size() == 0){
			return false;
		}
		Iterator ite1 = set1.iterator();
		Iterator ite2 = set2.iterator();

		boolean isFullEqual = true;
		while(ite2.hasNext()){
			if(!set1.contains(ite2.next())){
				isFullEqual = false;
			}
		}
		
		return isFullEqual;
	}

}