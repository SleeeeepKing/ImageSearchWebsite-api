package com.cytech.imagesearchwebsiteapi.common.domain.response;

/**
 * @author yang
 * 状态码
 */
public enum ResultCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(2000, "成功"),
    BAD_REQUEST(4000, "参数或者语法不对"),
    TASK_PROCESSING(2001, "任务正在异步处理中"),
    NOT_FOUND(4004, "资源未找到"),
    SERVER_ERROR(5000, "服务器内部错误")
    ;
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
