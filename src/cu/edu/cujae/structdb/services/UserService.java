package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.utils.FunctionBuilder;
import cu.edu.cujae.structdb.utils.FunctionType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import cu.edu.cujae.structdb.utils.exception.DeleteCurrentUserException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserService extends AbstractService {

    private String defaultPassword = hashPassword("rentacar");

    public UserService(String table) {
        super(table);
    }


    public void insert(UserDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.insert, table, 3, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setString(1, dto.getUsername());
            call.setString(2, defaultPassword);
            call.setInt(3, dto.getRol().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int  id) throws DeleteCurrentUserException, ConnectionFailedException {
        if (ServicesLocator.authService().isCurrentUser(id)) {
            throw new DeleteCurrentUserException();
        }

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

    public void update(UserDTO dto) throws ConnectionFailedException {
        String function = FunctionBuilder.newFunction(false, FunctionType.update, table, 4, null);
        try {
            Connection con = ServicesLocator.getConnection();
            CallableStatement call = con.prepareCall(function);
            call.setInt(1, dto.getId());
            call.setString(2, dto.getUsername());
            call.setString(3, hashPassword(dto.getPassword()));
            call.setInt(4, dto.getRol().getId());

            call.execute();
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserDTO> getAll() throws ConnectionFailedException {
        List<UserDTO> list = new LinkedList<>();
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
                UserDTO dto = new UserDTO();
                dto.setId(resultSet.getInt(1));
                dto.setUsername(resultSet.getString(2));
                dto.setPassword(resultSet.getString(3));
                dto.setRol(ServicesLocator.rolServices().getByID(resultSet.getInt(4)));
                list.add(dto);
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserDTO getByUsername(String username) throws ConnectionFailedException {
        UserDTO dto = new UserDTO();
        String function = FunctionBuilder.newFunction(true, FunctionType.get, table, 1, "username");
        try {
            Connection con = ServicesLocator.getConnection();
            con.setAutoCommit(false);
            CallableStatement call = con.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setString(2, username);
            call.execute();
            ResultSet resultSet = (ResultSet) call.getObject(1);
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            if (resultSet.next()) {
                dto.setId(resultSet.getInt(1));
                dto.setUsername(resultSet.getString(2));
                dto.setPassword(resultSet.getString(3));
                dto.setRol(ServicesLocator.rolServices().getByID(resultSet.getInt(4)));
            }
            call.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public boolean checkDefaultPassword(String pass) {
        return verifyPassword(pass, defaultPassword);
    }

    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean verifyPassword(String loginPassword, String hashPassword){
        return BCrypt.checkpw(loginPassword, hashPassword);
    }
}
