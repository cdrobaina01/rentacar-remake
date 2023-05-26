/*
 * Created by JFormDesigner on Sun May 21 20:55:16 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.dto.CarDTO;
import cu.edu.cujae.structdb.dto.ModelDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.Validator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

import static cu.edu.cujae.structdb.utils.Validator.validatePlate;

/**
 * @author Hyzoka
 */
public class CarInsertWindow extends JDialog {
    private List<AuxiliaryDTO> brands;
    private List<ModelDTO> models;
    private List<AuxiliaryDTO> situations;
    private CarDTO dto;
    private boolean isUpdating;
    public CarInsertWindow(Window owner, Object dto) {
        initComponents();
        this.setSize(480,320);
        this.dto = (CarDTO) dto;
        //se actualiza la lista de Marcas y se actualizan los combobox
        UpdateComboBrand();
        UpdateComboModel();
        UpdateComboSituation();

        DefineIsUpdate();
        
        if(isUpdating){
            tFPlate.setText(((CarDTO) dto).getPlate());
            tFPlate.setEditable(false);
            tFKm.setText(Integer.toString(((CarDTO) dto).getCantKm()));
            tFColor.setText(((CarDTO) dto).getColor());
            comboSituation.setSelectedItem(((CarDTO) dto).getSituation().getName());
            comboBrand.setSelectedItem(((CarDTO) dto).getModel().getBrand().getName());
            comboModel.setSelectedItem(((CarDTO) dto).getModel().getName());
            this.setResizable(false);
        }
    }

    private void DefineIsUpdate() {
        if(this.dto.getPlate()==null){
            isUpdating = false;
        }
        else{
            isUpdating = true;
        }
    }

    private void UpdateComboSituation() {
        comboSituation.removeAllItems();
        try {
            situations = ServicesLocator.situationServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        for (int i = 0; i<situations.size();i++){
            comboSituation.addItem(situations.get(i).getName());
        }
    }

    private void UpdateComboBrand() {
        comboBrand.removeAllItems();
        try {
            brands = ServicesLocator.brandServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        for (int i = 0; i<brands.size(); i++){
            comboBrand.addItem(brands.get(i).getName());
        }
    }
    private void UpdateComboModel() {
        comboModel.removeAllItems();
        try {
            models = ServicesLocator.modelServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        for (int i = 0; i<models.size(); i++){
            if(models.get(i).getBrand().getName().equals(comboBrand.getSelectedItem())){
                comboModel.addItem(models.get(i).getName());
            }
        }
    }

    private void comboBrandItemStateChanged(ItemEvent e) {
        UpdateComboModel();
    }

    private void ok(ActionEvent e) {
        if (isUpdating) {
            update();
        } else {
            insert();
        }
    }

    private void insert() {
        if (ValidateNullFields()) return;
        dto.setPlate(tFPlate.getText());
        dto.setCantKm(Integer.parseInt(tFKm.getText()));
        dto.setColor(tFColor.getText());
        int idex = 0;
        for(int i = 0; i<models.size();i++){
            if (models.get(i).getName().equals(comboModel.getSelectedItem().toString())){
                dto.setModel(models.get(i));
                break;
            }
        }
        dto.setSituation(situations.get(comboSituation.getSelectedIndex()));


        try {
            ServicesLocator.carServices().insert(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        JOptionPane.showMessageDialog(this, "Vehículo registrado exitosamente.");
        this.dispose();
    }

    private boolean ValidateNullFields() {
        if (tFPlate.getText().isBlank() || !validatePlate(tFPlate.getText())) {
             JOptionPane.showMessageDialog(okButton, "Debe introducir una placa válida.");
            return true;
         }
        if (tFColor.getText().isBlank()) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir una color.");
            return true;
        }
        if (tFKm.getText().isBlank()) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir cantidad de kilometros.");
            return true;
        }
        if (comboModel.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir un modelo.");
            return true;
        }
        if(comboBrand.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(okButton, "Debe introducir una marca.");
            return true;
        }
        if(comboSituation.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(okButton, "Debe introducir una situación.");
            return true;
        }
        return false;
    }

    private void update() {
        if (ValidateNullFields()) return;
        dto.setPlate(tFPlate.getText());
        dto.setCantKm(Integer.parseInt(tFKm.getText()));
        dto.setColor(tFColor.getText());
        for(int i = 0; i<models.size();i++){
            if (models.get(i).getName().equals(comboModel.getSelectedItem().toString())){
                dto.setModel(models.get(i));
                break;
            }
        }
        dto.setSituation(situations.get(comboSituation.getSelectedIndex()));


        try {
            ServicesLocator.carServices().update(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        JOptionPane.showMessageDialog(this, "Vehículo actualizado exitosamente.");
        this.dispose();
        
    }

    private void cancel(ActionEvent e) {
        dispose();
    }

    private void addBrand(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.insertAuxiliary, this, TableType.brand);
        UpdateComboBrand();
    }

    private void addModel(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.insertModel, this, TableType.model);
        UpdateComboModel();
    }

    private void addSituation(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.insertAuxiliary, this, TableType.situation);
        UpdateComboSituation();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        tFPlate = new JTextField();
        label3 = new JLabel();
        comboBrand = new JComboBox();
        addBrand = new JButton();
        label2 = new JLabel();
        comboModel = new JComboBox();
        addModel = new JButton();
        label6 = new JLabel();
        comboSituation = new JComboBox();
        addSituation = new JButton();
        label5 = new JLabel();
        tFKm = new JTextField();
        label4 = new JLabel();
        tFColor = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Insertar Carro");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
            .EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax
            .swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,
            12),java.awt.Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans
            .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("borde\u0072".equals(e.
            getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[108,fill]" +
                    "[206,fill]" +
                    "[65,grow,fill]",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("Placa");
                contentPanel.add(label1, "cell 0 0,alignx left,growx 0");
                contentPanel.add(tFPlate, "cell 1 0 2 1");

                //---- label3 ----
                label3.setText("Marca");
                contentPanel.add(label3, "cell 0 1,alignx left,growx 0");

                //---- comboBrand ----
                comboBrand.addItemListener(e -> comboBrandItemStateChanged(e));
                contentPanel.add(comboBrand, "cell 1 1");

                //---- addBrand ----
                addBrand.setText("Insertar Marca");
                addBrand.addActionListener(e -> addBrand(e));
                contentPanel.add(addBrand, "cell 2 1");

                //---- label2 ----
                label2.setText("Modelo");
                contentPanel.add(label2, "cell 0 2,alignx left,growx 0");
                contentPanel.add(comboModel, "cell 1 2");

                //---- addModel ----
                addModel.setText("Insertar Modelo");
                addModel.addActionListener(e -> addModel(e));
                contentPanel.add(addModel, "cell 2 2");

                //---- label6 ----
                label6.setText("Situaci\u00f3n");
                contentPanel.add(label6, "cell 0 3");
                contentPanel.add(comboSituation, "cell 1 3");

                //---- addSituation ----
                addSituation.setText("Insertar Situaci\u00f3n");
                addSituation.addActionListener(e -> addSituation(e));
                contentPanel.add(addSituation, "cell 2 3");

                //---- label5 ----
                label5.setText("Cantidad de Km");
                contentPanel.add(label5, "cell 0 4");
                contentPanel.add(tFKm, "cell 1 4 2 1");

                //---- label4 ----
                label4.setText("Color");
                contentPanel.add(label4, "cell 0 5,alignx left,growx 0");
                contentPanel.add(tFColor, "cell 1 5 2 1");
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
    private JTextField tFPlate;
    private JLabel label3;
    private JComboBox comboBrand;
    private JButton addBrand;
    private JLabel label2;
    private JComboBox comboModel;
    private JButton addModel;
    private JLabel label6;
    private JComboBox comboSituation;
    private JButton addSituation;
    private JLabel label5;
    private JTextField tFKm;
    private JLabel label4;
    private JTextField tFColor;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
