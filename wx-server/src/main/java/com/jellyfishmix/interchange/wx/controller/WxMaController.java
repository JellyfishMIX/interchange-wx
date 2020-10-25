package com.jellyfishmix.interchange.wx.controller;

import com.jellyfishmix.interchange.common.util.ResultVOUtil;
import com.jellyfishmix.interchange.common.vo.ResultVO;
import com.jellyfishmix.interchange.wx.dto.WxMaCode2OpenidDTO;
import com.jellyfishmix.interchange.wx.service.WxMaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 11:17
 */
@RequestMapping("/wxma")
@RestController
public class WxMaController {
    @Autowired
    private WxMaService wxMaService;

    /**
     * 微信code-openid换取
     * @param code 用户登录凭证（有效期五分钟）。开发者需要在开发者服务器后台调用 [auth.code2Session](https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html)，使用 code 换取 openid 和 session_key 等信息。
     */
    @GetMapping("/code_to_session")
    public ResultVO codeToSession(@RequestParam("code") String code) {
        WxMaCode2OpenidDTO wxMaCodeToSessionDTO = wxMaService.code2Session(code);
        if (!wxMaCodeToSessionDTO.getSuccess()) {
            return ResultVOUtil.fail(wxMaCodeToSessionDTO.getStateCode(), wxMaCodeToSessionDTO.getStateMsg());
        }
        return ResultVOUtil.success(wxMaCodeToSessionDTO.getStateCode(), wxMaCodeToSessionDTO.getStateMsg(), wxMaCodeToSessionDTO.getCodeToSessionSuccessResponse());
    }
}
