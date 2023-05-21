package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.CarDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarService extends AbstractService {

    public CarService(String table) {
        super(table);
    }
    public void insert(CarDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 6, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getPlate());
            call.setInt(2, dto.getModel().getBrand().getId());
            call.setInt(3, dto.getModel().getId());
            call.setInt(4, dto.getCantKm());
            call.setString(5, dto.getColor());
            call.setInt(6, dto.getSituation().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String plate) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.delete, table, 1, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, plate);
            call.execute();

            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CarDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 6, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getPlate());
            call.setInt(2, dto.getModel().getBrand().getId());
            call.setInt(3, dto.getModel().getId());
            call.setInt(4, dto.getCantKm());
            call.setString(5, dto.getColor());
            call.setInt(6, dto.getSituation().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CarDTO> getAll() throws ConnectionFailedException {
        List<CarDTO> list = new LinkedList<>();
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
                CarDTO dto = new CarDTO();
                dto.setPlate(resultSet.getString(1));
                dto.setModel(ServicesLocator.modelServices().getByID(resultSet.getInt(2)));
                dto.setCantKm(resultSet.getInt(4));
                dto.setColor(resultSet.getString(5));
                dto.setSituation(ServicesLocator.situationServices().getByID(resultSet.getInt(6)));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public CarDTO getByPlate(String plate) throws ConnectionFailedException {
        CarDTO dto = new CarDTO();
        dto.setPlate(plate);
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "plate");
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setString(2, plate);
            call.execute();

            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (resultSet == null) {
                return null;
            }
            if (resultSet.next()) {
                dto.setModel(ServicesLocator.modelServices().getByID(resultSet.getInt(2)));
                dto.setCantKm(resultSet.getInt(3));
                dto.setColor(resultSet.getString(4));
                dto.setSituation(ServicesLocator.situationServices().getByID(resultSet.getInt(5)));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
