package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.utils.TableType;

public class ViewHandlerBuilder {
    public static AbstractViewHandler build(TableType type) {
        AbstractViewHandler handler;
        switch (type) {
            case rol:
                handler = new RolViewHandler();
                break;
            case user:
                handler = new UserViewHandler();
                break;
            case model:
                handler = new ModelViewHandler();
                break;
            default:
                handler = new AuxiliaryViewHandler(type);
                break;
        }
        return handler;
    }
}
