package com.talent.base.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.talent.base.controller.RMConstants;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleException(GlobalException e) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put(RMConstants.RESPONSE_STATUS, RMConstants.RESPONSE_STATUS_FAIL);
        rspMap.put(RMConstants.RESPONSE_CODE, e.getErrorCode());
        rspMap.put(RMConstants.RESPONSE_MESSAGE, e.getData());
        return rspMap;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleException(Exception e) {
        log.error("error:", e);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put(RMConstants.RESPONSE_STATUS, RMConstants.RESPONSE_STATUS_FAIL);
        rspMap.put(RMConstants.RESPONSE_MESSAGE, RMConstants.RESPONSE_STATUS_FAIL_STR);
        rspMap.put(RMConstants.RESPONSE_CODE, RMConstants.RESPONSE_STATUS_FAIL_CODE);
        // rspMap.put(ResponseDefine.MESSAGE, "123435");
        return rspMap;
    }

    // 暂时没起作用
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    // @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleException(MaxUploadSizeExceededException e) {
        // log.error("MaxUploadSizeExceededException:", e);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put(RMConstants.RESPONSE_STATUS, RMConstants.RESPONSE_STATUS_FAIL);
        rspMap.put(RMConstants.RESPONSE_MESSAGE, ErrorCode.RESEARCHFILE_FILE_SIZE_EXCEED.getMessage());
        rspMap.put(RMConstants.RESPONSE_CODE, ErrorCode.RESEARCHFILE_FILE_SIZE_EXCEED.getCode());
        // rspMap.put(ResponseDefine.MESSAGE, "123435");
        return rspMap;
    }

}
