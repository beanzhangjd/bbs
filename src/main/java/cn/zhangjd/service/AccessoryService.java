package cn.zhangjd.service;

import cn.zhangjd.bean.Accessory;
import cn.zhangjd.mapper.AccessoryMapper;
import cn.zhangjd.service.iService.IAccessoryService;
import cn.zhangjd.util.FilesUtil;
import cn.zhangjd.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AccessoryService implements IAccessoryService {
    @Autowired
    private AccessoryMapper accessoryMapper;
    @Override
    public Accessory addAccessory( MultipartFile multipartFile, String token) throws Exception {
        if(multipartFile==null||token==null){
            throw new RuntimeException("文件上传失败");
        }
        String s=FilesUtil.uploadFile(multipartFile);
        Accessory accessory = new Accessory();
        Integer id=Integer.parseInt(JwtUtil.checkToken("user", token)[0]);
        accessory.setState(0);
        accessory.setPath(s);
        accessory.setFilename(multipartFile.getOriginalFilename());
        accessory.setCreate_user(id);
        accessoryMapper.insert(accessory);
        return accessory;
    }

    @Override
    public void delFileById(Integer id) throws Exception {
        Accessory accessory= accessoryMapper.selectById(id);
        accessoryMapper.delById(id);
       if (!FilesUtil.DelFile(accessory.getPath())){
           throw new RuntimeException("文件删除失败");
       }
    }
}
