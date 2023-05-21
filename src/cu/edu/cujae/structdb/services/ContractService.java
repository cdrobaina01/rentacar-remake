package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.ContractDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ContractService extends AbstractService{

    public ContractService(String table) {
        super(table);
    }

    public void insert(ContractDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 9, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getPlate());
            call.setDate(2, new Date(dto.getStartDate().toEpochDay()));
            call.setString(3, dto.getPassport());
            call.setDate(4, new Date(dto.getEndDate().toEpochDay()));
            call.setInt(5, dto.getStartKm());
            call.setDate(6, new Date(dto.getDeliveryDate().toEpochDay()));
            call.setInt(7, dto.getEndKm());
            call.setInt(8, dto.getPayMethod().getId());
            call.setString(9, dto.getDriver());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String plate, LocalDate startDate) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.delete, table, 2, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, plate);
            call.setDate(2, Date.valueOf(startDate));
            call.execute();

            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ContractDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 9, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getPlate());
            call.setDate(2, new Date(dto.getStartDate().toEpochDay()));
            call.setString(3, dto.getPassport());
            call.setDate(4, new Date(dto.getEndDate().toEpochDay()));
            call.setInt(5, dto.getStartKm());
            call.setDate(6, new Date(dto.getDeliveryDate().toEpochDay()));
            call.setInt(7, dto.getEndKm());
            call.setInt(8, dto.getPayMethod().getId());
            call.setString(9, dto.getDriver());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ContractDTO> getAll() throws ConnectionFailedException {
        List<ContractDTO> list = new LinkedList<>();
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
                ContractDTO dto = new ContractDTO();
                dto.setPlate(resultSet.getString(1));
                dto.setStartDate(resultSet.getDate(2).toLocalDate());
                dto.setPassport(resultSet.getString(3));
                dto.setEndDate(resultSet.getDate(4).toLocalDate());
                dto.setStartKm(resultSet.getInt(5));
                dto.setDeliveryDate(resultSet.getDate(6).toLocalDate());
                dto.setEndKm(resultSet.getInt(7));
                dto.setPayMethod(ServicesLocator.payMethodServices().getByID(resultSet.getInt(8)));
                dto.setDriver(resultSet.getString(9));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ContractDTO> getByPassport(String passport) throws ConnectionFailedException {
        List<ContractDTO> list = new LinkedList<>();
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
            while (resultSet.next()) {
                ContractDTO dto = new ContractDTO();
                dto.setPlate(resultSet.getString(1));
                dto.setStartDate(resultSet.getDate(2).toLocalDate());
                dto.setPassport(resultSet.getString(3));
                dto.setEndDate(resultSet.getDate(4).toLocalDate());
                dto.setStartKm(resultSet.getInt(5));
                dto.setDeliveryDate(resultSet.getDate(6).toLocalDate());
                dto.setEndKm(resultSet.getInt(7));
                dto.setPayMethod(ServicesLocator.payMethodServices().getByID(resultSet.getInt(8)));
                dto.setDriver(resultSet.getString(9));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ContractDTO> getByPlate(String plate) throws ConnectionFailedException {
        List<ContractDTO> list = new LinkedList<>();
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
            while (resultSet.next()) {
                ContractDTO dto = new ContractDTO();
                dto.setPlate(resultSet.getString(1));
                dto.setStartDate(resultSet.getDate(2).toLocalDate());
                dto.setPassport(resultSet.getString(3));
                dto.setEndDate(resultSet.getDate(4).toLocalDate());
                dto.setStartKm(resultSet.getInt(5));
                dto.setDeliveryDate(resultSet.getDate(6).toLocalDate());
                dto.setEndKm(resultSet.getInt(7));
                dto.setPayMethod(ServicesLocator.payMethodServices().getByID(resultSet.getInt(8)));
                dto.setDriver(resultSet.getString(9));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ContractDTO> getByDni(String dni) throws ConnectionFailedException {
        List<ContractDTO> list = new LinkedList<>();
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
            while (resultSet.next()) {
                ContractDTO dto = new ContractDTO();
                dto.setPlate(resultSet.getString(1));
                dto.setStartDate(resultSet.getDate(2).toLocalDate());
                dto.setPassport(resultSet.getString(3));
                dto.setEndDate(resultSet.getDate(4).toLocalDate());
                dto.setStartKm(resultSet.getInt(5));
                dto.setDeliveryDate(resultSet.getDate(6).toLocalDate());
                dto.setEndKm(resultSet.getInt(7));
                dto.setPayMethod(ServicesLocator.payMethodServices().getByID(resultSet.getInt(8)));
                dto.setDriver(resultSet.getString(9));
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
