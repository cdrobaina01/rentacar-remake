/*
 * Created by JFormDesigner on Sun May 21 20:42:03 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.dto.RolDTO;
import cu.edu.cujae.structdb.dto.TouristDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.ViewWindow;
import cu.edu.cujae.structdb.gui.insert.AuxiliaryInsertWindow;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.Validator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

import static cu.edu.cujae.structdb.utils.Validator.*;

/**
 * @author Hyzoka
 */
public class TouristInsertWindow extends JDialog{
    private AuxiliaryDTO country;
    private TouristDTO dto;
    private List<AuxiliaryDTO> countrys;
    private boolean isUpdating;
    public TouristInsertWindow(Window owner, Object dto) {
        initComponents();
        this.setSize(400, 320);
        this.dto = (TouristDTO)dto;
        //se actualiza la lista de paises y se actualizan los combobox
        UpdateComboCountry();
        UpdateComboSex();
        DefineIsUpdate();

        if(isUpdating) {
            tFPassport.setText(((TouristDTO) dto).getPassport());
            tFContact.setText(((TouristDTO) dto).getContact());
            tFAge.setText(String.valueOf(((TouristDTO) dto).getAge()));
            tFName.setText(((TouristDTO) dto).getName());

            comboCountry.setSelectedIndex(((TouristDTO) dto).getCountry().getId()-1);
            if(((TouristDTO) dto).getSex()== "M"){
                comboSex.setSelectedIndex(0);
            }
            else {
                comboSex.setSelectedIndex(1);
            }
        }
    }

    private void UpdateComboSex() {
        comboSex.addItem("M");
        comboSex.addItem("F");
    }

    private void DefineIsUpdate() {
        if(this.dto.getPassport()==null){
            isUpdating = false;
        }
        else{
            isUpdating = true;
        }
    }
    private void UpdateComboCountry() {
        try {
            countrys = ServicesLocator.countryServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        comboCountry.removeAllItems();
        for (int i = 0; i < countrys.size(); i++) {
            comboCountry.addItem(countrys.get(i).getName());
        }
    }

    private void insert() {
        if (ValidateNullFields()) return;
        System.out.println(tFName.getText());
        dto.setName(tFName.getText());
        dto.setAge(Integer.parseInt(tFAge.getText()));
        dto.setPassport(tFPassport.getText());
        dto.setContact(tFContact.getText());
        if(comboSex.getSelectedIndex()==0){
            dto.setSex("M");
        }
        else {
            dto.setSex("F");
        }
        dto.setCountry(countrys.get(comboCountry.getSelectedIndex()));

        try {
            ServicesLocator.touristServices().insert(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Turista registrado exitosamente.");
        this.dispose();
    }

    private boolean ValidateNullFields() {
        if (tFPassport.getText().isBlank() || !validatePassport(tFPassport.getText())) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir un pasaporte válido.");
            return true;
        }
        if (tFAge.getText().isBlank() || !validateAge(tFAge.getText())){
            JOptionPane.showMessageDialog(okButton, "Debe introducir una edad válida entre 18 y 110.");
            return true;
        }
        if (tFName.getText().isBlank() || !validateName(tFName.getText())) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir nombre y apellidos válidos.");
            return true;
        }
        if (tFContact.getText().isBlank() || !validatePhoneNumber(tFContact.getText())) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir un número de teléfono válido.");
            return true;
        }
        if(comboSex.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(okButton, "Debe introducir un sexo.");
            return true;
        }
        if(comboCountry.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(okButton, "Debe introducir un país.");
            return true;
        }
        return false;
    }

    private void ok(ActionEvent e) {
        if (isUpdating) {
            update();
        } else {
            insert();
        }
    }

    private void update() {
        if (ValidateNullFields()) return;
        dto.setName(tFName.getText());
        dto.setAge(Integer.parseInt(tFAge.getText()));
        dto.setPassport(tFPassport.getText());
        dto.setContact(tFContact.getText());
        if(comboSex.getSelectedIndex()==0){
            dto.setSex("M");
        }
        else {
            dto.setSex("F");
        }
        dto.setCountry(countrys.get(comboCountry.getSelectedIndex()));

        try {
            ServicesLocator.touristServices().update(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Turista actualizado exitosamente.");
        this.dispose();
    }

    private void cancel(ActionEvent e) {
        dispose();
    }

    private void addCountry(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.insertAuxiliary, this, TableType.country);
        UpdateComboCountry();

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jean Michel Ruiz Remis
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        tFPassport = new JTextField();
        label2 = new JLabel();
        tFName = new JTextField();
        label3 = new JLabel();
        tFAge = new JTextField();
        label5 = new JLabel();
        comboSex = new JComboBox();
        label6 = new JLabel();
        comboCountry = new JComboBox();
        addCountry = new JButton();
        label4 = new JLabel();
        tFContact = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Insertar Turista");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) )
            ; dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[118,fill]" +
                    "[grow,fill]",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow]" +
                    "[grow,fill]" +
                    "[grow]" +
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("Pasaporte");
                contentPanel.add(label1, "cell 0 0,alignx left,growx 0");
                contentPanel.add(tFPassport, "cell 1 0");

                //---- label2 ----
                label2.setText("Nombre y apellidos");
                contentPanel.add(label2, "cell 0 1,alignx left,growx 0");
                contentPanel.add(tFName, "cell 1 1");

                //---- label3 ----
                label3.setText("Edad");
                contentPanel.add(label3, "cell 0 2,alignx left,growx 0");
                contentPanel.add(tFAge, "cell 1 2");

                //---- label5 ----
                label5.setText("Sexo");
                contentPanel.add(label5, "cell 0 3");
                contentPanel.add(comboSex, "cell 1 3");

                //---- label6 ----
                label6.setText("Pa\u00eds");
                contentPanel.add(label6, "cell 0 4");
                contentPanel.add(comboCountry, "cell 1 4");

                //---- addCountry ----
                addCountry.setText("Insertar");
                addCountry.addActionListener(e -> addCountry(e));
                contentPanel.add(addCountry, "cell 1 4");

                //---- label4 ----
                label4.setText("Contacto");
                contentPanel.add(label4, "cell 0 5,alignx left,growx 0");
                contentPanel.add(tFContact, "cell 1 5");
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
    // Generated using JFormDesigner Evaluation license - Jean Michel Ruiz Remis
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField tFPassport;
    private JLabel label2;
    private JTextField tFName;
    private JLabel label3;
    private JTextField tFAge;
    private JLabel label5;
    private JComboBox comboSex;
    private JLabel label6;
    private JComboBox comboCountry;
    private JButton addCountry;
    private JLabel label4;
    private JTextField tFContact;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
