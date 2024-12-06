import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Example input file content
        String[] fileLines = {
                "Ilene Dover SF",
                "Sum-Yung Gai BL",
                "Jacques Strap IW",
                "Wilma Fingaheel HN",
                "Seymour Butts AL",
                "Omai Chestowsh HA",
                "Alotta Payne KS",
                "Mike Rotchertz KS"
        };

        // Process the file
        List<String> reorderedPatients = reorderPatients(fileLines);

        // Output the reordered patients
        System.out.println("Order for Patient to be Seen (Ascending Order):");
        for (String patient : reorderedPatients) {
            System.out.println(patient);
        }

        // Optionally, write to a file
        writeToFile(reorderedPatients, "reordered_patients.txt");
    }

    public static List<String> reorderPatients(String[] fileLines) {
        // Parse the input and assign priorities
        List<String[]> parsedPatients = new ArrayList<>();
        for (String line : fileLines) {
            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid input format: " + line);
            }
            int priority = getPriority(parts[2]); // Get the priority based on the triage code
            parsedPatients.add(new String[]{line, String.valueOf(priority)});
        }

        //sort
        parsedPatients.sort(Comparator.comparingInt(p -> Integer.parseInt(p[1])));

        
        List<String> reorderedPatients = new ArrayList<>();
        for (String[] patient : parsedPatients) {
            reorderedPatients.add(patient[0]);
        }
        return reorderedPatients;
    }

    public static int getPriority(String triageCode) {
        switch (triageCode) {
            case "AL":
            case "HA":
            case "ST":
                return 1;
            case "BL":
            case "SF":
            case "IW":
            case "KS":
            case "OT":
                return 2;
            case "HN":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid triage code: " + triageCode);
        }
    }

    public static void writeToFile(List<String> lines, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
