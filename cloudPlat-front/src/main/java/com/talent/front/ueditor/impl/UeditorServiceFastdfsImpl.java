package com.talent.front.ueditor.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.talent.base.constant.YesOrNo;
import com.talent.base.model.PageObject;
import com.talent.base.util.DateUtil;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.BaseFileDto;
import com.talent.front.entity.SysUser;
import com.talent.front.service.BaseFileService;
import com.talent.front.ueditor.viservice.editor.ueditor.UeditorService;
import com.talent.front.util.FastdfsFileManagerBoot;
import com.talent.front.util.security.UserCurrent;

/**
 * UeditorService实现 - Fastdfs
 */
@Component("UeditorServiceFastdfsImpl")
public class UeditorServiceFastdfsImpl implements UeditorService {

	@Autowired
	private BaseFileService baseFileService;

	@Override
	public com.talent.front.ueditor.viservice.editor.MultipartFile getMultipartFile(String filedName,
			HttpServletRequest request) {
		com.talent.front.ueditor.viservice.editor.MultipartFile resultFile = null;
		try {
			MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartHttpservletRequest.getFile(filedName);
			if (!multipartFile.isEmpty()) {
				resultFile = new com.talent.front.ueditor.viservice.editor.StandardMultipartFile(filedName,
						multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getSize());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}

	@Override
	public State saveFileByInputStream(com.talent.front.ueditor.viservice.editor.MultipartFile multipartFile,
			long maxSize) {
		State state = null;
		try {
			if (multipartFile.getSize() > maxSize) {
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			Map<String, Object> uploadResult = null;
			String originalFileName = multipartFile.getOriginalFilename();
			uploadResult = FastdfsFileManagerBoot.getInstance().uploadFile(multipartFile.getBytes(), originalFileName);

			if ((Boolean) uploadResult.get("status")) {
				state = new BaseState(true);
				state.putInfo("size", uploadResult.get("length").toString());
				state.putInfo("title", uploadResult.get("fileName").toString());
				// state.putInfo("groupName",
				// uploadResult.get("groupName").toString());
				// state.putInfo("storageFileName",
				// uploadResult.get("storageFileName").toString());
				state.putInfo("url", FastdfsFileManagerBoot.getUrlPre() + uploadResult.get("link").toString());

				// 把上传的文件信息记入数据库
				// 插入到数据库
				insertAccessory(uploadResult);
				return state;
			}
		} catch (IOException e) {

		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	@Override
	public State saveBinaryFile(byte[] data, String fileName) {
		State state = null;

		Map<String, Object> uploadResult = FastdfsFileManagerBoot.getInstance().uploadFile(data, fileName);
		if ((Boolean) uploadResult.get("status")) {
			state = new BaseState(true);
			state.putInfo("size", uploadResult.get("length").toString());
			state.putInfo("title", uploadResult.get("fileName").toString());
			// state.putInfo("groupName",
			// uploadResult.get("groupName").toString());
			// state.putInfo("storageFileName",
			// uploadResult.get("storageFileName").toString());
			state.putInfo("url", FastdfsFileManagerBoot.getUrlPre() + uploadResult.get("link").toString());
			// 插入到数据库
			insertAccessory(uploadResult);
			return state;
		}

		return new BaseState(false, AppInfo.IO_ERROR);
	}

	@Override
	public State listFile(String[] allowFiles, int start, int pageSize) {
		// 把计入数据库中的文件信息读取出来，返回即可
		/*
		 * String ext = "( "; for (int i = 0; i < allowFiles.length; i++) { if (i ==
		 * allowFiles.length - 1) { ext += " ext='" + allowFiles[i] + "' )"; } else {
		 * ext += " ext='" + allowFiles[i] + "' OR"; } }
		 * System.out.println("allowFiles:" + Arrays.toString(allowFiles));
		 * System.out.println("111111111" + ext);
		 */
		MultiState state = new MultiState(true);
		// 把上传的文件信息记入数据库
		SysUser token = UserCurrent.getInstance().getSysUserDto();
		if (token != null) {
			// 获取图片
			PageObject po = new PageObject();
			po.setPageSize(pageSize);
			po.setPageIndex(start);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileType", "PIC");
			map.put("creator", token.getUserId());
			map.put("deleteStatus", "N");
			po.setQueryCondition(map);
			long total = baseFileService.getAllCount(po);
			List<BaseFileDto> baseFileDtos = baseFileService.pageListDto(po).getQueryResult();

			BaseState fileState = null;
			for (BaseFileDto baseFileDto : baseFileDtos) {
				fileState = new BaseState(true);
				fileState.putInfo("url", FastdfsFileManagerBoot.getUrlPre() + baseFileDto.getFilePath());
				state.addState(fileState);
			}

			state.putInfo("total", total);
		} else {
			state.putInfo("total", 0);
		}
		state.putInfo("start", start);
		return state;
	}

	// 把上传的文件信息记入数据库
	public void insertAccessory(Map<String, Object> uploadResult) {
		SysUser token = UserCurrent.getInstance().getSysUserDto();
		if (token != null) {
			BaseFileDto accessory = new BaseFileDto();
			// 设置宽
			accessory.setDeleteStatus(YesOrNo.N.name());
			accessory.setFileWidth((Integer) uploadResult.get("width"));
			// 设置高
			accessory.setFileHeight((Integer) uploadResult.get("height"));
			// 文件后缀名
			accessory.setFileExt(uploadResult.get("fileExtName").toString());
			// uuid
			accessory.set_Id(UUIDUtil.getUUID());
			// 上传时间
			Date date = DateUtil.getCurrentDate();
			accessory.setAddTime(date);
			// 更新时间
			accessory.setUpdateTime(date);
			// 上传者
			accessory.setCreator(token.getUserId());
			// 链接地址
			accessory.setFilePath(uploadResult.get("link").toString());
			baseFileService.insertSelective(accessory);
		}
	}

	// ---------以下两个注释的方法，是我项目中的处理方式，可供参考-------------//

	// @SuppressWarnings("unchecked")
	// @Override
	// public State listFile(String[] allowFiles, int start, int pageSize) {
	// Pager pager = new Pager();
	//
	// int pageNumber = start / pageSize + 1;
	// pager.setPageSize(pageSize);
	// pager.setPageNumber(pageNumber);
	//
	// //System.out.println("allowFiles:"+Arrays.toString(allowFiles));
	// //allowFiles:[png, jpg, jpeg, gif, bmp]
	//
	// Criteria criteria = new Criteria();
	// criteria.add(Restrictions.order("createDate", "desc"));
	//
	// State state = null;
	// try {
	// pager = dfsFileService.getPager(getTenantId(), criteria, pager);
	// state = this.getState((List<DfsFile>) pager.getList());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// state.putInfo("start", start);
	// state.putInfo("total", pager.getTotalCount());
	//
	// return state;
	// }
	//
	// private State getState(List<DfsFile> files) {
	// MultiState state = new MultiState(true);
	// BaseState fileState = null;
	//
	// for (DfsFile dfsFile : files) {
	// fileState = new BaseState(true);
	// fileState.putInfo("url",
	// PropertiesUtil.getValue("applicationContext.properties",
	// "dfsFileAccessBasePath") + "/" + dfsFile.getLink());
	// state.addState(fileState);
	// }
	// return state;
	// }

}
