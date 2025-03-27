// Translated from PointerTest.vm
// push constant 3030
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 0
// Unsupported command
// push constant 3040
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1
// Unsupported command
// push constant 32
@32
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop this 2
// Unsupported command
// push constant 46
@46
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 6
// Unsupported command
// push pointer 0
// push pointer 1
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
// push this 2
// sub
// Unimplemented arithmetic: sub
// push that 6
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
