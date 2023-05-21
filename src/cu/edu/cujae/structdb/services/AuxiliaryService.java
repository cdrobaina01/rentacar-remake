package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.AuxiliaryDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AuxiliaryService extends AbstractService {
    private final String insertFunction;
    private final String removeFunction;
    private final String getFunction;
    private final String getByIdFunction;

    public AuxiliaryService(String table) {
        super(table);
        insertFunction = FunctionBuilder.newFunction(false, FunctionType.insert, table, 1, null);
        removeFunction = FunctionBuilder.newFunction(false, FunctionType.delete, table, 1, null);
        getFunction = FunctionBuilder.newFunction(true, FunctionType.get, table, 0, "all");
        getByIdFunction = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "id");
    }

    public void insert(AuxiliaryDTO dto) throws ConnectionFailedException{
        String function = insertFunction;
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getName());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String name) throws ForeignKeyException, ConnectionFailedException {
        String function = removeFunction;
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, name);
            call.execute();

            call.close();
            con.close();
        } catch (PSQLException e){
            if (e.getErrorCode() == 0) {
                throw new ForeignKeyException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AuxiliaryDTO> getAll() throws ConnectionFailedException {
        List<AuxiliaryDTO> list = new LinkedList<>();
        String function = getFunction;
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.execute();

            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                AuxiliaryDTO dto = new AuxiliaryDTO();
                dto.setId(resultSet.getInt(1));
                dto.setName(resultSet.getString(2));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public AuxiliaryDTO getByID(int id) throws ConnectionFailedException {
        AuxiliaryDTO dto = new AuxiliaryDTO();
        String function = getByIdFunction;
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, id);
            call.execute();

            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (resultSet == null) {
                return null;
            }
            if (resultSet.next()) {
                dto.setId(id);
                dto.setName(resultSet.getString(1));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
