package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class TraversalLogger {
    private ArrayList<ArrayList<Character>> path = new ArrayList<ArrayList<Character>>();
    private Move lastMove = Move.NONE;

    public void forward() {
        if (lastMove == Move.FORWARD) {
            path.getLast().add('F');
        } else {
            ArrayList<Character> tempList = new ArrayList<Character>();
            tempList.add('F');
            path.add(tempList);
            lastMove = Move.FORWARD;
        }
    }

    public void right() {
        if (lastMove == Move.RIGHT) {
            path.getLast().add('R');
        } else {
            ArrayList<Character> tempList = new ArrayList<Character>();
            tempList.add('R');
            path.add(tempList);
            lastMove = Move.RIGHT;
        }
    }

    public void left() {
        if (lastMove == Move.LEFT) {
            path.getLast().add('L');
        } else {
            ArrayList<Character> tempList = new ArrayList<Character>();
            tempList.add('L');
            path.add(tempList);
            lastMove = Move.LEFT;
        }
    }

    public String getCanonical() {
        String canonical = "";
        for (ArrayList<Character> chain : path) {
            for (Character move : chain) {
                canonical = canonical + move;
            }
            canonical = canonical + ' ';
        }
        return canonical;
    }
}

enum Move {
    NONE, FORWARD, LEFT, RIGHT
}