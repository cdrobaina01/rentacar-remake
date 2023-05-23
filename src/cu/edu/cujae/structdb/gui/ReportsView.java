/*
 * Created by JFormDesigner on Mon May 22 09:03:14 CDT 2023
 */

package cu.edu.cujae.structdb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

/**
 * @author carlosd.inc
 */
public class ReportsView extends JDialog {
    private DefaultTableModel dtm;
    public ReportsView(Window owner) {
        super(owner);
        initComponents();
        this.setTitle("Reportes");

        dtm = new DefaultTableModel();
        dtm.addColumn("Nombre");
        for (String report : ServicesLocator.reportServices().getReportsName()) {
            Object [] item = { report };
            dtm.addRow(item);
        }
        table.setModel(dtm);
    }

    private void ok(ActionEvent e) {
        this.dispose();
    }

    private void btnPreview(ActionEvent e) {
        try {
            int selection = table.getSelectedRow();
            if (selection == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar algún reporte.");
                return;
            }
            ServicesLocator.reportServices().previewReport(ServicesLocator.reportServices().getReport(table.getSelectedRow()));
        } catch (ConnectionFailedException ex) {
            GuiManager.handleBadDatabaseConnection(this);
        }
    }

    private void btnExport(ActionEvent e) {
        try {
            int selection = table.getSelectedRow();
            if (selection == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar algún reporte.");
                return;
            }
            ServicesLocator.reportServices().generateReport(ServicesLocator.reportServices().getReport(table.getSelectedRow()));
        } catch (ConnectionFailedException ex) {
            GuiManager.handleBadDatabaseConnection(this);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        btnPreview = new JButton();
        btnExport = new JButton();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(16, 39));
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        setResizable(false);
        setModal(true);
        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[grow,fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[top]"));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table);
                }
                contentPanel.add(scrollPane1, "cell 0 0 1 2");

                //---- btnPreview ----
                btnPreview.setText("Vista Previa");
                btnPreview.addActionListener(e -> btnPreview(e));
                contentPanel.add(btnPreview, "cell 1 0");

                //---- btnExport ----
                btnExport.setText("Exportar");
                btnExport.addActionListener(e -> btnExport(e));
                contentPanel.add(btnExport, "cell 1 1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("Volver");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable table;
    private JButton btnPreview;
    private JButton btnExport;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
