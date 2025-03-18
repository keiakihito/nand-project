import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses Hack assembly language files and provides clean instructions
 */
public class Parser {
    /**
     * Reads an assembly file and returns a list of cleaned instructions
     * (comments removed, whitespace trimmed, empty lines filtered)
     */
    public static List<String> readAndCleanInstructions(String filePath) throws IOException {
        List<String> cleanedInstructions = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove comments and whitespace
                int commentIndex = line.indexOf("//");
                if (commentIndex != -1) {
                    line = line.substring(0, commentIndex);
                }
                line = line.trim();
                
                if (!line.isEmpty()) {
                    cleanedInstructions.add(line);
                }
            }
        }
        
        return cleanedInstructions;
    }
    
    /**
     * Takes a list of cleaned instructions and returns only non-label instructions
     */
    public static List<String> filterLabels(List<String> cleanedInstructions) {
        List<String> filteredInstructions = new ArrayList<>();
        
        for (String instruction : cleanedInstructions) {
            if (!(instruction.startsWith("(") && instruction.endsWith(")"))) {
                filteredInstructions.add(instruction);
            }
        }
        
        return filteredInstructions;
    }
    
    /**
     * Determines if the instruction is an A-instruction
     */
    public static boolean isAInstruction(String instruction) {
        return instruction.startsWith("@");
    }
    
    /**
     * Extracts the value part from an A-instruction
     */
    public static String extractAValue(String instruction) {
        return instruction.substring(1);
    }
    
    /**
     * Extracts the dest part from a C-instruction (or null if not present)
     */
    public static String extractDest(String instruction) {
        if (instruction.contains("=")) {
            return instruction.split("=")[0];
        }
        return null;
    }
    
    /**
     * Extracts the comp part from a C-instruction
     */
    public static String extractComp(String instruction) {
        String comp;
        
        if (instruction.contains("=") && instruction.contains(";")) {
            // dest=comp;jump format
            comp = instruction.split("=")[1].split(";")[0];
        } else if (instruction.contains("=")) {
            // dest=comp format
            comp = instruction.split("=")[1];
        } else if (instruction.contains(";")) {
            // comp;jump format
            comp = instruction.split(";")[0];
        } else {
            // Just comp (rare, but possible)
            comp = instruction;
        }
        
        return comp;
    }
    
    /**
     * Extracts the jump part from a C-instruction (or null if not present)
     */
    public static String extractJump(String instruction) {
        if (instruction.contains(";")) {
            return instruction.split(";")[1];
        }
        return null;
    }
    
    /**
     * Extracts a label name from a label definition
     */
    public static String extractLabelName(String labelInstruction) {
        return labelInstruction.substring(1, labelInstruction.length() - 1);
    }
    
    /**
     * Checks if an instruction is a label definition
     */
    public static boolean isLabel(String instruction) {
        return instruction.startsWith("(") && instruction.endsWith(")");
    }
}