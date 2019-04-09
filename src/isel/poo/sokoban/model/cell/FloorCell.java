package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class FloorCell extends Cell {

    public FloorCell(Actor a) {
        super(a);
    }

    @Override
    public void updateCell(Actor a) {
        this.secondary = a;
    }

    @Override
    public void removeActor() {
        this.secondary = null;
    }

}