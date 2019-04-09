package isel.poo.sokoban.view;

import isel.leic.pg.Console;


public class BoxTile extends CellTile {

        public BoxTile(){
        setSize(CellTile.SIDE, CellTile.SIDE);
    }

    @Override
    public void paint(){
        Console.setBackground(Console.RED);
        Console.setForeground(Console.BLACK);
        print(0,0,'O'); print(0,1,'O');
        print(1,0,'O'); print(1,1,'O');
    }

}
