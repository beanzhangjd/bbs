package cn.zhangjd.service.iService;

import cn.zhangjd.bean.Accessory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAccessoryService {
    /**
     * 添加附件信息到数据库
     * @param multipartFile 附件
     * @param token 用户令牌
     * @return 附件集合
     */
    Accessory addAccessory(MultipartFile multipartFile, String token) throws Exception;

    /**
     * 取消上传附件
     * @param id 附件id
     */
    void delFileById(Integer id) throws Exception;
}
