package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class FloorTile extends CellTile{

    public FloorTile(){
        setSize(CellTile.SIDE, CellTile.SIDE);
    }

    @Override
    public void paint(){
        Console.setBackground(Console.WHITE);
        Console.setForeground(Console.WHITE);

    }
}
