package com.lastminute.store.product.util;

import com.lastminute.store.product.exception.ReadFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class Utility {

    private Utility() {
    }

    public static List<String> readFile(File file) {
        List<String> list;
        try {
            list = new ArrayList<>(Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            throw new ReadFileException("Error during loading file=[" + file + "]", e);
        }
        return list;
    }

}
