package com.dou.test.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * @author dsp
 * @create 2019-06-24
 * @Description todo
 */
public class LisJaxbXmlHelper {

    private static final Logger logger = LoggerFactory.getLogger(LisJaxbXmlHelper.class);

    private String wsdlUrl;

    public static class MarshallerListener  extends Marshaller.Listener{
        public static final String BLANK_CHAR = "";
        @Override
        public void beforeMarshal(Object source) {
            super.beforeMarshal(source);
            Field[] fields = source.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                try {
                    if (f.getType() == String.class && f.get(source) == null) {
                        f.set(source, BLANK_CHAR);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *   BEAN to XML
     * @param object 自定义entity
     * @return XML字符串
     */
    public static String objectToXML(Object object){
        String xml;
        try{
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter w = new StringWriter();
            marshaller.marshal(object, w);
            xml = w.toString();
        }catch (Exception e){
            logger.error("objectToXML error",e);
            throw new RuntimeException("objectToXML error");
        }
        return xml;
    }

    /**
     *  BEAN to XML
     * @param object
     * @param format 格式化
     * @param keepNullValue 是否保留空值的xml元素
     * @return
     */
    public static String objectToXML(Object object, Boolean format, Boolean keepNullValue){
        String xml;
        try{
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            if(format){
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            }
            if(keepNullValue){
                marshaller.setListener(new MarshallerListener());
            }
            StringWriter w = new StringWriter();
            marshaller.marshal(object, w);
            xml = w.toString();
        }catch (Exception e){
            logger.error("objectToXML error",e);
            throw new RuntimeException("objectToXML error");
        }
        return xml;
    }

    /**
     * XML to BEAN
     * @param clazz 自定义数据类型
     * @param xml XML字符串
     * @return 自定义数据对象
     */
    public static Object xmlToObject(Class clazz, String xml){
        Object obj;
        try{
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller um = context.createUnmarshaller();
            obj = um.unmarshal(new StringReader(xml));
        }catch (Exception e){
            logger.error("xmlToObject error",e);
            throw new RuntimeException("xmlToObject error");
        }
        return obj;
    }

    public Object[] wsInvoke(String var1, Object... var2) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        return client.invoke("GetSampleCount", var2);
    }

    /**
     * 获取默认cxf客户端
     * @return
     */
    public Client cxfClient() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(wsdlUrl);
    }

    /**
     *  获取自定义wsdl的cxf客户端
     * @param wsdlUrl
     * @return
     */
    public Client cxfClient(String wsdlUrl) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(wsdlUrl);
    }



// ===================getter/setter/constructor============================

    public LisJaxbXmlHelper() {
    }

    public LisJaxbXmlHelper(String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public String getWsdlUrl() {
        return wsdlUrl;
    }

    public void setWsdlUrl(String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }
}
