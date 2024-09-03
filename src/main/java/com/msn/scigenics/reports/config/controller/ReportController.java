package com.msn.scigenics.reports.config.controller;

import com.msn.scigenics.reports.config.SimpleReportExporter;
import com.msn.scigenics.reports.config.SimpleReportFiller;
import com.msn.scigenics.reports.model.ReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private SimpleReportFiller reportFiller;

    @Autowired
    private SimpleReportExporter simpleExporter;


    @PostMapping("/generatePDF")
    public void generatePdf(@RequestBody ReportModel model) {

        reportFiller.fillReport(model.getJsonkey(),model.getFermenter(),model.getProcessType());

        simpleExporter.setJasperPrint(reportFiller.getJasperPrint());

        simpleExporter.exportToPdf("msn.pdf", "baeldung");

    }
}
