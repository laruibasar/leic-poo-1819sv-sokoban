package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class EmptyTile extends CellTile{

    public EmptyTile(){
        setSize(CellTile.SIDE, CellTile.SIDE);
    }

    @Override
    public void paint(){
        Console.setBackground(Console.BLACK);
        Console.setForeground(Console.BLACK);
        }
}
