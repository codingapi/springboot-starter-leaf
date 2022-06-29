package com.sankuai.inf.leaf.server.snowflake.exception;

public class ClockGoBackException extends RuntimeException {

    public ClockGoBackException(String message) {
        super(message);
    }
}
