package com.kuyajon.learningportal.model.client;

public enum EProgressStatus {
    NOT_STARTED(false),
    STARTED(false),
    FAILED(true),
    COMPLETED(true),
    ABANDONED(true);

    private final boolean isFinal;

    EProgressStatus(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
