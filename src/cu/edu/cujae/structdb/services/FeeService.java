package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.DriverDTO;
import cu.edu.cujae.structdb.dto.FeeDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class FeeService extends AbstractService {
    public FeeService(String table) {
        super(table);
    }
    public void update(FeeDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 6, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setInt(1,dto.getId());
            call.getInt(2);
            call.setString(3,dto.setFee());
            call.getString(4);
            call.getDouble(5);
            call.setDouble(6,dto.setDay_cost());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
