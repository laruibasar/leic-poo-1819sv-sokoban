package isel.poo.sokoban.view;

import isel.leic.pg.Console;
import static isel.leic.pg.Console.*;

import isel.poo.console.FieldView;
import isel.poo.console.ParentView;

public class StatusPanel extends ParentView {

    private int boxes;
    private int moves;
    private int level;

    private FieldView fvLevel;
    private FieldView fvMoves;
    private FieldView fvBoxes;

    public static final int WIDTH = 7;
    public static final int HEIGHT = 16;

    public StatusPanel(int left) {
        super(0, left, HEIGHT, WIDTH, DARK_GRAY);

        this.level = 0;
        fvLevel = new FieldView("Level", 1, 1, Integer.toString(this.level));
        addView(fvLevel);

        fvMoves = new FieldView("Moves", 4, 1, Integer.toString(this.moves));
        addView(fvMoves);
        this.moves = 0;

        fvBoxes = new FieldView("Boxes", 7, 1, Integer.toString(this.boxes));
        addView(fvBoxes);
        this.boxes = 0;

        writeTitle(10, "Cursor");
        writeContent(11,"move");
        writeTitle(12,"Esc");
        writeContent(13,"finish");
        writeTitle(14,"S");
        writeContent(15,"start");
        cursor(0,0);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBoxes(int boxes) {
        this.boxes = boxes;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

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
