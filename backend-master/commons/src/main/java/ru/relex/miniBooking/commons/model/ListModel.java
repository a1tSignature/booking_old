package ru.relex.miniBooking.commons.model;

import java.util.ArrayList;
import java.util.List;

public class ListModel<T> {
    public List<T> list;
    private long amount;

    public ListModel ( ) {
        this.list = new ArrayList<> ( );
        this.amount = 0;
    }

    public ListModel ( List<T> list, long amount ) {
        this.list = list;
        this.amount = amount;
    }

    public List<T> getList ( ) {
        return list;
    }

    public void setList ( List<T> list ) {
        this.list = list;
    }

    public long getAmount ( ) {
        return amount;
    }

    public void setAmount ( long amount ) {
        this.amount = amount;
    }
}
