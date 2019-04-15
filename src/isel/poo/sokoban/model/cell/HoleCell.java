package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;
import isel.poo.sokoban.model.CellType;

public class HoleCell extends Cell {

    public HoleCell(CellType t) {
        super(t);
    }

    @Override
    public void updateCell(Actor a) {
        this.actor = a;
    }

    @Override
    public void removeActor() {
        this.actor = null;
    }
}
