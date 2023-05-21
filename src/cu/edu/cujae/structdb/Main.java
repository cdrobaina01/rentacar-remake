package cu.edu.cujae.structdb;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkContrastIJTheme;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ReportService;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Para probar otros temas sustituir el paramétro de init()
        // Si quieres verlos todos pones com.formdev.flatlaf.intellijthemes.materialthemeuilite. y usas el IntelliSense
        GuiManager.init(new FlatArcDarkContrastIJTheme());

        // test();
    }

    public static void test() {
        try {
            String path = "reports\\CarList.jasper";
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap<>(), ServicesLocator.getConnection());
            // JasperViewer.viewReport(jasperPrint);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("exports\\TouristList.pdf"));
            SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(config);
            exporter.exportReport();
        } catch (JRException | ConnectionFailedException e) {
            System.out.println("xd");
            throw new RuntimeException(e);
        }
    }
}
