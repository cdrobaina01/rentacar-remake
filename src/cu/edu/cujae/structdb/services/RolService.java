package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.RolDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RolService extends AbstractService {

    public RolService(String table) {
        super(table);
    }

    public void insert(RolDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 3, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setInt(1, dto.getId());
            call.setString(2, dto.getName());
            call.setString(3, dto.getDescription());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.delete, table, 1, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setInt(1, id);

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(RolDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 3, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setInt(1, dto.getId());
            call.setString(2, dto.getName());
            call.setString(3, dto.getDescription());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RolDTO getByID(int id) throws ConnectionFailedException {
        RolDTO dto = new RolDTO();
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "id");
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, id);
            call.execute();

            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            if (resultSet.next()) {
                dto.setId(resultSet.getInt(1));
                dto.setName(resultSet.getString(2));
                dto.setDescription(resultSet.getString(3));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<RolDTO> getAll() throws ConnectionFailedException {
        List<RolDTO> list = new LinkedList<>();
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 0, "all");
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.execute();

            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            while (resultSet.next()) {
                RolDTO dto = new RolDTO();
                dto.setId(resultSet.getInt(1));
                dto.setName(resultSet.getString(2));
                dto.setDescription(resultSet.getString(3));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
