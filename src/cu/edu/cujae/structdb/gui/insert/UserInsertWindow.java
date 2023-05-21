/*
 * Created by JFormDesigner on Tue May 16 17:35:34 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert;

import cu.edu.cujae.structdb.dto.RolDTO;
import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.ViewWindow;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author carlosd.inc
 */
public class UserInsertWindow extends JDialog {
    private UserDTO dto;
    private List<RolDTO> roles;
    private boolean isUpdating;
    public UserInsertWindow(Window owner, Object dto) {
        super(owner);
        initComponents();
        setLocationRelativeTo(null);
        this.dto = (UserDTO) dto;

        isUpdating = this.dto.getId() != null;
        if (isUpdating) {
            txtFld.setEnabled(false);
            txtFld.setText(((UserDTO) dto).getUsername());
            this.setTitle("Actualizar usuario");
        } else {
            this.setTitle("Crear nuevo usuario");
        }

        try {
            roles = ServicesLocator.rolServices().getAll();
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        for (RolDTO rol : roles) {
            cmBox.addItem(rol.getName());
        }
        checkOk();
    }

    private void checkOk() {
        boolean activate = false;
        if (!txtFld.getText().isBlank()) {
            activate = true;
        }
        okButton.setEnabled(activate);
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void ok(ActionEvent e) {
       if (isUpdating) {
           update();
       } else {
           insert();
       }
    }

    private void update() {
        dto.setUsername(txtFld.getText());
        dto.setRol(roles.get(cmBox.getSelectedIndex()));
        try {
            ServicesLocator.userServices().update(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.");
        this.dispose();
    }
    
    private void insert() {
        if (txtFld.getText().isBlank()) {
            JOptionPane.showMessageDialog(okButton, "Debe introducir un nombre de usuario.");
            return;
        }
        dto.setUsername(txtFld.getText());
        dto.setRol(roles.get(cmBox.getSelectedIndex()));
        try {
            ServicesLocator.userServices().insert(dto);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        this.dispose();
    }

    private void thisKeyReleased(KeyEvent e) {
    }

    private void thisMouseClicked(MouseEvent e) {
    }

    private void txtFldKeyPressed(KeyEvent e) {
        checkOk();
    }

    private void cmBox(ActionEvent e) {
        checkOk();
    }

    private void txtFld(ActionEvent e) {
        checkOk();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        txtFld = new JTextField();
        label2 = new JLabel();
        cmBox = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setMaximumSize(new Dimension(400, 180));
        setMinimumSize(new Dimension(400, 180));
        setPreferredSize(new Dimension(400, 180));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                thisKeyReleased(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new Font ("D\u0069alog"
            , Font .BOLD ,12 ), Color. red) ,dialogPane. getBorder
            ( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
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
                label1.setText("Nombre de Usuario: ");
                contentPanel.add(label1, "cell 0 0");

                //---- txtFld ----
                txtFld.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        txtFldKeyPressed(e);
                    }
                });
                txtFld.addActionListener(e -> txtFld(e));
                contentPanel.add(txtFld, "cell 1 0");

                //---- label2 ----
                label2.setText("Rol: ");
                contentPanel.add(label2, "cell 0 1");

                //---- cmBox ----
                cmBox.addActionListener(e -> cmBox(e));
                contentPanel.add(cmBox, "cell 1 1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 9 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, "cell 10 0");
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
    private JLabel label2;
    private JComboBox cmBox;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
