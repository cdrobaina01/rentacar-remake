package cu.edu.cujae.structdb.gui.abstractions.core;

import cu.edu.cujae.structdb.dto.ContractDTO;
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

public class OpenContractViewHandler extends AbstractViewHandler {

    private List<ContractDTO> list;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        dtm.setColumnCount(0);
        dtm.addColumn("Auto");
        dtm.addColumn("Turista");
        dtm.addColumn("Inicio");
        dtm.addColumn("Fin");
        dtm.addColumn("Kilometraje Inicial");
        dtm.addColumn("Método de Pago");
        dtm.addColumn("Chofer");
        dtm.addColumn("Importe");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.contractServices().getAllOpen();
        if (list == null) {
            return;
        }
        for (ContractDTO dto : list) {
            Object [] row = {dto.getPlate(), dto.getPassport(), dto.getStartDate(), dto.getEndDate(),
                             dto.getStartKm(), dto.getPayMethod().getName(), dto.getDriver(), dto.getValue()};
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

    public  void buttonClose(DefaultTableModel dtm, TableType type, Window owner, int selection){
        GuiManager.openDialog(GuiManager.DialogType.closeContract, owner, list.get(selection));
    }
}
