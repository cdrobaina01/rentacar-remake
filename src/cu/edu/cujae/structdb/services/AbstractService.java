package cu.edu.cujae.structdb.services;

public abstract class AbstractService {
    protected String table;
    public AbstractService(String table) {
        this.table = table;
    }
}
