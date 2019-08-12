package pt.isel.poo.powermap.view.entity;


import pt.isel.poo.powermap.model.CellListener;

public abstract class CellView implements CellListener {

    protected int x;
    protected int y;

    protected int bColor;
    protected int fColor;

    public CellView(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
