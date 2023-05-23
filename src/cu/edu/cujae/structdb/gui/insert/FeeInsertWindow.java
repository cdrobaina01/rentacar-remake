/*
 * Created by JFormDesigner on Mon May 22 22:10:49 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Set;
import javax.swing.*;

import cu.edu.cujae.structdb.dto.FeeDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.*;

/**
 * @author carlosd.inc
 */
public class FeeInsertWindow extends JDialog {
    private final int REGULAR_FEE = 0;
    private final int EXTENSION_FEE = 1;

    private List<FeeDTO> fees;
    public FeeInsertWindow(Window owner) throws ConnectionFailedException {
        super(owner);
        initComponents();
        fees = ServicesLocator.feeService().getAll();
        SpinnerNumberModel regularModel = new SpinnerNumberModel();
        regularModel.setValue(new Double(fees.get(REGULAR_FEE).getDayCost()));
        regularModel.setMinimum(0);
        regularModel.setStepSize(1);
        SpinnerNumberModel extensionModel = new SpinnerNumberModel();
        extensionModel.setValue(new Double(fees.get(EXTENSION_FEE).getDayCost()));
        extensionModel.setMinimum(0);
        regularModel.setStepSize(1);

        spinRegular.setModel(regularModel);
        spinExtension.setModel(extensionModel);
    }

    private void cancel(ActionEvent e) {
        dispose();
    }

    private void ok(ActionEvent e) {
        fees.get(REGULAR_FEE).setDayCost((Integer) spinRegular.getValue());
        fees.get(EXTENSION_FEE).setDayCost((Integer) spinExtension.getValue());
        try {
            for (FeeDTO dto : fees) {
                ServicesLocator.feeService().update(dto);
            }
        } catch (ConnectionFailedException exception) {
            GuiManager.handleBadDatabaseConnection(this);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        spinRegular = new JSpinner();
        label2 = new JLabel();
        spinExtension = new JSpinner();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("Modificar Tarifas");
        setMaximumSize(new Dimension(300, 180));
        setMinimumSize(new Dimension(300, 180));
        setPreferredSize(new Dimension(300, 180));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
            swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border
            .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog"
            ,java.awt.Font.BOLD,12),java.awt.Color.red),dialogPane. getBorder
            ()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
            .beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException
            ();}});
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
                label1.setText("Regular: ");
                contentPanel.add(label1, "cell 0 0");

                //---- spinRegular ----
                spinRegular.setModel(new SpinnerNumberModel(0, null, null, 1));
                contentPanel.add(spinRegular, "cell 1 0");

                //---- label2 ----
                label2.setText("Pr\u00f3rroga: ");
                contentPanel.add(label2, "cell 0 1");
                contentPanel.add(spinExtension, "cell 1 1");
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
    private JSpinner spinRegular;
    private JLabel label2;
    private JSpinner spinExtension;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
