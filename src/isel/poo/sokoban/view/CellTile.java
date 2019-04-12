package isel.poo.sokoban.view;

import isel.poo.console.tile.Tile;
import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

import static isel.poo.sokoban.model.Actor.OBJECTIVE;

public class CellTile extends Tile {

    public static final int SIDE = 2;

    private int background, foreground;

    public CellTile() {

        setSize(CellTile.SIDE, CellTile.SIDE);
    }
    //returns the correct tile based on type/actor of cell
    public static Tile tileOf(Cell cell) {

        Actor second = cell.getActor();
        Actor first = cell.getType();

        if(second==null) {
            switch (first) {
                case EMPTY:
                    return new EmptyTile();
                case FLOOR:
                    return new FloorTile();
                case HOLE:
                    return new HoleTile();
                case OBJECTIVE:
                    return new ObjectiveTile();
                case WALL:
                    return new WallTile();
                default:
                    return new HoleTile();
            }
        }else{
            switch (second) {
                case MAN:
                    return new ManTile();
                case BOX:{
                    if(first==OBJECTIVE)
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