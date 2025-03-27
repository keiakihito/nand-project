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
                writer.write("@SP\nM=M-1\nA=M\nM=M+D\n");
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
}
