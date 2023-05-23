/*
 * Created by JFormDesigner on Fri May 12 22:06:42 CDT 2023
 */

package cu.edu.cujae.structdb.gui;

import cu.edu.cujae.structdb.gui.abstractions.AbstractJDialog;
import cu.edu.cujae.structdb.gui.abstractions.AbstractViewHandler;
import cu.edu.cujae.structdb.gui.abstractions.ViewHandlerBuilder;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.DeleteCurrentUserException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author cdrobaina01
 */
public class ViewWindow extends AbstractJDialog {
    public AbstractViewHandler handler;
    private TableType type;
    private DefaultTableModel dtm;

    public ViewWindow(Window owner, Object type) {
        super(owner);
        initComponents();
        this.type = (TableType) type;
        handler = ViewHandlerBuilder.build(this.type);
        this.setTitle(handler.getTitle());
        dtm = new DefaultTableModel();
        try {
            handler.setDTM(dtm);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
        hideUnwantedItems();
        defaultTbl.setModel(dtm);
        setAccessLevel();
        applyAccessFilter();
    }

    private void hideUnwantedItems() {
        switch (type) {
            case model:
            case brand:
            case situation:
            case category:
            case country:
            case paymethod:
                btnUpdate.setVisible(false);
                break;
            case rol:
                btnInsert.setVisible(false);
                btnDelete.setVisible(false);
                break;
        }
    }

    public void refresh() {
        try {
            handler.refreshDTM(dtm);
        } catch (ConnectionFailedException e) {
            GuiManager.handleBadDatabaseConnection(this);
        }
    }

    private void btnDelete(ActionEvent e) {
        int selection = defaultTbl.getSelectedRow();
        if (selection == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una entrada.");
            return;
        }
        int option = JOptionPane.showOptionDialog(this, "¿Está seguro que desea eliminar la entrada seleccionada?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                new Object[] {"Sí", "No"},"No");
        if ( option != 0) {
            return;
        }
        try {
            handler.buttonDelete(dtm, selection);
        } catch (ForeignKeyException exception) {
            JOptionPane.showMessageDialog(this, "La entrada que desea eliminar es utilizada en otros campos.");
        } catch (DeleteCurrentUserException exception) {
            JOptionPane.showMessageDialog(this, "No se puede eliminar el usuario actual.");
        } catch (ConnectionFailedException exception) {
            GuiManager.handleBadDatabaseConnection(this);
        }
    }

    private void btnInsert(ActionEvent e) {
        handler.buttonInsert(dtm, type, this);
    }

    private void goBack(ActionEvent e) {
        this.dispose();
    }

    private void btnUpdate(ActionEvent e) {
        int selection = defaultTbl.getSelectedRow();
        if (selection == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una entrada.");
            return;
        }
        handler.buttonUpdate(dtm, type, this, selection);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        scrollPane1 = new JScrollPane();
        defaultTbl = new JTable();
        btnInsert = new JButton();
        btnDelete = new JButton();
        btnUpdate = new JButton();
        goBack = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(2147483647, 500));
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[147,grow,fill]" +
            "[fill]",
            // rows
            "[fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[bottom]" +
            "[]" +
            "[]"));

        //======== scrollPane1 ========
        {

            //---- defaultTbl ----
            defaultTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(defaultTbl);
        }
        contentPane.add(scrollPane1, "cell 0 0 1 5");

        //---- btnInsert ----
        btnInsert.setText("Insertar");
        btnInsert.addActionListener(e -> btnInsert(e));
        contentPane.add(btnInsert, "cell 1 0");

        //---- btnDelete ----
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(e -> btnDelete(e));
        contentPane.add(btnDelete, "cell 1 1");

        //---- btnUpdate ----
        btnUpdate.setText("Modificar");
        btnUpdate.addActionListener(e -> btnUpdate(e));
        contentPane.add(btnUpdate, "cell 1 2");

        //---- goBack ----
        goBack.setText("Volver");
        goBack.addActionListener(e -> goBack(e));
        contentPane.add(goBack, "cell 1 4");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
    private JScrollPane scrollPane1;
    private JTable defaultTbl;
    private JButton btnInsert;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton goBack;

    @Override
    protected void setAccessLevel() {
        
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
