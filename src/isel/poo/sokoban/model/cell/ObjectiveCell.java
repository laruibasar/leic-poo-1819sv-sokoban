package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class ObjectiveCell extends Cell {

    public ObjectiveCell(Actor a) {
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

    public boolean isCompleted() {
        return (secondary.getActor() == 'B') ? true : false;
    }
}
