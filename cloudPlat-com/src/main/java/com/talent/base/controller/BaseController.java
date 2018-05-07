package com.talent.base.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.talent.base.exception.BusinessException;
import com.talent.base.exception.ErrorCode;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;

public class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    public static String URL404 = "shop/404.html";

    protected PageObject po;

    /**
     * 获取分页、查询条件对象
     * 
     * @return
     * @throws BusinessException 系统异常
     */
    protected PageObject getPageObject() throws BusinessException {
        PageObject pageObject = new PageObject();
        // 获取记录开始行数
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String pageStr = request.getParameter("page");
        pageStr = StringUtils.isEmpty(pageStr) ? "1" : pageStr;
        // 获取每页显示的行数
        String pageSizeStr = request.getParameter("pageSize");
        pageSizeStr = StringUtils.isEmpty(pageSizeStr) ? "10" : pageSizeStr;
        // 排序从句
        String orderByClause = "";
        String sortBy = request.getParameter("sortBy");
        if (StringUtils.isNotEmpty(sortBy)) {
            orderByClause = this.getColumn(sortBy).trim();
            pageObject.setSortBy(orderByClause);
            String sortDir = request.getParameter("sortDir");
            if (StringUtils.isNotEmpty(sortDir)) {
                if (sortDir.trim().equalsIgnoreCase("asc") || sortDir.trim().equalsIgnoreCase("desc")) {
                    pageObject.setSortDir(sortDir.trim());
                    orderByClause = orderByClause + " " + sortDir.trim();
                }
            }
        } else {
            pageObject.setSortBy("update_time");
            pageObject.setSortDir("desc");
            orderByClause = "update_time desc";
        }
        int pageIndex, pageSize, curPage;
        try {
            curPage = Integer.parseInt(pageStr);
            pageIndex = Integer.parseInt(pageStr);
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.REQUEST_PARAM_ERROR);
        }
        if (pageSize == 0) {
            pageSize = Integer.MAX_VALUE;
        }
        pageIndex = pageIndex == 0 ? 1 : pageIndex;

        pageIndex = (pageIndex - 1) * pageSize;
        // 设置页数
        pageObject.setPageIndex(pageIndex);
        // 设置当前第几页
        pageObject.setCurPage(curPage);
        // 设置每页显示多少条
        pageObject.setPageSize(pageSize);
        pageObject.setOrderByClause(orderByClause);
        @SuppressWarnings("unchecked")
        Enumeration<String> enumerations = request.getParameterNames();
        // 封装查询条件
        while (enumerations.hasMoreElements()) {
            String parameterName = enumerations.nextElement();
            if (parameterName != "page" && parameterName != "pageSize" && parameterName != "sortBy"
                    && parameterName != "sortDir") {
                String parameterValue = request.getParameter(parameterName);
                pageObject.getQueryCondition().put(parameterName, parameterValue);
            }
        }
        // 加入删除过滤
        pageObject.getQueryCondition().put("deleteStatus", "N");
        return pageObject;
    }

    /**
     * 获取分页、查询条件对象
     * 
     * @return
     * @throws BusinessException 系统异常
     */
    protected PageObject getPageObject(String pageParam, String pageSizeparam) throws BusinessException {
        PageObject pageObject = new PageObject();
        // 获取记录开始行数
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String pageStr = pageParam;
        pageStr = pageStr == null ? "1" : pageStr;
        // 获取每页显示的行数
        String pageSizeStr = pageSizeparam;
        pageSizeStr = pageSizeStr == null ? "10" : pageSizeStr;
        // 排序从句
        String orderByClause = "";
        String sortBy = request.getParameter("sortBy");
        if (StringUtils.isNotEmpty(sortBy)) {
            orderByClause = this.getColumn(sortBy).trim();
            pageObject.setSortBy(orderByClause);
            String sortDir = request.getParameter("sortDir");
            if (StringUtils.isNotEmpty(sortDir)) {
                if (sortDir.trim().equalsIgnoreCase("asc") || sortDir.trim().equalsIgnoreCase("desc")) {
                    pageObject.setSortDir(sortDir.trim());
                    orderByClause = orderByClause + " " + sortDir.trim();
                }
            }
        }
        int pageIndex, pageSize, curPage;
        try {
            curPage = Integer.parseInt(pageStr);
            pageIndex = Integer.parseInt(pageStr);
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.REQUEST_PARAM_ERROR);
        }
        if (pageSize == 0) {
            pageSize = Integer.MAX_VALUE;
        }
        pageIndex = pageIndex == 0 ? 1 : pageIndex;

        pageIndex = (pageIndex - 1) * pageSize;
        // 设置页数
        pageObject.setPageIndex(pageIndex);
        // 设置当前第几页
        pageObject.setCurPage(curPage);
        // 设置每页显示多少条
        pageObject.setPageSize(pageSize);
        pageObject.setOrderByClause(orderByClause);
        @SuppressWarnings("unchecked")
        Enumeration<String> enumerations = request.getParameterNames();
        // 封装查询条件
        while (enumerations.hasMoreElements()) {
            String parameterName = enumerations.nextElement();
            if (parameterName != "page" && parameterName != "pageSize" && parameterName != "sortBy"
                    && parameterName != "sortDir") {
                String parameterValue = request.getParameter(parameterName);
                pageObject.getQueryCondition().put(parameterName, parameterValue);
            }
        }
        return pageObject;
    }

    /**
     * 
     * @param property
     * @return
     */
    private String getColumn(String property) {
        String column = property.replaceAll("([A-Z]+)", "_$1");
        return column.toLowerCase();
    }

    public ModelAndView redirect(String redirectUrl, Map<String, Object>... parament) {
        ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
        if (null != parament && parament.length > 0) {
            view.addAllObjects(parament[0]);
        }
        return view;
    }

    public ModelAndView redirect404() {
        return new ModelAndView(new RedirectView(URL404));
    }

    /**
     * 往Request里带值
     * 
     * @param request
     * @param key
     * @param value
     */
    protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
        request.setAttribute(key, value);
    }

    /**
     * [获取session]
     * 
     * @param request
     * @return
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /*******************************************************************/

    private Map<String, Object> rspMap;

    protected void init() {
        this.rspMap = new HashMap<String, Object>();
        this.rspMap.put(RMConstants.RESPONSE_STATUS, RMConstants.RESPONSE_STATUS_SUCCESS);
        this.rspMap.put(RMConstants.RESPONSE_CODE, RMConstants.RESPONSE_STATUS_SUCCESS_CODE);
        this.rspMap.put(RMConstants.RESPONSE_MESSAGE, RMConstants.RESPONSE_STATUS_SUCCESS_STR);
    }

    protected Map<String, Object> getRspMap() {
        return this.rspMap;
    }

    protected Map<String, Object> getDefinedMap() {
        this.init();
        return this.rspMap;
    }

    protected Map<String, Object> setKeyValue(String key, Object value) {
        this.init();
        this.rspMap.put(key, value);
        return this.rspMap;
    }

    protected Map<String, Object> setKeyValueAppend(String key, Object value) {
        this.rspMap.put(key, value);
        return this.rspMap;
    }

    protected Map<String, Object> setDataValue(Object value) {
        this.init();
        if (value instanceof List) {
            PageResult pageResult = new PageResult();
            if (value == null || ((List) value).size() == 0) {
                pageResult.setTotalCount(0);
            } else {
                pageResult.setTotalCount(((List) value).size());
                pageResult.setQueryResult((List) value);
            }
            return setKeyValue(RMConstants.RESPONSE_DATA, pageResult);
        }
        return setKeyValue(RMConstants.RESPONSE_DATA, value);
    }

}
