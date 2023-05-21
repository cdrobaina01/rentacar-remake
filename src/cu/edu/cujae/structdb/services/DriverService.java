package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.DriverDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriverService extends AbstractService{

    public DriverService(String table) {
        super(table);
    }

    public void insert(DriverDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 4, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getDni());
            call.setString(2, dto.getName());
            call.setInt(3, dto.getCategory().getId());
            call.setString(4, dto.getAddress());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String dni) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.delete, table, 1, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dni);
            call.execute();

            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(DriverDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 4, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getDni());
            call.setString(2, dto.getName());
            call.setInt(3, dto.getCategory().getId());
            call.setString(4, dto.getAddress());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DriverDTO> getAll() throws ConnectionFailedException {
        List<DriverDTO> list = new LinkedList<>();
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
                DriverDTO dto = new DriverDTO();
                dto.setDni(resultSet.getString(1));
                dto.setName(resultSet.getString(2));
                dto.setCategory(ServicesLocator.categoryServices().getByID(resultSet.getInt(3)));
                dto.setAddress(resultSet.getString(4));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DriverDTO getByDni(String dni) throws ConnectionFailedException {
        DriverDTO dto = new DriverDTO();
        dto.setDni(dni);
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "dni");
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setString(2, dni);
            call.execute();

            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (resultSet == null) {
                return null;
            }
            if (resultSet.next()) {
                dto.setName(resultSet.getString(2));
                dto.setCategory(ServicesLocator.categoryServices().getByID(resultSet.getInt(3)));
                dto.setAddress(resultSet.getString(4));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
