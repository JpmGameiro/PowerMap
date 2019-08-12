package pt.isel.poo.powermap.view.entity;


import isel.leic.pg.Console;
import pt.isel.poo.powermap.model.State;
import pt.isel.poo.powermap.model.entity.Curve;
import pt.isel.poo.powermap.view.PowerMapView;

public class CurveView extends CellView {

    public Curve curve;
    private char[][] upArray = new char[][]{{' ', '|', ' '}, {' ', 'o', '-'}, {' ', ' ', ' '}};
    private char[][] downArray = new char[][]{{' ', ' ', ' '}, {'-', 'o', ' '}, {' ', '|', ' '}};
    private char[][] leftArray = new char[][]{{' ', '|', ' '}, {'-', 'o', ' '}, {' ', ' ', ' '}};
    private char[][] rigthArray = new char[][]{{' ', ' ', ' '}, {' ', 'o', '-'}, {' ', '|', ' '}};

    public CurveView(int x, int y, Curve curve) {
        super(x, y);
        this.curve = curve;
        bColor = Console.BLACK;
        fColor = Console.WHITE;
    }

    @Override
    public void draw(boolean on) {
        char[][] aux;
        if (curve.getState() == State.UP) {
            aux = upArray;
        } else if (curve.getState() == State.DOWN) {
            aux = downArray;
        } else if (curve.getState() == State.LEFT) {
            aux = leftArray;
        } else aux = rigthArray;
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                if(aux[i][j]=='-' || aux[i][j] == '|') {
                    if (!on)
                        Console.color(fColor, bColor);
                    else Console.color(Console.BLACK, Console.YELLOW);
                }
                else Console.color(Console.WHITE,Console.BLACK);

                Console.cursor(PowerMapView.CELL_WIDTH * this.x + i, PowerMapView.CELL_HEIGHT * this.y + j);
                Console.print(aux[i][j]);
            }
        }
        Console.cursor(curve.getX() * PowerMapView.CELL_HEIGHT + 1, curve.getY() * PowerMapView.CELL_WIDTH + 1);
        if (!on)
            Console.color(fColor, bColor);
        else Console.color(Console.BLACK, Console.YELLOW);
        Console.print("o");
    }
}
