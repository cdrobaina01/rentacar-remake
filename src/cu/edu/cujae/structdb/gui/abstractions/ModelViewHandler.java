package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.dto.ModelDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ModelViewHandler extends AbstractViewHandler{

    private List<ModelDTO> list;

    @Override
    public String getTitle() {
        return "Ver Modelos de los Autos";
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        dtm.addColumn("Nombre");
        dtm.addColumn("Marca");
        refreshDTM(dtm);
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = ServicesLocator.modelServices().getAll();
        if (list == null) {
            return;
        }
        for (ModelDTO dto : list) {
            Object [] row = {dto.getName(), dto.getBrand().getName()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, ConnectionFailedException {
        ServicesLocator.modelServices().remove(list.get(selection).getName());
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertModel, owner, null);
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {

    }
}
