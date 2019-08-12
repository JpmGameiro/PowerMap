package pt.isel.poo.powermap.view.entity;

import isel.leic.pg.Console;
import pt.isel.poo.powermap.model.State;
import pt.isel.poo.powermap.model.entity.Power;
import pt.isel.poo.powermap.view.PowerMapView;

public class PowerView extends CellView {

    private Power power;
    private char[][] upArray = new char[][]{{' ', '|', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private char[][] downArray = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', '|', ' '}};
    private char[][] leftArray = new char[][]{{' ', ' ', ' '}, {'-', ' ', ' '}, {' ', ' ', ' '}};
    private char[][] rigthArray = new char[][]{{' ', ' ', ' '}, {' ', ' ', '-'}, {' ', ' ', ' '}};

    public PowerView(int x, int y, Power power) {
        super(x, y);
        this.power = power;
        bColor = Console.YELLOW;
        fColor = Console.BROWN;
    }

    @Override
    public void draw(boolean on) {
        char[][] aux;
        if (power.getState() == State.UP) {
            aux = upArray;
        } else if (power.getState() == State.DOWN) {
            aux = downArray;
        } else if (power.getState() == State.LEFT) {
            aux = leftArray;
        } else aux = rigthArray;

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
               if(aux[i][j]=='-' || aux[i][j] == '|')
                   Console.color(Console.BLACK,Console.YELLOW);
               else Console.color(Console.WHITE,Console.BLACK);

                Console.cursor(PowerMapView.CELL_WIDTH * this.x + i, PowerMapView.CELL_HEIGHT * this.y + j);
                Console.print(aux[i][j]);
            }
        }
        Console.cursor(power.getX() * PowerMapView.CELL_HEIGHT + 1, power.getY() * PowerMapView.CELL_WIDTH + 1);
        Console.color(fColor, bColor);
        Console.print("P");

    }
}
