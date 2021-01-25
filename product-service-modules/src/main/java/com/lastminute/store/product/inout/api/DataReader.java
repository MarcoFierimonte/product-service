package com.lastminute.store.product.inout.api;

public interface DataReader<T> {

    T extract(String[] cells);
}
