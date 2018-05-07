package com.talent.front.controller;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.util.FileUtil;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseFile;
import com.talent.front.service.BaseFileService;
import com.talent.front.service.SysResourceService;
import com.talent.front.service.SysRoleService;
import com.talent.front.service.SysUserRoleService;
import com.talent.front.service.SysUserService;
import com.talent.front.util.BCryptUtil;
import com.talent.front.util.FastdfsFileManagerBoot;
import com.talent.front.util.redis.RedisUtil;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员控制类
 */
@RestController
public class IndexController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private BaseFileService baseFileService;

    @Autowired
    RedisUtil redisUtil;

    @Value("${default.security.losetime}")
    long losetime;

    @Value("${default.file.url}")
    private String pathPrefix;

    @RequestMapping("/index")
    public Map<String, Object> index(Model model) {
        return setDataValue(null);
    }

    @RequestMapping(value = "login")
    public Map<String, Object> login(HttpServletRequest request, String username, String password) throws Exception {
        SysUserDto user = sysUserService.getUserByName(username);
        String sessionId = request.getSession().getId();
        if (user == null) {
            throw new GlobalException(ErrorCode.USER_NO_EXIST);
        }
        if (BCryptUtil.checkpw(password, user.getPassword()) && !StringUtils.isEmpty(sessionId)) {
            redisUtil.set(sessionId, user, losetime);
            // user.setRoles(null);
            // user.setBaseInstitutionDto(null);
            user.setToken(request.getSession().getId());
            return setDataValue(user);
        }
        throw new Exception();
    }

    @RequestMapping(value = "logout")
    public Map<String, Object> logout() throws Exception {
        UserCurrent.getInstance().logout();
        return setDataValue(null);
    }

    @RequestMapping("/deny")
    public Map<String, Object> deny(Model model) throws GlobalException {
        throw new GlobalException(ErrorCode.USER_DENY);
    }

    @RequestMapping("/relogin")
    public Map<String, Object> relogin(Model model) throws GlobalException {
        throw new GlobalException(ErrorCode.USER_RE_LOGIN);
    }

    /**
     * 文件上传Fastdfs,返回文件路径
     * 
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public Map<String, Object> upload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String imgUrl = FastdfsFileManagerBoot.getInstance().upload(fileName, file.getBytes(), suffix, null);
        logger.debug("上传成功路径: " + imgUrl);
        return setDataValue(imgUrl);
    }

    /**
     * 文件上传Fastdfs,文件信息入库baseFile表,返回com.talent.front.entity.BaseFile
     * 
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadFile")
    public Map<String, Object> uploadFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String imgUrl = FastdfsFileManagerBoot.getInstance().upload(fileName, file.getBytes(), suffix, null);

        SysUserDto sysUser = UserCurrent.getInstance().getSysUserDto();
        String userId = (sysUser == null ? "" : sysUser.getUserId());

        BaseFile baseFile = new BaseFile();
        baseFile.setFileId(UUIDUtil.getUUID(32));
        baseFile.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
        baseFile.setFilePath(imgUrl);
        baseFile.setFileExt(suffix);

        baseFile.setCreator(userId);
        baseFile.setAddTime(new Date());
        baseFile.setUpdateUser(userId);
        baseFile.setUpdateTime(new Date());

        baseFileService.insertSelective(baseFile);
        return setDataValue(baseFile);
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(String fileId, HttpServletResponse response) throws Exception {
        BaseFile baseFile = baseFileService.selectByPrimaryKey(fileId);
        if (baseFile == null) {
            throw new GlobalException(ErrorCode.FILE_NOT_FOUND);
        }

        try {
            response.setContentType("application/octet-stream");
            String suffix = baseFile.getFilePath().substring(baseFile.getFilePath().lastIndexOf(".") + 1).toLowerCase();
            response.setHeader("Content-disposition", "attachment;filename="
                    + new String(baseFile.getFileName().getBytes("gb2312"), "ISO8859-1") + "." + suffix);
            response.setCharacterEncoding("UTF-8");
            URL url = new URL(pathPrefix + baseFile.getFilePath());
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3 * 60 * 1000);

            byte[] buf = FileUtil.readInputStream(conn.getInputStream());
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.write(buf);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}