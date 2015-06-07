package com.phone.cn.service.sys;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phone.cn.entity.sys.SysSequence;
import com.phone.cn.mapper.sys.SysSequenceMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:02:08
 *   
 */
@Service
public class SysSequenceService extends BaseService<SysSequence, String>{

	@Autowired
	private SysSequenceMapper sequenceMapper;
	
	/**
	 *  针对并发,获取不同值 SeqValue, UpdateTime 相同
	 * @param code
	 * @param reset
	 * @return
	 */
	@Transactional
	public SysSequence nextSequence(String code, boolean reset) {

		SysSequence seq = sequenceMapper.selectByPrimaryKey(code);
		if (seq != null) {
			Date now = new Date();
			Long ret = Long.parseLong(seq.getSeqValue());
			ret++;
			if (reset) {
				if (seq.getUpdateTime() == null)
					ret = 1l;
				else {
					Calendar c = Calendar.getInstance();
					c.setTime(now);
					int today = c.get(Calendar.DATE);
					c.setTime(seq.getUpdateTime());
					if (c.get(Calendar.DATE) != today)
						ret = 1l;
				}
			}
			seq.setSeqValue(ret+"");
			seq.setUpdateTime(now);
			sequenceMapper.updateByPrimaryKeySelective(seq);
			return seq;
		}
		
		return null;
	}

}
