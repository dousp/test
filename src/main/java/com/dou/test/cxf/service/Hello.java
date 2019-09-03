package com.dou.test.cxf.service;

import com.dou.test.entity.xml.TicketRequest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author dsp
 * @create 2019-06-25
 * @Description todo
 */

/**
 * 上面package名反过来写
 */
@WebService(targetNamespace="http://service.cxf.test.dou.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {

    // @WebMethod(operationName = "sayHello", action = "http://service.cxf.test.dou.com/sayHello")
    // @WebResult(targetNamespace = "http://service.cxf.test.dou.com" )
    @WebMethod
    @WebResult(name = "request")
    TicketRequest sayHello(@WebParam(partName = "userName", name = "userName" ) String userName,
                   @WebParam(name = "passWord" ) String passWord);

    @WebMethod
    @WebResult
    String CallNumber(
            @WebParam(partName = "seq", name = "seq" ) String seq,
            @WebParam(partName = "time", name = "time" ) String time
    );
}