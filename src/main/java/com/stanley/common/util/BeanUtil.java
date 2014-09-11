package com.stanley.common.util;

import org.springframework.beans.BeanUtils;

public class BeanUtil {
	public static void copyProperties(Object dest, Object orig) {
        try {
            if (orig == null || dest == null) return;
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
