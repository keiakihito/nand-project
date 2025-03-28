import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Main inputFile.vm");
            return;
        }

        String inputFile = args[0];
        String outputFile = inputFile.replace(".vm", ".asm");

        Parser parser = new Parser(inputFile);
        CodeWriter codeWriter = new CodeWriter(outputFile);
        codeWriter.writeComment("Translated from " + inputFile);

        while (parser.hasMoreCommands()) {
            parser.advance();
            String commandType = parser.commandType();

            codeWriter.writeComment(parser.currentCommand());

            switch (commandType) {
                case "C_PUSH":
                    if ("constant".equals(parser.arg1())) {
                        int val = parser.arg2();
                        codeWriter.writePushConstant(val);
                    }
                    break;

                case "C_ARITHMETIC":
                    codeWriter.writeArithmetic(parser.currentCommand());
                    break;

                case "C_LABEL":
                    codeWriter.writeLabel(parser.arg1());
                    break;

                case "C_GOTO":
                    codeWriter.writeGoto(parser.arg1());
                    break;

                case "C_IF":
                    codeWriter.writeIf(parser.arg1());
                    break;

                case "C_FUNCTION":
                    codeWriter.writeFunction(parser.arg1(), parser.arg2());
                    break;

                case "C_CALL":
                    codeWriter.writeCall(parser.arg1(), parser.arg2());
                    break;

                case "C_RETURN":
                    codeWriter.writeReturn();
                    break;

                default:
                    codeWriter.writeComment("Unsupported command");
            }

        }

        parser.close();
        codeWriter.close();
        System.out.println("Translated: " + outputFile);
    }
}
