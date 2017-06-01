package com.galaxy.im.business.project.service;

import java.util.List;
import java.util.Map;

import com.galaxy.im.bean.project.ProjectBean;
import com.galaxy.im.common.db.service.IBaseService;

public interface IProjectService extends IBaseService<ProjectBean>{
	Map<String,Object> getBaseProjectInfo(Long id);
	List<Map<String,Object>> getFinanceHistory(Map<String,Object> paramMap);

}
