package com.phone.cn.entity.sys;

import java.util.Date;

import com.phone.cn.entity.BaseEntity;

@SuppressWarnings("serial")
public class OrdRecord  extends BaseEntity<Integer>{
    private Integer recordId;

    private Integer userId;

    private String userName;

    private Integer tradeId;
    //
//   未付款 no_pay, 	pay_ok 成功,  pay_fail 失败
    private String tradeStatus;
    
    private String tradeType;

    private String remarkStatus;

    //  订单: order    , 退单: refund
    private String contentType;

    	//  什么订单 , 
    private String content;

    private String createIp;

    private Date createTime;

    // 支付金额
    private Double tradeCount;
//  订单的 交易编号
    private String outtradeno;
// 第三方交易 no
    private String tradeno;
    
    
    public  static  enum  TradeTypeEnum{
    		no_pay("no_pay","未付款"),
    		go_pay("go_pay","支付中"),
    		pay_ok("pay_ok","付款成功"),
    		pay_fail("pay_fail","付款失败");
    		private  String value;
    		private String msg;
			private TradeTypeEnum(String value, String msg) {
				this.value = value;
				this.msg = msg;
			}
			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}
			public String getMsg() {
				return msg;
			}
			public void setMsg(String msg) {
				this.msg = msg;
			}
    		
			
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getRemarkStatus() {
        return remarkStatus;
    }

    public void setRemarkStatus(String remarkStatus) {
        this.remarkStatus = remarkStatus == null ? null : remarkStatus.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(Double tradeCount) {
        this.tradeCount = tradeCount;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno == null ? null : outtradeno.trim();
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

	@Override
	public Integer getId() {
		return  this.recordId;
	}
}