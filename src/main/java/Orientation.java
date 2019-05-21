public enum Orientation {
    EAST("E"),
    WEST("W"),
    NORTH("N"),
    SOUTH("S");

    private String value;

    Orientation(String abbreviation) {
        this.value = abbreviation;
    }

    public String getValue() {
        return this.value;
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
