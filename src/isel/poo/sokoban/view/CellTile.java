package isel.poo.sokoban.view;

import isel.poo.console.tile.Tile;
import isel.poo.sokoban.model.Cell;

public class CellTile extends Tile {

    public static final int SIDE = 2;

    private char type;
    private int color;

    public CellTile(Cell cell) {
        this.type = cell.getType();
    }

    public static Tile tileOf(Cell cell) { return new BoxTile(cell); }
}