package com.entry.entrydsm.user.domain;

public enum GraduateType {
    WILL,
    DONE,
    GED;

    public boolean isGed() {
        return this == GED;
    }
}
