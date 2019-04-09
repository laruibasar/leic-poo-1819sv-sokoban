package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class HoleTile extends CellTile{

    public HoleTile(){
        setSize(CellTile.SIDE, CellTile.SIDE);
    }

    @Override
    public void paint(){
        Console.setBackground(Console.CYAN);
        Console.setForeground(Console.BLACK);
        print(0,0,'~'); print(0,1,'~');
        print(1,0,'~'); print(1,1,'~');
    }
}
