package cn.zhangjd.service;

import java.util.List;

import cn.zhangjd.service.iService.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhangjd.bean.Module;
import cn.zhangjd.mapper.ModuleMapper;
@Service
public class ModuleService implements IModuleService {
	@Autowired
	private ModuleMapper moduleMapper;
	@Override
	public List<Module> getAll() {
		return moduleMapper.selectAll();
	}

	@Override
	public Module getModule(Integer id) {
		return moduleMapper.selectById(id);
	}

}
