package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class EmptyCell extends Cell {

    public EmptyCell(Actor a) {
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

    @Override
    public boolean canEnter() {
        // because we are over the wall, nope
        return false;
    }
}
