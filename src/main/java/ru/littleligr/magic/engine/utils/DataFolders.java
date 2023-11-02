package ru.littleligr.magic.engine.utils;

public enum DataFolders {

    SPELLS("spells"),
    FORMS("forms");

    public final String path;

    DataFolders(String path) {
        this.path= path;
    }
}
