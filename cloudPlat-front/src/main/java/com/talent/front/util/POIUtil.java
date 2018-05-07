package com.talent.front.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.talent.base.exception.BusinessException;
import com.talent.base.exception.ErrorCode;

/**
 * 操作Excel表格的功能类
 */
public class POIUtil {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(POIUtil.class);

    /**
     * 读取excel文件
     * 
     * @param filePath 文件路径字符串
     * @return
     */
    public static Workbook readExcel2Workbook(String filePath) {
        Workbook wb = null;
        InputStream is = null;
        boolean isE2007 = false; // 判断是否是excel2007格式
        if (filePath.endsWith("xlsx")) {
            isE2007 = true;
        } else if (filePath.endsWith("xls")) {
            isE2007 = false;
        } else {
            throw new BusinessException(ErrorCode.UNKNOW_ERROR);// "文件类型错误！");
        }
        try {
            is = new FileInputStream(filePath); // 建立输入流
            if (isE2007) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new HSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.UNKNOW_ERROR);// throw new
                                                                // BaseException("读取文件失败！");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    /**
     * 读取excel文件
     * 
     * @param filePath 文件路径字符串
     * @return
     * @throws InvalidFormatException
     * @throws EncryptedDocumentException
     */
    public static Workbook readExcel2WorkbookByUrl(String url)
            throws EncryptedDocumentException, InvalidFormatException {
        Workbook wb = null;
        InputStream is = null;
        boolean isE2007 = false; // 判断是否是excel2007格式
        if (url.endsWith("xlsx")) {
            isE2007 = true;
        } else if (url.endsWith("xls")) {
            isE2007 = false;
        } else {
            throw new BusinessException(ErrorCode.UNKNOW_ERROR);// "文件类型错误！");
        }
        try {
            // URL url = new URL(encodedUri);
            // URLConnection conn = url.openConnection();
            // conn.setConnectTimeout(3000);
            // conn.setReadTimeout(3 * 60 * 1000);
            // is = conn.getInputStream();
            // wb = WorkbookFactory.create(is);
            URL tmp = new URL(url);
            URLConnection conn = tmp.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3 * 60 * 1000);
            is = conn.getInputStream(); // 建立输入流
            // WorkbookFactory.create(is);
            // if (isE2007) {
            // wb = new XSSFWorkbook(is);
            // } else {
            // wb = new HSSFWorkbook(is);
            // }
            wb = WorkbookFactory.create(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.UNKNOW_ERROR);// throw new
                                                                // BaseException("读取文件失败！");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    /**
     * 读取Excel表格表头的内容
     * 
     * @param InputStream
     * @return String 表头内容的数组
     */
    public static String[] readExcelTitle(String filePath, int sheetIndex) {
        String[] title = null;
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        try {
            wb = readExcel2Workbook(filePath);
            sheet = wb.getSheetAt(sheetIndex); // 获得第sheetIndex个表单

            row = sheet.getRow(0);
            // 标题总列数
            int colNum = row.getPhysicalNumberOfCells();
            title = new String[colNum];
            for (int i = 0; i < colNum; i++) {
                // title[i] = getStringCellValue(row.getCell((short) i));
                title[i] = getCellFormatValue(row.getCell(i)).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.UNKNOW_ERROR);// throw new
                                                                // BaseException("读取文件失败！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return title;
    }

    /**
     * 写入Excel表格表头的内容
     * 
     * @param wb
     * @param excelHead
     * @param style
     * @return
     */
    public static Workbook writeExcelTitle(Workbook wb, String[] excelHead) {
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.createRow(0);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// setAlignment(CellStyle.ALIGN_CENTER);
                                                       // // 创建一个居中格式
        POIUtil.writeExcelTitle(row, excelHead, style);
        return wb;
    }

    public static Row writeExcelTitle(Row row, String[] excelHead, CellStyle style) {
        Cell cell = null;
        // excel头
        for (int i = 0; i < excelHead.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead[i]);
            cell.setCellStyle(style);
        }
        return row;
    }

    /**
     * 读取Excel数据内容
     * 
     * @param filePath
     * @param sheetIndex
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, List<String>> readExcelContent(String filePath, int sheetIndex) {
        Map<Integer, List<String>> content = new HashMap<Integer, List<String>>();
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        try {
            wb = readExcel2Workbook(filePath);
            sheet = wb.getSheetAt(sheetIndex); // 获得第sheetIndex个表单
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                List<String> contentList = new ArrayList<String>();
                int j = 0;
                while (j < colNum) {
                    contentList.add(j, getCellFormatValue(row.getCell(j)).trim());
                    j++;
                }
                content.put(i, contentList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.UNKNOW_ERROR);// throw new
                                                                // BaseException("读取文件失败！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    @SuppressWarnings("unused")
    public static void test() {
        logger.info("test!");
        try (Workbook wb = new XSSFWorkbook();
                FileOutputStream fileOut = new FileOutputStream("F:\\test\\file\\workbook.xlsx");) {
            Sheet sheet1 = wb.createSheet("new sheet");
            Sheet sheet2 = wb.createSheet("second sheet");
            String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns
                                                                                       // "
                                                                                       // O'Brien's
                                                                                       // sales
                                                                                       // "
            Sheet sheet3 = wb.createSheet(safeName);

            CreationHelper createHelper = wb.getCreationHelper();
            // Create a row and put some cells in it. Rows are 0 based.
            Row row = sheet1.createRow((short) 0);
            // Create a cell and put a value in it.
            Cell cell = row.createCell(0);
            cell.setCellValue(1);

            // Or do it on one line.
            row.createCell(1).setCellValue(1.2);
            row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
            row.createCell(3).setCellValue(true);

            Row row2 = sheet1.createRow((short) 2);
            row2.createCell(0).setCellValue(1.1);
            row2.createCell(1).setCellValue(new Date());
            row2.createCell(2).setCellValue(Calendar.getInstance());
            row2.createCell(3).setCellValue("a string");
            row2.createCell(4).setCellValue(true);
            row2.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Cell类型设置数据
     * 
     * @param cell
     * @return
     */
    public static String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                // 得到Boolean对象的方法
                cellvalue = cell.getBooleanCellValue() ? "1" : "0";
                break;
            // 如果当前Cell的Type为NUMERIC
            case Cell.CELL_TYPE_NUMERIC:
                // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式

                    // 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    // cellvalue = cell.getDateCellValue().toLocaleString();

                    // 方法2：这样子的data格式是带时分秒的：2011-10-12 00:00:00
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellvalue = sdf.format(date);
                } else { // 如果是纯数字
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    // 而且无论数字是否小数，使用cell.getNumbericCellValue()
                    // 去获取值的时候，会得到一个double，而且当长度大一点的时候会变成科学计数法形式
                    // 避免科学计数法
                    DecimalFormat df = new DecimalFormat("0");
                    cellvalue = df.format(cell.getNumericCellValue());

                    // 对整型数据处理
                    long longVal = Math.round(cell.getNumericCellValue());
                    if (cellvalue.equals(longVal + ".0")) {
                        cellvalue = String.valueOf(longVal);
                    }
                }
                break;
            // 公式
            case Cell.CELL_TYPE_FORMULA:
                cellvalue = cell.getCellFormula().toString();
                break;

            // 如果当前Cell的Type为STRING
            case Cell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = "";
                break;
            }
        }
        return cellvalue;
    }

    /**
     * 得到Cell里的字符串
     * 
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String str = "";
        // if (cell != null) {
        // cell.setCellType(CellType.STRING);
        // String value = cell.getStringCellValue();
        // if (value != null) {
        // str = value.trim();
        // }
        // }
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                str = cell.getBooleanCellValue() ? "1" : "0";
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    str = sdf.format(date);
                } else { // 如果是纯数字
                    str = String.valueOf(cell.getNumericCellValue());
                    DecimalFormat df = new DecimalFormat("0");
                    str = df.format(cell.getNumericCellValue());

                    // 对整型数据处理
                    long longVal = Math.round(cell.getNumericCellValue());
                    if (str.equals(longVal + ".0")) {
                        str = String.valueOf(longVal);
                    }
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                str = cell.getCellFormula().toString();
                break;

            case Cell.CELL_TYPE_STRING:
                str = cell.getRichStringCellValue().getString();
                break;
            default:
                str = "";
                break;
            }
        }
        return str;
    }

    /**
     * 
     * Description: 关闭
     *
     * @author fwp
     * @param in
     * @param wb
     */
    public static void close(InputStream in, Workbook wb) {
        try {
            if (in != null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (wb != null)
                wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // test();
        // String filePath = "d:\\wKgA5lo3p7mADb89AAEvZt6V2Xg65.xlsx";
        // String[] titles = readExcelTitle(filePath, 0);
        // Map<Integer, List<String>> content = readExcelContent(filePath, 0);
        // logger.info(JSON.toJSONString(titles));
        // logger.info(JSON.toJSONString(content));

        System.out.println(CellType.STRING.name());
        System.out.println(CellType.STRING.getCode());
        System.out.println(CellType.STRING.toString());
    }

}