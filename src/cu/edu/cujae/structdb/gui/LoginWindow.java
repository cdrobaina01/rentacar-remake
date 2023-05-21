/*
 * Created by JFormDesigner on Fri May 12 09:35:46 CDT 2023
 */

package cu.edu.cujae.structdb.gui;

import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.services.AuthService;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author cdrobaina01
 */
public class LoginWindow extends JFrame {
    public LoginWindow() {
        initComponents();
        lblErrorUser.setVisible(false);
        lblErrorPass.setVisible(false);
    }

    private void btnLoginKeyPressed(KeyEvent e) {
        if (e.equals(KeyEvent.VK_ENTER)) {
            login();
        }
    }

    private void btnLogin(ActionEvent e) {
        login();
    }

    private void login() {
        lblErrorUser.setVisible(false);
        lblErrorPass.setVisible(false);

        UserDTO credentials = new UserDTO();
        credentials.setUsername(txtFldUsername.getText());
        credentials.setPassword(String.valueOf(txtFldPassword.getPassword()));
        AuthService.LoginResult result = null;
        try {
            result = ServicesLocator.authService().login(credentials);
            switch (result) {
                case wrongUsername:
                    lblErrorUser.setVisible(true);
                    break;
                case wrongPassword:
                    lblErrorPass.setVisible(true);
                    break;
                case correct:
                    if (ServicesLocator.userServices().checkDefaultPassword(credentials.getPassword())) {
                        GuiManager.openDialog(GuiManager.DialogType.changePassword, this, credentials);
                        dispose();
                    } else {
                        GuiManager.openFrame(GuiManager.FrameType.main, this);
                    }
                    break;
            }
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        txtFldUsername = new JTextField();
        hSpacer2 = new JPanel(null);
        label2 = new JLabel();
        txtFldPassword = new JPasswordField();
        hSpacer1 = new JPanel(null);
        lblErrorUser = new JLabel();
        lblErrorPass = new JLabel();
        buttonBar = new JPanel();
        btnLogin = new JButton();

        //======== this ========
        setTitle("Inicio de Sesi\u00f3n");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon("D:\\workspaces\\cujae\\rentacar\\src\\main\\java\\cu\\edu\\cujae\\structdb\\gui\\icons\\rent.png").getImage());
        setMaximumSize(new Dimension(400, 200));
        setMinimumSize(new Dimension(400, 200));
        setPreferredSize(new Dimension(400, 200));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new Font ("Dia\u006cog" , Font .BOLD ,
            12 ), Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[fill]" +
                    "[left]" +
                    "[182,grow,fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- label1 ----
                label1.setText("Nombre de Usuario:");
                label1.setLabelFor(txtFldUsername);
                contentPanel.add(label1, "cell 1 0");
                contentPanel.add(txtFldUsername, "cell 2 0");
                contentPanel.add(hSpacer2, "cell 0 0 1 6");

                //---- label2 ----
                label2.setText("Contrase\u00f1a:");
                label2.setLabelFor(txtFldPassword);
                contentPanel.add(label2, "cell 1 2,alignx right,growx 0");
                contentPanel.add(txtFldPassword, "cell 2 2");
                contentPanel.add(hSpacer1, "cell 3 0 1 6");

                //---- lblErrorUser ----
                lblErrorUser.setText("Usuario incorrecto");
                lblErrorUser.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                lblErrorUser.setForeground(new Color(0xff3333));
                contentPanel.add(lblErrorUser, "cell 2 1");

                //---- lblErrorPass ----
                lblErrorPass.setText("Contrase\u00f1a incorrecta");
                lblErrorPass.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                lblErrorPass.setForeground(new Color(0xff3333));
                contentPanel.add(lblErrorPass, "cell 2 3");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[grow,center]",
                    // rows
                    null));

                //---- btnLogin ----
                btnLogin.setText("Iniciar Sesi\u00f3n");
                btnLogin.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        btnLoginKeyPressed(e);
                    }
                });
                btnLogin.addActionListener(e -> btnLogin(e));
                buttonBar.add(btnLogin, "cell 0 0");
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
    private JTextField txtFldUsername;
    private JPanel hSpacer2;
    private JLabel label2;
    private JPasswordField txtFldPassword;
    private JPanel hSpacer1;
    private JLabel lblErrorUser;
    private JLabel lblErrorPass;
    private JPanel buttonBar;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
