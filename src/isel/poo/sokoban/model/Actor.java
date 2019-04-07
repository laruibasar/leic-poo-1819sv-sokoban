package isel.poo.sokoban.model;

/**
 * Class Actor
 *
 * This class has the property of the symbol loaded from the Level file
 * and allows for a keep track of a object through the board
 */
public class Actor {

    /**
     * The symbol from the level is store in the Actor, this way, across the
     * game is easy to identify the actor without ask for getClass() or use a
     * string to identified
     */
    protected char symbol;

    /**
     * public constructor
     */
    public Actor() { }

    /**
     * public constructor
     * @param type is the char representing the actor
     */
    public Actor(char type) {
        this.symbol = type;
    }

    /**
     * Getter for present the type of actor of this instance
     * @return
     */
    public char getActor() {
        return this.symbol;
    }
}
