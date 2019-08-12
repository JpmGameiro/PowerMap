package pt.isel.poo.powermap.view.entity;


import isel.leic.pg.Console;
import pt.isel.poo.powermap.model.State;
import pt.isel.poo.powermap.model.entity.Line;
import pt.isel.poo.powermap.view.PowerMapView;

public class LineView extends CellView {

    public Line line;
    private char[][] upArray = new char[][]{{' ', '|', ' '}, {' ', 'o', ' '}, {' ', '|', ' '}};
    private char[][] downArray = new char[][]{{' ', ' ', ' '}, {'-', 'o', '-'}, {' ', ' ', ' '}};

    public LineView(int x, int y, Line line) {
        super(x, y);
        this.line = line;
        bColor = Console.BLACK;
        fColor = Console.WHITE;
    }

    @Override
    public void draw(boolean on) {
        char[][] aux;
        if (line.getState()== State.DOWN) {
            aux = downArray;
        }
        else aux = upArray;
        if(!on)
            Console.color(fColor, bColor);
        else Console.color(Console.BLACK,Console.YELLOW);
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
        Console.cursor(line.getX() * PowerMapView.CELL_HEIGHT + 1, line.getY() * PowerMapView.CELL_WIDTH + 1);
        if (!on)
            Console.color(fColor, bColor);
        else Console.color(Console.BLACK, Console.YELLOW);
        Console.print("o");
            }
        }



