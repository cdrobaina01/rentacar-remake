package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.io.ObjectStreamException;
import java.util.*;

public class ReportService {
    public class Report {
        public String localeName;
        public String reportName;
        public boolean hasParameters;
        public String paramName;
        public List<String> choices;
        public Report(String name, String locale) {
            reportName = name;
            localeName = locale;
            hasParameters = false;
            paramName = null;
            choices = null;
        }

        public Report(String name, String locale, String paramName, List<String> choices) {
            reportName = name;
            localeName = locale;
            hasParameters = true;
            this.paramName = paramName;
            this.choices = choices;
        }
    }
    private String jrxmlPath = "reports\\";
    private String jrxmlExtension = ".jrxml";
    private String jasperPath = "reports\\";
    private String jasperExtension = ".jasper";
    private String exportPath = "exports\\";
    private String exportExtension = ".pdf";
    private List<Report> reports;

    public ReportService() throws ConnectionFailedException {
        reports = new ArrayList<>();
        reports.add(new Report("TouristList", "Listado de los Turistas (Todos)"));
        reports.add(new Report("TouristListParam", "Listado de los Turistas (Parametrizado)", "País", ServicesLocator.countryServices().getNames()));
        reports.add(new Report("CarList", "Listado de los Autos"));
        reports.add(new Report("ContractList", "Listado de los Contratos"));
        reports.add(new Report("DriverList", "Listado de los Choferes"));
        reports.add(new Report("CarSituationList", "Situación de los Autos"));
        reports.add(new Report("DefaultersList", "Turistas Incumplidores"));
        reports.add(new Report("ContractBrandList", "Contratos por Marcas (Todos)"));
        reports.add(new Report("ContractBrandListParam", "Contratos por Marcas (Parametrizado)", "Marca", ServicesLocator.brandServices().getNames()));
        reports.add(new Report("ContractCountryList", "Contratos por Países (Todos)", "País", ServicesLocator.countryServices().getNames()));
        reports.add(new Report("ContractCountryListParam", "Contratos por Países (Parametrizado)", "País", ServicesLocator.countryServices().getNames()));
        reports.add(new Report("IncomeYearList", "Ingresos Anuales (Todos)"));
        reports.add(new Report("IncomeYearListParam", "Ingresos Anuales (Parametrizado)", "Año", ServicesLocator.contractServices().getYears()));
        // compileAll();
    }

    public Report getReport(int index) {
        return reports.get(index);
    }

    public void generateReport(Report report, Map<String, Object> params) throws ConnectionFailedException {
        try {
            JasperPrint jasperPrint = fillReport(jasperPath + report.reportName + jasperExtension, params);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportPath + report.reportName + exportExtension));
            SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(config);
            exporter.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void previewReport(Report report, Map<String, Object> params) throws ConnectionFailedException {
        JasperPrint jasperPrint = fillReport(jasperPath + report.reportName + jasperExtension, params);
        JasperViewer.viewReport(jasperPrint, false);
    }

    public List<Report> getReports() {
        return reports;
    }

    private JasperPrint fillReport(String path, Map<String, Object> params) throws ConnectionFailedException {
        try {
            return JasperFillManager.fillReport(path, params, ServicesLocator.getConnection());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private void compileAll() {
        for (Report report : reports) {
            compileReport(report.reportName);
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
