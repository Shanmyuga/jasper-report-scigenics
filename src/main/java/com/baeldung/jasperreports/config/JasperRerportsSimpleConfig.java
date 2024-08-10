package com.baeldung.jasperreports.config;

import com.baeldung.jasperreports.SimpleReportExporter;
import com.baeldung.jasperreports.SimpleReportFiller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;


import net.sf.jasperreports.engine.data.JsonDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class JasperRerportsSimpleConfig {

    @Bean
    public JRDataSource dataSource() throws JRException {

       JsonDataSource dataSource = new JsonDataSource(this.getClass().getClassLoader().getResourceAsStream("datasource.json"),"report");
       return dataSource;
       // return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:employee-schema.sql").build();
    }

    @Bean
    public SimpleReportFiller reportFiller() {
        return new SimpleReportFiller();
    }

    @Bean
    public SimpleReportExporter reportExporter() {
        return new SimpleReportExporter();
    }

}