package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.gui.GuiManager;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class AuxiliaryViewHandler extends AbstractViewHandler{
    private List<AuxiliaryDTO> list;
    private TableType type;

    public AuxiliaryViewHandler(TableType type) {
        this.type = type;
    }

    @Override
    public void setDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        dtm.addColumn("Nombre");
        refreshDTM(dtm);
    }

    @Override
    public String getTitle() {
        String tableName = new String();
        switch (type) {
            case brand:
                tableName = "Marcas";
                break;
            case model:
                tableName = "Modelos";
                break;
            case situation:
                tableName = "Situación de los Autos";
                break;
            case category:
                tableName = "Categorías de Licencia";
                break;
            case paymethod:
                tableName = "Métodos de Pago";
                break;
            case country:
                tableName = "Procedencia de los Clientes";
                break;
        }
        return "Ver " + tableName;
    }

    @Override
    public void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException {
        cleanDTM(dtm);
        list = new LinkedList<>();
        switch (type) {
            case brand:
                list = ServicesLocator.brandServices().getAll();
                break;
            case country:
                list = ServicesLocator.countryServices().getAll();
                break;
            case category:
                list = ServicesLocator.categoryServices().getAll();
                break;
            case paymethod:
                list = ServicesLocator.payMethodServices().getAll();
                break;
            case situation:
                list = ServicesLocator.situationServices().getAll();
                break;
        }
        if (list == null) {
            return;
        }
        for (AuxiliaryDTO dto : list) {
            Object [] row = {dto.getName()};
            dtm.addRow(row);
        }
    }

    @Override
    public void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, ConnectionFailedException {
        switch (type) {
            case brand:
                ServicesLocator.brandServices().remove(list.get(selection).getName());
                break;
            case situation:
                ServicesLocator.situationServices().remove(list.get(selection).getName());
                break;
            case category:
                ServicesLocator.categoryServices().remove(list.get(selection).getName());
                break;
            case country:
                ServicesLocator.countryServices().remove(list.get(selection).getName());
                break;
            case paymethod:
                ServicesLocator.payMethodServices().remove(list.get(selection).getName());
                break;
        }
        refreshDTM(dtm);
    }

    @Override
    public void buttonInsert(DefaultTableModel dtm, TableType type, Window owner) {
        GuiManager.openDialog(GuiManager.DialogType.insertAuxiliary, owner, type);
    }

    @Override
    public void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection) {
    }
}
