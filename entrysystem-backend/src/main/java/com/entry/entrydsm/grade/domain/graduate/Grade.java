package com.entry.entrydsm.grade.domain.graduate;

public enum Grade {
    A,
    B,
    C,
    D,
    E,
    X;

    public boolean isSkiped() {
        return this != X;
    }
}
