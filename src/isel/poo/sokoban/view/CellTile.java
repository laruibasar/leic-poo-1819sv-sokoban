package isel.poo.sokoban.view;

import isel.poo.console.tile.Tile;
import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.CellType;
import isel.poo.sokoban.model.Cell;

import static isel.poo.sokoban.model.CellType.OBJECTIVE;

public class CellTile extends Tile {

    public static final int SIDE = 2;

    private int background, foreground;

    public CellTile() {

        setSize(CellTile.SIDE, CellTile.SIDE);
    }
    //returns the correct tile based on type/actor of cell
    public static Tile tileOf(Cell cell) {

        CellType cellType = cell.getType();
        Actor actor = cell.getActor();

        if (actor == Actor.EMPTY) {
            switch (cellType) {
                case EMPTY:
                    return new EmptyTile();
                case FLOOR:
                    return new FloorTile();
                case OBJECTIVE:
                    return new ObjectiveTile();
                case WALL:
                    return new WallTile();
                case HOLE:
                default:
                    return new HoleTile();
            }
        } else {
            switch (actor) {
                case MAN:
                    return new ManTile();
                case BOX:{
                    if(cellType == OBJECTIVE)
                        return new ObjectiveBoxTile();
                    else
                        return new BoxTile();
                }
                default:
                    return new WallTile();
            }
        }
    }

    @Override
    public void paint() { }
}