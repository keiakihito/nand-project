import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main driver class for the Hack Assembler
 */
public class HackAssembler {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java HackAssembler <input_file.asm>");
            return;
        }
        
        String inputFile = args[0];
        String outputFile = inputFile.replace(".asm", ".hack");
        
        try {
            // Create symbol table
            SymbolTable symbolTable = new SymbolTable();
            
            // First pass: build symbol table with label definitions
            firstPass(inputFile, symbolTable);
            
            // Second pass: translate assembly code to binary
            secondPass(inputFile, outputFile, symbolTable);
            
            System.out.println("Assembly completed successfully!");
            System.out.println("Output file: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * First pass: scan the assembly file and build the symbol table with label definitions
     */
    private static void firstPass(String inputFile, SymbolTable symbolTable) throws IOException {
        List<String> instructions = Parser.readAndCleanInstructions(inputFile);
        
        int addressCounter = 0;
        for (String instruction : instructions) {
            if (Parser.isLabel(instruction)) {
                String label = Parser.extractLabelName(instruction);
                symbolTable.addEntry(label, addressCounter);
            } else {
                // Only non-label instructions increment the address counter
                addressCounter++;
            }
        }
    }
    
    /**
     * Second pass: translate the assembly instructions to binary
     */
    private static void secondPass(String inputFile, String outputFile, SymbolTable symbolTable) throws IOException {
        List<String> allInstructions = Parser.readAndCleanInstructions(inputFile);
        List<String> instructions = Parser.filterLabels(allInstructions);
        
        List<String> binaryInstructions = new ArrayList<>();
        
        for (String instruction : instructions) {
            if (Parser.isAInstruction(instruction)) {
                binaryInstructions.add(translateAInstruction(instruction, symbolTable));
            } else {
                binaryInstructions.add(translateCInstruction(instruction));
            }
        }
        
        Writer.writeToFile(binaryInstructions, outputFile);
    }
    
    /**
     * Translates an A-instruction to binary
     */
    private static String translateAInstruction(String instruction, SymbolTable symbolTable) {
        String value = Parser.extractAValue(instruction);
        int address;
        
        // If the value is a number, use it directly
        if (value.matches("\\d+")) {
            address = Integer.parseInt(value);
        } else {
            // It's a symbol, look it up or create it
            if (symbolTable.contains(value)) {
                address = symbolTable.getAddress(value);
            } else {
                address = symbolTable.addVariable(value);
            }
        }
        
        return Code.decimalToBinary(address);
    }
    
    /**
     * Translates a C-instruction to binary
     */
    private static String translateCInstruction(String instruction) {
        String dest = Parser.extractDest(instruction);
        String comp = Parser.extractComp(instruction);
        String jump = Parser.extractJump(instruction);
        
        String destBits = Code.dest(dest);
        String compBits = Code.comp(comp);
        String jumpBits = Code.jump(jump);
        
        return "111" + compBits + destBits + jumpBits;
    }
}