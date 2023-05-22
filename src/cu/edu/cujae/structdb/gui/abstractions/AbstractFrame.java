package cu.edu.cujae.structdb.gui.abstractions;

import cu.edu.cujae.structdb.services.ServicesLocator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFrame extends JFrame {
    protected List<JComponent> visitantLevel;
    protected List<JComponent> workerLevel;
    protected List<JComponent> bossLevel;
    protected List<List<JComponent>> array;

    protected AbstractFrame() {
        visitantLevel = new LinkedList<>();
        workerLevel = new LinkedList<>();
        bossLevel = new LinkedList<>();
        setAccessLevel();
        array = new ArrayList<>(3);
        array.add(visitantLevel);
        array.add(workerLevel);
        array.add(bossLevel);
        applyAccessFilter(ServicesLocator.authService().getCurrentUser().getRol().getId());
    }

    protected void applyAccessFilter(int level) {
        level -= 1;
        List<JComponent> list = array.get(level);
        for (JComponent component : list) {
            component.setVisible(false);
        }
    }

    protected abstract void setAccessLevel();
}
