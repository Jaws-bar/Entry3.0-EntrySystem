package com.entry.entrydsm.grade.domain.graduate;

public enum Grade {
    A(5),
    B(4),
    C(3),
    D(2),
    E(1),
    X(-1);

    private final int value;

    Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isPassed() {
        return this != X;
    }
}
