package life;


public class Universe {
    static int size = 100;
    private boolean[][] population;

    public Universe() {
        this.population = new boolean[size][size];
        populate();
    }


    public void populate() {
        var random = new java.util.Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                population[i][j] = random.nextBoolean();
            }
        }
    }

    public boolean[][] get() {
        return population;
    }

    public void set(boolean[][] nextGen) {
        this.population = nextGen;
    }

    public int getSurvivors() {
        int survivors = 0;

        for (boolean[] row : population) {
            for (boolean cell : row) {
                survivors += cell ? 1 : 0;
            }
        }
        return survivors;
    }
}
