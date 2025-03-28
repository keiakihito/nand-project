// Translated from FibonacciSeries.vm
// push argument 1
// pop pointer 1
// Unsupported command
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 0
// Unsupported command
// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 1
// Unsupported command
// push argument 0
// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
// sub
// Unimplemented arithmetic: sub
// pop argument 0
// Unsupported command
// label LOOP
(LOOP)
// push argument 0
// if-goto COMPUTE_ELEMENT
@SP
M=M-1
A=M
D=M
@COMPUTE_ELEMENT
D;JNE
// goto END
@END
0;JMP
// label COMPUTE_ELEMENT
(COMPUTE_ELEMENT)
// push that 0
// push that 1
// add
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M+D
@SP
M=M+1
// pop that 2
// Unsupported command
// push pointer 1
// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M+D
@SP
M=M+1
// pop pointer 1
// Unsupported command
// push argument 0
// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// sub
// Unimplemented arithmetic: sub
// pop argument 0
// Unsupported command
// goto LOOP
@LOOP
0;JMP
// label END
(END)
