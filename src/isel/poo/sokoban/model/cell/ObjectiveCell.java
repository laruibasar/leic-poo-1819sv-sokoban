package isel.poo.sokoban.model.cell;

import isel.poo.sokoban.model.Actor;
import isel.poo.sokoban.model.Cell;

public class ObjectiveCell extends Cell {

    /**
     * Indicates if objective is fullfiled (this means, if this as a box
     */
    private boolean objective;

    public ObjectiveCell(Actor a) {
        super(a);
        this.objective = false;
    }

    @Override
    public void updateCell(Actor a) {
        this.secondary = a;
        if (a.getActor() == 'B')
            this.objective = true;
    }

    @Override
    public void removeActor() {
        this.secondary = null;
    }

    @Override
    public boolean isBoxInObjective() {
        return this.objective;
    }
}
