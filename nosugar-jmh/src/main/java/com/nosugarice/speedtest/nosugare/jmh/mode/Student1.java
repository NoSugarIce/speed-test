package com.nosugarice.speedtest.nosugare.jmh.mode;

import com.nosugarice.mybatis.annotation.ColumnOptions;
import com.nosugarice.speedtest.base.StatusEnum;
import com.nosugarice.speedtest.base.StatusEnumTypeHandler;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dingjingyang@foxmail.com
 * @date 2021-6-8
 */
@Table(name = "student1")
public class Student1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    private String id;

    /** 姓名 */
    @Column(name = "name", nullable = false)
    private String name;

    /** 年龄 */
    @Column(name = "age", nullable = false)
    private Integer age;

    /** 性别,0:男,1:女 */
    @Column(name = "sex", nullable = false)
    private Integer sex;

    /** 学号 */
    @Column(name = "sno", nullable = false)
    private String sno;

    /** 电话号码 */
    @Column(name = "phone")
    private String phone;

    /** 住址 */
    @Column(name = "address")
    private String address;

    /** 学生卡余额 */
    @Column(name = "card_balance")
    private BigDecimal cardBalance;

    /** 在学状态,0:在学,1退学 */
    @ColumnOptions(typeHandler = StatusEnumTypeHandler.class)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

    /** 版本 */
    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    /** 创建时间 */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** 更新时间 */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    ///** 删除时间 */
    //@LogicDelete(deleteValue = "NOW")
    //@Column(name = "disabled_at")
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