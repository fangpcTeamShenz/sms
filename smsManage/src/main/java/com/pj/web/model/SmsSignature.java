package com.pj.web.model;

import java.util.Date;

public class SmsSignature {
    private Long smsSignatureId;

    private Long userId;

    private Integer signatureType;

    private Integer productType;

    private String signContent;

    private Integer status;

    private String reason;

    private Date createTime;

    private String pictureUrl;

    private String memo;

    public Long getSmsSignatureId() {
        return smsSignatureId;
    }

    public void setSmsSignatureId(Long smsSignatureId) {
        this.smsSignatureId = smsSignatureId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(Integer signatureType) {
        this.signatureType = signatureType;
    }

    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent == null ? null : signContent.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}
}