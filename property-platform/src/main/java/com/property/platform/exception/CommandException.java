package com.property.platform.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandException extends RuntimeException {
    public CommandException(String msg) {
        log.error("CommandException: " + msg);
//        super(msg);
    }
}