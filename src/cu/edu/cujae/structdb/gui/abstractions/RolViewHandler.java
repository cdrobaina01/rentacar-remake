package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.dto.RolDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RolViewHandler extends AbstractViewHandler{

    private List<RolDTO> list;
    @Override
    public String getTitle() {
        return "Ver Roles";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        dtm.addColumn("Nombre");
        dtm.addColumn("Descripción");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.rolServices().getAll();
        if (list == null) {
            return;
        }
        for (RolDTO dto : list) {
            Object [] row = {dto.getName(), dto.getDescription()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) {
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {

    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        JOptionPane.showMessageDialog(owner, "Recuerde que aunque modifique\n" +
                                                     "el nombre o la descripción del rol,\n" +
                                                     "su nivel de acceso no cambiará.");
        GuiManager.openDialog(GuiManager.DialogType.insertRol, owner, list.get(selection));
    }
}
