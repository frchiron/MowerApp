import java.util.Arrays;
import java.util.Objects;

public class Mower {
    private Grown grown;
    private Orientation currentOrientation;
    private Position currentPosition;

    private Mower(Position initialPosition, Orientation initialOrientation, Grown grown) {
        this.currentPosition = initialPosition;
        this.currentOrientation = initialOrientation;
        this.grown = grown;
    }

    public static Mower of(Position initialPosition, String initialOrientation) {
        return of(initialPosition, initialOrientation, Grown.of(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public static Mower of(Position initialPosition, String initialOrientation, Grown grown) {
        if (grown.isOutOfBounds(initialPosition)) {
            throw new IllegalArgumentException("mower must start inside the grown");
        }
        grown.addBusyPosition(initialPosition);
        return new Mower(initialPosition, Orientation.fromString(initialOrientation), grown);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public void executeSingleInstruction(String instruction) {
        switch ((instruction)) {
            case "L":
                currentOrientation = currentOrientation.turnLeft();
                break;
            case "R":
                currentOrientation = currentOrientation.turnRight();
                break;
            case "F":
                tryToMoveForward();
                break;
            default:
                throw new IllegalArgumentException("unknown instruction");
        }
    }

    private void tryToMoveForward() {
        Position potentialNewPosition = computePotentialNewPosition();
        if (grown.isAvailablePosition(potentialNewPosition) && !potentialNewPosition.equals(currentPosition)) {
            grown.addBusyPosition(potentialNewPosition);
            grown.releasePosition(currentPosition);
            currentPosition = potentialNewPosition;
        }
    }

    private Position computePotentialNewPosition() {
        Position forwardPosition = computeForwardPosition();
        return grown.isOutOfBounds(forwardPosition) ? currentPosition : forwardPosition;
    }

    private Position computeForwardPosition() {
        switch (currentOrientation) {
            case NORTH:
                return Position.of(currentPosition.getX(), currentPosition.getY() + 1);
            case EAST:
                return Position.of(currentPosition.getX() + 1, currentPosition.getY());
            case WEST:
                return Position.of(currentPosition.getX()-1, currentPosition.getY());
            case SOUTH:
                return Position.of(currentPosition.getX(), currentPosition.getY() - 1);
            default:
                throw new IllegalArgumentException("unknown orientation");
        }
    }

    public void executeInstructions(String instructions) {
        Arrays.stream(instructions.split("")).forEach(instruction -> executeSingleInstruction(instruction));
    }

    public String display() {
        return currentPosition.getX() + " " + currentPosition.getY() + " " + currentOrientation.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mower mower = (Mower) o;
        return grown.equals(mower.grown) &&
                currentOrientation == mower.currentOrientation &&
                currentPosition.equals(mower.currentPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grown, currentOrientation, currentPosition);
    }
}
