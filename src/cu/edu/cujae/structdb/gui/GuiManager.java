package cu.edu.cujae.structdb.gui;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkContrastIJTheme;
import cu.edu.cujae.structdb.gui.insert.AuxiliaryInsertWindow;
import cu.edu.cujae.structdb.gui.insert.ModelInsertWindow;
import cu.edu.cujae.structdb.gui.insert.RolInsertWindow;
import cu.edu.cujae.structdb.gui.insert.UserInsertWindow;
import cu.edu.cujae.structdb.gui.insert.core.CarInsertWindow;
import cu.edu.cujae.structdb.gui.insert.core.ContractInsertWindow;
import cu.edu.cujae.structdb.gui.insert.core.DriverInsertWindow;
import cu.edu.cujae.structdb.gui.insert.core.TouristInsertWindow;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GuiManager {
    public enum FrameType {login, main};
    public enum DialogType {view, changePassword, reports, insertAuxiliary, insertModel, insertUser, insertRol, insertTourist, insertCar, insertDriver, insertContract}
    private interface AbstractFrame {
        void show();
    }
    private interface AbstractDialog {
        void show(Window parent, Object prop);
    }

    private static HashMap<FrameType, AbstractFrame> frames;
    private static HashMap<DialogType, AbstractDialog> dialogs;
    private static boolean darkTheme = true;

    /**
     * Inicializa la interfaz gráfica y establece el tema a utilizar
     * @param theme
     */
    public static void init(LookAndFeel theme) {
        initWindows();
        try {
            UIManager.setLookAndFeel(theme);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        openFrame(FrameType.login, null);
    }

    public static void changeTheme(Component component) {
        darkTheme = darkTheme ? false : true;
        try {
            if (darkTheme) {
                UIManager.setLookAndFeel(new FlatArcDarkContrastIJTheme());
                SwingUtilities.updateComponentTreeUI(component);
            } else {
                UIManager.setLookAndFeel(new FlatArcIJTheme());
                SwingUtilities.updateComponentTreeUI(component);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Este método permite levantar una nueva ventana.
     * @param toOpen Indentificador de la ventana que se debe abrir
     * @param toClose Ventana que se debe cerrar (si no se desea cerrar ninguna pasar null)
     */
    public static void openFrame(FrameType toOpen, Window toClose) {
        if (toClose != null) {
            toClose.dispose();
        }
        frames.get(toOpen).show();
    }
    public static void openDialog(DialogType toOpen, Window parent, Object prop) {
        dialogs.get(toOpen).show(parent, prop);
    }

    public static void handleBadDatabaseConnection(Component component) {
        JOptionPane.showMessageDialog(component, "No se ha podido establecer la " +
                                                         "conexión con la base de datos.");
    }

    /*
    Cuando se añade una nueva ventana al flujo de la app, se debe añadir en este metodo una entrada que la referencie
    en el HashMap windows. El código que se debe pegar es el siguiente:
    JFrame frame = <Aqui debe colocar el constructor de la ventana deseada>;
            frame.setLocationRelativeTo(null);
            frame.setIconImage(new ImageIcon("D:\\workspaces\\cujae\\rentacar\\src\\main\\java\\cu\\edu\\" +
                    "cujae\\structdb\\gui\\icons\\rent.png").getImage());
            frame.setVisible(true);
     */
    private static void initWindows() {
        frames = new HashMap<>();
        dialogs = new HashMap<>();
        frames.put(FrameType.login, () -> {
            initFrame(new LoginWindow());
        });
        frames.put(FrameType.main, () -> {
            initFrame(new HomeWindow());
        });
        dialogs.put(DialogType.view, (parent, prop) -> {
            initFrame(new ViewWindow(prop));
        });
        dialogs.put(DialogType.insertAuxiliary, (parent, prop) -> {
            initFrame(new AuxiliaryInsertWindow(prop, parent));
        });
        dialogs.put(DialogType.insertModel, (parent, prop) -> {
            initFrame(new ModelInsertWindow(parent));
        });
        dialogs.put(DialogType.insertUser, (parent, dto) -> {
            initFrame(new UserInsertWindow(parent, dto));
        });
        dialogs.put(DialogType.insertRol, (parent, dto) -> {
            initFrame(new RolInsertWindow(parent, dto));
        });
        dialogs.put(DialogType.changePassword, (parent, dto) -> {
            initFrame(new PasswordChangeWindow(parent, dto));
        });
        dialogs.put(DialogType.insertTourist, (parent, dto) -> {
            initFrame(new TouristInsertWindow(parent, dto));
        });
        dialogs.put(DialogType.insertCar, (parent, dto) -> {
            initFrame(new CarInsertWindow(parent, dto));
        });
        dialogs.put(DialogType.insertDriver, (parent, dto) -> {
            initFrame(new DriverInsertWindow(parent, dto));
        });
        dialogs.put(DialogType.insertContract, (parent, dto) -> {
            initFrame(new ContractInsertWindow(parent, dto));
        });
        dialogs.put(DialogType.reports, ((parent, prop) -> {
            initFrame(new ReportsView(parent));
        }));
    }

    private static void initFrame(Window window) {
        window.setLocationRelativeTo(null);
        window.setIconImage(new ImageIcon("resources\\rent.png").getImage());
        window.setVisible(true);
    }
}
