package cu.edu.cujae.structdb.gui.abstractions.core;

import cu.edu.cujae.structdb.dto.CarDTO;
import cu.edu.cujae.structdb.dto.ContractDTO;
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

public class ContractViewHandler extends AbstractViewHandler
{
    private List<ContractDTO> list;
    @Override
    public String getTitle() {
        return "Agregar Contrato";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException
    {
        dtm.setColumnCount(0);
        dtm.addColumn("Matrícula");
        dtm.addColumn("Pasaporte");
        dtm.addColumn("Fecha de inicio");
        dtm.addColumn("Fecha de fin");
        dtm.addColumn("Fecha de entrega");
        dtm.addColumn("Método de pago");
        dtm.addColumn("Conductor");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.contractServices().getAll();
        if (list == null) {
            return;
        }
        for (ContractDTO a : list) {
            Object [] row = {a.getPlate(),a.getPassport(),a.getStartDate(),a.getEndDate(),a.getDeliveryDate(),a.getPayMethod(),a.getDriver()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, DeleteCurrentUserException, ConnectionFailedException {
        ServicesLocator.contractServices().remove(list.get(selection).getPlate(), list.get(selection).getStartDate());
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertContract, owner, new TouristDTO());
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        GuiManager.openDialog(GuiManager.DialogType.insertContract, owner, list.get(selection));
    }
}
