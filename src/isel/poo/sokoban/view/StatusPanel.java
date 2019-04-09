package isel.poo.sokoban.view;

import isel.leic.pg.Console;
import static isel.leic.pg.Console.*;

import isel.poo.console.View;
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


        HelpView c = new HelpView();
        addView(c);
    }


    public void setLevel(int level) { fvLevel.setValue(level); }

    public void setBoxes(int boxes) { fvBoxes.setValue(boxes); }

    public void setMoves(int moves) { fvMoves.setValue(moves); }

    private static class HelpView extends View {
        private HelpView() {
            super(0,0, HEIGHT, WIDTH, DARK_GRAY);
            repaint();
        }

        @Override public void paint() {
            setBackground(DARK_GRAY);
            setForeground(YELLOW);
            print(10,0,"Cursor");
            setForeground(WHITE);
            print(11,1,"move");
            setForeground(YELLOW);
            print(12,0,"Esc");
            setForeground(WHITE);
            print(13,1,"finish");
            setForeground(YELLOW);
            print(14,0,"S");
            setForeground(WHITE);
            print(15,1,"start");
        }
    }
}
