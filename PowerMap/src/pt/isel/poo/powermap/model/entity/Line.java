package pt.isel.poo.powermap.model.entity;

import pt.isel.poo.powermap.Utils;
import pt.isel.poo.powermap.model.State;

import java.util.LinkedList;

public class Line extends Cell {


    public State state;
    public boolean on = false;
    private LinkedList<State> list = new LinkedList<>();

    public Line(int x, int y, State state) {
        super(x, y);
        this.state = state;
        initiateState(state);
    }

    public State getState() {
        return state;
    }

    @Override
    public void rotate() {
        if(this.state==State.UP) {
            state = State.DOWN;
            list.remove(State.UP);
            list.remove(State.DOWN);
            list.add(State.RIGHT);
            list.add(State.LEFT);
        }
        else {
            state = State.UP;
            list.remove(State.LEFT);
            list.remove(State.RIGHT);
            list.add(State.UP);
            list.add(State.DOWN);
        }
    }

    @Override
    public void initiateState(State state) {
        list=new LinkedList<>();
        if(state==State.UP) {
            list.add(state);
            list.add(State.DOWN);
        }else {
            list.add(State.LEFT);
            list.add(State.RIGHT);
        }
    }

    @Override
    public boolean hasState(State state) {
        return list.contains(state);
    }

    public static Line createRandom(int x, int y) {
        return new Line(x, y,State.values()[Utils.randomNum(0,1)]);
    }


}
