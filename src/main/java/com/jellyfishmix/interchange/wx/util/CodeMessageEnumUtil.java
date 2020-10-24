package com.jellyfishmix.interchange.wx.util;

import com.jellyfishmix.interchange.wx.enums.CodeMessageEnum;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 10:57
 */
public class CodeMessageEnumUtil {
    public static <T extends CodeMessageEnum> T code2Enum(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }
}
