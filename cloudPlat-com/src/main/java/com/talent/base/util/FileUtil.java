package com.talent.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {
	/**
	 * @功能 下载临时文件
	 * @param filePath
	 *            文件将要保存的目录
	 * @param method
	 *            请求方法，包括POST和GET
	 * @param url
	 *            请求的路径
	 * @return
	 */
	public static File saveUrlAs(String url, String filePath, String method) {
		// System.out.println("fileName---->"+filePath);
		// 创建不同的文件夹目录
		File file = new File(filePath);
		// 判断文件夹是否存在
		if (!file.exists()) {
			// 如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		try {
			// 建立链接
			URL httpUrl = new URL(url);
			conn = (HttpURLConnection) httpUrl.openConnection();
			// 以Post方式提交表单，默认get方式
			//conn.setRequestMethod(method);
			//conn.setDoInput(true);
			//conn.setDoOutput(true);
			// post方式不能使用缓存
			conn.setUseCaches(false);
			// 连接指定的资源
			conn.connect();
			// 获取网络输入流
			inputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			// 判断文件的保存路径后面是否以/结尾
			if (!filePath.endsWith("/")) {

				filePath += "/";

			}
			// 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
			String newStr = filePath + "123.xlsx";
			File newFile = new File(newStr);
			if (newFile.exists())
				newFile.delete();
			fileOut = new FileOutputStream(newStr);
			BufferedOutputStream bos = new BufferedOutputStream(fileOut);

			byte[] buf = new byte[1024];
			int length = bis.read(buf);
			// 保存文件
			while (length != -1) {
				bos.write(buf, 0, length);
				length = bis.read(buf);
			}
			bis.close();
			bos.flush();
			bos.close();
			
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("抛出异常！！");
		}

		return file;

	}
//	
//	public static void main(String[] args) {
//		saveUrlAs("http://peer1:55555/group1/M00/00/00/wKgA5lo3p7mADb89AAEvZt6V2Xg65.xlsx","/tmp","GET");
//	}
	
	/** 
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");  
        
        conn.setRequestProperty("Accept",
                "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-cn");
        conn.setRequestProperty("UA-CPU", "x86");
        conn.setRequestProperty("Accept-Encoding", "gzip");//为什么没有deflate呢
        conn.setRequestProperty("Content-type", "text/html");
        conn.setRequestProperty("Connection", "close"); //keep-Alive，有什么用呢，你不是在访问网站，你是在采集。嘿嘿。减轻别人的压力，也是减轻自己。

        conn.setUseCaches(false);//不要用cache，用了也没有什么用，因为我们不会经常对一个链接频繁访问。（针对程序）
        conn.setConnectTimeout(6 * 1000);
        conn.setReadTimeout(6*1000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
  
        System.out.println("info:"+url+" download success");   
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
  
    public static void main(String[] args) {  
        try{  
            downLoadFromUrl("http://peer1:55555/group1/M00/00/00/wKgA51o40LKAIm6IAAAjBcVJ9yI89.xlsx",  
                    "aweeeeeeeeee.xlsx","/tmp");  
        }catch (Exception e) {  
            e.printStackTrace();
        }  
    }
}
