package com.veronika.mars_rover.exceptions;

public class InvalidInstructionException extends RuntimeException {

    public InvalidInstructionException() {
        super("Robot control instruction is not provided or instruction is not valid. Possible letters are L, R and M");
    }
}
