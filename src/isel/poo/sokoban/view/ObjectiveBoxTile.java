package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class ObjectiveBoxTile extends CellTile{

    public ObjectiveBoxTile(){

        super();
    }

    @Override
    public void paint(){
        Console.setBackground(Console.GREEN);
        Console.setForeground(Console.BLACK);
        print(0,0,'O'); print(0,1,'O');
        print(1,0,'O'); print(1,1,'O');
    }
}
