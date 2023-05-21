package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.utils.Connection;
import cu.edu.cujae.structdb.utils.DatabaseInfo;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

import javax.swing.plaf.PanelUI;
import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * ServicesLocator works as a link to every service implemented in the system.<br>
 * TODO: List Services
 */
public class ServicesLocator {
    private static TouristService touristService;
    private static CarService carService;
    private static DriverService driverService;
    private static ContractService contractService;
    private static ModelService modelService;
    private static AuxiliaryService brandService;
    private static AuxiliaryService categoryService;
    private static AuxiliaryService countryService;
    private static AuxiliaryService payMethodService;
    private static AuxiliaryService situationService;
    private static UserService userService;
    private static RolService rolService;
    private static AuthService authService;
    public static DatabaseInfo dbInfo;


    /**
     * Open a new connection to the Database
     *
     * @return {@link java.sql.Connection}
     */
    public static java.sql.Connection getConnection() throws ConnectionFailedException {
        Connection connection = null;
        try {
            connection = new Connection(DatabaseInfo.server, DatabaseInfo.database, DatabaseInfo.user, DatabaseInfo.pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionFailedException();
        }
        return connection.getConnection();
    }

    public static TouristService touristServices() {
        if (touristService == null) {
            touristService = new TouristService("tourist");
        }
        return touristService;
    }

    public static CarService carServices() {
        if (carService == null) {
            carService = new CarService("car");
        }
        return carService;
    }

    public static DriverService driverServices() {
        if (driverService == null) {
            driverService = new DriverService("driver");
        }
        return driverService;
    }

    public static ContractService contractServices() {
        if (contractService == null) {
            contractService = new ContractService("contract");
        }
        return contractService;
    }

    public static ModelService modelServices() {
        if (modelService == null) {
            modelService = new ModelService("model");
        }
        return modelService;
    }

    public static AuxiliaryService brandServices() {
        if (brandService == null) {
            brandService = new AuxiliaryService("brand");
        }
        return brandService;
    }

    public static AuxiliaryService categoryServices() {
        if (categoryService == null) {
            categoryService = new AuxiliaryService("category");
        }
        return categoryService;
    }

    public static AuxiliaryService countryServices() {
        if (countryService == null) {
            countryService = new AuxiliaryService("country");
        }
        return countryService;
    }

    public static AuxiliaryService payMethodServices() {
        if (payMethodService == null) {
            payMethodService = new AuxiliaryService("paymethod");
        }
        return payMethodService;
    }

    public static AuxiliaryService situationServices() {
        if (situationService == null) {
            situationService = new AuxiliaryService("situation");
        }
        return situationService;
    }

    public static UserService userServices() {
        if (userService == null) {
            userService = new UserService("user");
        }
        return userService;
    }

    public static RolService rolServices() {
        if (rolService == null) {
            rolService = new RolService("rol");
        }
        return rolService;
    }

    public static AuthService authService() {
        if (authService == null) {
            authService = new AuthService();
        }
        return authService;
    }
}
