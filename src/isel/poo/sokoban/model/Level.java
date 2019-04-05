package isel.poo.sokoban.model;

import isel.poo.sokoban.ctrl.Sokoban;

import java.io.CharConversionException;

public class Level {

    private int levelNumber;
    private int height;
    private int width;
    private int boxes;
    private int moves;

    /**
     * The game area, full of cells, is a bi-dimensional array
     * of size to be define at each level
     */
    private Cell cellboard[][];

    /**
     * Event listener
     */
    private Observer listener;

    /**
     * Class constructor, setting up the paramenters needed to start the show
     *
     * @param levelNumber the level number for the game
     * @param height the number of columns
     * @param width the number of lines
     */
    public Level (int levelNumber, int height, int width) {
        this.levelNumber = levelNumber;
        this.height = height;
        this.width = width;
        this.cellboard = new Cell[width][height];
        this.moves = 0;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isFinished() {
        return false;
    }

    public boolean manIsDead() {
        return false;
    }

    public int getNumber() {
        return levelNumber;
    }

    public int getRemainingBoxes() {
        return boxes;
    }

    public int getMoves() {
        return moves;
    }

    /**
     * Returns the Cell at line and column asked
     * @param line the line in the game area
     * @param column the column in the game area
     * @return the Cell in the l x c
     */
    public Cell getCell(int line, int column) {
        return cellboard[line][column];
    }

    public void moveMan(Dir dir) {

    }

    /**
     * Reset the level, meaning the moves are set to zero, cells, etc
     */
    public void reset() {
        moves = 0;
    }

    /**
     * Method to insert into the game area the type of cell depending of the
     * char read from file
     *
     * @param line
     * @param column
     * @param type
     */
    public void put(int line, int column, char type) {
        printCellBoard(line, column, type);
        if (type != '*') {
            //Actor actor = createActor(type);
            //cellboard[line][column] = createCell(actor);
            printCellBoard(line, column, type);
        }
    }

    /**
     * Set a new instance of Actor depending of type.
     * We need to think carefull if we have a special Cell that can have more
     * than one Actor, like HoleCell, ObjectiveCell, FloorCell,
     *
     * @param type symbol for the actor type
     * @return instance of a Type of Actor
     */
    private Actor createActor(char type) {
        switch (type) {
            case '@':
                return new ManActor(type);
            case 'B':
                return new BoxActor(type);
            case '.':
                return new EmptyActor(type);
            case ' ':
                return new FloorActor(type);
            case 'X':
                return new WallActor(type);
            case '*':
                return new ObjectiveActor(type);
            case 'H':
                return new HoleActor(type);
            default:
                return null;
        }
    }

    private Cell createCell(Actor a) {
        switch (a.getActor()) {
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
                return null;
        }
    }

    private void printCellBoard(int l, int c, char t) {
        //System.out.println(l + " x " + c + " : " + " (" + t + ") " +
        // (cellboard[l][c]).getClass());
        System.out.println(l + " x " + c + " : " + " (" + t + ") ");
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
