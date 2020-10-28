package com.jellyfishmix.interchange.wx.enums;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 10:57
 */
@FeignClient(name = "interchange-wx")
public interface CodeMessageEnum {
    Integer getCode();
}
