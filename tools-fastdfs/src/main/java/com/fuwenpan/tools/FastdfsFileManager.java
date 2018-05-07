package com.fuwenpan.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

import com.fuwenpan.tools.pool.FastDfsUtil;

public class FastdfsFileManager {
	private static final Logger logger = LoggerFactory
			.getLogger(FastdfsFileManager.class);
	// private static TrackerClient trackerClient;
	// private static TrackerServer trackerServer;
	// private static StorageServer storageServer;
	// private static StorageClient storageClient;

	public static final String PROTOCOL = "http://";
	public static final String SEPARATOR = "/";
	public static final String TRACKER_NGNIX_PORT = "80";
	public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";

	static {
		// Initialize Fast DFS Client configurations
		try {
//			String classPath = new File(FastdfsFileManager.class.getResource(
//					"/").getFile()).getCanonicalPath();
			
			String classPath=new File(FastdfsFileManager.class.getResource("/").getPath()).getAbsolutePath();
			
			String fdfsClientConfigFilePath = classPath + File.separator
					+ CLIENT_CONFIG_FILE;
			logger.info("Fast DFS configuration file path:"
					+ fdfsClientConfigFilePath);
			ClientGlobal.init(fdfsClientConfigFilePath);
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
	public static String upload(String name, byte[] content, String ext,
			NameValuePair[] meta_list) {
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
		logger.info("upload_file time used: "
				+ (System.currentTimeMillis() - startTime) + " ms");
		if (uploadResults == null) {
			logger.info("upload file fail, error code: "
					+ storageClient.getErrorCode());
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
	public static String uploadByPool(String name, byte[] content, String ext,
			NameValuePair[] meta_list) {
		FastDfsUtil fdu = FastDfsUtil.getInstance();
		fdu.init();
		return fdu.upload(name, content, ext, meta_list);
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
	public static void deleteFile(String groupName, String remoteFileName)
			throws Exception {
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
			logger.info("delete groupName: " + groupName
					+ "    remoteFileName:" + remoteFileName);
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

	public static ServerInfo[] getFetchStorages(String groupName,
			String remoteFileName) throws IOException {
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			return trackerClient.getFetchStorages(trackerServer, groupName,
					remoteFileName);
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
		java.util.Random random = new java.util.Random();// 定义随机类
		int result = random.nextInt(getImageServiceIp().length);
		return getImageServiceIp()[result];
		/*
		 * if (TRACKER_NGNIX_PORT.equals("80")) { return PROTOCOL + IP +
		 * SEPARATOR; } else { return PROTOCOL + IP + ":" + TRACKER_NGNIX_PORT +
		 * SEPARATOR; }
		 */
	}

	public static String[] getImageServiceIp() {
		IniFileReader iniReader;
		try {
			String classPath = new File(FastdfsFileManager.class.getResource(
					"/").getFile()).getCanonicalPath();
			String fdfsClientConfigFilePath = classPath + File.separator
					+ CLIENT_CONFIG_FILE;
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
