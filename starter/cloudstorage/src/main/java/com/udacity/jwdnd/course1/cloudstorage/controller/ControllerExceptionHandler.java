package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({MaxUploadSizeExceededException.class, SizeLimitExceededException.class})
    public String handleFileSizeTooLarge(HttpServletResponse response) {
        return "redirect:error/413";
    }
}
