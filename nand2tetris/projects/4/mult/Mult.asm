//@2: Sets the A-register to address 2 (RAM[2]), which is used as R2 (the result storage).
//@1: Sets the A-register to address 1 (RAM[1]), which stores R1.
//@0: Sets the A-register to address 0 (RAM[0]), which stores R0.
//@i: This is an undefined variable, so the assembler assigns it to an available RAM address (starting from RAM[16]).
//@LOOP: sets the A-register to the memory address of the LOOP label, allowing jumps.
//@END sets the A-register to the memory address of the END label, allowing conditional or unconditional jumps.

    @2
    M=0     // R2 = 0 (Initialize the result to 0)
    @i
    M=0     // i=0 (Initialize the loop counter)

// This loop keeps adding R1 to R2, R0 times.
(LOOP)
    //Checking if i >= R0 (Exit Condition)
    @i
    D=M     // D=i
    @0
    D=D-M   // D=i-R0
    @END
    D;JGE    // if i-R0 >= 0 goto END, JGE: "Jump if Greater than or Equal to 0".

    //Performing Addition (R2 += R1)
    @1
    D=M     // D=R1
    @2
    M=D+M   // R2=R2+R1 (Add R1 to the result)
    @i
    M=M+1   // i=i+1(Increment loop counter)
    @LOOP
    0;JMP   // Repeat

//Stays in the infinite loop to finish the program.
(END)
    @END
    0;JMP


