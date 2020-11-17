package ru.relex.miniBooking.rest.exception;

public class NoSuchObjectException extends RuntimeException {
    public NoSuchObjectException ( Class object ) {
        super ( "Object " + object.getSimpleName () + " not found", null, false, false );
    }
}
