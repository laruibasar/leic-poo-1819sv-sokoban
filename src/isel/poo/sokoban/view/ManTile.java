package isel.poo.sokoban.view;

import isel.leic.pg.Console;


public class ManTile extends CellTile {


    public ManTile(){

        super();
    }

    @Override
    public void paint(){
        Console.setBackground(Console.YELLOW);
        Console.setForeground(Console.BLACK);
        print(0,0,'@'); print(0,1,'@');
        print(1,0,'@'); print(1,1,'@');
    }
}
