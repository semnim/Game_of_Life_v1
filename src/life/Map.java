package life;

import javax.swing.*;
import java.awt.*;


public class Map extends JPanel {
    private Cell[][] cells;

    public Map() {
        super();
    }


    public void init(int size) {
        this.setLayout(new GridLayout(size, size, 1, 1));
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(new Color(61, 61, 64));

        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.cells = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
                this.add(cells[i][j]);
            }
        }
    }

    public void updateMap(boolean[][] population) {
        if (cells != null) {
            for (int i = 0; i < population.length; i++) {
                for (int j = 0; j < population.length; j++) {
                    if(cells[i][j].isAlive() != population[i][j]) {
                        cells[i][j].setAlive(population[i][j]);
                    }
                }
            }
            repaint();
        } else {
            init(population.length);
            updateMap(population);
        }
    }
}
