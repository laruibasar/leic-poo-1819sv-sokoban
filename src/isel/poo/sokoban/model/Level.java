package isel.poo.sokoban.model;

import isel.poo.sokoban.model.cell.*;

import static isel.poo.sokoban.model.Actor.*;

public class Level {

    private int levelNumber;
    private int height;
    private int width;
    private int boxes;
    private int moves;
    private int manLine;
    private int manColumn;

    private boolean manInHole;
    private boolean boxInHole;

    private Actor man;


    /**
     * The game area, full of cells, is a bi-dimensional array
     * of size to be define at each level
     */
    private Cell[][] cellboard;

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
        return (this.boxes == 0) || manInHole || boxInHole;
    }

    public boolean manIsDead() {
        // we also die from stupidity: don't put boxes in holes
        return this.manInHole || this.boxInHole;
    }

    public int getNumber() {
        return this.levelNumber;
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

        // we made this moot when we allowed talk between Cells
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

        Cell next = cellboard[newLine][newColumn];
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

        } else if (next.getActor() == BOX) {
            int fwdLine = newLine + (newLine - manLine);
            int fwdColumn = newColumn + (newColumn - manColumn);
            Cell fwd = cellboard[fwdLine][fwdColumn];

            if (fwd.canEnter()) {
                System.out.println("We move forward");
                // we place the box in the forward cell
                fwd.updateCell(next.getActor());
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

                // lookout: if we put the box in the hole we loose
                if (fwd.getType() == HOLE && fwd.getActor() == BOX) {
                    boxInHole = true;
                    System.out.println("Box went to china");
                }
            }
        } else {
            // let's head back because we can't move
            System.out.println("Could not play");
            return;
        }

        paintGame();

        // we really should check for good this happening

        // and if something good ended and we are in a slump
        if (next.getType() == HOLE && next.getActor() == MAN) {
            manInHole = true;
            System.out.println("Down the rabbit hole");
        }

    }

    /**
     * We verified if we have a Objective Cell with a Box
     */
    private void updateBoxes(Cell c) {
        if (c.getType() == OBJECTIVE && c.isBoxInObjective()) {
            boxes--;
        } else {
            boxes++;
        }
    }

    private void paintGame() {
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cellboard[i][j].getActor() != null)
                    System.out.print(cellboard[i][j].getActor() + "\t");
                else
                    System.out.print(cellboard[i][j].getType() + "\t");
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

        if (cellboard[line][column] == null) {
            cellboard[line][column] = createCell(actor);
        } else {
            cellboard[line][column].updateCell(actor);
            updateBoxes(cellboard[line][column]);
        }

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
                return BOX;
            case '.':
                return EMPTY;
            case ' ':
                return FLOOR;
            case 'X':
                return WALL;
            case '*':
                return OBJECTIVE;
            case 'H':
                return HOLE;
            default:
                return null;
        }
    }

    private Cell createCell(Actor a) {
        switch (a) {
            case MAN:
            case BOX:
                Cell c = new FloorCell(FLOOR);
                c.updateCell(a);
                return c;
            case EMPTY:
                return new EmptyCell(a);
            case FLOOR:
                return new FloorCell(a);
            case WALL:
                return new WallCell(a);
            case OBJECTIVE:
                return new ObjectiveCell(a);
            case HOLE:
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
