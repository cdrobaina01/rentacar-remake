/*
 * Created by JFormDesigner on Fri May 19 18:40:49 CDT 2023
 */

package cu.edu.cujae.structdb.gui;

import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author cdrobaina01
 */
public class PasswordChangeWindow extends JDialog {
    private UserDTO dto;
    public PasswordChangeWindow(Window owner, Object dto) {
        super(owner);
        initComponents();
        this.dto = (UserDTO) dto;
        lblWrongPass.setVisible(false);
        lblNotMatch.setVisible(false);
    }

    private void ok(ActionEvent e) {
        lblNotMatch.setVisible(false);
        lblWrongPass.setVisible(false);
        if (!ServicesLocator.userServices().verifyPassword(String.valueOf(txtFldActual.getPassword()), dto.getPassword())) {
            lblWrongPass.setVisible(true);
        } else if (!String.valueOf(txtFldNew.getPassword()).equals(String.valueOf(txtFldConfirm.getPassword()))) {
            lblNotMatch.setVisible(true);
        } else {
            dto.setPassword(String.valueOf(txtFldNew.getPassword()));
            try {
                ServicesLocator.userServices().update(dto);
            } catch (ConnectionFailedException ex) {
                GuiManager.handleBadDatabaseConnection(this);
            }
            if (getOwner() instanceof LoginWindow) {
                JOptionPane.showMessageDialog(this, "Contraseña cambiada con exitosamente");
                GuiManager.openFrame(GuiManager.FrameType.main, this);
            }
            dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        txtFldActual = new JPasswordField();
        lblWrongPass = new JLabel();
        label2 = new JLabel();
        txtFldNew = new JPasswordField();
        label3 = new JLabel();
        txtFldConfirm = new JPasswordField();
        lblNotMatch = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setMaximumSize(new Dimension(400, 260));
        setMinimumSize(new Dimension(400, 260));
        setPreferredSize(new Dimension(400, 260));
        setTitle("Cambiar Contrase\u00f1a");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new Font ("D\u0069al\u006fg" , Font .BOLD ,12 ), Color. red) ,dialogPane. getBorder( )) )
            ; dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
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
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- label1 ----
                label1.setText("Contrase\u00f1a actual: ");
                contentPanel.add(label1, "cell 0 0");
                contentPanel.add(txtFldActual, "cell 1 0");

                //---- lblWrongPass ----
                lblWrongPass.setText("Error: Contrase\u00f1a incorrecta ");
                lblWrongPass.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                lblWrongPass.setForeground(new Color(0xff3300));
                contentPanel.add(lblWrongPass, "cell 1 1");

                //---- label2 ----
                label2.setText("Contrase\u00f1a nueva:");
                contentPanel.add(label2, "cell 0 2");
                contentPanel.add(txtFldNew, "cell 1 2");

                //---- label3 ----
                label3.setText("Confirmar contrase\u00f1a: ");
                contentPanel.add(label3, "cell 0 3");
                contentPanel.add(txtFldConfirm, "cell 1 3");

                //---- lblNotMatch ----
                lblNotMatch.setText("Error: Las contrase\u00f1as no coinciden");
                lblNotMatch.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                lblNotMatch.setForeground(new Color(0xff3300));
                contentPanel.add(lblNotMatch, "cell 1 4");
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
                okButton.setText("OK");
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
    private JLabel label1;
    private JPasswordField txtFldActual;
    private JLabel lblWrongPass;
    private JLabel label2;
    private JPasswordField txtFldNew;
    private JLabel label3;
    private JPasswordField txtFldConfirm;
    private JLabel lblNotMatch;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
