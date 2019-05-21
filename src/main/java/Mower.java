import java.util.Arrays;

public class Mower {
    private Grown grown;
    private Orientation currentOrientation;
    private Position currentPosition;

    private Mower(Position initialPosition, Orientation initialOrientation, Grown grown) {
        this.currentPosition = initialPosition;
        this.currentOrientation = initialOrientation;
        this.grown = grown;
    }

    public static Mower of(Position initialPosition, String initialOrientation){
        return new Mower(initialPosition,Orientation.fromString(initialOrientation), new Grown(Integer.MAX_VALUE,Integer.MAX_VALUE));
    }

    public static Mower of(Position initialPosition, String initialOrientation, Grown grown) {
        return new Mower(initialPosition,Orientation.fromString(initialOrientation),grown);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public void executeSingleInstruction(String instruction) {
        if ("L".equals(instruction)) {
            currentOrientation = turnLeft();
        }
        else if ("R".equals(instruction)){
            currentOrientation = turnRight();
        }
        else if ("F".equals(instruction)){
            currentPosition = moveForward();
        }
        else {
            throw new IllegalArgumentException("unknown instruction");
        }
    }

    private Position moveForward(){
        switch(currentOrientation){
            case NORTH: return Position.of(currentPosition.getX(),Math.min(currentPosition.getY()+1,grown.getMaxY()));
            case EAST: return Position.of(Math.min(currentPosition.getX()+1,grown.getMaxX()),currentPosition.getY());
            case WEST: return Position.of(Math.max(currentPosition.getX()-1,0),currentPosition.getY());
            case SOUTH: return Position.of(currentPosition.getX(),Math.max(currentPosition.getY()-1,0));
            default:throw new IllegalArgumentException("unknown orientation");
        }
    }


    // TODO A ameliorer (a mettre dans l'enum)
    private Orientation turnLeft() {
        switch (currentOrientation){
            case NORTH: return Orientation.WEST;
            case WEST : return Orientation.SOUTH;
            case SOUTH: return Orientation.EAST;
            case EAST : return Orientation.NORTH;
            default: throw new IllegalArgumentException("unknown orientation");
        }
    }
    private Orientation turnRight() {
        switch (currentOrientation){
            case NORTH: return Orientation.EAST;
            case WEST : return Orientation.NORTH;
            case SOUTH: return Orientation.WEST;
            case EAST : return Orientation.SOUTH;
            default: throw new IllegalArgumentException("unknown orientation");
        }
    }

    public void executeInstructions(String instructions) {
        Arrays.stream(instructions.split("")).forEach(instruction -> executeSingleInstruction(instruction));
    }

    public String display() {
        return currentPosition.getX() + " " +currentPosition.getY()+ " " + currentOrientation.getValue();
    }
}
