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
        array = new ArrayList<>(3);
        array.add(0, visitantLevel);
        array.add(1, workerLevel);
        array.add(2, bossLevel);
    }

    protected void applyAccessFilter() {
        int level = ServicesLocator.authService().getCurrentUser().getRol().getId() - 1;
        if (level >= 3) {
            return;
        }
        level -= -1;
        List<JComponent> list = array.get(level);
        for (JComponent component : list) {
            try {
                component.setVisible(false);
            } catch (NullPointerException e) {
                System.out.println(e.getCause());
            }
        }
    }

    protected abstract void setAccessLevel();
}
