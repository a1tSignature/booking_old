package ru.relex.miniBooking.commons.model;

public enum Role {
    TENANT ( 1 ), LANDLORD ( 2 );
    private final int id;

    Role ( int id ) {
        this.id = id;
    }

    public int getId ( ) {
        return id;
    }

    public static Role fromId ( Integer id ) {
        return Role.values ( )[ id-1 ];
    }
}

