/*
 * Created by JFormDesigner on Fri May 12 14:37:45 CDT 2023
 */

package cu.edu.cujae.structdb.gui;

import cu.edu.cujae.structdb.dto.CarDTO;
import cu.edu.cujae.structdb.dto.ContractDTO;
import cu.edu.cujae.structdb.dto.DriverDTO;
import cu.edu.cujae.structdb.dto.TouristDTO;
import cu.edu.cujae.structdb.gui.insert.AuxiliaryInsertWindow;
import cu.edu.cujae.structdb.gui.insert.ModelInsertWindow;
import cu.edu.cujae.structdb.services.ServicesLocator;
import cu.edu.cujae.structdb.utils.TableType;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author carlosd.inc
 */
public class HomeWindow extends JFrame {
    DefaultTableModel touristDTM;
    DefaultTableModel carDTM;
    DefaultTableModel driverDTM;
    DefaultTableModel contractDTM;
    public HomeWindow() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void mItemSeePayMehtod(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.paymethod);
    }

    private void mItemSeeSituation(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.situation);
    }

    private void mItemSeeBrand(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.brand);
    }

    private void mItemSeeModel(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.model);
    }

    private void mItemSeeCategory(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.category);
    }

    private void mItemCountry(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.country);
    }

    private void mItemSeeUser(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.user);
    }

    private void mItemSeeRol(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.view, this, TableType.rol);
    }

    private void mItemInsertPayMethod(ActionEvent e) {
        AuxiliaryInsertWindow dialog = new AuxiliaryInsertWindow(TableType.paymethod, this);
        dialog.setVisible(true);
    }

    private void mItemCreateCountry(ActionEvent e) {
        AuxiliaryInsertWindow dialog = new AuxiliaryInsertWindow(TableType.country, this);
        dialog.setVisible(true);
    }

    private void mItemCreateBrand(ActionEvent e) {
        AuxiliaryInsertWindow dialog = new AuxiliaryInsertWindow(TableType.brand, this);
        dialog.setVisible(true);
    }

    private void mItemCreateModel(ActionEvent e) {
        ModelInsertWindow dialog = new ModelInsertWindow(this);
        dialog.setVisible(true);
    }

    private void mItemCreateSituation(ActionEvent e) {
        AuxiliaryInsertWindow dialog = new AuxiliaryInsertWindow(TableType.situation, this);
        dialog.setVisible(true);
    }

    private void mItemCreateCategory(ActionEvent e) {
        AuxiliaryInsertWindow dialog = new AuxiliaryInsertWindow(TableType.category, this);
        dialog.setVisible(true);
    }

    private void mItemClose(ActionEvent e) {
        ServicesLocator.authService().logout();
        GuiManager.openFrame(GuiManager.FrameType.login, this);
    }

    private void mItemTheme(ActionEvent e) {
        GuiManager.changeTheme(this);
    }

    private void mItemChangePass(ActionEvent e) {
        GuiManager.openDialog(GuiManager.DialogType.changePassword, this, ServicesLocator.authService().getCurrentUser());
    }

    private void turistB(ActionEvent e) {
        principalTable.setModel(touristDTM);
        fillTouristTable();
        System.out.println("TocasteTuristB");
    }

    private void carB(ActionEvent e) {
        principalTable.setModel(carDTM);
        fillCarTable();
    }

    private void driverB(ActionEvent e) {
        principalTable.setModel(driverDTM);
        fillDriverTable();
    }

    private void contractsB(ActionEvent e) {
        principalTable.setModel(contractDTM);
        fillContractTable();
    }

    private void openContractB(ActionEvent e) {
        principalTable.setModel(contractDTM);
    }

    private void closeContractB(ActionEvent e) {
        principalTable.setModel(contractDTM);
    }

    private void initComponents() {
        DeclareTableModels();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
        menuBar = new JMenuBar();
        menuAdmin = new JMenu();
        mItemChangePass = new JMenuItem();
        mItemClose = new JMenuItem();
        mItemTheme = new JMenuItem();
        mItemSeeUser = new JMenuItem();
        mItemSeeRol = new JMenuItem();
        menuManage = new JMenu();
        menuContract = new JMenu();
        mItemCreateContract = new JMenuItem();
        mItemCloseContract = new JMenuItem();
        mItemSeePayMehtod = new JMenuItem();
        mItemInsertPayMethod = new JMenuItem();
        menuTourist = new JMenu();
        mItemCreateTourist = new JMenuItem();
        mItemCountry = new JMenuItem();
        mItemCreateCountry = new JMenuItem();
        menuCar = new JMenu();
        mItemCreateCar = new JMenuItem();
        mItemSeeBrand = new JMenuItem();
        mItemCreateBrand = new JMenuItem();
        mItemSeeModel = new JMenuItem();
        mItemCreateModel = new JMenuItem();
        mItemSeeSituation = new JMenuItem();
        mItemCreateSituation = new JMenuItem();
        menuDriver = new JMenu();
        mItemCreateDriver = new JMenuItem();
        mItemSeeCategory = new JMenuItem();
        mItemCreateCategory = new JMenuItem();
        menuSee = new JMenu();
        mItemGetContracts = new JMenuItem();
        mItemGetOpen = new JMenuItem();
        mItemGetClosed = new JMenuItem();
        mItemGetCars = new JMenuItem();
        mItemGetDrivers = new JMenuItem();
        mItemGetTourists = new JMenuItem();
        menuReports = new JMenu();
        mItemRport1 = new JMenuItem();
        mItemReport2 = new JMenuItem();
        mItemReport3 = new JMenuItem();
        mItemReport4 = new JMenuItem();
        mItemReport5 = new JMenuItem();
        mItemReport6 = new JMenuItem();
        mItemReport7 = new JMenuItem();
        mItemReport8 = new JMenuItem();
        mItemReport9 = new JMenuItem();
        menuHelp = new JMenu();
        mItemDocs = new JMenuItem();
        mItemAbout = new JMenuItem();
        panel5 = new JPanel();
        turistB = new JButton();
        driverB = new JButton();
        openContractB = new JButton();
        closeContractB = new JButton();
        contractsB = new JButton();
        carB = new JButton();
        scrollPane2 = new JScrollPane();
        principalTable = new JTable();
        button23 = new JButton();
        button24 = new JButton();
        button25 = new JButton();

        //======== this ========
        setTitle("Rent a Car");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[fill]" +
            "[611,grow,fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[grow,fill]"));

        //======== menuBar ========
        {

            //======== menuAdmin ========
            {
                menuAdmin.setText("Administrar");

                //---- mItemChangePass ----
                mItemChangePass.setText("Cambiar Contrase\u00f1a");
                mItemChangePass.addActionListener(e -> mItemChangePass(e));
                menuAdmin.add(mItemChangePass);

                //---- mItemClose ----
                mItemClose.setText("Cerrar Sesi\u00f3n");
                mItemClose.addActionListener(e -> mItemClose(e));
                menuAdmin.add(mItemClose);

                //---- mItemTheme ----
                mItemTheme.setText("Cambiar Tema Claro/Oscuro");
                mItemTheme.addActionListener(e -> mItemTheme(e));
                menuAdmin.add(mItemTheme);
                menuAdmin.addSeparator();

                //---- mItemSeeUser ----
                mItemSeeUser.setText("Administrar Usuarios");
                mItemSeeUser.addActionListener(e -> mItemSeeUser(e));
                menuAdmin.add(mItemSeeUser);

                //---- mItemSeeRol ----
                mItemSeeRol.setText("Ver Roles");
                mItemSeeRol.addActionListener(e -> mItemSeeRol(e));
                menuAdmin.add(mItemSeeRol);
            }
            menuBar.add(menuAdmin);

            //======== menuManage ========
            {
                menuManage.setText("Gesti\u00f3n");

                //======== menuContract ========
                {
                    menuContract.setText("Gestionar Contratos");

                    //---- mItemCreateContract ----
                    mItemCreateContract.setText("Abrir Contrato");
                    menuContract.add(mItemCreateContract);

                    //---- mItemCloseContract ----
                    mItemCloseContract.setText("Cerrar Contrato");
                    menuContract.add(mItemCloseContract);
                    menuContract.addSeparator();

                    //---- mItemSeePayMehtod ----
                    mItemSeePayMehtod.setText("Gestionar M\u00e9todos de Pago");
                    mItemSeePayMehtod.addActionListener(e -> mItemSeePayMehtod(e));
                    menuContract.add(mItemSeePayMehtod);

                    //---- mItemInsertPayMethod ----
                    mItemInsertPayMethod.setText("Crear M\u00e9todo de Pago");
                    mItemInsertPayMethod.addActionListener(e -> mItemInsertPayMethod(e));
                    menuContract.add(mItemInsertPayMethod);
                }
                menuManage.add(menuContract);

                //======== menuTourist ========
                {
                    menuTourist.setText("Gestionar Turistas");

                    //---- mItemCreateTourist ----
                    mItemCreateTourist.setText("Registrar Cliente");
                    menuTourist.add(mItemCreateTourist);
                    menuTourist.addSeparator();

                    //---- mItemCountry ----
                    mItemCountry.setText("Gestionar Pa\u00edses");
                    mItemCountry.addActionListener(e -> mItemCountry(e));
                    menuTourist.add(mItemCountry);

                    //---- mItemCreateCountry ----
                    mItemCreateCountry.setText("Registrar Pa\u00eds");
                    mItemCreateCountry.addActionListener(e -> mItemCreateCountry(e));
                    menuTourist.add(mItemCreateCountry);
                }
                menuManage.add(menuTourist);

                //======== menuCar ========
                {
                    menuCar.setText("Getionar Autos");

                    //---- mItemCreateCar ----
                    mItemCreateCar.setText("Registrar Auto");
                    menuCar.add(mItemCreateCar);
                    menuCar.addSeparator();

                    //---- mItemSeeBrand ----
                    mItemSeeBrand.setText("Gestionar Marcas de los Autos");
                    mItemSeeBrand.addActionListener(e -> mItemSeeBrand(e));
                    menuCar.add(mItemSeeBrand);

                    //---- mItemCreateBrand ----
                    mItemCreateBrand.setText("Registrar Marca");
                    mItemCreateBrand.addActionListener(e -> mItemCreateBrand(e));
                    menuCar.add(mItemCreateBrand);
                    menuCar.addSeparator();

                    //---- mItemSeeModel ----
                    mItemSeeModel.setText("Gestionar Modelos de los Autos");
                    mItemSeeModel.addActionListener(e -> mItemSeeModel(e));
                    menuCar.add(mItemSeeModel);

                    //---- mItemCreateModel ----
                    mItemCreateModel.setText("Registrar Modelo");
                    mItemCreateModel.addActionListener(e -> mItemCreateModel(e));
                    menuCar.add(mItemCreateModel);
                    menuCar.addSeparator();

                    //---- mItemSeeSituation ----
                    mItemSeeSituation.setText("Gestionar Situaci\u00f3n de los Autos");
                    mItemSeeSituation.addActionListener(e -> mItemSeeSituation(e));
                    menuCar.add(mItemSeeSituation);

                    //---- mItemCreateSituation ----
                    mItemCreateSituation.setText("Crear Situaci\u00f3n de los Autos");
                    mItemCreateSituation.addActionListener(e -> mItemCreateSituation(e));
                    menuCar.add(mItemCreateSituation);
                }
                menuManage.add(menuCar);

                //======== menuDriver ========
                {
                    menuDriver.setText("Gestionar Choferes");

                    //---- mItemCreateDriver ----
                    mItemCreateDriver.setText("Contratar Chofer");
                    menuDriver.add(mItemCreateDriver);
                    menuDriver.addSeparator();

                    //---- mItemSeeCategory ----
                    mItemSeeCategory.setText("Gestionar Categor\u00edas de Licencia");
                    mItemSeeCategory.addActionListener(e -> mItemSeeCategory(e));
                    menuDriver.add(mItemSeeCategory);

                    //---- mItemCreateCategory ----
                    mItemCreateCategory.setText("Registra Categor\u00eda de Licencia");
                    mItemCreateCategory.addActionListener(e -> mItemCreateCategory(e));
                    menuDriver.add(mItemCreateCategory);
                }
                menuManage.add(menuDriver);
            }
            menuBar.add(menuManage);

            //======== menuSee ========
            {
                menuSee.setText("Ver");

                //---- mItemGetContracts ----
                mItemGetContracts.setText("Contratos");
                menuSee.add(mItemGetContracts);

                //---- mItemGetOpen ----
                mItemGetOpen.setText("Contratos Abiertos");
                menuSee.add(mItemGetOpen);

                //---- mItemGetClosed ----
                mItemGetClosed.setText("Contratos Terminados");
                menuSee.add(mItemGetClosed);
                menuSee.addSeparator();

                //---- mItemGetCars ----
                mItemGetCars.setText("Autos");
                menuSee.add(mItemGetCars);

                //---- mItemGetDrivers ----
                mItemGetDrivers.setText("Choferes");
                menuSee.add(mItemGetDrivers);

                //---- mItemGetTourists ----
                mItemGetTourists.setText("Turistas");
                menuSee.add(mItemGetTourists);
            }
            menuBar.add(menuSee);

            //======== menuReports ========
            {
                menuReports.setText("Reportes");

                //---- mItemRport1 ----
                mItemRport1.setText("Listado de Turistas");
                menuReports.add(mItemRport1);

                //---- mItemReport2 ----
                mItemReport2.setText("Listado de Autos");
                menuReports.add(mItemReport2);

                //---- mItemReport3 ----
                mItemReport3.setText("Listado de Contratos");
                menuReports.add(mItemReport3);

                //---- mItemReport4 ----
                mItemReport4.setText("Listado de Choferes");
                menuReports.add(mItemReport4);

                //---- mItemReport5 ----
                mItemReport5.setText("Situacion de los Autos");
                menuReports.add(mItemReport5);

                //---- mItemReport6 ----
                mItemReport6.setText("Turistas Incumplidores");
                menuReports.add(mItemReport6);

                //---- mItemReport7 ----
                mItemReport7.setText("Contratos por Autos");
                menuReports.add(mItemReport7);

                //---- mItemReport8 ----
                mItemReport8.setText("Contratos por Pa\u00edses");
                menuReports.add(mItemReport8);

                //---- mItemReport9 ----
                mItemReport9.setText("Ingresos Anuales");
                menuReports.add(mItemReport9);
            }
            menuBar.add(menuReports);

            //======== menuHelp ========
            {
                menuHelp.setText("Ayuda");

                //---- mItemDocs ----
                mItemDocs.setText("Documentaci\u00f3n");
                menuHelp.add(mItemDocs);

                //---- mItemAbout ----
                mItemAbout.setText("Sobre Rentacar");
                menuHelp.add(mItemAbout);
            }
            menuBar.add(menuHelp);
        }
        setJMenuBar(menuBar);

        //======== panel5 ========
        {
            panel5.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel5. getBorder () ) ); panel5. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel5.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[75,fill]0" +
                "[75,fill]0" +
                "[75,fill]0" +
                "[75,fill]0" +
                "[75,fill]0" +
                "[75,fill]",
                // rows
                "[grow,fill]"));

            //---- turistB ----
            turistB.setText("Turistas");
            turistB.addActionListener(e -> turistB(e));
            panel5.add(turistB, "cell 0 0");

            //---- driverB ----
            driverB.setText("Choferes");
            driverB.addActionListener(e -> driverB(e));
            panel5.add(driverB, "cell 2 0");

            //---- openContractB ----
            openContractB.setText("C.Abiertos");
            openContractB.addActionListener(e -> openContractB(e));
            panel5.add(openContractB, "cell 4 0,aligny bottom,growy 0");

            //---- closeContractB ----
            closeContractB.setText("C.Cerrados");
            closeContractB.addActionListener(e -> closeContractB(e));
            panel5.add(closeContractB, "cell 5 0,aligny bottom,growy 0");

            //---- contractsB ----
            contractsB.setText("Contratos");
            contractsB.addActionListener(e -> contractsB(e));
            panel5.add(contractsB, "cell 3 0,aligny bottom,growy 0");

            //---- carB ----
            carB.setText("Carros");
            carB.addActionListener(e -> carB(e));
            panel5.add(carB, "cell 1 0");
        }
        contentPane.add(panel5, "cell 0 0 2 1");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(principalTable);
        }
        contentPane.add(scrollPane2, "cell 0 1 2 5,grow");

        //---- button23 ----
        button23.setText("Agregar");
        contentPane.add(button23, "cell 2 1");

        //---- button24 ----
        button24.setText("Eliminar");
        contentPane.add(button24, "cell 2 2");

        //---- button25 ----
        button25.setText("Editar");
        contentPane.add(button25, "cell 2 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    private void DeclareTableModels(){
        touristDTM = new DefaultTableModel();
        touristDTM.addColumn("Pasaporte");
        touristDTM.addColumn("Nombre");
        touristDTM.addColumn("Edad");
        touristDTM.addColumn("Sexo");
        touristDTM.addColumn("Contacto");
        touristDTM.addColumn("País");

        carDTM = new DefaultTableModel();
        carDTM.addColumn("Matrícula");
        carDTM.addColumn("Modelo");
        carDTM.addColumn("Km");
        carDTM.addColumn("Color");
        carDTM.addColumn("Situación");

        driverDTM = new DefaultTableModel();
        driverDTM.addColumn("DNI");
        driverDTM.addColumn("Nombre");
        driverDTM.addColumn("Categoría");
        driverDTM.addColumn("Dirección");

        contractDTM = new DefaultTableModel();
        contractDTM.addColumn("Matrícula");
        contractDTM.addColumn("Pasaporte");
        contractDTM.addColumn("Fecha de inicio");
        contractDTM.addColumn("Fecha de fin");
        contractDTM.addColumn("Fecha de entrega");
        contractDTM.addColumn("Método de pago");
        contractDTM.addColumn("Conductor");
    }

    private void fillTouristTable(){
        touristDTM.setRowCount(0);
        List<TouristDTO> list = null;
        try {
            list = ServicesLocator.touristServices().getAll();
        } catch (ConnectionFailedException e) {
            throw new RuntimeException(e);
        }
        for (TouristDTO a : list) {
            Object [] row = {a.getPassport(),a.getName(),a.getAge(),a.getSex(),a.getContact(),a.getCountry()};
            touristDTM.addRow(row);
        }
    }
    private void fillCarTable(){
        carDTM.setRowCount(0);
        List<CarDTO> list = null;
        try {
            list = ServicesLocator.carServices().getAll();
        } catch (ConnectionFailedException e) {
            throw new RuntimeException(e);
        }
        for (CarDTO a : list) {
            Object [] row = {a.getPlate(),a.getModel(),a.getCantKm(),a.getColor(),a.getSituation()};
            carDTM.addRow(row);
        }
    }
    private void fillDriverTable(){
        driverDTM.setRowCount(0);
        List<DriverDTO> list = null;
        try {
            list = ServicesLocator.driverServices().getAll();
        } catch (ConnectionFailedException e) {
            throw new RuntimeException(e);
        }
        for (DriverDTO a : list) {
            Object [] row = {a.getDni(),a.getName(),a.getCategory(),a.getAddress()};
            driverDTM.addRow(row);
        }
    }
    private void fillContractTable(){
        contractDTM.setRowCount(0);
        List<ContractDTO> list = null;
        try {
            list = ServicesLocator.contractServices().getAll();
        } catch (ConnectionFailedException e) {
            throw new RuntimeException(e);
        }
        for (ContractDTO a : list) {
            Object [] row = {a.getPlate(),a.getPassport(),a.getStartDate(),a.getEndDate(),a.getDeliveryDate(),a.getPayMethod(),a.getDriver()};
            contractDTM.addRow(row);
        }
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Carlos Daniel Robaina Rivero
    private JMenuBar menuBar;
    private JMenu menuAdmin;
    private JMenuItem mItemChangePass;
    private JMenuItem mItemClose;
    private JMenuItem mItemTheme;
    private JMenuItem mItemSeeUser;
    private JMenuItem mItemSeeRol;
    private JMenu menuManage;
    private JMenu menuContract;
    private JMenuItem mItemCreateContract;
    private JMenuItem mItemCloseContract;
    private JMenuItem mItemSeePayMehtod;
    private JMenuItem mItemInsertPayMethod;
    private JMenu menuTourist;
    private JMenuItem mItemCreateTourist;
    private JMenuItem mItemCountry;
    private JMenuItem mItemCreateCountry;
    private JMenu menuCar;
    private JMenuItem mItemCreateCar;
    private JMenuItem mItemSeeBrand;
    private JMenuItem mItemCreateBrand;
    private JMenuItem mItemSeeModel;
    private JMenuItem mItemCreateModel;
    private JMenuItem mItemSeeSituation;
    private JMenuItem mItemCreateSituation;
    private JMenu menuDriver;
    private JMenuItem mItemCreateDriver;
    private JMenuItem mItemSeeCategory;
    private JMenuItem mItemCreateCategory;
    private JMenu menuSee;
    private JMenuItem mItemGetContracts;
    private JMenuItem mItemGetOpen;
    private JMenuItem mItemGetClosed;
    private JMenuItem mItemGetCars;
    private JMenuItem mItemGetDrivers;
    private JMenuItem mItemGetTourists;
    private JMenu menuReports;
    private JMenuItem mItemRport1;
    private JMenuItem mItemReport2;
    private JMenuItem mItemReport3;
    private JMenuItem mItemReport4;
    private JMenuItem mItemReport5;
    private JMenuItem mItemReport6;
    private JMenuItem mItemReport7;
    private JMenuItem mItemReport8;
    private JMenuItem mItemReport9;
    private JMenu menuHelp;
    private JMenuItem mItemDocs;
    private JMenuItem mItemAbout;
    private JPanel panel5;
    private JButton turistB;
    private JButton driverB;
    private JButton openContractB;
    private JButton closeContractB;
    private JButton contractsB;
    private JButton carB;
    private JScrollPane scrollPane2;
    private JTable principalTable;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
