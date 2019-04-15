package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class ObjectiveTile extends CellTile{

    public ObjectiveTile(){
        super();
    }

    @Override
    public void paint(){
        Console.setBackground(Console.WHITE);
        Console.setForeground(Console.GREEN);
        print(0,0,'+'); print(0,1,'+');
        print(1,0,'+'); print(1,1,'+');
    }
}
