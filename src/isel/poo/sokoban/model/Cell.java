package isel.poo.sokoban.model;

public class Cell {

    private Actor main;

    public Cell() { }

    public Cell(Actor a) {
        this.main = a;
    }

    public Actor getType() {
        return this.main;
    }

    public void updateCell(Actor a) { }
}
