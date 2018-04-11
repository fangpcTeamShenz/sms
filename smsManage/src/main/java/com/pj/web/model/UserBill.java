package com.pj.web.model;

import java.util.Date;

public class UserBill {
    private Long billId;

    private Long userId;

    private Integer feeType;

    private Integer afterAmount;

    private String memo;

    private Date createTime;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(Integer afterAmount) {
        this.afterAmount = afterAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}