import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles writing the output binary file
 */
public class Writer {
    /**
     * Writes a list of binary instructions to a file
     */
    public static void writeToFile(List<String> binaryInstructions, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String instruction : binaryInstructions) {
                bw.write(instruction);
                bw.newLine();
            }
        }
    }
}