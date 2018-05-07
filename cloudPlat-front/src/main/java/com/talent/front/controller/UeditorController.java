package com.talent.front.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talent.front.ueditor.utils.UnicodeUtil;
import com.talent.front.ueditor.viservice.editor.ueditor.UeditorActionEnter;
import com.talent.front.ueditor.viservice.editor.ueditor.UeditorService;

/**
 * Ueditor后台处理入口
 */
@Controller("UeditorController")
@RequestMapping("ueditor")
public class UeditorController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "UeditorServiceFastdfsImpl")
	private UeditorService ueditoreService;

	@RequestMapping(value = "execute")
	public void execute(HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {

		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String resultMsg = new UeditorActionEnter(request, rootPath, this.ueditoreService).exec();

		logger.error("ueditor execute ... resultMsg:" + UnicodeUtil.fromUnicode(resultMsg));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			out.write("<script type='text/javascript'>document.domain='dding.com'</script>");
//			out.write(UnicodeUtil.fromUnicode(resultMsg));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
		
		try {  
            PrintWriter writer = response.getWriter();  
            //writer.write("<script type='text/javascript'>document.domain='dding.com'</script>");
            writer.write(resultMsg);  
            writer.flush();  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}

}
