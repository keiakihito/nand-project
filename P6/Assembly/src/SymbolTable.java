import java.util.HashMap;
import java.util.Map;

/**
 * Keeps a correspondence between symbolic labels and numeric addresses
 */
public class SymbolTable {
    private final Map<String, Integer> symbolTable;
    private int nextVariableAddress;
    
    /**
     * Creates a new empty symbol table with predefined symbols
     */
    public SymbolTable() {
        symbolTable = new HashMap<>();
        nextVariableAddress = 16; // Variables start at RAM address 16
        
        // Add predefined symbols
        addEntry("SP", 0);
        addEntry("LCL", 1);
        addEntry("ARG", 2);
        addEntry("THIS", 3);
        addEntry("THAT", 4);
        addEntry("R0", 0);
        addEntry("R1", 1);
        addEntry("R2", 2);
        addEntry("R3", 3);
        addEntry("R4", 4);
        addEntry("R5", 5);
        addEntry("R6", 6);
        addEntry("R7", 7);
        addEntry("R8", 8);
        addEntry("R9", 9);
        addEntry("R10", 10);
        addEntry("R11", 11);
        addEntry("R12", 12);
        addEntry("R13", 13);
        addEntry("R14", 14);
        addEntry("R15", 15);
        addEntry("SCREEN", 16384);
        addEntry("KBD", 24576);
    }
    
    /**
     * Adds a new entry to the symbol table
     */
    public void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }
    
    /**
     * Checks if the symbol table contains the given symbol
     */
    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }
    
    /**
     * Returns the address associated with the symbol
     */
    public int getAddress(String symbol) {
        return symbolTable.get(symbol);
    }
    
    /**
     * Allocates a new address for a variable and returns it
     */
    public int addVariable(String symbol) {
        int address = nextVariableAddress++;
        addEntry(symbol, address);
        return address;
    }
}