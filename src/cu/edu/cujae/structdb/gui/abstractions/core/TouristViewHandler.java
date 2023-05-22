package cu.edu.cujae.structdb.gui.abstractions.core;

import cu.edu.cujae.structdb.dto.TouristDTO;
import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.gui.abstractions.AbstractViewHandler;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.DeleteCurrentUserException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TouristViewHandler extends AbstractViewHandler {
    private List<TouristDTO> list;
    @Override
    public String getTitle() {
        return "Agregar Turista";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException
    {
        dtm.setColumnCount(0);
        dtm.addColumn("Pasaporte");
        dtm.addColumn("Nombre");
        dtm.addColumn("Edad");
        dtm.addColumn("Sexo");
        dtm.addColumn("Contacto");
        dtm.addColumn("País");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.touristServices().getAll();
        if (list == null) {
            return;
        }
        for (TouristDTO a : list) {
            Object [] row = {a.getPassport(),a.getName(),a.getAge(),a.getSex(),a.getContact(),a.getCountry().getName()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, DeleteCurrentUserException, ConnectionFailedException {
        ServicesLocator.touristServices().remove(list.get(selection).getPassport());
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertTourist, owner, new TouristDTO());
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        GuiManager.openDialog(GuiManager.DialogType.insertTourist, owner, list.get(selection));
    }
}
