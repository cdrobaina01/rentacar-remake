/*
 * Created by JFormDesigner on Sat May 13 10:03:53 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.ViewWindow;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author cdrobaina01
 */
public class AuxiliaryInsertWindow extends JDialog {
    private TableType type;
    private AuxiliaryDTO dto;
    public AuxiliaryInsertWindow(Object type, Window owner) {
        super(owner);
        initComponents();
        setLocationRelativeTo(null);
        this.type = (TableType) type;
        this.dto = new AuxiliaryDTO();
        switch (this.type) {
            case situation:
                this.setTitle("Registrar Situación de los autos");
                break;
            case category:
                this.setTitle("Registrar Categoría de Licencia");
                break;
            case country:
                this.setTitle("Registrar País de los Clientes");
                break;
            case brand:
                this.setTitle("Registrar Marcas de los Autos");
                break;
            case paymethod:
                this.setTitle("Registrar Método de Pago");
                break;
        }
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void ok(ActionEvent e) {
        if (txtFld.getText().isBlank()) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir un nombre.");
            return;
        }
        dto.setName(txtFld.getText());
        try {
            switch (type) {
                case situation:
                    ServicesLocator.situationServices().insert(dto);
                    break;
                case category:
                    ServicesLocator.categoryServices().insert(dto);
                    break;
                case country:
                    ServicesLocator.countryServices().insert(dto);
                    break;
                case brand:
                    ServicesLocator.brandServices().insert(dto);
                    break;
                case paymethod:
                    ServicesLocator.payMethodServices().insert(dto);
                    break;
            }
        } catch (ConnectionFailedException exception) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        String message = new String();
        switch (type) {
            case situation: message = "Situación de los autos creada";
            break;
            case brand:
                message = "Marca registrada";
                break;
            case paymethod:
                message = "Método de pago creado";
                break;
            case country:
                message = "País registrado";
                break;
            case category:
                message ="Categoría de licencia registrada";
                break;
        }
        JOptionPane.showMessageDialog(this, message + " exitosamente.");
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        txtFld = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setMaximumSize(new Dimension(400, 150));
        setMinimumSize(new Dimension(400, 150));
        setPreferredSize(new Dimension(400, 150));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[45,fill]" +
                    "[grow,fill]",
                    // rows
                    "[]"));

                //---- label1 ----
                label1.setText("Nombre: ");
                contentPanel.add(label1, "cell 0 0");
                contentPanel.add(txtFld, "cell 1 0");
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
    private JTextField txtFld;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
