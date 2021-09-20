package life;

import javax.swing.*;
import java.awt.*;


public class Cell extends JPanel {
    private boolean isAlive;

    public Cell() {
        super();
        setVisible(false);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }


    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
        setVisible(this.isAlive);
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
