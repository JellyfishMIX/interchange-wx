package com.jellyfishmix.interchange.wx.vo;

import com.jellyfishmix.interchange.wx.enums.WxMaAuthEnum;
import lombok.Data;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 10:35
 */
@Data
public class CodeToSessionErrorResponse {
    private Integer errcode;
    private String errmsg;

    public CodeToSessionErrorResponse(WxMaAuthEnum wxMaAuthEnum) {
        this.errcode = wxMaAuthEnum.getCode();
        this.errmsg = wxMaAuthEnum.getMessage();
    }
}
