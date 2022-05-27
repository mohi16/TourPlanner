package org.easytours.tourplanner.utils;

public class Wrapper<T> {
    T value;

    public Wrapper() {
        value = null;
    }

    public Wrapper(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
