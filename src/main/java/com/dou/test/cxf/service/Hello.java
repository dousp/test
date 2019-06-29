package com.dou.test.cxf.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author dsp
 * @create 2019-06-25
 * @Description todo
 */

/**
 * 上面package名反过来写
 */
@WebService(targetNamespace="http://ws.test2.example.com")
public interface Hello {

    @WebMethod
    @WebResult
    String sayHello(@WebParam(name = "userName") String userName);
}