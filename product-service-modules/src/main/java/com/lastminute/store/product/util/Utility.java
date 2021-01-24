package com.lastminute.store.product.util;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Utility {

    private Utility() {
    }

    public static List<String> readFile(File file) {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            // nothing to do
        }
        return list;
    }

    /**
     * Generate short UUID (13 characters)
     *
     * @return short UUID
     */
    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

}
