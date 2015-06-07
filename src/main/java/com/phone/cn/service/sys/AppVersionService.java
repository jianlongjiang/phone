package com.phone.cn.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.sys.AppVersionBean;
import com.phone.cn.bean.sys.VelAppVersionDto;
import com.phone.cn.conf.enums.VersionStatusEnum;
import com.phone.cn.entity.sys.AppVersion;
import com.phone.cn.mapper.sys.AppVersionMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:00:02
 *   
 */
@Service
public class AppVersionService extends BaseService<AppVersion, Integer>{

	@Autowired
	AppVersionMapper versionMapper;
	
	public VelAppVersionDto checkVersion(String versionStr) {
		AppVersion appVersion = loadNewest();
		VelAppVersionDto dto = new VelAppVersionDto();
		if (appVersion == null || versionStr == null) {
			dto.setUpdateStatus(VersionStatusEnum.NO_UPDATE.getValue());
			return dto;
		}
		Double version = Double.parseDouble(versionStr);
		Double minVersion = Double.parseDouble(appVersion.getMinVersion());
		Double newVersion = Double.parseDouble(appVersion.getVersion());
		if (version == newVersion) {
			dto.setUpdateStatus(VersionStatusEnum.NO_UPDATE.getValue());
			return dto;
		}

		dto.setVersion(appVersion.getVersion());
		dto.setUpdateMsg(appVersion.getUpdateMsg());
		if (version < minVersion) {
			dto.setUpdateStatus(VersionStatusEnum.MUST_UPDATE.getValue());
		} else {
			dto.setUpdateStatus(appVersion.getUpdateStatus());
		}
		return dto;
	}
	
	public AppVersion loadNewest() {
		AppVersionBean bean = new AppVersionBean();
		bean.setSort("id.desc");
		bean.setPageSize(1);
		PageList<AppVersion> pageList = super.queryAllWithPage(bean,
				bean.toPageBounds());
		if (pageList != null && pageList.size() > 0) {
			return pageList.get(0);
		} else {
			return null;
		}
	}

}
