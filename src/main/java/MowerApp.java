import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MowerApp {

    public void runFrom(String inputFile) throws IOException {

        File file = new File(inputFile);
        List<String> lines = Files.lines(file.toPath()).collect(Collectors.toList());

        String[] grownInput = lines.get(0).split(" ");
        Grown grown = new Grown(Integer.valueOf(grownInput[0]), Integer.valueOf(grownInput[1]));

        List<Mower> mowers = new ArrayList<Mower>();
        List<String> instructionsPerMower = new ArrayList<String>();
        for (int index = 1;index<lines.size()-1;index=index+2){

            String[] mowerInput = lines.get(index).split(" ");
            Mower mower = Mower.of(Position.of(Integer.valueOf(mowerInput[0]), Integer.valueOf(mowerInput[1])), mowerInput[2],grown);
            mowers.add(mower);
            String instructions = lines.get(index + 1);
            instructionsPerMower.add(instructions);
        }

        for (int index=0;index<mowers.size();index++){
            Mower mower = mowers.get(index);
            mower.executeInstructions(instructionsPerMower.get(index));
            System.out.println(mower.display());
        }

    }

    public static void main(String args[]) throws IOException {
        MowerApp mowerApp = new MowerApp();
        mowerApp.runFrom("src/test/resources/sample.txt");

    }

}
