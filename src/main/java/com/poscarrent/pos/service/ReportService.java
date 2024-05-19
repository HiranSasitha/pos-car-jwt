package com.poscarrent.pos.service;

import com.poscarrent.pos.entity.Customer;
import com.poscarrent.pos.repo.CustomerRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ResourceLoader resourceLoader;

    public String exportReport(String reportFormat) throws IOException, JRException {
        List<Customer> customers = customerRepo.findAll();
        //load file and compile it

       // File file = ResourceUtils.getFile("classpath:customer.jrxml");
      //  JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        Resource resource = resourceLoader.getResource("classpath:reports/customer.jrxml");
        InputStream  inputStream = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("create By","Hiran");


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource );

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\hiran\\Desktop"+"\\customer.html");
        }if(reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\hiran\\Desktop"+"\\customer.pdf");
        }
        return "success";
    }
}
