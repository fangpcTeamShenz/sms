package com.pj.service.mongo.model;

import org.bson.Document;

/**
 * MongoDB实体
 * @author Fangpc
 *
 */
public class MongoSmsNews {
	//主键
	private String _id;
	
	private Long orderSystemUniqueId;
	
	//签名id
	private Long smsSignatureId;
	
	//模板id
	private Long smsTemplateId;
	
	//产品id
	private Long productId;
	
	//内容
	private String smsContent;
	
	//扩展码号
	private String extNumber;

	//备注
	private String memo;
	
	//拆分短信msgID已，分割，主要用于cmpp拆分后推送回执(待确定字段)
	private String groupMsgId;
	
	//亢余字段,手机号码
	private String phone;
	
	//装换成MongoDB
	public Document getInsertOneDocument(){
		Document insertDoc = new Document();
		insertDoc.put("_id", _id);
		insertDoc.put("orderSystemUniqueId", orderSystemUniqueId);
		insertDoc.put("smsContent", smsContent);
		insertDoc.put("extNumber", extNumber);
		insertDoc.put("phone", phone);
		return insertDoc;
	}
	
	//装换成MongoDB
	public Document getUpdateOneDocument(){
		Document updateDoc = new Document();
		updateDoc.put("smsSignatureId", smsSignatureId);
		updateDoc.put("smsTemplateId", smsTemplateId);
		updateDoc.put("productId", productId);
		return updateDoc;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Long getSmsSignatureId() {
		return smsSignatureId;
	}

	public void setSmsSignatureId(Long smsSignatureId) {
		this.smsSignatureId = smsSignatureId;
	}

	public Long getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(Long smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getExtNumber() {
		return extNumber;
	}

	public void setExtNumber(String extNumber) {
		this.extNumber = extNumber;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGroupMsgId() {
		return groupMsgId;
	}

	public void setGroupMsgId(String groupMsgId) {
		this.groupMsgId = groupMsgId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderSystemUniqueId() {
		return orderSystemUniqueId;
	}

	public void setOrderSystemUniqueId(Long orderSystemUniqueId) {
		this.orderSystemUniqueId = orderSystemUniqueId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
