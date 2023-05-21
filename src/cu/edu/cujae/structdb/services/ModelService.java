package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.ModelDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.ForeignKeyException;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModelService extends AbstractService {

    public ModelService(String table) {
        super(table);
    }

    public void insert(ModelDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 2, null);;
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getName());
            call.setInt(2, dto.getBrand().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String name) throws ForeignKeyException, ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.delete, table, 1, null);;;
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, name);
            call.execute();

            call.close();
            con.close();
        } catch (PSQLException e) {
            if (e.getErrorCode() == 0) {
                throw new ForeignKeyException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ModelDTO> getAll() throws ConnectionFailedException {
        List<ModelDTO> list = new LinkedList<>();
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 0, "all");
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
                ModelDTO dto = new ModelDTO();
                dto.setId(resultSet.getInt(2));
                dto.setBrand(ServicesLocator.brandServices().getByID(resultSet.getInt(1)));
                dto.setName(resultSet.getString(3));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ModelDTO getByID(int id) throws ConnectionFailedException {
        ModelDTO dto = new ModelDTO();
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "id");
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
                dto.setBrand(ServicesLocator.brandServices().getByID(resultSet.getInt(2)));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
