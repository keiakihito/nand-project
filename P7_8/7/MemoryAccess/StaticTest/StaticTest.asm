// Translated from StaticTest.vm
// push constant 111
@111
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 333
@333
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 888
@888
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop static 8
// Unsupported command
// pop static 3
// Unsupported command
// pop static 1
// Unsupported command
// push static 3
// push static 1
// sub
// Unimplemented arithmetic: sub
// push static 8
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
