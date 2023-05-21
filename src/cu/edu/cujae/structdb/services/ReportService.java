package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportService {
    private String jasperPath = "reports\\";
    private String jasperExtension = ".jasper";
    private String exportPath = "exports\\";
    private String exportExtension = ".pdf";
    private List<String> reports;

    public ReportService() {
        reports = new ArrayList<>();
        reports.add("TouristList");
        reports.add("CarList");
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
        JasperViewer.viewReport(jasperPrint);
    }

    private JasperPrint fillReport(String path) throws ConnectionFailedException {
        try {
            return JasperFillManager.fillReport(path, new HashMap<>(), ServicesLocator.getConnection());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
