package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class WallTile extends CellTile{
    public WallTile(){

        super();
    }

    @Override
    public void paint() {
        Console.setBackground(Console.BROWN);
        Console.setForeground(Console.BLACK);
        print(0, 0, '-'); print(0, 1, '-');
        print(1, 0, '-'); print(1, 1, '-');
    }
}
