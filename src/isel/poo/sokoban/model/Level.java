package isel.poo.sokoban.model;

import isel.poo.sokoban.model.actor.*;
import isel.poo.sokoban.model.cell.*;

public class Level {

    private int levelNumber;
    private int height;
    private int width;
    private int boxes;
    private int moves;
    private int manLine;
    private int manColumn;
    private Actor man;

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
     * Class constructor, setting up the parameters needed to start the show
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
        this.man = new ManActor();
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
        int newLine = manLine;
        int newColumn = manColumn;
        Cell current = cellboard[manLine][manColumn];
        Cell next;

        // we made this moot when we allowed talk between cells
        switch (dir) {
            case UP:
                if (--newLine < 0)
                    return;
                break;
            case DOWN:
                if (++newLine > (height - 1))
                    return;
                break;
            case LEFT:
                if (--newColumn < 0)
                    return;
                break;
            case RIGHT:
                if (++newColumn > (width - 1))
                    return;
                break;
            default:
                return;
        }

        next = cellboard[newLine][newColumn];
        if (next.canEnter()) {
            // we place the man
            next.updateCell(man);
            //listener.cellUpdated(manLine, manColumn, next);

            // we remove the man
            current.removeActor();
            //listener.cellReplaced(newLine, newColumn, current);

            manLine = newLine;
            manColumn = newColumn;
            moves++;
            paintGame();
        } else if (next.getType().getActor() == 'B') {
            int fwdLine = newLine + (newLine - manLine);
            int fwdColumn = newColumn + (newColumn - manColumn);
            Cell fwd = cellboard[fwdLine][fwdColumn];
            if (fwd.canEnter()) {
                // we place the box in the forward cell
                fwd.updateCell(next.getType());
                //listener.cellUpdated(manLine, manColumn, fwd);

                // we remove the box, and place the man
                next.removeActor();
                next.updateCell(man);
                //listener.cellReplaced(newLine, newColumn, next);

                // man exit the current cell, we clean the cell
                current.removeActor();
                //listener.cellReplaced(newLine, newColumn, current);

                manLine = newLine;
                manColumn = newColumn;
                moves++;
                paintGame();
            }
        }

    }

    public void paintGame() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(" " + cellboard[i][j].getType().getActor() + " ");
            }
            System.out.println(" ;");
        }
    }

    /**
     * Reset the level stats et all, meaning the moves are set to zero,
     * cells, etc
     */
    public void reset() {
        moves = 0;
        boxes = 0;
        cellboard = new Cell[width][height];
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
        // fix the position of man
        if (type == '@') {
            manLine = line;
            manColumn = column;
        }

        // count the number of boxes in the level
        if (type == 'B')
            boxes++;

        Actor actor = createActor(type);
        if (cellboard[line][column] == null)
            cellboard[line][column] = createCell(actor);
        else
            cellboard[line][column].updateCell(actor);
    }

    /**
     * Set a new instance of Actor depending of type.
     * We need to think careful if we have a special Cell that can have more
     * than one Actor, like HoleCell, ObjectiveCell, FloorCell,
     *
     * @param type symbol for the actor type
     * @return instance of a Type of Actor
     */
    private Actor createActor(char type) {
        switch (type) {
            case '@':
                return man;
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
            case 'B':
                Cell c = new FloorCell(new FloorActor(' '));
                c.updateCell(a);
                return c;
            case '.':
                return new EmptyCell(a);
            case ' ':
                return new FloorCell(a);
            case 'X':
                return new WallCell(a);
            case '*':
                return new ObjectiveCell(a);
            case 'H':
                return new HoleCell(a);
            default:
                return null;
        }
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
