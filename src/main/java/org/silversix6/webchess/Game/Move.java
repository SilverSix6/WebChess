package org.silversix6.webchess.Game;

import org.silversix6.webchess.User;

import java.util.List;

public class Move {

    int prevX, prevY;
    int newX, newY;

    public Move(int prevX, int prevY, int newX, int newY) {
        this.prevX = prevX;
        this.prevY = prevY;
        this.newX = newX;
        this.newY = newY;
    }

    public Move(List<String> list) {
        this.prevX = Integer.parseInt(list.get(0));
        this.prevY = Integer.parseInt(list.get(1));
        this.newX = Integer.parseInt(list.get(2));
        this.newY = Integer.parseInt(list.get(3));
    }

    public boolean valid(Game game, User user) {
        return true; // todo
    }

    public void addToList(List<String> list) {
        list.add(String.valueOf(prevX));
        list.add(String.valueOf(prevY));
        list.add(String.valueOf(newX));
        list.add(String.valueOf(newY));
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public int getNewX() {
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }
}
