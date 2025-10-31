package org.lilyana.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lilyana.dao.entity.UserDO;
import org.lilyana.dto.req.UserRegisterReqDTO;

public interface UserService extends IService<UserDO> {

    void register(UserRegisterReqDTO requestParam);
}
