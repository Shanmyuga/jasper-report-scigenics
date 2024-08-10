package com.baeldung.jasperreports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SimpleReportFiller {

    private String reportFileName;

    private JasperReport jasperReport;

    private JasperPrint jasperPrint;

    @Autowired
    private JRDataSource dataSource;

    private Map<String, Object> parameters;

    public SimpleReportFiller() {
        parameters = new HashMap<>();
    }

    public void prepareReport() {
        compileReport();
        fillReport();
    }

    public void compileReport() {
        try {
            InputStream reportStream = null;

             reportStream = getClass().getClassLoader().getResourceAsStream("Blank_A4_14.jrxml");

            JRSaver.saveObject(JasperCompileManager.compileReport(reportStream), "Blank_A4_14.jrxml".replace(".jrxml", ".jasper"));

             reportStream = getClass().getClassLoader().getResourceAsStream("Blank_A4_15.jrxml");

            JRSaver.saveObject(JasperCompileManager.compileReport(reportStream), "Blank_A4_15.jrxml".replace(".jrxml", ".jasper"));

            reportStream = getClass().getClassLoader().getResourceAsStream("Blank_A4_17.jrxml");

            JRSaver.saveObject(JasperCompileManager.compileReport(reportStream), "Blank_A4_17.jrxml".replace(".jrxml", ".jasper"));

             reportStream = this.getClass().getClassLoader().getResourceAsStream("Blank_A4_13.jrxml");
            jasperReport = JasperCompileManager.compileReport(reportStream);
            JRSaver.saveObject(jasperReport, "Blank_A4_13.jrxml".replace(".jrxml", ".jasper"));
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillReport() {

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }



    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

}