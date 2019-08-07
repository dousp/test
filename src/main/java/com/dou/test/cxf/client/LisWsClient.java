package com.dou.test.cxf.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * @author dsp
 * @date 2019-08-06
 */
public class LisWsClient {
    private QName serviceName;
    private String operation = "Send";
    private String wsdl;

    public LisWsClient() {
    }

    public LisWsClient(String wsdl) {
        this.wsdl = wsdl;
    }

    public LisWsClient(String wsdl, QName serviceName ) {
        this.serviceName = serviceName;
        this.wsdl = wsdl;
    }

    public Client cxfClient(String wsdlUrl, QName serviceName) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(wsdlUrl,serviceName);
    }

    public Client cxfClient(String wsdlUrl) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(wsdlUrl);
    }

    public Client cxfClient() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(wsdl,serviceName);
    }

    public QName getServiceName() {
        return serviceName;
    }

    public LisWsClient setServiceName(QName serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public LisWsClient setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getWsdl() {
        return wsdl;
    }

    public LisWsClient setWsdl(String wsdl) {
        this.wsdl = wsdl;
        return this;
    }
}
