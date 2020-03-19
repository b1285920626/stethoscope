package com.pang.stethoscope.enums;

/**
 * @author BaiPang
 * @date 2020/3/9 23:58
 */
public enum Limits implements BaseValueEnum{
    /**
     * 普通用户权限
     */
    LIMITS_USER(100,"用户"),
    /**
     * 管理员权限
     */
    LIMITS_ADMIN(200,"管理员");

    int value;
    String description;

    Limits(int value, String description){
        this.value = value;
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Limits{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }

}
