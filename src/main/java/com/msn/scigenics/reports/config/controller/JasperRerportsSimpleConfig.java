package com.msn.scigenics.reports.config.controller;

import com.msn.scigenics.reports.config.SimpleReportExporter;
import com.msn.scigenics.reports.config.SimpleReportFiller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;


import net.sf.jasperreports.engine.data.JsonDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasperRerportsSimpleConfig {

    @Bean
    public JRDataSource dataSource() throws JRException {

       JsonDataSource dataSource = new JsonDataSource(this.getClass().getClassLoader().getResourceAsStream("datasource.json"),"report");
       return dataSource;
       // return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:employee-schema.sql").build();
    }



}