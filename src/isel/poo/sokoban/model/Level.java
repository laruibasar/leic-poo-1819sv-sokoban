package isel.poo.sokoban.model;

public class Level {

    public Level (int levelNumber, int height, int width) {

    }

    public int getHeight() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public boolean isFinished() {
        return false;
    }

    public boolean manIsDead() {
        return false;
    }

    public int getNumber() {
        return 0;
    }

    public int getRemainingBoxes() {
        return 0;
    }

    public int getMoves() {
        return 0;
    }

    public Cell getCell() {
        return new Cell();
    }

    public int moveMan(Dir dir) {

        return 0;
    }

    public void reset() {

    }

    public void put(int line, int column, char type) {

    }

    public void init(Game game) {

    }

    public interface Observer {
        public void cellUpdated(int l, int c, Cell cell);

        public void cellRepalced(int l, int c, Cell cell);

    }
}
