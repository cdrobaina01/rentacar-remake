/*
 * Created by JFormDesigner on Sun May 21 20:55:16 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;

/**
 * @author Hyzoka
 */
public class CarInsertWindow extends JDialog {
    public CarInsertWindow(Window owner, Object dto) {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        comboBox3 = new JComboBox();
        label3 = new JLabel();
        comboBox4 = new JComboBox();
        label5 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField5 = new JTextField();
        label6 = new JLabel();
        comboBox1 = new JComboBox();
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
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
            EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing
            .border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),
            java.awt.Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener()
            {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))
            throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[85,grow,fill]" +
                    "[grow,fill]rel" +
                    "[grow,fill]" +
                    "[grow,fill]",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("Placa");
                contentPanel.add(label1, "cell 0 0,alignx left,growx 0");
                contentPanel.add(textField1, "cell 1 0");

                //---- label2 ----
                label2.setText("Marca");
                contentPanel.add(label2, "cell 2 0,alignx left,growx 0");
                contentPanel.add(comboBox3, "cell 3 0");

                //---- label3 ----
                label3.setText("Modelo");
                contentPanel.add(label3, "cell 0 1,alignx left,growx 0");
                contentPanel.add(comboBox4, "cell 1 1");

                //---- label5 ----
                label5.setText("Cantidad de KM");
                contentPanel.add(label5, "cell 2 1");
                contentPanel.add(textField2, "cell 3 1");

                //---- label4 ----
                label4.setText("Color");
                contentPanel.add(label4, "cell 0 2,alignx left,growx 0");
                contentPanel.add(textField5, "cell 1 2");

                //---- label6 ----
                label6.setText("Situaci\u00f3n");
                contentPanel.add(label6, "cell 2 2");
                contentPanel.add(comboBox1, "cell 3 2");
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
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancelar");
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
    private JTextField textField1;
    private JLabel label2;
    private JComboBox comboBox3;
    private JLabel label3;
    private JComboBox comboBox4;
    private JLabel label5;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField5;
    private JLabel label6;
    private JComboBox comboBox1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
