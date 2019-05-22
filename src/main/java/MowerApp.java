import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MowerApp {

    public void runFrom(String inputFile) throws IOException {

        Map<Mower, String> mowersAndInstructions = extractMowersAndInstructionsFrom(inputFile);

        mowersAndInstructions.forEach((mower,instructions) -> mower.executeInstructions(instructions));
        mowersAndInstructions.forEach((mower,instructions) -> System.out.println(mower.display()));
    }

    private Map<Mower, String> extractMowersAndInstructionsFrom(String inputFile) throws IOException {
        List<String> lines = getInputLines(inputFile);

        Grown grown = createGrownFrom(lines.get(0));
        Map<Mower,String> mowersAndInstructions = new HashMap<Mower,String>();
        for (int index = 1;index<lines.size()-1;index=index+2){
            Mower mower = createMowerWithGrownFrom(lines.get(index), grown);
            String instructions = lines.get(index + 1);
            mowersAndInstructions.put(mower,instructions);

        }
        return mowersAndInstructions;
    }

    private Mower createMowerWithGrownFrom(String line, Grown grown) {
        String[] mowerInput = line.split(" ");
        return Mower.of(Position.of(Integer.valueOf(mowerInput[0]), Integer.valueOf(mowerInput[1])), mowerInput[2],grown);
    }

    private Grown createGrownFrom(String line) {
        String[] grownInput = line.split(" ");
        return Grown.of(Integer.valueOf(grownInput[0]), Integer.valueOf(grownInput[1]));
    }

    private List<String> getInputLines(String inputFile) throws IOException {
        File file = new File(inputFile);
        return Files.lines(file.toPath()).collect(Collectors.toList());
    }

    public static void main(String args[]) throws IOException {
        if (args.length==0){
            System.out.println("Please provide an input file, eg src/test/resources/sample.txt");
            System.exit(-1);
        };

        MowerApp mowerApp = new MowerApp();
        mowerApp.runFrom(args[0]);
    }

}
