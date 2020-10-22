package com.jellyfishmix.interchange.wx.service;

import com.jellyfishmix.interchange.wx.dto.WxMaCode2OpenidDTO;

/**
 * 微信小程序service
 *
 * @author JellyfishMIX
 * @date 2020/10/20 10:19
 */
public interface WxMaService {
    /**
     * 微信code换取openid和session_key
     * @param code 前端提供的code
     * @return
     */
    WxMaCode2OpenidDTO code2Session(String code);
}
