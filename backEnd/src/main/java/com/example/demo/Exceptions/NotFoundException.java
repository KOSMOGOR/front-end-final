package com.example.demo.Exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends Exception {
    String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
