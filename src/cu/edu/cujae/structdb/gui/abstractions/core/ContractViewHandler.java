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
        return "Abrir Contrato";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException
    {
        dtm.setColumnCount(0);
        dtm.addColumn("Auto");
        dtm.addColumn("Turista");
        dtm.addColumn("Inicio");
        dtm.addColumn("Fin");
        dtm.addColumn("Kilometraje Inicial");
        dtm.addColumn("Kilometraje Final");
        dtm.addColumn("Fecha de Entrega");
        dtm.addColumn("Método de Pago");
        dtm.addColumn("Chofer");
        dtm.addColumn("Importe");
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
            String deliveryDate = a.getDeliveryDate() == null ? "Pendiente" : a.getDeliveryDate().toString();
            String driver = a.getDriver() == null ? "No Contratado" : a.getDriver();
            Object[] row = {a.getPlate(), a.getPassport(), a.getStartDate(), a.getEndDate(), a.getStartKm(), a.getEndKm(), deliveryDate, a.getPayMethod().getName(), driver, a.getValue()};
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
        GuiManager.openDialog(GuiManager.DialogType.insertContract, owner, new ContractDTO());
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
        GuiManager.openDialog(GuiManager.DialogType.insertContract, owner, list.get(selection));
    }
}
