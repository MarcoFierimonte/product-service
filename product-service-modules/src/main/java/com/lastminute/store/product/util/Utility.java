package com.lastminute.store.product.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class Utility {

    private Utility() {
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
