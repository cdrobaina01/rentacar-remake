/*
 * Created by JFormDesigner on Fri May 19 17:43:27 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert;

import cu.edu.cujae.structdb.dto.RolDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.ViewWindow;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author cdrobaina01
 */
public class RolInsertWindow extends JDialog {
    
    RolDTO dto;
    public RolInsertWindow(Window owner, Object dto) {
        super(owner);
        initComponents();
        this.dto = (RolDTO) dto;
        txtFldName.setText(this.dto.getName());
        txtFldDescription.setText(this.dto.getDescription());
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void ok(ActionEvent e) {
        dto.setName(txtFldName.getText());
        dto.setDescription(txtFldDescription.getText());
        try {
            ServicesLocator.rolServices().update(dto);
        } catch (ConnectionFailedException ex) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        Window owner = getOwner();
        if (owner instanceof ViewWindow) {
            ((ViewWindow) owner).refresh();
        }
        JOptionPane.showMessageDialog(this, "Rol actualizado exitosamente.");
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        txtFldName = new JTextField();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtFldDescription = new JTextArea();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Modificar Rol");
        setMinimumSize(new Dimension(400, 300));
        setMaximumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(200, 300));
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );
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
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("Nombre: ");
                label1.setLabelFor(txtFldName);
                contentPanel.add(label1, "cell 0 0");
                contentPanel.add(txtFldName, "cell 1 0");

                //---- label2 ----
                label2.setText("Descripci\u00f3n: ");
                label2.setLabelFor(txtFldDescription);
                contentPanel.add(label2, "cell 0 1");

                //======== scrollPane1 ========
                {

                    //---- txtFldDescription ----
                    txtFldDescription.setLineWrap(true);
                    scrollPane1.setViewportView(txtFldDescription);
                }
                contentPanel.add(scrollPane1, "cell 1 1");
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
    private JScrollPane scrollPane1;
    private JTextArea txtFldDescription;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
