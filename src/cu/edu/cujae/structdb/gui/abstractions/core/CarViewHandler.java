package cu.edu.cujae.structdb.gui.abstractions.core;

import cu.edu.cujae.structdb.dto.CarDTO;
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
import java.util.List;

public class CarViewHandler extends AbstractViewHandler {
    private List<CarDTO> list;
    @Override
    public String getTitle() {
        return "Agregar Carro";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException
    {
        dtm.setColumnCount(0);
        dtm.addColumn("Matrícula");
        dtm.addColumn("Marca");
        dtm.addColumn("Modelo");
        dtm.addColumn("Km");
        dtm.addColumn("Color");
        dtm.addColumn("Situación");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.carServices().getAll();
        if (list == null) {
            return;
        }
        for (CarDTO a : list) {
            Object [] row = {a.getPlate(), a.getModel().getBrand().getName(), a.getModel().getName(),a.getCantKm(),a.getColor(),a.getSituation().getName()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, DeleteCurrentUserException, ConnectionFailedException {
        ServicesLocator.carServices().remove(list.get(selection).getPlate());
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertCar, owner, new CarDTO());
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        GuiManager.openDialog(GuiManager.DialogType.insertCar, owner, list.get(selection));
    }
}
