package com.galaxy.im.business.flow.investmentPolicy.dao;

import java.util.List;
import java.util.Map;

import com.galaxy.im.bean.Test;
import com.galaxy.im.common.db.IBaseDao;

public interface IInvestmentPolicyDao extends IBaseDao<Test, Long>{

	List<Map<String, Object>> investmentpolicy(Map<String, Object> paramMap);

	List<Map<String, Object>> hasPassMeeting(Map<String, Object> paramMap);

	String projectResult(Map<String, Object> paramMap);

}
