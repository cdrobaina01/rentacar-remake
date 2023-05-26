/*
 * Created by JFormDesigner on Sun May 21 20:55:31 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.dto.CarDTO;
import cu.edu.cujae.structdb.dto.DriverDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.ViewWindow;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

import static cu.edu.cujae.structdb.utils.Validator.validateDNI;
import static cu.edu.cujae.structdb.utils.Validator.validateName;

/**
 * @author Hyzoka
 */
public class DriverInsertWindow extends JDialog {
    private DriverDTO dto;
    private List<AuxiliaryDTO> category;
    private boolean isUpdating;
    public DriverInsertWindow(Window owner, Object dto) {
        initComponents();
        this.setSize(480,320);
        this.dto = (DriverDTO) dto;
        //se actualiza la lista categorias y se actualiza el combobox
        UpdateComboCategory();
        DefineIsUpdate();

        if(isUpdating){
            tFDNI.setText(((DriverDTO) dto).getDni());
            tFDNI.setEditable(false);
            tFAdreess.setText(((DriverDTO) dto).getAddress());
            tFName.setText(((DriverDTO) dto).getName());
            comboCategory.setSelectedItem(((DriverDTO) dto).getCategory());
        }
        this.setResizable(false);
    }

    private void DefineIsUpdate() {
        if(this.dto.getDni()==null){
            isUpdating = false;
        }
        else{
            isUpdating = true;
        }
    }
    private void UpdateComboCategory() {
        comboCategory.removeAllItems();
        try {
            category = ServicesLocator.categoryServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        for (int i = 0; i< category.size(); i++){
            comboCategory.addItem(category.get(i).getName());
        }
    }

    private void insert() {
        if (ValidateNullFields()) return;
        dto.setDni(tFDNI.getText());
        dto.setName(tFName.getText());
        dto.setAddress(tFAdreess.getText());
        dto.setCategory(category.get(comboCategory.getSelectedIndex()));
        try {
            ServicesLocator.driverServices().insert(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Conductor registrado exitosamente.");
        this.dispose();
    }

    private boolean ValidateNullFields() {
        if (tFDNI.getText().isBlank() || !validateDNI(tFDNI.getText())) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir una DNI.");
            return true;
        }
        if (tFName.getText().isBlank() || !validateName(tFName.getText())) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir una nombre.");
            return true;
        }
        if (tFAdreess.getText().isBlank()) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir una dirección.");
            return true;
        }
        if (comboCategory.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir una categoría.");
            return true;
        }
        return false;
    }

    private void update() {
        if (ValidateNullFields()) return;
        dto.setDni(tFDNI.getText());
        dto.setName(tFName.getText());
        dto.setAddress(tFAdreess.getText());
        dto.setCategory(category.get(comboCategory.getSelectedIndex()));
        try {
            ServicesLocator.driverServices().update(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Conductor registrado exitosamente.");
        this.dispose();
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

    private void addCategory(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.insertAuxiliary, this, TableType.category);
        UpdateComboCategory();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        tFDNI = new JTextField();
        label5 = new JLabel();
        comboCategory = new JComboBox();
        addCategory = new JButton();
        label2 = new JLabel();
        tFName = new JTextField();
        label6 = new JLabel();
        tFAdreess = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Insertar Conductor");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[85,grow,fill]" +
                    "[301,grow,fill]rel" +
                    "[grow,fill]",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("DNI");
                contentPanel.add(label1, "cell 0 0,alignx left,growx 0");
                contentPanel.add(tFDNI, "cell 1 0 2 1");

                //---- label5 ----
                label5.setText("Categor\u00eda");
                contentPanel.add(label5, "cell 0 1");
                contentPanel.add(comboCategory, "cell 1 1");

                //---- addCategory ----
                addCategory.setText("Insertar Categor\u00eda");
                addCategory.addActionListener(e -> addCategory(e));
                contentPanel.add(addCategory, "cell 2 1");

                //---- label2 ----
                label2.setText("Nombre y apellidos");
                contentPanel.add(label2, "cell 0 2,alignx left,growx 0");
                contentPanel.add(tFName, "cell 1 2 2 1");

                //---- label6 ----
                label6.setText("Direcci\u00f3n");
                contentPanel.add(label6, "cell 0 3");
                contentPanel.add(tFAdreess, "cell 1 3 2 1");
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
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField tFDNI;
    private JLabel label5;
    private JComboBox comboCategory;
    private JButton addCategory;
    private JLabel label2;
    private JTextField tFName;
    private JLabel label6;
    private JTextField tFAdreess;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
