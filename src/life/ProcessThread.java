package life;


public class ProcessThread extends Thread {
    Controller controller;

    public ProcessThread(Controller controller) {
        this.controller = controller;
    }


    @Override
    public synchronized void start() {
        super.start();
        controller.run();
    }

    public void playPauseGame() {
        controller.togglePlayPause();
    }

    public void resetGame() {
        controller.reset();
    }
}
