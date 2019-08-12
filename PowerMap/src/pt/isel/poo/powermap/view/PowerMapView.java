package pt.isel.poo.powermap.view;

import pt.isel.poo.powermap.model.entity.*;
import pt.isel.poo.powermap.model.PowerMapModel;
import pt.isel.poo.powermap.view.entity.*;

public class PowerMapView implements PowerViewListener {
    public static final int CELL_HEIGHT = 3;
    public static final int CELL_WIDTH = 3;
    private CellView[][] cellView;


    public void setViews(PowerMapModel model) {
        cellView = new CellView[model.getNumLines()][model.getNumColumns()];
        for (int i = 0; i < cellView.length; i++) {
            for (int j = 0; j < cellView.length; j++) {
                cellView[i][j] = map(model.getCell(i, j));
            }
        }
    }

    private CellView map(Cell cell) {
        CellView cellView = null;

        if (cell instanceof Branch)
            cellView = new BranchView(cell.getX(), cell.getY(), (Branch) cell);

        if (cell instanceof Curve)
            cellView = new CurveView(cell.getX(), cell.getY(), (Curve) cell);

        if (cell instanceof Line)
            cellView = new LineView(cell.getX(), cell.getY(), (Line) cell);

        if (cell instanceof Home)
            cellView = new HomeView(cell.getX(), cell.getY(), (Home) cell);

        if (cell instanceof Power)
            cellView = new PowerView(cell.getX(), cell.getY(), (Power) cell);

        return cellView;

    }

    public void repaintAll(PowerMapModel model) {
        for (int i = 0; i < model.getNumLines(); i++) {
            for (int j = 0; j < model.getNumColumns(); j++) {
                if(model.getCell(i,j)!=null)
                    repaint(i, j, model.getCell(i,j).on);
            }
        }
    }

    @Override
    public void repaint(int x, int y,boolean on) {
        cellView[x][y].draw(on);
    }
}