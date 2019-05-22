package com.kata.mower.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grown {

    private final int maxX;
    private final int maxY;

    private List<Position> busyPositions;

    private Grown(int maxX, int maxY) {
        this.maxX= maxX;
        this.maxY = maxY;
        this.busyPositions = new ArrayList<Position>();
    }

    public static Grown of(int maxX, int maxY){
        return new Grown(maxX,maxY);
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void addBusyPosition(Position currentPosition) {
        busyPositions.add(currentPosition);
    }

    public boolean isAvailablePosition(Position position){
        return !busyPositions.contains(position);
    }

    public void releasePosition(Position position){
        busyPositions.remove(position);
    }


    private boolean isInBounds(Position position){
        return (position.getX() >=0 && position.getX()<=maxX && position.getY() >=0 && position.getY()<=maxY);
    }
    public boolean isOutOfBounds(Position position){
        return !isInBounds(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grown grown = (Grown) o;
        return maxX == grown.maxX &&
                maxY == grown.maxY &&
                Objects.equals(busyPositions, grown.busyPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxX, maxY, busyPositions);
    }
}
