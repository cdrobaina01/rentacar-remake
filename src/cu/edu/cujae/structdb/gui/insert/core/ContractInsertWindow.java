/*
 * Created by JFormDesigner on Sun May 21 20:55:02 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import com.toedter.calendar.*;
import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.dto.ContractDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

import static cu.edu.cujae.structdb.utils.Validator.validatePlate;

/**
 * @author Hyzoka
 */
public class ContractInsertWindow extends JDialog {
    private JCalendar startDate;
    private JCalendar endDate;
    private boolean isUpdating;
    private List<AuxiliaryDTO> payMethods;
    private ContractDTO dto;

    public ContractInsertWindow(Window owner, Object dto) {
        initComponents();
        checkDriver.setSelected(false);
        tFDriverDni.setEditable(false);

        this.dto = (ContractDTO) dto;
        UpdateComboPayMethod();
        DefineIsUpdate();
        if (isUpdating) {
            tFPlate.setText(((ContractDTO) dto).getPlate());
            tFPlate.setEditable(false);

            LocalDate localDate = ((ContractDTO) dto).getStartDate();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            startDate.setDate(date);
            startDate.setEnabled(false);

            comboPayMethod.setSelectedItem(((ContractDTO) dto).getPayMethod().getName());
            tFPassport.setText(((ContractDTO) dto).getPassport());

            localDate = ((ContractDTO) dto).getEndDate();
            date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            endDate.setDate(date);

            tFDriverDni.setText(((ContractDTO) dto).getDriver());
            if (((ContractDTO) dto).getDriver() != null) {
                checkDriver.setSelected(true);
            }
        }
        this.setResizable(false);

    }

    private void UpdateComboPayMethod() {
        comboPayMethod.removeAllItems();
        try {
            payMethods = ServicesLocator.payMethodServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        for (int i = 0; i < payMethods.size(); i++) {
            comboPayMethod.addItem(payMethods.get(i).getName());
        }
    }

    private void checkDriverItemStateChanged(ItemEvent e) {
        if (checkDriver.isSelected()) {
            tFDriverDni.setEditable(true);
        } else {
            tFDriverDni.setText("");
            tFDriverDni.setEditable(false);
        }
    }

    private void ok(ActionEvent e) {
        if (isUpdating) {
            update();
        } else {
            insert();
        }
    }

    private void cancel(ActionEvent e) {
        dispose();
    }



    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        tFPlate = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        comboPayMethod = new JComboBox();
        label2 = new JLabel();
        tFPassport = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        checkDriver = new JCheckBox();
        tFDriverDni = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Insertar Contrato");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) )
            ; dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[112,fill]rel" +
                    "[grow,fill]rel",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("Placa del auto");
                contentPanel.add(label1, "cell 0 0,alignx left,growx 0");
                contentPanel.add(tFPlate, "cell 1 0");

                //---- label3 ----
                label3.setText("Fecha de Inicio");
                contentPanel.add(label3, "cell 0 1,align left top,grow 0 0");

                //---- label4 ----
                label4.setText("Forma de pago");
                contentPanel.add(label4, "cell 0 2,alignx left,growx 0");
                contentPanel.add(comboPayMethod, "cell 1 2");

                //---- label2 ----
                label2.setText("Pasaporte del cliente");
                contentPanel.add(label2, "cell 0 3,alignx left,growx 0");
                contentPanel.add(tFPassport, "cell 1 3");

                //---- label5 ----
                label5.setText("Fecha de Fin");
                contentPanel.add(label5, "cell 0 4,aligny top,growy 0");

                //---- label6 ----
                label6.setText("DNI Conductor");
                contentPanel.add(label6, "cell 0 5");

                //---- checkDriver ----
                checkDriver.addItemListener(e -> checkDriverItemStateChanged(e));
                contentPanel.add(checkDriver, "cell 1 5,width 20:20:20");
                contentPanel.add(tFDriverDni, "cell 1 5");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Aceptar");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancelar");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        this.setSize(800, 750);
        startDate = new JCalendar();
        endDate = new JCalendar();
        contentPanel.add(startDate, "cell 1 1");
        contentPanel.add(endDate, "cell 1 4");
    }

    private void DefineIsUpdate() {
        if (this.dto.getPlate() == null) {
            isUpdating = false;
        } else {
            isUpdating = true;
        }
    }

    private void insert() {
        dto.setPlate(tFPlate.getText());
        dto.setPassport(tFPassport.getText());
        dto.setStartDate(LocalDate.of(startDate.getDate().getYear() + 1900, startDate.getDate().getMonth(), startDate.getDate().getDay()));
        dto.setEndDate(LocalDate.of(endDate.getDate().getYear() + 1900, endDate.getDate().getMonth(), endDate.getDate().getDay()));
        dto.setPayMethod(payMethods.get(comboPayMethod.getSelectedIndex()));
        if(checkDriver.isSelected()){
            dto.setDriver(tFDriverDni.getText());
        }

        try {
            ServicesLocator.contractServices().open(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        JOptionPane.showMessageDialog(this, "Contrato abierto exitosamente.");
        this.dispose();
    }

    private void update() {

    }

    //private boolean

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField tFPlate;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboPayMethod;
    private JLabel label2;
    private JTextField tFPassport;
    private JLabel label5;
    private JLabel label6;
    private JCheckBox checkDriver;
    private JTextField tFDriverDni;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
