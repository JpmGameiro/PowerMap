package pt.isel.poo.powermap.model.entity;

import pt.isel.poo.powermap.Utils;
import pt.isel.poo.powermap.model.State;

import java.util.LinkedList;


public class Power extends Cell {

    public State state;
    private LinkedList<State> list = new LinkedList<>();

    public Power(int x, int y, State state) {
        super(x, y);
        this.state = state;
        initiateState(state);
    }

    public State getState() {
        return state;
    }

    @Override
    public void rotate() {
        if (this.state == State.UP) {
            state = State.RIGHT;
            list.remove(State.UP);
            list.add(State.RIGHT);
        }
        else if (this.state == State.RIGHT) {
            state = State.DOWN;
            list.remove(State.RIGHT);
            list.add(State.DOWN);

        }
        else if (this.state == State.DOWN) {
            state = State.LEFT;
            list.remove(State.DOWN);
            list.add(State.LEFT);
        }
        else {
            state = State.UP;
            list.remove(State.LEFT);
            list.add(State.UP);

        }
    }

    @Override
    public void initiateState(State state) {
        list=new LinkedList<>();
        if(state==State.UP) {
            list.add(state);
        }else if(state==State.DOWN) {
            list.add(state);
        }else if(state==State.RIGHT) {
            list.add(state);
        }else if(state==State.LEFT) {
            list.add(state);
        }
    }

    @Override
    public boolean hasState(State state) {
        return list.contains(state);
    }

    public static Power createRandom(int x, int y) {
        return new Power(x, y, State.values()[Utils.randomNum(0, 3)]);
    }
}
