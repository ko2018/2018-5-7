package com.talent.tools.snl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.talent.base.util.CommonUtil;
import com.talent.base.util.PinYinUtil;
import com.talent.base.util.UUIDUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 标准术语excel文件解析  生成标准术语结构sql文件 标准术语对象类
 * @author wangdj
 * 2017年11月23日
 */
public class ResolveSnl4Excel
{
    private AtomicInteger atomicInteger = new AtomicInteger();
    Map<String, String> snlCodes = new HashMap();
    Map<SnlNode, List<String>> valueMap = new HashMap();
    
    public static void main(String[] args)
    {
    	/*
        if (ArrayUtil.isEmpty(args))
        {
            System.err.println("Please Usage: FilePath");
            return;
        }
        
        File f = new File(args[0]);
        if (!f.exists() || !f.canRead())
        {
            System.err.println("Invalid file path(" + args[0] + ")");
            return;
        }
        */
//    	Workbook workbook = null; InputStream is = null;
//		try  {
//			String path = "http://host90:55555/group1/M00/00/00/wKgGWVpcWwCAUgr4AAGct-J3Nv077.xlsx";
//			// group1/M00/00/00/wKgA51o41TWAU2JcAAElBwkE2Ag36.xlsx
//
//			URL url = new URL(path);
//			URLConnection conn = url.openConnection();
//			conn.setConnectTimeout(3000);
//			conn.setReadTimeout(3 * 60 * 1000);
//			is = conn.getInputStream();
//			workbook = WorkbookFactory.create(is);
//		}
//		catch (Exception e) {
//			System.err.println(e);
//		}
//		finally
//		{
//			if (workbook != null) workbook.close();
//			if (is != null) is.close();
//		}

			// wb = POIUtil.readExcel2Workbook(file.getAbsolutePath());
//    	File f = new File("http://host90:55555/group1/M00/00/00/wKgGWVpcWwCAUgr4AAGct-J3Nv077.xlsx");
        
//        String ext = args[0].substring(args[0].lastIndexOf("."));
        ResolveSnl4Excel doBusinuss = new ResolveSnl4Excel();
        Workbook workbook = null; InputStream is = null;
        try
        {
        	String path = "http://host90:55555/group1/M00/00/01/SNL(ME,QC,QN).xlsx";
			// group1/M00/00/00/wKgA51o41TWAU2JcAAElBwkE2Ag36.xlsx

			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3 * 60 * 1000);
			is = conn.getInputStream();
			workbook = WorkbookFactory.create(is);
			
//            Workbook workbook;
//            if (".xls".equals(ext))
//            {
//                workbook = new HSSFWorkbook(new FileInputStream(f));
//            }
//            else if (".xlsx".equals(ext))
//            {
//                workbook = new XSSFWorkbook(new FileInputStream(f));
//            }
//            else 
//            {
//                throw new Exception("unkown excel file");
//            }
//            
            Sheet sheetSnl = null;
            List<SnlEntity> snlEntityList = new ArrayList<SnlEntity>();
            SnlEntity snlEntity = null;
            // 三个sheet页   第一页:ME;第二页:QN;第三页:QC 
            
            for (int i = 0; i < workbook.getNumberOfSheets(); i++)
            {
                sheetSnl = workbook.getSheetAt(i);
                // 第一行表头
                if (!doBusinuss.isVerify(sheetSnl.getRow(0), i))
                {
                    throw new Exception("unkown excel file");
                }
                
                for (int j = 1; j <= sheetSnl.getLastRowNum(); j++)
                {
                    snlEntity = doBusinuss.readRow2Snl(sheetSnl.getRow(j), i);
                    if (snlEntity != null && snlEntity.isVerify())
                    {
                        snlEntityList.add(snlEntity);
                        if (snlEntity.getRootName().equals("医学检查"))
                        {
                        	System.err.println(snlEntity);
                        }
                    }
                }
                
//                System.err.println(snlEntityList.size() + ";" + sheetSnl.getSheetName() + ";" + sheetSnl.getLastRowNum());
            }
//            System.err.println(snlEntityList.size());
            List<SnlNode> snls = doBusinuss.generateSqlScript(snlEntityList);
//            doBusinuss.writeJavaClass4SnlEntity(snlEntityList);
            doBusinuss.writeJavaClass4SnlList(snls);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("unkown excel file(" + args[0] + ")");
        }
        finally
        {
        	try
        	{
        		if (workbook != null) workbook.close();
        		if (is != null) is.close();
        	}
        	catch (Exception e)
            {
                e.printStackTrace();
            }	
        }
    }
    
    private void writeJavaClass4SnlList(List<SnlNode> snls) throws Exception
    {
        if (snls != null)
        {
            List<SnlNode> nodes = snls.stream().filter(s -> s.getDictId() > 0).collect(Collectors.toList());
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("src\\main\\java\\com\\talent\\tools\\flt"));
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate("physicalExaminationRecord.flt", "utf-8");
            
            Map<String, Object> entity = new HashMap();
            entity.put("packageDomain", "com.talent.base.entity");
            entity.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            entity.put("annotation", "体检报告");
            entity.put("propertyList", buildProperty1(nodes));
            
            Writer writer  = new OutputStreamWriter(new FileOutputStream("src\\main\\java\\com\\talent\\base\\entity\\PhysicalExaminationRecord.java"),"UTF-8");  
            template.process(entity, writer); 
        }
    }
    
    private List<Property> buildProperty1(List<SnlNode> nodes)
    {
        List<Property> propertyList = new ArrayList();
        if (nodes != null)
        {
            for(SnlNode node : nodes)
            {
                Property p = new Property();
                p.setJavaType("String");
                p.setFullRelation(node.getSnlId());
                p.setNameCn(node.getNameCn());
                p.setNameUs(node.getSnlCode());
                p.setPropertyName("_" + node.getDictId());
                
                propertyList.add(p);
            }
        }
       
        return propertyList;
    }
    
    private void writeJavaClass4SnlEntity(List<SnlEntity> snlEntityList) throws Exception
    {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File("src\\main\\java\\com\\talent\\tools\\flt"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("physicalExaminationRecord.flt", "utf-8");
        
        Map<String, Object> entity = new HashMap();
        entity.put("packageDomain", "com.talent.base.entity");
        entity.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entity.put("annotation", "体检报告");
        entity.put("propertyList", buildProperty(snlEntityList));
        
        Writer writer  = new OutputStreamWriter(new FileOutputStream("src\\main\\java\\com\\talent\\base\\entity\\PhysicalExaminationRecord.java"),"UTF-8");  
        template.process(entity, writer); 
//        String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, entity);
//        String dir = "src\\main\\java\\com\\talent\\base\\entity\\PhysicalExaminationRecord.java";
//        File file = new File(dir);
//        FileUtils.writeStringToFile(file, temp, "utf-8");
    }
    
    private List<Property> buildProperty(List<SnlEntity> snlEntityList)
    {
        List<Property> propertyList = new ArrayList();
        List<String> codes = new ArrayList();
        String code = "";
        for (SnlEntity snl : snlEntityList)
        {
            Property property = new Property();
            property.setFullRelation(snl.getFullRelation());
            property.setJavaType("String");
            
            
            if (!StringUtils.isEmpty(snl.getCode()))
            {
                code = snl.getCode();
                property.setPropertyName(snl.getCode());
                property.setNameCn(snl.getName());
            }
            
            if (!codes.contains(code))
            {
                codes.add(code);
                propertyList.add(property);
            }
        }
        return propertyList;
    }
    
    private List<SnlNode> generateSqlScript(List<SnlEntity> snlEntityList)
    {
        File dir = new File("script");
        if (!dir.exists())
        {
            dir.mkdir();
        }
        File file = new File("script\\snlSql.sql");
        if (file.exists())
        {
            file.delete();
        }
        List<SnlNode> snlNodes = null;
        try (FileWriter fw = new FileWriter(file, true))
        {
            snlNodes = resolveSnlNode(snlEntityList);
            for (SnlNode snl : snlNodes)
            {
                fw.write(snl.getSqlScipt());
                fw.write(System.getProperty("line.separator"));
            }
            
            Iterator<Map.Entry<SnlNode, List<String>>> entries = valueMap.entrySet().iterator();  
            while (entries.hasNext()) 
            {  
                Map.Entry<SnlNode, List<String>> entry = (Map.Entry) entries.next();  
                fw.write(System.getProperty("line.separator"));
                fw.write(entry.getKey().getSqlScipt4Value(UUIDUtil.getUUID(32), entry.getValue().get(0), entry.getValue().get(1)));
            } 
            
            fw.flush();
            fw.close();  
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }  
        return snlNodes;
    }
    
    private List<SnlNode> resolveSnlNode(List<SnlEntity> snlEntityList)
    {
        List<SnlNode> snlNodes = new ArrayList();
        
        for (SnlEntity snl : snlEntityList)
        {
//            System.out.println(" **** " + snl + "****");
            String snlId = "";
            String parentId = "";
            
            if (!snlCodes.containsKey(snl.getRootName()))
            {
            	snlId = UUIDUtil.getUUID(32);
                snlCodes.put(snl.getRootName(), snlId);
                parentId = "-1";
                snlNodes.add(buildSnlNode(snl, "root", snlId, parentId, 1));
            }
            
            if (!StringUtils.isEmpty(snl.getParentName()))
            {
            	if (!snlCodes.containsKey(snl.getParentName()))
            	{
            		snlId = UUIDUtil.getUUID(32);
            		snlCodes.put(snl.getParentName(), snlId);
            		parentId = snlCodes.get(snl.getRootName());
            		snlNodes.add(buildSnlNode(snl, "parent", snlId, parentId, 2));
            	}
            	
            	if (!snlCodes.containsKey(snl.getNodeName()))
            	{
            		snlId = UUIDUtil.getUUID(32);
            		snlCodes.put(snl.getNodeName(), snlId);
            		parentId = snlCodes.get(snl.getParentName());
            		snlNodes.add(buildSnlNode(snl, "node", snlId, parentId, 3));
            	}
                
                if (!snlCodes.containsKey(snl.getName()))
                {
                	snlId = UUIDUtil.getUUID(32);
                    snlCodes.put(snl.getName(), snlId);
                    parentId = snlCodes.get(snl.getNodeName());
                    SnlNode snlNode = buildSnlNode(snl, "leaf", snlId, parentId, snl.getParentName().equals(snl.getNodeName()) ? 3 : 4);
                    snlNodes.add(snlNode);
                    
                    if ("文本资料".equals(snl.getDataType()))
                    {
                    	valueMap.put(snlNode, Arrays.asList("b07f9dbff8184429ab9ba438e6ecb723", "文本型"));
                    }
                    else if ("计量资料".equals(snl.getDataType()))
                    {
                    	valueMap.put(snlNode, Arrays.asList("e77b1c93f5f44a0b832fd68a005fc3a0", "数值型"));
                    }
                    else if ("分类资料".equals(snl.getDataType()))
                    {
                    	valueMap.put(snlNode, Arrays.asList("d7bb54baedf643359791f04caa9ca132", "枚举型"));
                    }
                    else
                    {
                    	valueMap.put(snlNode, Arrays.asList(null, null));
                    }
                }
            }
            else
            {
            	if (!snlCodes.containsKey(snl.getName()))
                {
                	snlId = UUIDUtil.getUUID(32);
                    snlCodes.put(snl.getName(), snlId);
                    parentId = snlCodes.get(snl.getRootName());
                    SnlNode snlNode = buildSnlNode(snl, "leaf", snlId, parentId, 2);
                    snlNodes.add(snlNode);
                    
                    if ("文本资料".equals(snl.getDataType()))
                    {
                    	valueMap.put(snlNode, Arrays.asList("b07f9dbff8184429ab9ba438e6ecb723", "文本型"));
                    }
                    else if ("计量资料".equals(snl.getDataType()))
                    {
                    	valueMap.put(snlNode, Arrays.asList("e77b1c93f5f44a0b832fd68a005fc3a0", "数值型"));
                    }
                    else if ("分类资料".equals(snl.getDataType()))
                    {
                    	valueMap.put(snlNode, Arrays.asList("d7bb54baedf643359791f04caa9ca132", "枚举型"));
                    }
                    else
                    {
                    	valueMap.put(snlNode, Arrays.asList(null, null));
                    }
                }
            }
        }
        
        return snlNodes;
    }
    
    private SnlNode buildSnlNode(SnlEntity snlEntity, String type, String snlId, String parentId, int depth)
    {
        SnlNode snlNode = new SnlNode();
        snlNode.setSnlId(snlId);
        snlNode.setSnlType(1);
        snlNode.setSnlTpyeName("MedDRA");
        snlNode.setCreator("system");
        snlNode.setUpdateUser("system");
        String date = CommonUtil.getFullDateString(new Date());
        snlNode.setAddTime(date);
        snlNode.setUpdateTime(date);
        
        if ("root".equalsIgnoreCase(type))
        {
            snlNode.setNameCn(snlEntity.getRootName());
            snlNode.setFullNameCn(PinYinUtil.cn2Spell(snlEntity.getRootName()));
            snlNode.setShortNameCn(PinYinUtil.cn2FirstSpell(snlEntity.getRootName()));
            snlNode.setParentCode(parentId);
            snlNode.setDepth(depth);
            snlNode.setIsLeaf(0);
        }
        else if ("parent".equalsIgnoreCase(type))
        {
        	snlNode.setNameCn(snlEntity.getParentName());
            snlNode.setFullNameCn(PinYinUtil.cn2Spell(snlEntity.getParentName()));
            snlNode.setShortNameCn(PinYinUtil.cn2FirstSpell(snlEntity.getParentName()));
            snlNode.setParentCode(parentId);
            snlNode.setDepth(depth);
            snlNode.setIsLeaf(0);
        }
        else if ("node".equalsIgnoreCase(type))
        {
        	snlNode.setNameCn(snlEntity.getNodeName());
            snlNode.setFullNameCn(PinYinUtil.cn2Spell(snlEntity.getNodeName()));
            snlNode.setShortNameCn(PinYinUtil.cn2FirstSpell(snlEntity.getNodeName()));
            snlNode.setParentCode(parentId);
            snlNode.setDepth(depth);
            snlNode.setIsLeaf(0);
        }
        else if ("leaf".equalsIgnoreCase(type))
        {
        	snlNode.setSnlCode(snlEntity.getCode());
            snlNode.setNameCn(snlEntity.getName());
            snlNode.setFullNameCn(PinYinUtil.cn2Spell(snlEntity.getName()));
            snlNode.setShortNameCn(PinYinUtil.cn2FirstSpell(snlEntity.getName()));
            snlNode.setParentCode(parentId);
            snlNode.setDepth(depth);
            snlNode.setIsLeaf(1);
            snlNode.setDictId(atomicInteger.incrementAndGet());
        }
        
        return snlNode;
    }
    
    public boolean isVerify(Row row, int num)
    {
        String sample1 = "术语名称|分类|一级分类|二级分类|三级分类|数据类型|术语编码|";
        String sample2 = "术语名称|分类|一级分类|数据类型|术语编码|";
        StringBuffer stb = new StringBuffer();
        if (row != null)
        {
            Cell cell;
            for (int i = 0; i < 7; i++)
            {
                cell = row.getCell(i);
                if (cell != null && cell.getStringCellValue() != null)
                {
                    stb.append(cell.getStringCellValue().trim()).append("|");
                }
            }
        }
        
        if (num == 0)
        {
        	if (sample1.equalsIgnoreCase(stb.toString()))
        	{
        		return true;
        	}
        }
        else
        {
        	if (sample2.equalsIgnoreCase(stb.toString()))
        	{
        		return true;
        	}
        }
        
        return false;
    }
    
    /**
     * excel结构  
     * @param row
     * @return
     */
    private SnlEntity readRow2Snl(Row row, int num)
    {
        SnlEntity snlEntity = null;
        if (row != null)
        {
        	if (num == 0)
        	{
        		snlEntity = new SnlEntity();
        		Cell cell = row.getCell(0);
        		String value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setName(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(1);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setFullRelation(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(2);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setRootName(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(3);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setParentName(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(4);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setNodeName(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(5);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setDataType(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(6);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setCode(StringUtils.isEmpty(value) == true ? null : value);
        	}
        	else
        	{
        		snlEntity = new SnlEntity();
        		Cell cell = row.getCell(0);
        		String value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setName(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(1);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setFullRelation(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(2);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setRootName(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(3);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setDataType(StringUtils.isEmpty(value) == true ? null : value);
        		
        		cell = row.getCell(4);
        		value = (cell == null) ? null : cell.getStringCellValue();
        		snlEntity.setCode(StringUtils.isEmpty(value) == true ? null : value);
        	}
            
        }
        
        return snlEntity;
    }
}
