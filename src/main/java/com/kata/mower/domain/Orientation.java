package com.kata.mower.domain;

public enum Orientation {
    EAST("E","N","S"),
    WEST("W","S","N"),
    NORTH("N","W","E"),
    SOUTH("S","E","W");

    private String value;
    private String leftOrientation;
    private String rightOrientation;

    Orientation(String value, String leftOrientation, String rightOrientation) {
        this.value = value;
        this.leftOrientation =leftOrientation;
        this.rightOrientation = rightOrientation;
    }

    public String getValue() {
        return this.value;
    }

    public Orientation turnLeft(){
        return fromString(leftOrientation);
    }

    public Orientation turnRight(){
        return fromString(rightOrientation);
    }


    public static Orientation fromString(String text) {
        for (Orientation b : Orientation.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
