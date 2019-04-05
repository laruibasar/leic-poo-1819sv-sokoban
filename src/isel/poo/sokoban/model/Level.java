package isel.poo.sokoban.model;

import isel.poo.sokoban.ctrl.Sokoban;

import java.io.CharConversionException;

public class Level {

    private int levelNumber;
    private int height;
    private int width;
    private int boxes;
    private int moves;

    // the game area, full of cells is a bi-dimensional array
    private Cell cellboard[][];

    private Observer listener;

    public Level (int levelNumber, int height, int width) {
        this.levelNumber = levelNumber;
        this.height = height;
        this.width = width;
        this.cellboard = new Cell[width][height];
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
        cellboard[line][column] = createCell(type);
        printCellBoard(line, column, type);
    }

    private Cell createCell(char t) {
        switch (t) {
            case '@':
                return new ManCell();
            case 'B':
                return new BoxCell();
            case '.':
                return new EmptyCell();
            case ' ':
                return new FloorCell();
            case 'X':
                return new WallCell();
            case '*':
                return new ObjectiveCell();
            case 'H':
                return new HoleCell();
            default:
                return new EmptyCell();
        }
    }

    private void printCellBoard(int l, int c, char t) {
        System.out.println(l + " x " + c + " : " + " (" + t + " ) " + (cellboard[l][c]).getClass());
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
