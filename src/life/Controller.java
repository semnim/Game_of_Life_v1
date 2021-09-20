package life;


public class Controller {
    private Universe population;
    private GameOfLife gui;
    private int size;
    private int gen;
    private boolean isPaused;

    public Controller(Universe population, GameOfLife gui, int size) {
        //get all relative objects to interact in controller
        this.population = population;
        this.gui = gui;
        this.size = size;
        this.isPaused = true;
    }


    public void run() {
        //start from 0, get empty map object from gui and initialize to new cell objects
        gen = 0;
        gui.getMap().init(size);

        //eternal loop to make processor thread sleep if paused (slow or fast sleep)
        while (true) {
            try {
                Thread.sleep(gui.getSliderValue());
            } catch (InterruptedException ie) {
                System.out.println("Interrupted.");
            }

            //if "play" is toggled, overwrite previous generation with next one and update view
            if (!isPaused) {
                Evolution.evolve(population);
                gen++;
                updateView(gen);
            }
        }
    }

    public void updateView(int gen) {
        //update info labels, get the updated population and reset map accordingly, revalidate (repaint "dirty" regions)
        gui.getGenerationLabel().setText("Generation #" + gen);
        gui.getGenerationLabel().repaint();

        gui.getAliveLabel().setText("Alive: " + population.getSurvivors());
        gui.getAliveLabel().repaint();

        gui.getMap().updateMap(population.get());
        gui.getMap().revalidate();
    }

    public void reset() {
        //if reset button is pressed, populate with different seed, reset gen, and update map.
        population.populate();
        gen = 0;
        updateView(gen);
    }

    public void togglePlayPause() {
        isPaused = !isPaused;
    }
}
