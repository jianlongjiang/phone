package com.phone.cn.service.sys;

import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.OrdRecordBean;
import com.phone.cn.entity.sys.OrdRecord;
import com.phone.cn.service.BaseService;

@Service
public class OrdRecordService extends BaseService<OrdRecord, Integer> {

	
	public OrdRecord findByOrderCode(String out_trade_no) {
		OrdRecordBean ordRecord = new OrdRecordBean();
		ordRecord.setOuttradeno(out_trade_no);
		return   selectOneByExample(ordRecord);
	}
	

}
