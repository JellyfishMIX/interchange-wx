package com.jellyfishmix.interchange.wx.dto;

import com.jellyfishmix.interchange.wx.vo.CodeToSessionErrorResponse;
import com.jellyfishmix.interchange.wx.vo.CodeToSessionSuccessResponse;
import lombok.Data;

/**
 * @author JellyfishMIX
 * @date 2020/10/20 10:32
 */
@Data
public class WxMaCode2OpenidDTO {
    private Boolean success;
    private Integer stateCode;
    private String stateMsg;
    private CodeToSessionSuccessResponse codeToSessionSuccessResponse;
    private CodeToSessionErrorResponse codeToSessionErrorResponse;

    /**
     * codeToSession成功时构造方法
     * @param stateCode
     * @param stateMsg
     * @param codeToSessionSuccessResponse
     */
    public WxMaCode2OpenidDTO(Integer stateCode, String stateMsg, CodeToSessionSuccessResponse codeToSessionSuccessResponse) {
        this.success = true;
        this.stateCode = stateCode;
        this.stateMsg = stateMsg;
        this.codeToSessionSuccessResponse = codeToSessionSuccessResponse;
    }

    /**
     * codeToSession失败时构造方法
     * @param stateCode
     * @param stateMsg
     * @param codeToSessionErrorResponse
     */
    public WxMaCode2OpenidDTO(Integer stateCode, String stateMsg, CodeToSessionErrorResponse codeToSessionErrorResponse) {
        this.success = false;
        this.stateCode = stateCode;
        this.stateMsg = stateMsg;
        this.codeToSessionErrorResponse = codeToSessionErrorResponse;
    }
}

