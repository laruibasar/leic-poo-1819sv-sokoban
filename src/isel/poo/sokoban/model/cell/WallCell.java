package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class WallCell extends Cell {

    public WallCell(Actor a) {
        super(a);
    }

    @Override
    public void updateCell(Actor a) { }

    @Override
    public void removeActor() { }

    @Override
    public boolean canEnter() {
        // because we are a wall, we say nay
        return false;
    }

}
