package com.dou.test;


import com.dou.test.entity.xml.ApplyForm;
import com.dou.test.utils.LisJaxbXmlHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CxfAndJacksonXmlAndJaxbTests {

    private static final String WSDL ="http://127.0.0.1/TransData.asmx?WSDL";
    Object[] objects = new Object[0];

    @Test
    public void xmlMapper(){
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JaxbAnnotationModule());
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ApplyForm applyForm =null;
        try {
            Path path = Paths.get("src\\test\\resources\\ObjToXml.xml");
            File file = path.toFile();
            applyForm = xmlMapper.readValue(this.getClass().getResourceAsStream("/apply.xml"), new TypeReference<ApplyForm>(){});
            // jackson xml
            xmlMapper.writeValue(file,applyForm);
            String str = xmlMapper.writeValueAsString(applyForm);
            System.out.println(str);
            // jaxb
            JAXBContext jaxbContext = JAXBContext.newInstance(ApplyForm.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setListener(new LisJaxbXmlHelper.MarshallerListener());
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(applyForm , file );
            jaxbMarshaller.marshal(applyForm, System.out);

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("==============="+applyForm.getFilialeCode());
        System.out.println("==============="+applyForm.getCustomers().size());
    }
    

    @Test
    public void testGetSampleCount() throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WSDL);
        Document GetSampleCount = DocumentHelper.createDocument();
        Element planxml = GetSampleCount.addElement("GetSampleCount");
        planxml.addElement("UserName").addText("5");
        planxml.addElement("PassWord").addText("5");
        planxml.addElement("HospitalCode").addText("5");
        planxml.addElement("ItemIndexMisCode").addText("5");
        objects = client.invoke("GetSampleCount", "5","5","5","5");
        System.out.println(objects[0].toString());
    }


    @Test
    public void getSprInfoByReportId() throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WSDL);
        objects = client.invoke("getSprInfoByReportId", 9580);
    }

    @Test
    public void lisSendDataTest() throws Exception {
        String _send_userName = "use";
        String _send_passWord = "1234";
        String _send_messagename = "PISSendData";
        String _send_parameter = Files.lines(Paths.get("src\\test\\resources\\apply.xml"), StandardCharsets.UTF_8).reduce("",(a, b)-> a.trim() + b.trim());
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WSDL);
        objects = client.invoke("Send", _send_userName, _send_passWord, _send_messagename, _send_parameter );
        System.out.println(objects[0].toString());
    }

    @Test
    public void lisLoadDataTest() throws Exception {
        String _send_userName = "use";
        String _send_passWord = "1234";
        String _send_messagename = "PISLoadData";
        String _send_parameter = "<request><FilialeCode>84</FilialeCode></request>";
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WSDL);
        objects = client.invoke("Send", _send_userName, _send_passWord, _send_messagename, _send_parameter );
        System.out.println(objects[0].toString());
    }

    @Test
    public void lisCallbackDataTest() throws Exception {
        String _send_userName = "use";
        String _send_passWord = "1234";
        String _send_messagename = "ChangeStatus";
        String _send_parameter = Files.lines(Paths.get("src\\test\\resources\\callback.xml"), StandardCharsets.UTF_8).reduce("",(a, b)-> a.trim() + b.trim());
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WSDL);
        objects = client.invoke("Send", _send_userName, _send_passWord, _send_messagename, _send_parameter );
        System.out.println(objects[0].toString());
    }

}
