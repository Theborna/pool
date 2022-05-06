package com.project.models;

public class Vector<E extends Number> {
    private E v1, v2;

    public Vector(E v1, E v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public E getV1() {
        return v1;
    }

    public E getV2() {
        return v2;
    }

    public static <T extends Number> T add(T x, T y) {
        if (x == null || y == null) {
            return null;
        }
        if (x instanceof Double) {
            return (T) new Double(x.doubleValue() + y.doubleValue());
        } else if (x instanceof Integer) {
            return (T) new Integer(x.intValue() + y.intValue());
        } else {
            throw new IllegalArgumentException("Type " + x.getClass() + " is not supported by this method");
        }
    }

    public void add(Vector<E> v) {
        add(v1, v.getV1());
        add(v2, v.getV2());
    }
}
