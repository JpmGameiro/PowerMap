package pt.isel.poo.powermap.console;

import isel.leic.pg.*;
import pt.isel.poo.powermap.model.PowerMapModel;
import pt.isel.poo.powermap.view.PowerMapView;

import java.awt.event.KeyEvent;
import java.io.FileReader;

public class PowerMapCtrl {
    private PowerMapModel model = new PowerMapModel();
    private PowerMapView view = new PowerMapView();

    public static void main(String[] args) {
        PowerMapCtrl control = new PowerMapCtrl();
        if (control.loadLevel("map1.txt"))
            control.run();
    }

    private void run() {
        Console.open("Power Map",
                model.getNumLines() * PowerMapView.CELL_HEIGHT,
                model.getNumColumns() * PowerMapView.CELL_WIDTH);
        Console.mouseClick(true);           // Liga a dete��o de clicks
        model.setChangeListener(view);      // Para atualiza��es de cada c�lula
        view.setViews(model);               // Inicia o view de cada c�lula
        view.repaintAll(model);             // Apresenta todas as c�lulas
        int key;
        for (; ; ) {
            key = Console.waitKeyPressed(0);    // Espera indefinidamente por uma tecla premida
            Console.waitKeyReleased(key);       // Espera que a tecla seja libertada
            if (key == KeyEvent.VK_ESCAPE)        // Abandona o jogo
                break;
            if (key == Console.MOUSE_CLICKED) {
                rotateCell(Console.getMouseClick()); // Roda a c�lula no local do click
                if (model.isCompleted()) {          // Mapa completo ?
                    toast("TERMINATED");
                    break;
                }
            }
        }
        toast("BYE");
        Console.close();
    }

    private void rotateCell(Location click) {
        int lin = click.lin / PowerMapView.CELL_HEIGHT;
        int col = click.col / PowerMapView.CELL_WIDTH;
        model.connectMap(lin, col);
        view.repaint(lin,col,model.getCell(lin,col).on); /*acrescentei eu*/
    }

    private void toast(String msg) {
        Console.cursor(0, (model.getNumColumns() * PowerMapView.CELL_WIDTH - msg.length()) / 2);
        Console.color(Console.RED, Console.YELLOW);
        Console.print(msg);
        while (Console.isKeyPressed()) ;
        Console.waitKeyPressed(5000);
        view.repaintAll(model);
    }

    private boolean loadLevel(String fileName) {
        try (FileReader file = new FileReader(fileName)) {
            model.loadLevel(file);
            model.checkNeighbours(model.getCell(model.xPower,model.yPower));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
