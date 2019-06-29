package com.dou.test.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // @ExceptionHandler(Exception.class)
    // public Result jsonErrorHandler(HttpServletRequest req, Exception e){
    //     return ResultGenerator.genFailResult(e.getMessage());
    // }

    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest req, Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", e.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("stackTrace", e.getStackTrace());
        modelAndView.setViewName("error");
        e.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(RuntimeException.class)
    public Object runtimeException(HttpServletRequest req, RuntimeException e){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", e.getClass().getName());
        modelAndView.addObject("msg", e.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("stackTrace", e.getStackTrace());
        modelAndView.setViewName("runtimeerror");
        e.printStackTrace();
        return modelAndView;
    }
}
