package life;


public class Evolution {


    public static void evolve(Universe population) {
        boolean[][] currGen = population.get();
        int size = currGen.length;
        boolean[][] nextGen = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int aliveNeighbours = getNeighbours(i, j, currGen);

                boolean staysAlive = currGen[i][j] &&
                        (aliveNeighbours == 2 || aliveNeighbours == 3);

                boolean willBeBorn = !currGen[i][j] &&
                        aliveNeighbours == 3;

                nextGen[i][j] = staysAlive || willBeBorn;
            }
        }
        population.set(nextGen);
    }

    private static int getNeighbours(int row, int col, boolean[][] currGen) {
        int size = currGen.length;
        int aliveNeighbours = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                if (i == row && j == col) {
                    continue;
                }
                if (currGen[(i + size) % size][(j + size) % size]) {
                    aliveNeighbours += 1;
                }
            }
        }
        return aliveNeighbours;
    }
}
