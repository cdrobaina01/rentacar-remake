/*
 * Created by JFormDesigner on Fri May 26 03:23:35 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;

import com.toedter.calendar.JCalendar;
import cu.edu.cujae.structdb.dto.ContractDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

/**
 * @author Hyzoka
 */
public class CloseContractWindow extends JFrame {
    JCalendar deliveryDate;
    ContractDTO dto;
    public CloseContractWindow(Window owner, Object dto) {
        this.dto = (ContractDTO) dto;
        initComponents();
        deliveryDate = new JCalendar();
        LocalDate localDate = ((ContractDTO) dto).getStartDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        tFPlate.setText(((ContractDTO) dto).getPlate());
        tFPlate.setEditable(false);
        contentPanel.add(deliveryDate, "cell 1 2");
        tFStartDay.setText(((ContractDTO) dto).getStartDate().toString());
        tFStartDay.setEditable(false);
    }

    private void ok(ActionEvent e) {
        dto.setDeliveryDate(LocalDate.of(deliveryDate.getDate().getYear() + 1900, deliveryDate.getDate().getMonth(), deliveryDate.getDate().getDay()));
        dto.setEndKm(Integer.parseInt(tFKmFinal.getText()));
        try {
            ServicesLocator.contractServices().close(dto);
        } catch (ConnectionFailedException ex) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        this.dispose();
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label4 = new JLabel();
        tFPlate = new JTextField();
        label3 = new JLabel();
        tFStartDay = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        tFKmFinal = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Cerrar Contrato");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
            swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border
            .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog"
            ,java.awt.Font.BOLD,12),java.awt.Color.red),dialogPane. getBorder
            ()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
            .beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException
            ();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[135,fill]" +
                    "[219,fill]",
                    // rows
                    "[76]" +
                    "[76]" +
                    "[76]" +
                    "[grow]"));

                //---- label4 ----
                label4.setText("Placa");
                contentPanel.add(label4, "cell 0 0");
                contentPanel.add(tFPlate, "cell 1 0");

                //---- label3 ----
                label3.setText("Fecha de inicio");
                contentPanel.add(label3, "cell 0 1");
                contentPanel.add(tFStartDay, "cell 1 1");

                //---- label1 ----
                label1.setText("Fecha de entrega");
                contentPanel.add(label1, "cell 0 2");

                //---- label2 ----
                label2.setText("Km Final");
                contentPanel.add(label2, "cell 0 3");
                contentPanel.add(tFKmFinal, "cell 1 3");
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
                okButton.setText("Aceptar");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("Cancelar");
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
    private JLabel label4;
    private JTextField tFPlate;
    private JLabel label3;
    private JTextField tFStartDay;
    private JLabel label1;
    private JLabel label2;
    private JTextField tFKmFinal;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
