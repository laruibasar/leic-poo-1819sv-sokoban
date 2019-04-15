package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.CellType;
import isel.poo.sokoban.model.Cell;

public class FloorCell extends Cell {

    public FloorCell(CellType t) {
        super(t);
    }

    @Override
    public void updateCell(Actor a) {
        this.actor = a;
    }

    @Override
    public void removeActor() {
        this.actor = Actor.EMPTY;
    }

}