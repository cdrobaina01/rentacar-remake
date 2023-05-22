package cu.edu.cujae.structdb.gui.abstractions.core;

import cu.edu.cujae.structdb.dto.DriverDTO;
import cu.edu.cujae.structdb.dto.TouristDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.abstractions.AbstractViewHandler;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.DeleteCurrentUserException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Driver;
import java.util.List;

public class DriverViewHandler extends AbstractViewHandler {
    private List<DriverDTO> list;
    @Override
    public String getTitle() {
        return "Agregar Conductor";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException
    {
        dtm.setColumnCount(0);
        dtm.addColumn("DNI");
        dtm.addColumn("Nombre");
        dtm.addColumn("Categoría");
        dtm.addColumn("Dirección");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.driverServices().getAll();
        if (list == null) {
            return;
        }
        for (DriverDTO a : list) {
            Object [] row = {a.getDni(),a.getName(),a.getCategory().getName(),a.getAddress()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, DeleteCurrentUserException, ConnectionFailedException {
        ServicesLocator.driverServices().remove(list.get(selection).getDni());
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertDriver, owner, new DriverDTO());
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        GuiManager.openDialog(GuiManager.DialogType.insertDriver, owner, list.get(selection));
    }
}
