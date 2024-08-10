package com.example.demo;

import com.baeldung.jasperreports.SimpleReportExporter;
import com.baeldung.jasperreports.SimpleReportFiller;
import com.baeldung.jasperreports.config.JasperRerportsSimpleConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {


        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JasperRerportsSimpleConfig.class);
        ctx.refresh();

        SimpleReportFiller simpleReportFiller = ctx.getBean(SimpleReportFiller.class);
        simpleReportFiller.setReportFileName("sampleJavareport.jrxml");
        simpleReportFiller.compileReport();



        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Employee Report Example");
        parameters.put("minSalary", 15000.0);
        parameters.put("condition", " LAST_NAME ='Smith' ORDER BY FIRST_NAME");

        simpleReportFiller.setParameters(parameters);
        simpleReportFiller.fillReport();

        SimpleReportExporter simpleExporter = ctx.getBean(SimpleReportExporter.class);
        simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());

        simpleExporter.exportToPdf("employeeReport.pdf", "baeldung");
        simpleExporter.exportToXlsx("employeeReport.xlsx", "Employee Data");
        simpleExporter.exportToCsv("employeeReport.csv");
        simpleExporter.exportToHtml("employeeReport.html");


    }

}
