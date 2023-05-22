package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.DeleteCurrentUserException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class AbstractViewHandler {
    public void cleanDTM(DefaultTableModel dtm) {
        dtm.setRowCount(0);
    }
    public abstract String getTitle();
    public abstract void setDTM(DefaultTableModel dtm) throws ConnectionFailedException ;
    public abstract void refreshDTM(DefaultTableModel dtm) throws ConnectionFailedException;
    public abstract void buttonDelete(DefaultTableModel dtm, int selection) throws ForeignKeyException, DeleteCurrentUserException, ConnectionFailedException;
    public abstract void buttonInsert(DefaultTableModel dtm, TableType type, Window owner);
    public abstract void buttonUpdate(DefaultTableModel dtm, TableType type, Window owner, int selection);
}
