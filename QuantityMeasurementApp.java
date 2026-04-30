import java.util.LinkedHashSet;

public class TrainConsistUC5 {

    public static void main(String[] args) {

        // LinkedHashSet maintains insertion order + no duplicates
        LinkedHashSet<String> trainFormation = new LinkedHashSet<>();

        // Adding bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        // Attempt duplicate
        trainFormation.add("Sleeper"); // will be ignored

        // Display formation
        System.out.println("Final Train Formation:");
        System.out.println(trainFormation);
    }
}