package com.talent.front.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.IniFileReader;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fuwenpan.tools.pool.FastDfsUtil;
import com.talent.front.ueditor.utils.FileTypeUtil;
import com.talent.front.ueditor.utils.FileUtil;

/**
 * springboot加载配置文件
 * 
 * @author Administrator
 *
 */
@Component
public class FastdfsFileManagerBoot {
	private static final Logger logger = LoggerFactory.getLogger(FastdfsFileManagerBoot.class);
	// private static TrackerClient trackerClient;
	// private static TrackerServer trackerServer;
	// private static StorageServer storageServer;
	// private static StorageClient storageClient;

	public static final String PROTOCOL = "http://";
	public static final String SEPARATOR = "/";
	public static final String TRACKER_NGNIX_PORT = "80";
	public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";

	public static String connectTimeout;

	public static String networkTimeout;

	public static String charset;

	public static String antiStealToken;

	public static String secretKey;

	public static String trackerHttpPort;

	public static String trackerServers;

	@Value("${fastdfs.connect_timeout_in_seconds}")
	public void setConnectTimeout(String connect_timeout_in_seconds) {
		connectTimeout = connect_timeout_in_seconds;
	}

	@Value("${fastdfs.network_timeout_in_seconds}")
	public void setNetworkTimeout(String network_timeout_in_seconds) {
		networkTimeout = network_timeout_in_seconds;
	}

	@Value("${fastdfs.charset}")
	public void setCharset(String a_charset) {
		charset = a_charset;
	}

	@Value("${fastdfs.http_anti_steal_token}")
	public void setAntiStealToken(String http_anti_steal_token) {
		antiStealToken = http_anti_steal_token;
	}

	@Value("${fastdfs.http_secret_key}")
	public void setSecretKey(String http_secret_key) {
		secretKey = http_secret_key;
	}

	@Value("${fastdfs.http_tracker_http_port}")
	public void setTrackerHttpPort(String http_tracker_http_port) {
		trackerHttpPort = http_tracker_http_port;
	}

	@Value("${fastdfs.tracker_servers}")
	public void setTrackerServers(String tracker_servers) {
		trackerServers = tracker_servers;
	}

	public static String fileUrl;

	@Value("${default.file.url}")
	public void setFileUrl(String url) {
		fileUrl = url;
	}

	private static FastdfsFileManagerBoot instance;

	public static synchronized FastdfsFileManagerBoot getInstance() {
		if (instance == null) {
			instance = new FastdfsFileManagerBoot();
			instance.init();
		}
		return instance;
	}

	public void init() {
		// Initialize Fast DFS Client configurations
		try {
			Properties props = new Properties();
			props.put("fastdfs.connect_timeout_in_seconds", connectTimeout);
			props.put("fastdfs.network_timeout_in_seconds", networkTimeout);
			props.put("fastdfs.charset", charset);
			props.put("fastdfs.http_anti_steal_token", antiStealToken);
			props.put("fastdfs.http_secret_key", secretKey);
			props.put("fastdfs.http_tracker_http_port", trackerHttpPort);
			props.put("fastdfs.tracker_servers", trackerServers);

			ClientGlobal.initByProperties(props);
			// trackerClient = new TrackerClient();
			// trackerServer = trackerClient.getConnection();
			// storageClient = new StorageClient(trackerServer, storageServer);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Fast DFS Initialize fail", e);
		}
	}

	/**
	 * 主要上传图片方法
	 * 
	 * @param name
	 *            图片名称 可传空字符串""
	 * @param content
	 *            图片bytes数组
	 * @param ext
	 *            后缀
	 * @param meta_list
	 *            附加信息，可传Null
	 * @return
	 */
	public String upload(String name, byte[] content, String ext, NameValuePair[] meta_list) {
		TrackerServer trackerServer = null;
		// StorageServer storageServer = null;
		StorageClient storageClient = null;
		// NameValuePair[] meta_list = new NameValuePair[3];
		// meta_list[0] = new NameValuePair("width", "120");
		// meta_list[1] = new NameValuePair("heigth", "120");
		// meta_list[2] = new NameValuePair("author", "Diandi");
		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, null);
			uploadResults = storageClient.upload_file(content, ext, meta_list);
		} catch (IOException e) {
			logger.error("IO Exception when uploading the file: " + name, e);
		} catch (Exception e) {
			logger.error("Non IO Exception when uploading the file: " + name, e);
		} finally {
			try {
				trackerServer.close();
			} catch (Exception e) {
				trackerServer = null;
			}
		}
		logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
		if (uploadResults == null) {
			logger.info("upload file fail, error code: " + storageClient.getErrorCode());
			return "";
		}
		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];
		String fileAbsolutePath = groupName + SEPARATOR + remoteFileName;
		logger.info("upload_file time used: " + fileAbsolutePath);
		return fileAbsolutePath;
	}

	/**
	 * 连接池提交，暂时不使用
	 * 
	 * @param name
	 * @param content
	 * @param ext
	 * @param meta_list
	 * @return
	 */
	public static String uploadByPool(String name, byte[] content, String ext, NameValuePair[] meta_list) {
		FastDfsUtil fdu = FastDfsUtil.getInstance();
		fdu.init();
		return fdu.upload(name, content, ext, meta_list);
	}

	/**
	 * 字节流方式上传
	 */
	public Map<String, Object> uploadFile(byte[] fileBuff, String fileName) {
		logger.debug("fileName:" + fileName);
		String originalFileName = FilenameUtils.getName(fileName);// 文件名
		logger.debug("originalFileName:" + originalFileName);
		String baseName = FilenameUtils.getBaseName(fileName);// 不含后缀名
		logger.debug("baseName:" + baseName);
		String fileExtName = FilenameUtils.getExtension(originalFileName);// 文件后缀名
		logger.debug("fileExtName:" + fileExtName);

		long length = fileBuff.length;// 字节
		logger.debug("length:" + length);

		logger.debug("fileBuff.length:" + fileBuff.length);

		boolean isImage = isImage(originalFileName);
		logger.debug("isImage:" + isImage);
		String mimeType = FileUtil.getMimeType(fileName);
		logger.debug("mimeType:" + mimeType);

		int width = 0;
		int height = 0;
		if (isImage) {
			int[] imageInfo = getImageInfo(fileBuff);
			if (imageInfo != null) {
				width = imageInfo[0];
				height = imageInfo[1];
			}
		}

		String fileType = FileTypeUtil.getFileTypeByStream(fileBuff);
		logger.debug("fileType:" + fileType);

		NameValuePair[] metaList = new NameValuePair[] { new NameValuePair("fileName", fileName),
				new NameValuePair("isImage", isImage + ""), new NameValuePair("mimeType", mimeType),
				new NameValuePair("width", width + ""), new NameValuePair("height", height + ""),
				new NameValuePair("author", "FastdfsUtils") };

		boolean status = false;
		String message = "文件上传失败！";
		String responseData = upload(fileName, fileBuff, fileExtName, metaList);
		Map<String, Object> uploadResult = new HashMap<String, Object>();
		if (responseData != null && !responseData.equals("")) {
			status = true;
			message = "文件上传成功！";

			uploadResult.put("isImage", isImage);
			if (isImage) {
				uploadResult.put("width", width);
				uploadResult.put("height", height);
			}
			uploadResult.put("fileExtName", fileExtName);
			uploadResult.put("link", responseData);// 文件访问链接
		}

		uploadResult.put("status", status);
		uploadResult.put("message", message);

		uploadResult.put("fileName", fileName);
		uploadResult.put("mimeType", mimeType);
		uploadResult.put("length", length);

		return uploadResult;
	}

	private static int[] getImageInfo(byte[] fileBuff) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(fileBuff);// 将byte[]作为输入流；
			BufferedImage image = ImageIO.read(in);// 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
			int width = image.getWidth();
			int height = image.getHeight();
			return new int[] { width, height };
		} catch (Exception e) {
			logger.error("FastdfsUtils.getImageInfo时发生异常:", e);
		}
		return new int[] { 0, 0 };
	}

	/**
	 * 是否是图片
	 */
	private static boolean isImage(String fileName) {
		return FileTypeUtil.isImageByExtension(fileName);
	}

	public static FileInfo getFile(String groupName, String remoteFileName) {
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			StorageClient storageClient = new StorageClient(trackerServer, null);
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		} finally {
			try {
				trackerServer.close();
			} catch (Exception e) {
				trackerServer = null;
			}
		}
		return null;
	}

	/**
	 * 删除该group下的图片
	 * 
	 * @param groupName
	 *            eg：G1
	 * @param remoteFileName
	 *            eg：M00/08/F3/tLIsSlmuP_uALDkhAAB-SxLhoJY091.jpg
	 * @throws Exception
	 */
	public static void deleteFile(String groupName, String remoteFileName) throws Exception {
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			StorageClient storageClient = new StorageClient(trackerServer, null);
			storageClient.delete_file(groupName, remoteFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				trackerServer.close();
			} catch (Exception e) {
				trackerServer = null;
			}
		}
	}

	/**
	 * 删除该地址图片（主要使用）
	 * 
	 * @param path
	 *            eg:G1/M00/08/F3/tLIsSlmuP_uALDkhAAB-SxLhoJY091.jpg
	 */
	public static void deleteFilePath(String path) {
		try {
			String groupName = path.substring(0, path.indexOf(SEPARATOR));
			String remoteFileName = path.substring(path.indexOf(SEPARATOR) + 1);
			logger.info("delete groupName: " + groupName + "    remoteFileName:" + remoteFileName);
			deleteFile(groupName, remoteFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static StorageServer[] getStoreStorages(String groupName) {
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			return trackerClient.getStoreStorages(trackerServer, groupName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				trackerServer.close();
			} catch (Exception e) {
				trackerServer = null;
			}
		}
		return null;
	}

	public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				trackerServer.close();
			} catch (Exception e) {
				trackerServer = null;
			}
		}
		return null;
	}

	/**
	 * 得到图片域名地址
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static String getUrlPre() {
		return fileUrl;
		/*
		 * if (TRACKER_NGNIX_PORT.equals("80")) { return PROTOCOL + IP + SEPARATOR; }
		 * else { return PROTOCOL + IP + ":" + TRACKER_NGNIX_PORT + SEPARATOR; }
		 */
	}

	public static String[] getImageServiceIp() {
		IniFileReader iniReader;
		try {
			String classPath = new File(FastdfsFileManagerBoot.class.getResource("/").getFile()).getCanonicalPath();
			String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
			iniReader = new IniFileReader(fdfsClientConfigFilePath);
			String[] image_server = iniReader.getValues("image_server");
			return image_server;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
