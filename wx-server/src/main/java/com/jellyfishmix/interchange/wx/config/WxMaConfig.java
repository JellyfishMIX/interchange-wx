package com.jellyfishmix.interchange.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 10:50
 */
@Component
@ConfigurationProperties(prefix = "wx.miniapp.config")
@Data
public class WxMaConfig {
    String appid;
    String secret;
}
