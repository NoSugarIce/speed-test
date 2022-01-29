package com.nosugarice.speedtest.base;

/**
 * @author dingjingyang@foxmail.com
 * @date 2021/4/28
 */
public enum StatusEnum {
    ON(0, "开启"),
    OFF(1, "开启"),
    ;

    private final Integer status;
    private final String des;

    StatusEnum(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public static StatusEnum valueOfStatus(Integer status) {
        for (StatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDes() {
        return des;
    }
}
