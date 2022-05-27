package com.admin.common.util;

import java.util.UUID;

/**
 * @author Songwe
 * @date 2022/5/21 1:01
 */
public class UUIDUtils {
    
    public static String get() {
        final UUID uuid = UUID.randomUUID();
        return Long.toHexString(uuid.getMostSignificantBits()) + Long.toHexString(uuid.getLeastSignificantBits());
    }
}
