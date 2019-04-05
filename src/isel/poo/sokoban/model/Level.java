package isel.poo.sokoban.model;

import isel.poo.sokoban.ctrl.Sokoban;

public class Level {

    private int levelNumber;
    private int height;
    private int width;
    private int boxes;
    private int moves;

    private Observer listener;

    public Level (int levelNumber, int height, int width) {
        this.levelNumber = levelNumber;
        this.height = height;
        this.width = width;
        this.moves = 0;
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

    public Cell getCell(int line, int column) {
        return new Cell();
    }

    public void moveMan(Dir dir) {

    }

    public void reset() {

    }


    public void put(int line, int column, char type) {
        if (type == 'B')
            this.boxes++;

    }

    public void init(Game game) {

    }

    public void setObserver(Observer listener) {
        this.listener = listener;
    }

    public interface Observer {

        public void cellUpdated(int l, int c, Cell cell);

        public void cellReplaced(int l, int c, Cell cell);

    }

}
