package com.ruoyi.system.common;

/**
 * ClassName: ResultCodeEnum
 * Package: com.ruoyi.system.common
 * Description:
 *
 * @Author iuuani
 * @Create 2024/3/25 12:35
 * @Version 1.0
 */
public enum ResultCodeEnum {

    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(503, "passwordError"),
    NOTLOGIN(504, "notLogin"),
    USERNAME_USED(505, "userNameUsed");

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
