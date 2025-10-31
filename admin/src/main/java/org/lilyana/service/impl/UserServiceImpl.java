package org.lilyana.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.lilyana.common.convention.exception.ClientException;
import org.lilyana.config.RedissonBloomFilterConfiguration;
import org.lilyana.dao.entity.UserDO;
import org.lilyana.dao.mapper.UserMapper;
import org.lilyana.dto.req.UserRegisterReqDTO;
import org.lilyana.service.UserService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static org.lilyana.common.constant.RedisConstant.LOCK_USER_REGISTER_KEY;
import static org.lilyana.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static org.lilyana.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterBloomFilter;
    private final RedissonClient redissonClient;


    public void register(UserRegisterReqDTO requestParam) {
        if (hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        if (!lock.tryLock()) {
            throw new ClientException(USER_NAME_EXIST);
        }
        int insert = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
        if (insert != 1) {
            throw new ClientException(USER_SAVE_ERROR);
        }
        userRegisterBloomFilter.add(requestParam.getUsername());
    }

    public boolean hasUsername(String username) {
        return userRegisterBloomFilter.contains(username);
    }
}
