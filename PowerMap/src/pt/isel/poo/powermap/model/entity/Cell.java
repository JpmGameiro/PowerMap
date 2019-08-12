package pt.isel.poo.powermap.model.entity;


import pt.isel.poo.powermap.model.State;

public abstract class Cell {

    private int x;

    private int y;
    public boolean on;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract State getState();

    public abstract void rotate();

    public abstract void initiateState(State state);

    public abstract boolean hasState(State state);
}
