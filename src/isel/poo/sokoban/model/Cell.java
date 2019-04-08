package isel.poo.sokoban.model;

/**
 * Class Cell
 *
 * This class represents the cell used in creating the game area.
 * This class allows for for subclasses to extends this and get different
 * behaviours according with the actors expected to host.
 */
public class Cell {

    /**
     * Define the base type of actor the cell host
     */
    protected Actor main;

    /**
     * There are cells types that should allow to host to actors.
     * E.g. the floor cells should allow to have the Man or the Box to go
     * through them.
     */
    protected Actor secondary;

    public Cell() { }

    /**
     * Public constructor
     * @param a represent the main actor to be host on the cell.
     */
    public Cell(Actor a) {
        this.main = a;
    }

    /**
     * Method to identified the type of cell in game. The type is relative to
     * the main actor of the cell
     * @return the actor of the cell
     */
    public Actor getType() {
        return this.main;
    }

    /**
     * We need a more talkative class, so this method is more subtle than the
     * getType, here we return the actor using the cell
     */
    public Actor getActor() {
        return this.secondary;
    }

    /**
     * This update the actor placed in the cell
     * @param a the actor to be placed on the cell
     */
    public void updateCell(Actor a) { }

    /**
     * This remove the actor placed in the cell
     */
    public void removeActor() { }

    /**
     * And this say to anyone who cares if the cell allows changing type.
     * If allows someone to enter the cell
     */
    public boolean canEnter() {
        return (this.secondary == null);
    }

    /**
     * This answers the question: am i fulfilled?
     * Only overrides in ObjectiveCell
     */
    public boolean isBoxInObjective() {
        return false;
    }
}
