package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class ObjectiveCell extends Cell {

    private Actor secondary;

    public ObjectiveCell() {
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
