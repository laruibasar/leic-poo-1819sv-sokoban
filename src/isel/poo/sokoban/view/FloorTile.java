package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class FloorTile extends CellTile{

    public FloorTile(){

        super();
    }

    @Override
    public void paint(){
        Console.setBackground(Console.WHITE);
        Console.setForeground(Console.WHITE);
        print(0,0,'O'); print(0,1,'O');
        print(1,0,'O'); print(1,1,'O');

    }
}
