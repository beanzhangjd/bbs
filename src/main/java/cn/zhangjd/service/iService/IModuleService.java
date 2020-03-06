package cn.zhangjd.service.iService;

import java.util.List;

import cn.zhangjd.bean.Module;

/**
 * 模块的业务接口
 */
public interface IModuleService {
	/**
	 * 获取所有模块信息
	 * @return 模块信息集合
	 */
	List<Module> getAll();

	/**
	 * 获取单个模块信息
	 * @param id 模块id
	 * @return 模块信息
	 */
	Module getModule(Integer id);
	
}
