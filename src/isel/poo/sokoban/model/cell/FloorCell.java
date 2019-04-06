package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class FloorCell extends Cell {
    private Actor secondary;

    public FloorCell() {
        super();
    }

    @Override
    public void updateCell(Actor a) {
        this.secondary = a;
    }

    public void removeActor() {
        this.secondary = null;
    }
}