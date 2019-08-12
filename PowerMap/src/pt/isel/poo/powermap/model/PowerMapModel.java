package pt.isel.poo.powermap.model;

import jdk.internal.util.xml.impl.Input;
import pt.isel.poo.powermap.model.entity.*;
import pt.isel.poo.powermap.view.PowerViewListener;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PowerMapModel {
    private int numLines;
    private int numCols;
    public int xPower;
    public int yPower;
    private PowerViewListener viewer;
    private Cell[][] board;
    private boolean[][] boardOn;
    public static int numHomesOn;
    private ArrayList<Home> homes = new ArrayList<>();


    public int getNumLines() {
        return numLines;
    }

    public int getNumColumns() {
        return numCols;
    }

    public void setChangeListener(PowerViewListener view) {
        viewer = view;
    }

    public boolean isCompleted() {
        return numHomesOn == homes.size();
    }

    public void connectMap(int lin, int col) {
        board[lin][col].rotate();
        boardOn = new boolean[getNumLines()][getNumColumns()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    board[i][j].on = false;
                    if (board[i][j] instanceof Home && numHomesOn > 0)
                        --numHomesOn;
                }
            }
        }
        checkNeighbours(getCell(xPower, yPower));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    viewer.repaint(i, j, board[i][j].on);
                    if (board[i][j] instanceof Home && board[i][j].on)
                        ++numHomesOn;
                }
            }
        }

    }

    private void setCell(Cell c, int x, int y) {
        if (viewer != null) {
            board[x][y] = c;
            viewer.repaint(x, y, getCell(x, y).on);
        } else board[x][y] = c;
        if (c instanceof Home)
            homes.add((Home) c);
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public void checkNeighbours(Cell c) {
        c.on = true;
        boardOn[c.getX()][c.getY()] = true;
        Cell cell;

        if (c.hasState(State.UP)) {
            if (c.getX() - 1 >= 0) {
                cell = board[c.getX() - 1][c.getY()];
                if (cell != null && cell.hasState(State.DOWN))
                    verifyOn(cell);
            }
        }
        if (c.hasState(State.DOWN)) {
            if (c.getX() + 1 < board.length) {
                cell = board[c.getX() + 1][c.getY()];
                if (cell != null && cell.hasState(State.UP))
                    verifyOn(cell);
            }
        }
        if (c.hasState(State.LEFT)) {
            if (c.getY() - 1 >= 0) {
                cell = board[c.getX()][c.getY() - 1];
                if (cell != null && cell.hasState(State.RIGHT))
                    verifyOn(cell);
            }
        }
        if (c.hasState(State.RIGHT)) {
            if (c.getY() + 1 < board.length) {
                cell = board[c.getX()][c.getY() + 1];
                if (cell != null && cell.hasState(State.LEFT)) {
                    verifyOn(cell);
                }
            }
        }
        return;
    }

    private void verifyOn(Cell cell) {
        if (!boardOn[cell.getX()][cell.getY()]) {
            boardOn[cell.getX()][cell.getY()] = true;
            checkNeighbours(cell);
        }
    }


    public void loadLevel(FileReader file) throws IOException {
        BufferedReader in = new BufferedReader(file);
        Scanner scan = new Scanner(in.readLine());
        numLines = scan.nextInt();
        scan.next();
        numCols = scan.nextInt();
        board = new Cell[getNumLines()][getNumColumns()];
        boardOn = new boolean[getNumLines()][getNumColumns()];

        char c;
        String s;
        for (int i = 0; i < numLines; i++) {
            s = in.readLine();
            for (int j = 0; j < numCols; j++) {
                c = s.charAt(j);
                switch (c) {
                    case 'P':
                        setCell(Power.createRandom(i, j), i, j);
                        xPower = i;
                        yPower = j;
                        break;
                    case 'H':
                        setCell(Home.createRandom(i, j), i, j);
                        break;
                    case '.':
                        setCell(Line.createRandom(i, j), i, j);
                        break;
                    case 'c':
                        setCell(Curve.createRandom(i, j), i, j);
                        break;
                    case 'T':
                        setCell(Branch.createRandom(i, j), i, j);
                        break;
                }
            }
        }
    }
}