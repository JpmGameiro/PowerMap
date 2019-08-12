package pt.isel.poo.powermap.model.entity;


import pt.isel.poo.powermap.Utils;
import pt.isel.poo.powermap.model.State;

import java.util.LinkedList;

public class Branch extends Cell {

    public State state;
    public boolean on=false;
    private LinkedList<State> list;


    public Branch(int x, int y, State state) {
        super(x, y);
        this.state = state;
        initiateState(state);
    }

    public void initiateState(State state) {
        list=new LinkedList<>();
        if(state==State.UP) {
            list.add(state);
            list.add(State.LEFT);
            list.add(State.RIGHT);
        }else if(state==State.DOWN) {
            list.add(state);
            list.add(State.LEFT);
            list.add(State.RIGHT);
        }else if(state==State.RIGHT) {
            list.add(state);
            list.add(State.UP);
            list.add(State.DOWN);

        }else if(state==State.LEFT) {
            list.add(state);
            list.add(State.UP);
            list.add(State.DOWN);
        }

    }

    @Override
    public boolean hasState(State state) {
        return list.contains(state);
    }

    public State getState() {
        return state;
    }

    @Override
    public void rotate() {
        if (this.state == State.UP){
            state = State.RIGHT;
            list.remove(State.LEFT);
            list.add(State.DOWN);
        }
        else if (this.state == State.RIGHT) {
            state = State.DOWN;
            list.remove(State.UP);
            list.add(State.LEFT);
        }
        else if (this.state == State.DOWN) {
            state = State.LEFT;
            list.remove(State.RIGHT);
            list.add(State.UP);
        }
        else {
            state = State.UP;
            list.remove(State.DOWN);
            list.add(State.RIGHT);
        }
    }


    public static Branch createRandom(int x, int y) {
        return new Branch(x, y, State.values()[Utils.randomNum(0, 3)]);
    }
}
