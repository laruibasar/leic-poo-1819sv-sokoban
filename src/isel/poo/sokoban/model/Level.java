package isel.poo.sokoban.model;

import isel.poo.sokoban.model.cell.*;

import static isel.poo.sokoban.model.Actor.*;
import static isel.poo.sokoban.model.CellType.*;

public class Level {

    private int levelNumber;
    private int height;
    private int width;

    private int remainingBoxes;
    private int moves;

    /**
     * The numObjectives and numBoxes is for check for losing conditions,
     * when we have less boxes than objectives
     */
    private int numObjectives;
    private int numBoxes;

    private boolean manInHole;
    private boolean boxInHole;

    private Actor man;
    private int manLine;
    private int manColumn;

    /**
     * The game area, full of cells, is a bi-dimensional array
     * of size to be define at each level
     */
    private Cell[][] cellboard;

    private Observer listener;

    /**
     * Class constructor, setting up the parameters needed to start the show
     *
     * @param levelNumber the level number for the game
     * @param height the number of lines
     * @param width the number of columns
     */
    public Level (int levelNumber, int height, int width) {
        this.levelNumber = levelNumber;
        this.height = height;
        this.width = width;
        this.cellboard = new Cell[height][width];
        this.moves = 0;
        this.remainingBoxes = 0;

        this.numBoxes = 0;
        this.numObjectives = 0;

        this.manInHole = false;
        this.boxInHole = false;
        this.man = MAN;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isFinished() {
        return (this.remainingBoxes == 0) || manInHole || boxInHole;
    }

    public boolean manIsDead() {
        // we also die from stupidity: don't put to many boxes in holes
        return this.manInHole || this.boxInHole;
    }

    public int getNumber() {
        return this.levelNumber;
    }

    public int getRemainingBoxes() {
        return remainingBoxes;
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

    /**
     * Method called for the movement of the man in the game area
     * @param dir the movement direction from the user
     */
    public void moveMan(Dir dir) {
        int newLine = manLine;
        int newColumn = manColumn;

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
        }

        // moves the man and save the new position
        if (moveManInCell(newLine, newColumn)) {
            manLine = newLine;
            manColumn = newColumn;

            moves++;
        } else {
            return;
        }

        updateRemainingBoxes();
        paintGame();
    }

    /**
     * Method to make the man and boxes move in the cells
     * @param newLine the new line in the board to move to move into
     * @param newColumn the new column in the board to move into
     * @return success of the move
     */
    public boolean moveManInCell(int newLine, int newColumn) {
        Cell current = cellboard[manLine][manColumn];
        Cell next = cellboard[newLine][newColumn];

        if (next.canEnter()) {
            next.updateCell(man);
            listener.cellReplaced(newLine, newColumn, next);

            current.removeActor();
            listener.cellReplaced(manLine, manColumn, current);
        } else if (next.getActor() == BOX) {
            int fwdLine = newLine + (newLine - manLine);
            int fwdColumn = newColumn + (newColumn - manColumn);
            Cell fwd = cellboard[fwdLine][fwdColumn];

            if (fwd.canEnter()) {
                // lookout: if we put the box in the hole we lose it
                if (fwd.getType() == HOLE && next.getActor() == BOX) {
                    numBoxes--;

                    // we loose if we can't end
                    if (numBoxes < numObjectives)
                        boxInHole = true;

                    cellboard[fwdLine][fwdColumn] = new FloorCell(FLOOR);
                    listener.cellReplaced(fwdLine, fwdColumn, fwd);
                }

                fwd.updateCell(next.getActor());
                listener.cellReplaced(fwdLine, fwdColumn, fwd);

                next.removeActor();
                next.updateCell(man);
                listener.cellReplaced(newLine, newColumn, next);

                current.removeActor();
                listener.cellReplaced(manLine, manColumn, current);
            } else {
                return false;
            }
        } else {
            // let's head back because we really can't move
            return false;
        }

        // if something good ended and we go down the rabbit hole
        if (next.getType() == HOLE && next.getActor() == MAN) {
            manInHole = true;
        }

        return true;
    }


    /**
     * Do a full sweep to check for boxes in objectives and updates
     * the remaining boxes in the level
     */
    private void updateRemainingBoxes() {
        Cell c;
        int bc = 0;

        // count all boxes
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                c = cellboard[i][j];

                if (c.getActor() == BOX)
                    bc++;
            }
        }

        // count all boxes in ObjectiveCells
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                c = cellboard[i][j];

                if (c.getType() == OBJECTIVE) {
                    if (c.getActor() == BOX)
                        bc--;
                }
            }
        }
        remainingBoxes = bc;
    }

    private void paintGame() {
        System.out.println();
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cellboard[i][j].getActor() != Actor.EMPTY) {
                    switch (cellboard[i][j].getActor()) {
                        case MAN:
                            System.out.print("@ ");
                            break;
                        case BOX:
                            System.out.print("B ");
                            break;
                    }
                } else {
                    switch (cellboard[i][j].getType()) {
                        case EMPTY:
                            System.out.print(". ");
                            break;
                        case FLOOR:
                            System.out.print("  ");
                            break;
                        case WALL:
                            System.out.print("X ");
                            break;
                        case OBJECTIVE:
                            System.out.print("* ");
                            break;
                        case HOLE:
                            System.out.print("H ");
                            break;
                    }
                }
            }
            System.out.println();
        }

    }

    /**
     * Reset the level stats et all, meaning the moves are set to zero,
     * cells, etc
     */
    public void reset() {
        moves = 0;
        remainingBoxes = 0;
        cellboard = new Cell[height][width];
    }

    /**
     * Method to insert into the game area the type of cell depending of the
     * char read from file
     *
     * @param line the line in the game area
     * @param column the column in the game area
     * @param type the type of actor
     */
    public void put(int line, int column, char type) {
        // lock man position
        if (type == '@') {
            manLine = line;
            manColumn = column;
        }

        // count the number of boxes in the level
        if (type == 'B') {
            remainingBoxes++;
            numBoxes++;
        }

        // count the objectives to account for lost boxes
        if (type == '*')
            numObjectives++;

        if (cellboard[line][column] == null) {
            cellboard[line][column] = createCell(type);
        } else {
            Actor a = createActor(type);
            cellboard[line][column].updateCell(a);
            updateBoxes(cellboard[line][column]);
        }

    }

    /**
     * We verified if we have a Objective Cell with a Box
     */
    private void updateBoxes(Cell c) {
        if (c.getType() == OBJECTIVE && c.isBoxInObjective()) {
            remainingBoxes--;
        } else {
            remainingBoxes++;
        }
    }

    /**
     * Set a new instance of Actor depending of type.
     * We need to think careful if we have a special Cell that can have more
     * than one Actor, like HoleCell, ObjectiveCell, FloorCell,
     *
     * @param type symbol for the actor type
     * @return enum of the Type of Actor
     */
    private Actor createActor(char type) {
        switch (type) {
            case '@':
                return man;
            case 'B':
                return BOX;
            case '.':
            default:
                return Actor.EMPTY;
        }
    }

    /**
     * Create the cell depending of the type of actor.
     * @param ch the char read from file
     * @return the cell type
     */
    private Cell createCell(char ch) {
        Cell c = new FloorCell(FLOOR);

        switch (ch) {
            case '@':
                c.updateCell(man);
                return c;
            case 'B':
                c.updateCell(Actor.BOX);
                return c;
            case '.':
                return new EmptyCell(CellType.EMPTY);
            case ' ':
                return c;
            case 'X':
                return new WallCell(WALL);
            case '*':
                return new ObjectiveCell(OBJECTIVE);
            case 'H':
                return new HoleCell(HOLE);
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
