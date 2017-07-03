package com.galaxy.im.business.flow.duediligence.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.im.bean.Test;
import com.galaxy.im.business.flow.duediligence.dao.IDuediligenceDao;
import com.galaxy.im.common.CUtils;
import com.galaxy.im.common.db.IBaseDao;
import com.galaxy.im.common.db.service.BaseServiceImpl;
import com.galaxy.im.common.exception.ServiceException;

@Service
public class DuediligenceServiceImpl extends BaseServiceImpl<Test> implements IDuediligenceService{
	private Logger log = LoggerFactory.getLogger(DuediligenceServiceImpl.class);
	
	@Autowired
	private IDuediligenceDao dao;
	
	@Override
	protected IBaseDao<Test, Long> getBaseDao() {
		return dao;
	}

	@Override
	public Map<String, Object> duediligenceOperateStatus(Map<String, Object> paramMap) {
		try{
			int count1 =0;
			int count2 =0;
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("pass", false);
			result.put("veto", true);
			List<Map<String,Object>> dataList = dao.duediligenceOperateStatus(paramMap);
			if(dataList!=null && dataList.size()>0){
				String dictCode;
				Integer pcount = 0;
				String projectType="";
				for(Map<String,Object> map : dataList){
					dictCode = CUtils.get().object2String(map.get("dictCode"), "");
					pcount = CUtils.get().object2Integer(map.get("pcount"), 0);
					projectType = CUtils.get().object2String(map.get("projectType"), "");
					if(projectType.equals("projectType:1")){
						//投资：是否上传了:业务尽调报告、人事尽调报告、法务尽调报告、财务尽调报告、尽调启动报告、尽调总结会报告
						if("fileWorktype:1".equals(dictCode)){
							if(pcount>0){
								count1++;
							}
						}else if("fileWorktype:2".equals(dictCode)){
							if(pcount>0){
								count1++;
							}
						}else if("fileWorktype:3".equals(dictCode)){
							if(pcount>0){
								count1++;
							}
						}else if("fileWorktype:4".equals(dictCode)){
							if(pcount>0){
								count1++;
							}
						}else if("fileWorktype:18".equals(dictCode)){
							if(pcount>0){
								count1++;
							}
						}else if("fileWorktype:19".equals(dictCode)){
							if(pcount>0){
								count1++;
							}
						}
					}else{
						//创建：
						if("fileWorktype:1".equals(dictCode)){
							if(pcount>0){
								count2++;
							}
						}else if("fileWorktype:2".equals(dictCode)){
							if(pcount>0){
								count2++;
							}
						}else if("fileWorktype:18".equals(dictCode)){
							if(pcount>0){
								count2++;
							}
						}else if("fileWorktype:19".equals(dictCode)){
							if(pcount>0){
								count2++;
							}
						}
					}
				}
				if(count1>=6 || count2>=4){
					result.put("pass", true);
				}
			}
			return result;
		}catch(Exception e){
			log.error(DuediligenceServiceImpl.class.getName() + ":duediligenceOperateStatus",e);
			throw new ServiceException(e);
		}
	}
	
	
}
