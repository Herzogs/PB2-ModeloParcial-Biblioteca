package com.parcial.biblioteca;

public class NoMoreCopyException extends Exception{

    private String msg;

    public NoMoreCopyException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
