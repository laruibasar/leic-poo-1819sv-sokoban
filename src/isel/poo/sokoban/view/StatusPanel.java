package isel.poo.sokoban.view;

import isel.leic.pg.Console;
import static isel.leic.pg.Console.*;

import isel.poo.console.FieldView;
import isel.poo.console.ParentView;

public class StatusPanel extends ParentView {

    private FieldView fvLevel;
    private FieldView fvMoves;
    private FieldView fvBoxes;

    public static final int WIDTH = 7;
    public static final int HEIGHT = 16;

    public StatusPanel(int left) {
        super(0, left, HEIGHT, WIDTH, DARK_GRAY);

        fvLevel = new FieldView("Level", 1, 1, "0");
        addView(fvLevel);

        fvMoves = new FieldView("Moves", 4, 1, "0");
        addView(fvMoves);

        fvBoxes = new FieldView("Boxes", 7, 1, "0");
        addView(fvBoxes);

        writeTitle(10, "Cursor");
        writeContent(11,"move");
        writeTitle(12,"Esc");
        writeContent(13,"finish");
        writeTitle(14,"S");
        writeContent(15,"start");
        cursor(0,0);
    }

    public void setLevel(int level) { fvLevel.setValue(level); }

    public void setBoxes(int boxes) { fvBoxes.setValue(boxes); }

    public void setMoves(int moves) { fvMoves.setValue(moves); }

    private void writeTitle(int top, String s) {
        cursor(top, 0);
        setForeground(YELLOW);
        Console.print(s);
    }

    private void writeContent(int top, String s) {
        cursor(top, 1);
        setForeground(WHITE);
        Console.print(s);
    }
}
