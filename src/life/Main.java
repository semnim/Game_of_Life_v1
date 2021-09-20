package life;

public class Main {
    //Version 1.0
    public static void main(String[] args) {
        final Universe population = new Universe();
        final GameOfLife gui = new GameOfLife();
        final Controller controller = new Controller(population, gui, 100);
        final ProcessThread thread = new ProcessThread(controller);

        gui.setGameThread(thread);
        thread.start();
    }
}