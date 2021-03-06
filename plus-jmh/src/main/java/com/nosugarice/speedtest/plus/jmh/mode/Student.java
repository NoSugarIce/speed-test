package com.nosugarice.speedtest.plus.jmh.mode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.nosugarice.speedtest.base.StatusEnum;
import com.nosugarice.speedtest.base.StatusEnumTypeHandler;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dingjingyang@foxmail.com
 * @date 2021-6-8
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 性别,0:男,1:女 */
    private Integer sex;

    /** 学号 */
    private String sno;

    /** 电话号码 */
    private String phone;

    /** 住址 */
    private String address;

    /** 学生卡余额 */
    private BigDecimal cardBalance;

    /** 在学状态,0:在学,1退学 */
    @TableField(typeHandler = StatusEnumTypeHandler.class)
    private StatusEnum status;

    /** 版本 */
    @Version
    private Integer version;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    ///** 删除时间 */
    //@TableLogic
    //private LocalDateTime disabledAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //public LocalDateTime getDisabledAt() {
    //    return disabledAt;
    //}
    //
    //public void setDisabledAt(LocalDateTime disabledAt) {
    //    this.disabledAt = disabledAt;
    //}
}