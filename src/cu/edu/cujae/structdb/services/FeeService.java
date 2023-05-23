package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.FeeDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FeeService extends AbstractService {
    public FeeService(String table) {
        super(table);
    }

    public List<FeeDTO> getAll() throws ConnectionFailedException {
        List<FeeDTO> list = new LinkedList<>();
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
                FeeDTO dto = new FeeDTO();
                dto.setId(resultSet.getInt(1));
                dto.setName(resultSet.getString(2));
                dto.setDayCost(resultSet.getDouble(3));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(FeeDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 2, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setInt(1,dto.getId());
            call.setDouble(2, dto.getDayCost());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
