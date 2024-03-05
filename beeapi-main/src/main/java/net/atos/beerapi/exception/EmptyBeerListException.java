package net.atos.beerapi.exception;

import jdk.jfr.StackTrace;

import java.io.PrintWriter;
import java.io.StringWriter;

public class EmptyBeerListException extends RuntimeException {

        public EmptyBeerListException(String message) {
            super(message);
        }
}


