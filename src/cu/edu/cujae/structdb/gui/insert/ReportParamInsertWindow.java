/*
 * Created by JFormDesigner on Thu May 25 21:06:44 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cu.edu.cujae.structdb.gui.ReportsView;
import cu.edu.cujae.structdb.services.ReportService;
import net.miginfocom.swing.*;

/**
 * @author carlosd.inc
 */
public class ReportParamInsertWindow extends JDialog {
    private ReportService.Report report;
    public ReportParamInsertWindow(Window owner, Object report) {
        super(owner);
        initComponents();
        this.report = (ReportService.Report) report;
        txtFldName.setText(((ReportService.Report) report).localeName);
        txtFldName.setEnabled(false);
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        for (String choice : ((ReportService.Report) report).choices) {
            comboModel.addElement(choice);
        }
        cmBox.setModel(comboModel);
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void ok(ActionEvent e) {
        ReportsView view = (ReportsView) getOwner();
        view.doAction(report, (String) cmBox.getSelectedItem());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        txtFldName = new JTextField();
        label2 = new JLabel();
        cmBox = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Parametrizar Reporte");
        setMaximumSize(new Dimension(300, 180));
        setMinimumSize(new Dimension(300, 180));
        setPreferredSize(new Dimension(300, 180));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.
            red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[fill]" +
                    "[grow,fill]",
                    // rows
                    "[]" +
                    "[]"));

                //---- label1 ----
                label1.setText("Reporte: ");
                contentPanel.add(label1, "cell 0 0");
                contentPanel.add(txtFldName, "cell 1 0");

                //---- label2 ----
                label2.setText("Par\u00e1metro: ");
                contentPanel.add(label2, "cell 0 1");
                contentPanel.add(cmBox, "cell 1 1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, "cell 1 0");
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
    private JLabel label1;
    private JTextField txtFldName;
    private JLabel label2;
    private JComboBox cmBox;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
