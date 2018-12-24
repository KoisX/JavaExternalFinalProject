package com.javacourse.shared;

public enum CRUDCommandEnum {
    CREATE("create"),
    READ("read"),
    UPDATE("edit"),
    DELETE("delete");
    String command;

    CRUDCommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
