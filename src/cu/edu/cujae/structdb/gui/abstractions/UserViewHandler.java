package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.DeleteCurrentUserException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserViewHandler extends AbstractViewHandler {

    private List<UserDTO> list;

    @Override
    public String getTitle() {
        return "Ver Usuarios";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        dtm.addColumn("Nombre de Usuario");
        dtm.addColumn("Rol");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.userServices().getAll();
        if (list == null) {
            return;
        }
        for (UserDTO dto : list) {
            Object [] row = {dto.getUsername(), dto.getRol().getName()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws DeleteCurrentUserException, ConnectionFailedException {
        ServicesLocator.userServices().remove(list.get(selection).getId());
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertUser, owner, new UserDTO());
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        GuiManager.openDialog(GuiManager.DialogType.insertUser, owner, list.get(selection));
    }
}
