package game.logic;

public class Game {
    public static int GAME_SIZE = 4;
    private static int NUMBER_TO_WIN = 2048;
    private int[][] cells = new int[GAME_SIZE][GAME_SIZE];
    private int availableSpace = GAME_SIZE*GAME_SIZE;
    private int score = 0;
    private boolean hasWon = false;

    public void init() {
        for(int i = 0; i < GAME_SIZE; i++)
            for (int j = 0; j < GAME_SIZE; j++)
                cells[i][j] = 0;

        spawnRandomCell();
        spawnRandomCell();
    }

    public int getScore() {
        return score;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void resetGame() {
        score = 0;
        hasWon = false;
        availableSpace = GAME_SIZE*GAME_SIZE;
        init();
    }

    public boolean canMove() {
        if(availableSpace != 0) {
            return true;
        }

        for(int i = 0; i < GAME_SIZE-1; i++) {
            for (int j = 0; j < GAME_SIZE-1; j++) {
                if(cells[i][j] == cells[i+1][j] || cells[i][j] == cells[i][j+1])
                    return true;
            }
        }

        return false;
    }

    public void spawnRandomCell() {
        while(availableSpace > 0) {
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            if (cells[x][y] == 0) {
                cells[x][y] = 2;
                score += 2;
                availableSpace--;
                break;
            }
        }
    }

    public int[][] getCells() {
        return cells;
    }

    public void left() {
        boolean madeMove = false;

        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j<GAME_SIZE; j++) {
                if(cells[j][i] == 0) {
                    for (int k = j +1; k < GAME_SIZE; k++) {
                        if(cells[k][i] != 0) {
                            cells[j][i] = cells[k][i];
                            cells[k][i] = 0;
                            madeMove = true;
                            break;
                        }
                    }
                }
                else if(cells[j][i] != 0) {
                    for (int k = j +1; k < GAME_SIZE; k++) {
                        if((k-1 == j &&  cells[k][i] == cells[j][i]) || (cells[k-1][i] == 0 && cells[k][i] == cells[j][i])) {
                            cells[j][i] += cells[k][i];
                            cells[k][i] = 0;
                            availableSpace++;
                            madeMove = true;
                        }
                    }
                }
                if(cells[j][i] == NUMBER_TO_WIN)
                    hasWon = true;
            }
        }

        if(madeMove)
            spawnRandomCell();
    }

    public void right() {
        boolean madeMove = false;

        for (int i = GAME_SIZE-1; i >= 0; i--) {
            for (int j = GAME_SIZE-1; j>=0; j--) {
                if(cells[j][i] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if(cells[k][i] != 0) {
                            cells[j][i] = cells[k][i];
                            cells[k][i] = 0;
                            madeMove = true;
                            break;
                        }
                    }
                }
                else if(cells[j][i] != 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if((k+1 == j &&  cells[k][i] == cells[j][i]) || (cells[k+1][i] == 0 && cells[k][i] == cells[j][i])) {
                            cells[j][i] += cells[k][i];
                            cells[k][i] = 0;
                            availableSpace++;
                            madeMove = true;
                        }
                    }
                }
                if(cells[j][i] == NUMBER_TO_WIN)
                    hasWon = true;
            }
        }

        if(madeMove)
            spawnRandomCell();
    }

    public void down() {
        boolean madeMove = false;

        for (int i = GAME_SIZE-1; i >= 0; i--) {
            for (int j = GAME_SIZE-1; j>=0; j--) {
                if(cells[i][j] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if(cells[i][k] != 0) {
                            cells[i][j] = cells[i][k];
                            cells[i][k] = 0;
                            madeMove = true;
                            break;
                        }
                    }
                }
                else if(cells[i][j] != 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if((k+1 == j &&  cells[i][k] == cells[i][j]) || (cells[i][k+1] == 0 && cells[i][k] == cells[i][j])) {
                            cells[i][j] += cells[i][k];
                            cells[i][k] = 0;
                            availableSpace++;
                            madeMove = true;
                        }
                    }
                }
                if(cells[j][i] == NUMBER_TO_WIN)
                    hasWon = true;
            }
        }

        if(madeMove)
            spawnRandomCell();
    }

    public void up() {
        boolean madeMove = false;

        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j<GAME_SIZE; j++) {
                if(cells[i][j] == 0) {
                    for (int k = j +1; k < GAME_SIZE; k++) {
                        if(cells[i][k] != 0) {
                            cells[i][j] = cells[i][k];
                            cells[i][k] = 0;
                            madeMove = true;
                            break;
                        }
                    }
                }
                else if(cells[i][j] != 0) {
                    for (int k = j +1; k < GAME_SIZE; k++) {
                        if((k-1 == j &&  cells[i][k] == cells[i][j]) || (cells[i][k-1] == 0 && cells[i][k] == cells[i][j])) {
                            cells[i][j] += cells[i][k];
                            cells[i][k] = 0;
                            availableSpace++;
                            madeMove = true;
                        }
                    }
                }
                if(cells[j][i] == NUMBER_TO_WIN)
                    hasWon = true;
            }
        }

        if(madeMove)
            spawnRandomCell();
    }
}
