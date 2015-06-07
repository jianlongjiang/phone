package com.phone.cn.service.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phone.cn.entity.member.UserLevel;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午7:56:28
 *   
 */
@Service
public class UserLevelService  extends BaseService<UserLevel, Integer>{

	public String findLevelName(Integer experience){
		List<UserLevel> list = findAll();
		if(list!=null){
			Collections.sort(list, new LevelCompare());
			for (int i = 0; i < list.size(); i++) {
				if(experience >= list.get(i).getExperience().intValue()){
					return list.get(i).getLevelName();
				}
			}
		}
		return "";
	}
	
	public class LevelCompare implements Comparator<UserLevel>{

    	@Override
    	public int compare(UserLevel o1, UserLevel o2) {
    		UserLevel u1 = (UserLevel) o1;
    		UserLevel u2 = (UserLevel) o2;
    		return u2.getExperience() - u1.getExperience();
    	}
    	
    }
}
