package com.vasflam.lab03.common;

import java.util.Optional;
import com.vasflam.lab03.group.Group;

public class Utils {
    public static void throwIfEmpty(Optional<?> g, String msg) throws Exception {
        if (g.isEmpty()) {
            throw new Exception(msg);
        }
    }
}
