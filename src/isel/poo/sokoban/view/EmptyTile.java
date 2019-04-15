package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class EmptyTile extends CellTile{

    public EmptyTile(){
        super();
    }

    @Override
    public void paint(){
        Console.setBackground(Console.BLACK);
        Console.setForeground(Console.BLACK);
        print(0,0,'O'); print(0,1,'O');
        print(1,0,'O'); print(1,1,'O');
        }
}
