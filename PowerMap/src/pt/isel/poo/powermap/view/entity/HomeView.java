package pt.isel.poo.powermap.view.entity;

import isel.leic.pg.Console;
import pt.isel.poo.powermap.model.State;
import pt.isel.poo.powermap.model.entity.Home;
import pt.isel.poo.powermap.view.PowerMapView;

public class HomeView extends CellView {
    private Home home;
    private char[][] upArray = new char[][]{{' ', '|', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private char[][] downArray = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', '|', ' '}};
    private char[][] leftArray = new char[][]{{' ', ' ', ' '}, {'-', ' ', ' '}, {' ', ' ', ' '}};
    private char[][] rigthArray = new char[][]{{' ', ' ', ' '}, {' ', ' ', '-'}, {' ', ' ', ' '}};

    public HomeView(int x, int y, Home home) {
        super(x, y);
        this.home = home;
        bColor = Console.RED;
        fColor = Console.WHITE;
    }


    @Override
    public void draw(boolean on) {
        char[][] aux;
        if (home.getState() == State.UP) {
            aux = upArray;
        } else if (home.getState() == State.DOWN) {
            aux = downArray;
        } else if (home.getState() == State.LEFT) {
            aux = leftArray;
        } else aux = rigthArray;

        if(!on)
            Console.color(Console.WHITE, Console.BLACK);
        else Console.color(Console.BLACK,Console.YELLOW);
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                if(aux[i][j]=='-' || aux[i][j] == '|') {
                    if (!on)
                        Console.color(fColor, Console.BLACK);
                    else Console.color(Console.BLACK, Console.YELLOW);
                }
                else Console.color(Console.WHITE,Console.BLACK);

                Console.cursor(PowerMapView.CELL_WIDTH * this.x + i, PowerMapView.CELL_HEIGHT * this.y + j);
                Console.print(aux[i][j]);
            }
        }
        Console.cursor(home.getX() * PowerMapView.CELL_HEIGHT + 1, home.getY() * PowerMapView.CELL_WIDTH + 1);
        if (!on)
            Console.color(fColor, bColor);
        else Console.color(Console.RED, Console.YELLOW);
        Console.print("H");

    }
}
