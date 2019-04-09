package isel.poo.sokoban.view;

import isel.poo.console.tile.Tile;
import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class CellTile extends Tile {

    public static final int SIDE = 2;

    private int background, foreground;
    private Actor type;

    public CellTile(Cell cell) {
        this.type = cell.getType();
    }

    public static Tile tileOf(Cell cell) {

        switch (cell.getActor()){
            case '@': return new ManTile();
            break;
            case '@': return new BoxTile();
            break;
            case '@': return new EmptyTile();
            break;
            case '@': return new FloorTile();
            break;
            case '@': return new HoleTile();
            break;
            case '@': return new ObjectiveTile();
            break;
            case '@': return new ObjectiveBoxTile();
            break;
            case '@': return new WallTile();
            break;
        }

        return new BoxTile(cell); }

    @Override
    public void paint() { }
}