/*
 * Created by JFormDesigner on Sun May 21 20:55:02 CDT 2023
 */

package cu.edu.cujae.structdb.gui.insert.core;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.*;
import net.miginfocom.swing.*;

/**
 * @author Hyzoka
 */
public class ContractInsertWindow extends JDialog {
    private JCalendar startDate;
    private JCalendar endDate;
    public ContractInsertWindow(Window owner, Object dto) {
        initComponents();
    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        comboBox3 = new JComboBox();
        label2 = new JLabel();
        textField2 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        checkBox1 = new JCheckBox();
        textField5 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Insertar Contrato");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
            EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
            . border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
            java . awt. Color .red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
            { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )
            throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[112,fill]rel" +
                    "[grow,fill]rel",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]"));

                //---- label1 ----
                label1.setText("Placa del auto");
                contentPanel.add(label1, "cell 0 0,alignx left,growx 0");
                contentPanel.add(textField1, "cell 1 0");

                //---- label3 ----
                label3.setText("Fecha de Inicio");
                contentPanel.add(label3, "cell 0 1,align left top,grow 0 0");

                //---- label4 ----
                label4.setText("Forma de pago");
                contentPanel.add(label4, "cell 0 2,alignx left,growx 0");
                contentPanel.add(comboBox3, "cell 1 2");

                //---- label2 ----
                label2.setText("Pasaporte del cliente");
                contentPanel.add(label2, "cell 0 3,alignx left,growx 0");
                contentPanel.add(textField2, "cell 1 3");

                //---- label5 ----
                label5.setText("Fecha de Fin");
                contentPanel.add(label5, "cell 0 4,aligny top,growy 0");

                //---- label6 ----
                label6.setText("Conductor");
                contentPanel.add(label6, "cell 0 5");
                contentPanel.add(checkBox1, "cell 1 5,width 20:20:20");
                contentPanel.add(textField5, "cell 1 5");
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
        this.setSize(800, 600);
        startDate = new JCalendar();
        endDate = new JCalendar();
        contentPanel.add(startDate, "cell 1 1");
        contentPanel.add(endDate, "cell 1 4");
        this.setResizable(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboBox3;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label5;
    private JLabel label6;
    private JCheckBox checkBox1;
    private JTextField textField5;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
