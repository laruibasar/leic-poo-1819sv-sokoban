package isel.poo.sokoban.model;

/*
 * Class Actor
 *
 * This class as the property of the symbol loaded from the Level file
 * and allows for a keep track of a object through the board
 */
public class Actor {
    private char symbol;

    public Actor() { }

    public Actor(char type) {
        this.symbol = type;
    }

    public char getActor() {
        return symbol;
    }
}
