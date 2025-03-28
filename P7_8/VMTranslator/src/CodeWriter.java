import java.io.*;

public class CodeWriter {
    private BufferedWriter writer;

    public CodeWriter(String outputPath) throws IOException {
        writer = new BufferedWriter(new FileWriter(outputPath));
    }

    public void writeComment(String comment) throws IOException {
        writer.write("// " + comment + "\n");
    }

    public void writePushConstant(int value) throws IOException {
        writer.write("@" + value + "\n");
        writer.write("D=A\n");
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("M=D\n");
        writer.write("@SP\n");
        writer.write("M=M+1\n");
    }

    public void writeArithmetic(String command) throws IOException {
        switch (command) {
            case "add":
                writer.write("@SP\nM=M-1\nA=M\nD=M\n");
                writer.write("@SP\nM=M-1\nA=M\nM=D+M\n");
                writer.write("@SP\nM=M+1\n");
                break;
            // other arithmetic commands (sub, neg, etc.) can be added here
            default:
                writeComment("Unimplemented arithmetic: " + command);
        }
    }

    public void close() throws IOException {
        writer.close();
    }

    public void writeLabel(String label) throws IOException {
        writer.write("(" + label + ")\n");
    }

    public void writeGoto(String label) throws IOException {
        writer.write("@" + label + "\n");
        writer.write("0;JMP\n");
    }

    public void writeIf(String label) throws IOException {
        writer.write("@SP\n");
        writer.write("M=M-1\n");
        writer.write("A=M\n");
        writer.write("D=M\n");
        writer.write("@" + label + "\n");
        writer.write("D;JNE\n");
    }

    public void writeFunction(String functionName, int numLocals) throws IOException {
        writer.write("(" + functionName + ")\n");
        for (int i = 0; i < numLocals; i++) {
            writePushConstant(0); // initialize local variables to 0
        }
    }

    private int callCounter = 0;

    public void writeCall(String functionName, int numArgs) throws IOException {
        String returnLabel = "RETURN_LABEL" + (callCounter++);

        // Push return address
        writer.write("@" + returnLabel + "\n");
        writer.write("D=A\n");
        writer.write("@SP\nA=M\nM=D\n@SP\nM=M+1\n");

        // Push LCL, ARG, THIS, THAT
        for (String segment : new String[]{"LCL", "ARG", "THIS", "THAT"}) {
            writer.write("@" + segment + "\n");
            writer.write("D=M\n");
            writer.write("@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        }

        // Reposition ARG = SP - 5 - numArgs
        writer.write("@SP\nD=M\n");
        writer.write("@" + (5 + numArgs) + "\n");
        writer.write("D=D-A\n");
        writer.write("@ARG\nM=D\n");

        // Reposition LCL = SP
        writer.write("@SP\nD=M\n@LCL\nM=D\n");

        // Goto function
        writer.write("@" + functionName + "\n0;JMP\n");

        // Return address label
        writer.write("(" + returnLabel + ")\n");
    }

    public void writeReturn() throws IOException {
        // FRAME = LCL → R13
        writer.write("@LCL\nD=M\n@R13\nM=D\n");

        // RET = *(FRAME - 5) → R14
        writer.write("@5\nA=D-A\nD=M\n@R14\nM=D\n");

        // *ARG = pop()
        writer.write("@SP\nM=M-1\nA=M\nD=M\n@ARG\nA=M\nM=D\n");

        // SP = ARG + 1
        writer.write("@ARG\nD=M+1\n@SP\nM=D\n");

        // THAT = *(FRAME - 1)
        writer.write("@R13\nAM=M-1\nD=M\n@THAT\nM=D\n");

        // THIS = *(FRAME - 2)
        writer.write("@R13\nAM=M-1\nD=M\n@THIS\nM=D\n");

        // ARG = *(FRAME - 3)
        writer.write("@R13\nAM=M-1\nD=M\n@ARG\nM=D\n");

        // LCL = *(FRAME - 4)
        writer.write("@R13\nAM=M-1\nD=M\n@LCL\nM=D\n");

        // goto RET
        writer.write("@R14\nA=M\n0;JMP\n");
    }

}





} // end of CodeWriter
