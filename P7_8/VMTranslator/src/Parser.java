import java.io.*;

public class Parser {
    private BufferedReader reader;
    private String currentLine;

    public Parser(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
    }

    public boolean hasMoreCommands() throws IOException {
        while ((currentLine = reader.readLine()) != null) {
            currentLine = currentLine.split("//")[0].trim(); // strip comments
            if (!currentLine.isEmpty()) return true;
        }
        return false;
    }

    public void advance() {
        // currentLine is already updated in hasMoreCommands
    }

    public String currentCommand() {
        return currentLine;
    }

    public String commandType() {
        String[] tokens = currentLine.split(" ");
        switch (tokens[0]) {
            case "push":
                return "C_PUSH";
            case "pop":
                return "C_POP";
            case "add": case "sub": case "neg":
            case "eq": case "gt": case "lt":
            case "and": case "or": case "not":
                return "C_ARITHMETIC";
            default:
                return "C_UNKNOWN";
        }
    }

    public String arg1() {
        String[] tokens = currentLine.split(" ");
        if (tokens.length >= 2) return tokens[1];
        return "";
    }


    public int arg2() {
        String[] tokens = currentLine.split(" ");
        if (tokens.length >= 3) return Integer.parseInt(tokens[2]);
        return -1; // or throw an exception
    }


    public void close() throws IOException {
        reader.close();
    }
}
