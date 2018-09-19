package com.entry.entrydsm.info.domain;

public enum Admission {
    NORMAL,
    MEISTER,
    SOCIAL;

    public boolean isSocial() {
        return this == SOCIAL;
    }
}
