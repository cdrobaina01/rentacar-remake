/*
 * Created by JFormDesigner on Mon May 22 17:42:12 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class FeeInsertWindow extends JDialog {
    public FeeInsertWindow(Window owner) {
        super(owner);
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
    }

    private void spinner1StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void spinner2StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jean Michel Ruiz Remis
        label1 = new JLabel();
        spinner1 = new JSpinner();
        label2 = new JLabel();
        label3 = new JLabel();
        spinner2 = new JSpinner();
        button1 = new JButton();

        //======== this ========
        setTitle("Actualizar Tarifa");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
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
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Regular");
        contentPane.add(label1, "cell 0 0");

        //---- spinner1 ----
        spinner1.addChangeListener(e -> spinner1StateChanged(e));
        contentPane.add(spinner1, "cell 1 0,dock center");
        contentPane.add(label2, "cell 3 0");

        //---- label3 ----
        label3.setText("Pr\u00f3rroga");
        contentPane.add(label3, "cell 0 2");

        //---- spinner2 ----
        spinner2.addChangeListener(e -> spinner2StateChanged(e));
        contentPane.add(spinner2, "cell 1 2");

        //---- button1 ----
        button1.setText("Aceptar");
        contentPane.add(button1, "cell 1 5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jean Michel Ruiz Remis
    private JLabel label1;
    private JSpinner spinner1;
    private JLabel label2;
    private JLabel label3;
    private JSpinner spinner2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
