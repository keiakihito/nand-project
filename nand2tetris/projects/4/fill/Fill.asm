// initializes cur_screen_word to point to SCREEN which the first address of the screen memory.
(RESET) 
	@SCREEN
	D=A
	@cur_screen_word 
	M=D

(LOOP)	
	// if a key is pressed (KBD > 0), it jumps to FILL, filling the screen.
    @KBD
	D=M
	@FILL 
	D; JGT
	
    // Otherwise, it jumps to BLANK, clearing the screen.
	@BLANK 
	0; JMP

// Fill the Screen
(FILL)
	@cur_screen_word
	A=M // Loads cur_screen_word (current screen address).
	M=-1 // Sets the current screen word to -1 (blackens the screen).
	
    // Jumps to CHECK to move to the next screen location.
	@CHECK
	0; JMP

// Clearing the Screen	
(BLANK)
	@cur_screen_word
	A=M // Loads cur_screen_word (current screen address).
	M=0 // Sets the current screen word to 0 (clears the screen).
	
    // Jumps to CHECK to move to the next screen location.
	@CHECK
	0; JMP

// Checking If the Whole Screen is Processed 
(CHECK) 
	@cur_screen_word
	MD=M+1 //Moves to the next screen word
	@KBD
	D=D-A //Compares it with KBD (keyboard address).
	
    // If it reaches the end of the screen, it jumps to RESET to start over.
	@RESET 
	D; JEQ
	
    //Otherwise, it jumps back to LOOP to continue processing.
	@LOOP  
	0; JMP
	