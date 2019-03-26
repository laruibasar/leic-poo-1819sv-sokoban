package isel.poo.sokoban.model;

public class Level {

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

    }

    public interface Observer {
        public void cellUpdated(int l, int c, Cell cell);

        public void cellRepalced(int l, int c, Cell cell);

    }
}
