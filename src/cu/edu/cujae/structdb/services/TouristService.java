package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.TouristDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TouristService extends AbstractService{

    public TouristService(String table) {
        super(table);
    }

    public void insert(TouristDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 6, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getPassport());
            call.setString(2, dto.getName());
            call.setInt(3, dto.getAge());
            call.setString(4, dto.getSex());
            call.setString(5, dto.getContact());
            call.setInt(6, dto.getCountry().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String passport) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.delete, table, 1, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, passport);
            call.execute();

            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TouristDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 6, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getPassport());
            call.setString(2, dto.getName());
            call.setInt(3, dto.getAge());
            call.setString(4, dto.getSex());
            call.setString(5, dto.getContact());
            call.setInt(6, dto.getCountry().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TouristDTO> getAll() throws ConnectionFailedException {
        List<TouristDTO> list = new LinkedList<>();
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
                TouristDTO dto = new TouristDTO();
                dto.setPassport(resultSet.getString(1));
                dto.setName(resultSet.getString(2));
                dto.setAge(resultSet.getInt(3));
                dto.setSex(resultSet.getString(4));
                dto.setContact(resultSet.getString(5));
                dto.setCountry(ServicesLocator.countryServices().getByID(resultSet.getInt(6)));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public TouristDTO getByPassport(String passport) throws ConnectionFailedException {
        TouristDTO dto = new TouristDTO();
        dto.setPassport(passport);
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "passport");
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setString(2, passport);
            call.execute();
            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (resultSet == null) {
                return null;
            }
            if (resultSet.next()) {
                dto.setName(resultSet.getString(2));
                dto.setAge(resultSet.getInt(3));
                dto.setSex(resultSet.getString(4));
                dto.setContact(resultSet.getString(5));
                dto.setCountry(ServicesLocator.countryServices().getByID(resultSet.getInt(6)));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
