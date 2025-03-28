// Translated from BasicLoop.vm
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 0
// Unsupported command
// label LOOP
(LOOP)
// push argument 0
// push local 0
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
// pop local 0
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
// push argument 0
// if-goto LOOP
@SP
M=M-1
A=M
D=M
@LOOP
D;JNE
// push local 0
