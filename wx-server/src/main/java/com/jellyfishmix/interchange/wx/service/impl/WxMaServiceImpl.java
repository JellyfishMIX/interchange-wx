package com.jellyfishmix.interchange.wx.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jellyfishmix.interchange.wx.config.WxMaConfig;
import com.jellyfishmix.interchange.wx.dto.WxMaCode2OpenidDTO;
import com.jellyfishmix.interchange.wx.enums.WxMaAuthEnum;
import com.jellyfishmix.interchange.wx.service.WxMaService;
import com.jellyfishmix.interchange.wx.util.CodeMessageEnumUtil;
import com.jellyfishmix.interchange.wx.vo.CodeToSessionErrorResponse;
import com.jellyfishmix.interchange.wx.vo.CodeToSessionSuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 10:43
 */
@Service("wxMaAuthService")
@Slf4j
public class WxMaServiceImpl implements WxMaService {
    @Autowired
    private WxMaConfig wxMaConfig;

    /**
     * 微信code换取openid和session_key
     * @param code 前端传递的code
     * @return
     */
    @Override
    public WxMaCode2OpenidDTO code2Session(String code) {
        log.info("进入codeToSession方法");
        log.info("code = {}", code);

        // 拉取access_token和openid, method = GET
        String url = "https://api.weixin.qq.com/sns/jscode2session?"
                + "appid=" + wxMaConfig.getAppid()
                + "&secret=" + wxMaConfig.getSecret()
                + "&js_code=" + code
                + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        // access_token和openid在responseJSON中，responseJSON是一个JSON字符串
        String responseJSON = restTemplate.getForObject(url, String.class);
        log.info("response={}", responseJSON);

        WxMaCode2OpenidDTO wxMaCode2OpenidDTO = null;

        // responseJSON为失败的逻辑

        if (responseJSON == null) {
            CodeToSessionErrorResponse codeToSessionErrorResponse = new CodeToSessionErrorResponse(WxMaAuthEnum.NULL);
            wxMaCode2OpenidDTO = new WxMaCode2OpenidDTO(codeToSessionErrorResponse.getErrcode(), codeToSessionErrorResponse.getErrmsg(), codeToSessionErrorResponse);
            return wxMaCode2OpenidDTO;
        }

        String errorCode = "errcode";
        if (responseJSON.contains(errorCode)) {
            CodeToSessionErrorResponse codeToSessionErrorResponse = null;
            JSONObject jsonObject = new JSONObject(responseJSON);
            Integer errcode = jsonObject.getInt("errcode");
            WxMaAuthEnum wxMaAuthEnum = CodeMessageEnumUtil.code2Enum(errcode, WxMaAuthEnum.class);
            if (wxMaAuthEnum == null) {
                codeToSessionErrorResponse = new CodeToSessionErrorResponse(WxMaAuthEnum.UNKNOWN);
            } else {
                codeToSessionErrorResponse = new CodeToSessionErrorResponse(wxMaAuthEnum);
            }
            wxMaCode2OpenidDTO = new WxMaCode2OpenidDTO(codeToSessionErrorResponse.getErrcode(), codeToSessionErrorResponse.getErrmsg(), codeToSessionErrorResponse);
            return wxMaCode2OpenidDTO;
        }

        // responseJSON为成功的逻辑

        CodeToSessionSuccessResponse codeToSessionSuccessResponse = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            codeToSessionSuccessResponse = objectMapper.readValue(responseJSON, CodeToSessionSuccessResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        wxMaCode2OpenidDTO = new WxMaCode2OpenidDTO(WxMaAuthEnum.REQUEST_SUCCESS.getCode(), WxMaAuthEnum.REQUEST_SUCCESS.getMessage(), codeToSessionSuccessResponse);

        return wxMaCode2OpenidDTO;
    }
}
