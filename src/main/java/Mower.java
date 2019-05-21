public class Mower {
    private Position currentPosition;

    private Mower(Position initialPosition) {
        this.currentPosition = initialPosition;
    }

    public static Mower of(Position initialPosition){
        return new Mower(initialPosition);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
}
