package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.util.*;

public class ReportService {
    private String jrxmlPath = "reports\\";
    private String jrxmlExtension = ".jrxml";
    private String jasperPath = "reports\\";
    private String jasperExtension = ".jasper";
    private String exportPath = "exports\\";
    private String exportExtension = ".pdf";
    private List<String> reports;

    public ReportService() {
        reports = new ArrayList<>();
        reports.add("TouristList");
        reports.add("CarList");
        reports.add("ContractList");
        reports.add("DriverList");
        reports.add("CarSituationList");
        reports.add("DefaultersList");
        reports.add("ContractBrandList");
        reports.add("ContractCountryList");
        reports.add("MonthIncomeList");
        compileAll();
    }

    public String getReport(int index) {
        return reports.get(index);
    }

    public void generateReport(String report) throws ConnectionFailedException {
        try {
            JasperPrint jasperPrint = fillReport(jasperPath + report + jasperExtension);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportPath + report + exportExtension));
            SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(config);
            exporter.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void previewReport(String report) throws ConnectionFailedException {
        JasperPrint jasperPrint = fillReport(jasperPath + report + jasperExtension);
        JasperViewer.viewReport(jasperPrint, false);
    }

    private JasperPrint fillReport(String path) throws ConnectionFailedException {
        try {
            return JasperFillManager.fillReport(path, new HashMap<>(), ServicesLocator.getConnection());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private void compileAll() {
        for (String report : reports) {
            compileReport(report);
        }
    }

    private void compileReport(String report) {
        try {
            JasperCompileManager.compileReportToFile(jrxmlPath + report + jrxmlExtension, jasperPath + report + jasperExtension);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
