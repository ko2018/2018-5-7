package com.talent.dcs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.talent.base.exception.BusinessException;
import com.talent.base.exception.ErrorCode;

/**
 * 操作CSV
 */
public class CSVUtil {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CSVUtil.class);

	/**
	 * 读取CSV文件
	 * 
	 * @param filePath
	 *            文件路径字符串
	 * @return
	 */
	public static CSVReader readCSV2CSVReader(String filePath, InputStream is) {
		CSVReader cr = null;
		try {
			is = new FileInputStream(filePath); // 建立输入流
			cr = new CSVReader(new InputStreamReader(is, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);// throw new
		}
		return cr;
	}

	/**
	 * 读取CSV文件
	 * 
	 * @param path
	 *            url路径
	 * @return
	 */
	public static CSVReader readCSV2CSVReaderByUrl(String path, InputStream is) {
		CSVReader cr = null;
		try {
			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3 * 60 * 1000);
			is = conn.getInputStream();
			cr = new CSVReader(new InputStreamReader(is, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cr;
	}

	/**
	 * 
	 * Description: 关闭
	 *
	 * @author fwp
	 * @param in
	 * @param cr
	 */
	public static void close(InputStream in, CSVReader cr) {
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (cr != null)
				cr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}